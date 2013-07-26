package com.jordanneil23.SpawnMob.M;

import java.util.HashMap;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import com.jordanneil23.SpawnMob.Main;

public enum SheepColors {
	
	/**
	 * SpawnMob - Villager Professions
	 * @author jordanneil23
	 */
	BLACK(DyeColor.BLACK),
	BLUE(DyeColor.BLUE),
	BROWN(DyeColor.BROWN),
	CYAN(DyeColor.CYAN),
	GRAY(DyeColor.GRAY),
	GREEN(DyeColor.GREEN),
	LIGHTBLUE(DyeColor.LIGHT_BLUE),
	LIME(DyeColor.LIME),
	MAGENTA(DyeColor.MAGENTA),
	ORANGE(DyeColor.ORANGE),
	PINK(DyeColor.PINK),
	PURPLE(DyeColor.PURPLE),
	RED(DyeColor.RED),
	SILVER(DyeColor.SILVER),
	WHITE(DyeColor.WHITE),
	YELLOW(DyeColor.YELLOW);
		
    private SheepColors(DyeColor p) {
        this.alt = null;
        this.type = p;
    }
    private SheepColors(DyeColor p, String altName) {
        this.alt = altName;
        this.type = p;
    }
    
    public String s = "s";
    private String alt;
    private DyeColor type;
	
    private static HashMap<String, SheepColors> hashMap = new HashMap<String, SheepColors>();
    
    static{
        for(SheepColors prof : SheepColors.values()){
            hashMap.put(prof.toString().toLowerCase(), prof);
            if(prof.alt != null) hashMap.put(prof.alt.toLowerCase(), prof);
        }
    }
    
    public LivingEntity spawn(Player byWhom, Location loc) throws Exception {
		try {
            World world = byWhom.getWorld();
            LivingEntity s = (LivingEntity) world.spawnEntity(loc, EntityType.SHEEP);
            ((Sheep) s).setColor(this.type);
            return s;
        } catch(Exception e) {
        	Main.log.log(java.util.logging.Level.SEVERE,"Unable to spawn mob.");
        	return null;
        }
    }
	
	public static SheepColors fromName(String n){
		try {
            int i = Integer.valueOf(n.toUpperCase());
            for(SheepColors prof : hashMap.values()) {
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
