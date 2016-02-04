package com.jordanneil23.SpawnMob.M;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

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
	    BAT(Enemies.NEUTRAL, EntityType.BAT),
		CHICKEN(Enemies.FRIENDLY, EntityType.CHICKEN),
		COW(Enemies.FRIENDLY, EntityType.COW),
		HORSE(Enemies.FRIENDLY, EntityType.HORSE),
		IRON_GOLEM(Enemies.NEUTRAL, EntityType.IRON_GOLEM, "IronGolem"),
		MUSHROOM_COW(Enemies.FRIENDLY, EntityType.MUSHROOM_COW, "Mooshroom"),
		OCELOT(Enemies.FRIENDLY, EntityType.OCELOT),
		PIG(Enemies.FRIENDLY, EntityType.PIG),
		RABBIT(Enemies.FRIENDLY, EntityType.RABBIT),
		SHEEP(Enemies.FRIENDLY, EntityType.SHEEP),
		SNOWMAN(Enemies.FRIENDLY, EntityType.SNOWMAN, "SnowGolem"),
		SQUID(Enemies.FRIENDLY, EntityType.SQUID),
		VILLAGER(Enemies.FRIENDLY, EntityType.VILLAGER),
		WOLF(Enemies.NEUTRAL, EntityType.WOLF),

	    //Unfriendly mobs
		BLAZE(Enemies.ENEMY, EntityType.BLAZE),
		CAVE_SPIDER(Enemies.ENEMY, EntityType.CAVE_SPIDER, "CaveSpider"),
		CREEPER(Enemies.ENEMY, EntityType.CREEPER),
		ELDER_GUARDIAN(Enemies.ENEMY, EntityType.GUARDIAN, "ElderGuardian"),
		ENDERMAN(Enemies.NEUTRAL, EntityType.ENDERMAN),
		ENDER_DRAGON(Enemies.ENEMY, EntityType.ENDER_DRAGON, "EnderDragon"),
		GUARDIAN(Enemies.ENEMY, EntityType.GUARDIAN, "Guardian"),
		GHAST(Enemies.ENEMY, EntityType.GHAST, "NetherSquid"),
		GIANT(Enemies.ENEMY, EntityType.GIANT, "Giant"),
		MAGMA_CUBE(Enemies.ENEMY, EntityType.MAGMA_CUBE, "MagmaCube"),
		PIG_ZOMBIE(Enemies.NEUTRAL, EntityType.PIG_ZOMBIE, "PigZombie"),
		SILVERFISH(Enemies.ENEMY, EntityType.SILVERFISH),
		SKELETON(Enemies.ENEMY, EntityType.SKELETON),
		SLIME(Enemies.ENEMY, EntityType.SLIME),
	    SPIDER(Enemies.ENEMY, EntityType.SPIDER),
		WITCH(Enemies.ENEMY, EntityType.WITCH),
	    WITHER(Enemies.ENEMY, EntityType.WITHER),
		WITHERSKELETON(Enemies.ENEMY, EntityType.SKELETON, "WitherSkeleton"),
		ENDERMITE(Enemies.ENEMY, EntityType.ENDERMITE, "Endermite"),
		ZOMBIEVILAGER(Enemies.ENEMY, EntityType.VILLAGER, "ZombieVillager"),
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
    private static HashMap<String, String> hashMap2 = new HashMap<String, String>();
    static{
        for(Mob mob : Mob.values()){
            hashMap.put(mob.toString().toLowerCase(), mob);
            if(mob.alt != null) hashMap.put(mob.alt.toLowerCase(), mob);
            
            if(mob.alt != null){ 
            	
            	hashMap2.put(mob.alt.toLowerCase(), "");
            
            }else{
            	hashMap2.put(mob.toString().toLowerCase(), "");
            }
        }
    }
	
	public LivingEntity spawn(Player byWhom, Location loc) throws MobException {
		try {
            World world = byWhom.getWorld();
            LivingEntity mob = (LivingEntity) world.spawnEntity(loc, this.type);
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
            int i = Integer.valueOf(n.toUpperCase());
            for(Mob m : hashMap.values()) {
                if(i == m.ordinal()) return m;
            }
            return null;
        } catch(NumberFormatException x) {
            return hashMap.get(n.toLowerCase());
        }
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object listmobs(){
		ArrayList kit2 = new ArrayList(Arrays.asList(hashMap2.keySet()));
        for (Iterator iter2 = kit2.iterator(); iter2.hasNext();){
        return iter2.next();
        }
		return null;			
	}
	public static String[] killableMobs(){
		String killableMobs[] = {
	    		"Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderMan", 
	    		"EnderDragon", "Ghast", "Giant", "Pig", "PigZombie", "Sheep",
	    		"SilverFish", "Skeleton", "Slime", "Spider", "Squid", "Villager", "Wolf",
	    		"Zombie", "Twolf", "All", "Monsters", "Animals", "Wolves", "Ender_Dragon",
	    		"Dragon", "Pig_Zombie", "Magma_Cube", "MagmaCube", "SnowMan", "SnowGolem", 
	    		"Ocelot", "Cat", "Tcat", "Tocelot", "IronGolem", "Bat","Witch","Wither",
	    		"Horse","Endermite","Guardian","Rabbit"};
		return killableMobs; 
		
		
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

