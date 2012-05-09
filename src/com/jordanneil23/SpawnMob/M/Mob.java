package com.jordanneil23.SpawnMob.M;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.jordanneil23.SpawnMob.Main;

public enum Mob {
	
	/**
	 * SpawnMob - Mobs
	 * @author jordanneil23
	 */
	
	//Friendly mobs
		CHICKEN(Enemies.FRIENDLY, EntityType.CHICKEN),
		COW(Enemies.FRIENDLY, EntityType.COW),
		IRON_GOLEM(Enemies.NEUTRAL, EntityType.IRON_GOLEM, "IronGolem"),
		MUSHROOM_COW(Enemies.FRIENDLY, EntityType.MUSHROOM_COW, "Mooshroom"),
		OCELOT(Enemies.FRIENDLY, EntityType.OCELOT, "Cat"),
		PIG(Enemies.FRIENDLY, EntityType.PIG),
		SHEEP(Enemies.FRIENDLY, EntityType.SHEEP),
		SNOWMAN(Enemies.FRIENDLY, EntityType.SNOWMAN, "SnowGolem"),
		SQUID(Enemies.FRIENDLY, EntityType.SQUID),
		VILLAGER(Enemies.ENEMY, EntityType.VILLAGER, "NPC"),
		WOLF(Enemies.NEUTRAL, EntityType.WOLF, "Wolf"),
	    //Unfriendly mobs
		BLAZE(Enemies.ENEMY, EntityType.BLAZE),
		CAVE_SPIDER(Enemies.ENEMY, EntityType.CAVE_SPIDER, "CaveSpider"),
		CREEPER(Enemies.ENEMY, EntityType.CREEPER),
		ENDERMAN(Enemies.NEUTRAL, EntityType.ENDERMAN),
		ENDER_DRAGON(Enemies.ENEMY, EntityType.ENDER_DRAGON, "EnderDragon"),
		GHAST(Enemies.ENEMY, EntityType.GHAST, "NetherSquid"),
		GIANT(Enemies.ENEMY, EntityType.GIANT, "Giant"),
		MAGMA_CUBE(Enemies.ENEMY, EntityType.MAGMA_CUBE, "MagmaCube"),
		PIG_ZOMBIE(Enemies.NEUTRAL, EntityType.PIG_ZOMBIE, "PigZombie"),
		SILVERFISH(Enemies.ENEMY, EntityType.SILVERFISH),
		SKELETON(Enemies.ENEMY, EntityType.SKELETON),
		SLIME(Enemies.ENEMY, EntityType.SLIME),
	    SPIDER(Enemies.ENEMY, EntityType.SPIDER),
	    ZOMBIE(Enemies.ENEMY, EntityType.ZOMBIE); 
    
    private Mob(Enemies cat, EntityType t) {
        this.category = cat;
        this.alt = null;
        this.type = t;
    }
    private Mob(Enemies cat, EntityType t, String altName) {
        this.category = cat;
        this.alt = altName;
        this.type = t;
    }
    
    public Enemies category;
    public String s = "s";
    private String alt;
    private EntityType type;
	
    private static HashMap<String, Mob> hashMap = new HashMap<String, Mob>();
    
    static{
        for(Mob mob : Mob.values()){
            hashMap.put(mob.toString().toLowerCase(), mob);
            if(mob.alt != null) hashMap.put(mob.alt.toLowerCase(), mob);
        }
    }
	
	public LivingEntity spawn(Player byWhom, Location loc) throws MobException {
		try {
            World world = byWhom.getWorld();
            LivingEntity mob = world.spawnCreature(loc, this.type);
            return mob;
        } catch(Exception e) {
        	Main.log.log(java.util.logging.Level.SEVERE,"Unable to spawn mob. Error: ");
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
	
	public String getName() {
        StringBuilder s = new StringBuilder(this.toString());
        s.setCharAt(0, Character.toUpperCase(s.charAt(0)));
        int u = s.indexOf("_");
        if(u >= 0)
            s.setCharAt(u+1, Character.toUpperCase(s.charAt(u+1)));
        return s.toString().replace("_","");
    }
}

