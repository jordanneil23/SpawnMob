package com.jordanneil23.SpawnMob.M;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.jordanneil23.SpawnMob.Main;

public enum HorseColors {
	
	/**
	 * SpawnMob - Villager Professions
	 * @author jordanneil23
	 */
	BLACK(Color.BLACK),
	BROWN(Color.BROWN),
	CHESTNUT(Color.CHESTNUT),
	CREAMY(Color.CREAMY),
	DARK_BROWN(Color.DARK_BROWN),
	GRAY(Color.GRAY),
	WHITE(Color.WHITE);
		
    private HorseColors(Color p) {
        this.alt = null;
        this.type = p;
    }
    private HorseColors(Color p, String altName) {
        this.alt = altName;
        this.type = p;
    }
    
    public String s = "s";
    private String alt;
    private Color type;
	
    private static HashMap<String, HorseColors> hashMap = new HashMap<String, HorseColors>();
    
    static{
        for(HorseColors prof : HorseColors.values()){
            hashMap.put(prof.toString().toLowerCase(), prof);
            if(prof.alt != null) hashMap.put(prof.alt.toLowerCase(), prof);
        }
    }
    
    public LivingEntity spawn(Player byWhom, Location loc) throws Exception {
		try {
            World world = byWhom.getWorld();
            LivingEntity h = (LivingEntity) world.spawnEntity(loc, EntityType.HORSE);
            ((Horse) h).setColor(this.type);
            return h;
        } catch(Exception e) {
        	Main.log.log(java.util.logging.Level.SEVERE,"Unable to spawn mob.");
        	return null;
        }
    }
	
	public static HorseColors fromName(String n){
		try {
            int i = Integer.valueOf(n.toUpperCase());
            for(HorseColors prof : hashMap.values()) {
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
