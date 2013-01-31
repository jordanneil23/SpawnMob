package com.jordanneil23.SpawnMob.M;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import com.jordanneil23.SpawnMob.Main;

public enum Professions {
	
	/**
	 * SpawnMob - Villager Professions
	 * @author jordanneil23
	 */
	BLACKSMITH(Profession.BLACKSMITH),
    BUTCHER(Profession.BUTCHER),
	FARMER(Profession.FARMER),
	LIBRARIAN(Profession.LIBRARIAN),
	PRIEST(Profession.PRIEST);
		
    private Professions(Profession p) {
        this.alt = null;
        this.type = p;
    }
    private Professions(Profession p, String altName) {
        this.alt = altName;
        this.type = p;
    }
    
    public String s = "s";
    private String alt;
    private Profession type;
	
    private static HashMap<String, Professions> hashMap = new HashMap<String, Professions>();
    
    static{
        for(Professions prof : Professions.values()){
            hashMap.put(prof.toString().toLowerCase(), prof);
            if(prof.alt != null) hashMap.put(prof.alt.toLowerCase(), prof);
        }
    }
    
    public LivingEntity spawn(Player byWhom, Location loc) throws Exception {
		try {
            World world = byWhom.getWorld();
            LivingEntity v = (LivingEntity) world.spawnEntity(loc, EntityType.VILLAGER);
            ((Villager) v).setProfession(this.type);
            return v;
        } catch(Exception e) {
        	Main.log.log(java.util.logging.Level.SEVERE,"Unable to spawn mob.");
        	return null;
        }
    }
	
	public static Professions fromName(String n){
		try {
            int i = Integer.valueOf(n);
            for(Professions prof : hashMap.values()) {
                if(i == prof.ordinal()) return prof;
            }
            return null;
        } catch(NumberFormatException x) {
            return hashMap.get(n.toLowerCase());
        }
	}
	
	public String getName() {
        StringBuilder s = new StringBuilder(this.toString());
        s.setCharAt(0, Character.toUpperCase(s.charAt(0)));
        int u = s.indexOf("_");
        if(u >= 0)
            s.setCharAt(u+1, Character.toUpperCase(s.charAt(u+1)));
        return s.toString().replace("_","");
    }
}
