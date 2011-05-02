package com.jordanneil23.SpawnMob;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public enum Mob {
	
	/**
	 * SpawnMob - Mobs
	 * @author jordanneil23
	 */
	PIG(Enemies.FRIENDLY, CreatureType.PIG),
    CHICKEN(Enemies.FRIENDLY, CreatureType.CHICKEN),
    COW(Enemies.FRIENDLY, CreatureType.COW),
    SHEEP(Enemies.FRIENDLY, CreatureType.SHEEP),
    SQUID(Enemies.FRIENDLY, CreatureType.SQUID),
    CREEPER(Enemies.ENEMY, CreatureType.CREEPER),
    GHAST(Enemies.ENEMY, CreatureType.GHAST, "NetherSquid"),
    PIG_ZOMBIE(Enemies.NEUTRAL, CreatureType.PIG_ZOMBIE, "ZombiePigman"),
    SKELETON(Enemies.ENEMY, CreatureType.SKELETON),
    SPIDER(Enemies.ENEMY, CreatureType.SPIDER),
    ZOMBIE(Enemies.ENEMY, CreatureType.ZOMBIE),
    SLIME(Enemies.ENEMY, CreatureType.SLIME),
    MONSTER(Enemies.ENEMY, CreatureType.MONSTER, "Human"),
    GIANT_ZOMBIE(Enemies.ENEMY, CreatureType.GIANT, "Giant"),
    WOLF(Enemies.NEUTRAL, CreatureType.WOLF, "Wolf");
    
    private Mob(Enemies cat, CreatureType t) {
        this.category = cat;
        this.alt = null;
        this.type = t;
    }
    private Mob(Enemies cat, CreatureType t, String altName) {
        this.category = cat;
        this.alt = altName;
        this.type = t;
    }
    
    public Enemies category;
    public String s = "s";
    private String alt;
    private CreatureType type;
	
    private static HashMap<String, Mob> hashMap = new HashMap<String, Mob>();
    
    static{
        for(Mob mob : Mob.values()){
            hashMap.put(mob.toString().toLowerCase(), mob);
            if(mob.alt != null) hashMap.put(mob.alt.toLowerCase(), mob);
        }
    }
	
	public LivingEntity spawn(Player byWhom, SpawnMob plugin, Location loc) throws MobException {
		try {
            World world = byWhom.getWorld();
            LivingEntity mob = world.spawnCreature(loc, this.type);
            return mob;
        } catch(Exception e) {
        	plugin.log.log(java.util.logging.Level.SEVERE,"Unable to spawn mob. Error: ");
            throw new MobException();
        }
    }
	public enum Enemies{
        FRIENDLY("friendly"),
        NEUTRAL("neutral"),
        ENEMY("enemy");
        
        private Enemies(String t){
            this.type = t;
        }
        
        protected String type;
    }
	
	public class MobException extends Exception{
		private static final long serialVersionUID = 1L;
	}
	
	public static Mob fromName(String n){
		try {
            int i = Integer.valueOf(n);
            for(Mob m : hashMap.values()) {
                if(i == m.ordinal()) return m;
            }
            return null;
        } catch(NumberFormatException x) {
            return hashMap.get(n.toLowerCase());
        }
	}
	String getName() {
        StringBuilder s = new StringBuilder(this.toString());
        s.setCharAt(0, Character.toUpperCase(s.charAt(0)));
        int u = s.indexOf("_");
        if(u >= 0)
            s.setCharAt(u+1, Character.toUpperCase(s.charAt(u+1)));
        return s.toString().replace("_","");
    }
}
