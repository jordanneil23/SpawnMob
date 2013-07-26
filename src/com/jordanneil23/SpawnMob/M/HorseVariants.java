package com.jordanneil23.SpawnMob.M;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import com.jordanneil23.SpawnMob.Main;

public enum HorseVariants {
	
	/**
	 * SpawnMob - Villager Professions
	 * @author jordanneil23
	 */
	DONKEY(Variant.DONKEY),
	HORSE(Variant.HORSE),
	MULE(Variant.MULE),
	SKELETON_Horse(Variant.SKELETON_HORSE, "Skeleton"),
	UNDEAD_Horse(Variant.UNDEAD_HORSE, "Undead");
		
    private HorseVariants(Variant p) {
        this.alt = null;
        this.type = p;
    }
    private HorseVariants(Variant p, String altName) {
        this.alt = altName;
        this.type = p;
    }
    
    public String s = "s";
    private String alt;
    private Variant type;
	
    private static HashMap<String, HorseVariants> hashMap = new HashMap<String, HorseVariants>();
    
    static{
        for(HorseVariants prof : HorseVariants.values()){
            hashMap.put(prof.toString().toLowerCase(), prof);
            if(prof.alt != null) hashMap.put(prof.alt.toLowerCase(), prof);
        }
    }
    
    public LivingEntity spawn(Player byWhom, Location loc) throws Exception {
		try {
            World world = byWhom.getWorld();
            LivingEntity h = (LivingEntity) world.spawnEntity(loc, EntityType.HORSE);
            ((Horse) h).setVariant(this.type);
            return h;
        } catch(Exception e) {
        	Main.log.log(java.util.logging.Level.SEVERE,"Unable to spawn mob.");
        	return null;
        }
    }
	
	public static HorseVariants fromName(String n){
		try {
            int i = Integer.valueOf(n.toUpperCase());
            for(HorseVariants prof : hashMap.values()) {
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
