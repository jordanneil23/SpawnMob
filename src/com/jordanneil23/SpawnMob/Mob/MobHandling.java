package com.jordanneil23.SpawnMob.Mob;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import com.jordanneil23.SpawnMob.CommandHandler;
import com.jordanneil23.SpawnMob.SpawnMob;
import com.jordanneil23.SpawnMob.Mob.Mob.MobException;

public class MobHandling {
	private static SpawnMob plugin;
	public static int sz = 1; //Slime Size
	static DyeColor dye = DyeColor.WHITE; //Default dye color
	
    public static String sheepcolors[] = 
	{ 
			"BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN", 
			"LIGHT_BLUE", "LIME", "MAGENTA", "ORANGE", "PINK", 
			"PURPLE", "RED", "SILVER", "WHITE", "YELLOW"
	};
    
	/* Creeper  */
	public static Creeper setforCreeper(Player p, Location loc, boolean electric){
	     World world = p.getWorld();
		if (electric == true){
			 Creeper c = (Creeper) world.spawnCreature(loc, CreatureType.CREEPER);
			 c.setPowered(true);
			 return c;
		} else {
			 Creeper c = (Creeper) world.spawnCreature(loc, CreatureType.CREEPER);
			 return c;
		}
	}
	/* Wolf  */
	public static Wolf setforWolf(Player p, Location loc){
		     World world = p.getWorld();
			 Wolf w = (Wolf) world.spawnCreature(loc, CreatureType.WOLF);
			 w.setOwner(p);
			 w.setSitting(false);
			 w.setAngry(false);
			 w.setHealth(10);
			 return w;
	}
	/* Villager  */
	public static Villager setforVillager(Player p, Location loc){
		/**
		 * @Note You can't set their "jobs/colors" yet.
		 */
		     World world = p.getWorld();
			 Villager v = (Villager) world.spawnCreature(loc, CreatureType.VILLAGER);
			 return v;
	}
	/* Spawning  */
	public static LivingEntity spawnIt(Mob mob, Player p, Location loc){
    	LivingEntity spawned = null;
    	try {
            spawned = mob.spawn(p, plugin, loc);
        } catch (MobException e) {
            p.sendMessage(ChatColor.RED + "Unable to spawn mob.");
            return spawned;
        }
        return spawned;
    }
	/* You should be able to tell what this does. */
	public static CreatureType Check(String m)
	{
		if (m.equalsIgnoreCase("EnderDragon") || m.equalsIgnoreCase("Ender_Dragon") || m.equalsIgnoreCase("Dragon"))
		{
			return CreatureType.ENDER_DRAGON;
		}else
			if (m.equalsIgnoreCase("Mushroom_Cow") || m.equalsIgnoreCase("MushroomCow") || m.equalsIgnoreCase("Mooshroom"))
			{
				return CreatureType.MUSHROOM_COW;
			}else
				if (m.equalsIgnoreCase("Cave_Spider") || m.equalsIgnoreCase("CaveSpider") || m.equalsIgnoreCase("BlueSpider"))
				{
					return CreatureType.CAVE_SPIDER;
				}else
					if (m.equalsIgnoreCase("Pig_Zombie") || m.equalsIgnoreCase("PigZombie") || m.equalsIgnoreCase("ZombiePig"))
					{
						return CreatureType.PIG_ZOMBIE;
					}
		return null;
	}
	/* Rider Spawning  */
    public static LivingEntity spawnRider(Mob mob, Player p, Location loc){
    	LivingEntity spawned1 = null;
        try {
            spawned1 = mob.spawn(p, plugin, loc);
        } catch (MobException e) {
            p.sendMessage(ChatColor.RED + "Unable to spawn mob.");
            return spawned1;
        }
		return spawned1;
    }
    /* Slime Handling  */
	public static void setforSlime(Player p, Location loc, LivingEntity spawned, String args){
		/**
		 * @Note: The sizes may not look right on spawn, this is not a bug within SpawnMob it is a bug within Minecraft
		 */
		if (CommandHandler.checkIfNumber(args) == false)
		{
			if (!(args.equalsIgnoreCase("Tiny") || args.equalsIgnoreCase("Small")|| args.equalsIgnoreCase("Medium")|| args.equalsIgnoreCase("Large")|| args.equalsIgnoreCase("Huge")|| args.equalsIgnoreCase("Colossal")))
			{
				return;
			}
			if (args.equalsIgnoreCase("Tiny")){
				sz = 1;
			}
			else if (args.equalsIgnoreCase("Small")){
				sz = 2;
			}
			else if (args.equalsIgnoreCase("Medium")){
				sz = 3;
			}
			else if (args.equalsIgnoreCase("Large")){
				sz = 4;
			}
			else if (args.equalsIgnoreCase("Huge")){
				sz = 8;
			}
			else if (args.equalsIgnoreCase("Colossal")){
				sz = 16;
			}
		} 
		else { sz = CommandHandler.convertStringtoInt(args); }
		if(sz == 0) return; // No data was specified; leave at default
		if(!(spawned instanceof Slime)) return;
		Slime slime = (Slime) spawned;
		slime.setSize(sz);
		return;
	}
	/* MagmaCubes */
	public static void setforMagmaCube(Player p, Location loc, LivingEntity spawned, String args){
		if (CommandHandler.checkIfNumber(args) == false)
		{
			if (!(args.equalsIgnoreCase("Tiny") || args.equalsIgnoreCase("Small")|| args.equalsIgnoreCase("Medium")|| args.equalsIgnoreCase("Large")|| args.equalsIgnoreCase("Huge")|| args.equalsIgnoreCase("Colossal")))
			{
				return;
			}
			if (args.equalsIgnoreCase("Tiny")){
				sz = 1;
			}
			else if (args.equalsIgnoreCase("Small")){
				sz = 2;
			}
			else if (args.equalsIgnoreCase("Medium")){
				sz = 3;
			}
			else if (args.equalsIgnoreCase("Large")){
				sz = 4;
			}
			else if (args.equalsIgnoreCase("Huge")){
				sz = 8;
			}
			else if (args.equalsIgnoreCase("Colossal")){
				sz = 16;
			}
		} 
		else { sz = CommandHandler.convertStringtoInt(args); }
		if(sz == 0) return; // No data was specified; leave at default
		if(!(spawned instanceof MagmaCube)) return;
		MagmaCube c = (MagmaCube) spawned;
		c.setSize(sz);
		return;
	}
	/* Sheep Handling  */
	public static void setforSheep(Player p, Location loc, String args, boolean hasclr)
	{
		World world = p.getWorld();
		LivingEntity m = world.spawnCreature(loc, CreatureType.SHEEP);
		if (hasclr == true){
			if (isColor(args) == true)
			{
					dye = DyeColor.valueOf(args.toUpperCase());
			} else {}
			Sheep sheep = (Sheep) m;
			sheep.setColor(dye);
			return;
		}
			return;
	}
	/* Check for colors  */
	 public static boolean isColor(String name)
		{
			for (String colors : sheepcolors)
				if (colors.equalsIgnoreCase(name))
					return true;
			
			return false;
		}
	
}
