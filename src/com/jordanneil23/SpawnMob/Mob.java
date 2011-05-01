package com.jordanneil23.SpawnMob;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;

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
    public boolean isMonster(LivingEntity e){
        return (e instanceof Creeper) || (e instanceof Monster) || (e instanceof Skeleton) || (e instanceof Spider) || (e instanceof Zombie) || (e instanceof PigZombie) || (e instanceof Ghast) || (e instanceof Giant) || (e instanceof Slime);
    }

    public boolean isAnimal(LivingEntity e){
      return (e instanceof Chicken) || (e instanceof Cow) || (e instanceof Sheep) || (e instanceof Squid) || (e instanceof Pig);
    }
    //start of individual monsters
    public boolean isZombie (LivingEntity e){
    	return (e instanceof Zombie);
    }
    public boolean Monster (LivingEntity e){
    	return (e instanceof Monster);
    }
    public boolean isCreeper (LivingEntity e){
    	return (e instanceof Creeper);
    }
    public boolean isSkeleton (LivingEntity e){
    	return (e instanceof Skeleton);
    }
    public boolean isSpider (LivingEntity e){
    	return (e instanceof Spider);
    }
    public boolean isPigZombie (LivingEntity e){
    	return (e instanceof PigZombie);
    }
    public boolean isGiant (LivingEntity e){
    	return (e instanceof Giant);
    }
    public boolean isSlime (LivingEntity e){
    	return (e instanceof Slime);
    }
    public boolean isGhast (LivingEntity e){
    	return (e instanceof Ghast);
    }
    //end
    //
    //Start of individual animals
    public boolean isCow (LivingEntity e){
    	return (e instanceof Cow);
    }
    public boolean isPig (LivingEntity e){
    	return (e instanceof Pig);
    }
    public boolean isSheep (LivingEntity e){
    	return (e instanceof Sheep);
    }
    public boolean isChicken (LivingEntity e){
    	return (e instanceof Chicken);
    }
    public boolean isWolf (LivingEntity e){
    	return (e instanceof Wolf);
    }
    public boolean isSquid(LivingEntity e){
    	return (e instanceof Squid);
    }
    //end
    
	public void Kill(World world, String type){
        List<?> mobs = world.getLivingEntities();
        for(Iterator<?> iterator = mobs.iterator(); iterator.hasNext();)
        {
            LivingEntity m = (LivingEntity)iterator.next();
            if(isAnimal(m) && (type.equalsIgnoreCase("animals") || type.equals("all")))
            {
                m.setHealth(0);
            } else
            if(isMonster(m) && (type.equalsIgnoreCase("monsters") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isZombie(m) && (type.equalsIgnoreCase("zombie") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isCreeper(m) && (type.equalsIgnoreCase("creeper") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isSkeleton(m) && (type.equalsIgnoreCase("skeleton") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isSpider(m) && (type.equalsIgnoreCase("spider") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isGhast(m) && (type.equalsIgnoreCase("ghast") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isSlime(m) && (type.equalsIgnoreCase("slime") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isGiant(m) && (type.equalsIgnoreCase("giant") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isPigZombie(m) && (type.equalsIgnoreCase("pigzombie") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(Monster(m) && (type.equalsIgnoreCase("pigzombie") || type.equals("all")))
            {
                m.setHealth(0);
            }
            //start of animals
            if(isSheep(m) && (type.equalsIgnoreCase("sheep") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isChicken(m) && (type.equalsIgnoreCase("chicken") || type.equals("all")))
            {
                m.setHealth(0);
            }if(isSquid(m) && (type.equalsIgnoreCase("squid") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isCow(m) && (type.equalsIgnoreCase("cow") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isPig(m) && (type.equalsIgnoreCase("pig") || type.equals("all")))
            {
                m.setHealth(0);
            }
            if(isWolf(m) && (type.equalsIgnoreCase("wolf")))
            {
                m.setHealth(0);
            }
    }
	}
}
