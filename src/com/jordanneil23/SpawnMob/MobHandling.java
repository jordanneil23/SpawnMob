package com.jordanneil23.SpawnMob;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import com.jordanneil23.SpawnMob.Mob.MobException;

public class MobHandling {
	private static SpawnMob plugin;
	public static int sz = 1;
	static DyeColor dye = DyeColor.WHITE;
	
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
	/* Spawning  */
	static LivingEntity spawnIt(Mob mob, Player p, Location loc){
    	LivingEntity spawned = null;
    	try {
            spawned = mob.spawn(p, plugin, loc);
        } catch (MobException e) {
            p.sendMessage(ChatColor.RED + "Unable to spawn mob.");
            return spawned;
        }
        return spawned;
    }
	/* Rider Spawning  */
    static LivingEntity spawnRider(Mob mob, Player p, Location loc){
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
	/* Sheep Handling  */
	public static void setforSheep(LivingEntity m, String args)
	{
		if (isColor(args) == true)
		{
				dye = DyeColor.valueOf(args.toUpperCase());
		} else {}
		Sheep sheep = (Sheep) m;
		sheep.setColor(dye);
		return;
	}
	/* Check for colors  */
	 private static boolean isColor(String name)
		{
			for (String colors : sheepcolors)
				if (colors.equalsIgnoreCase(name))
					return true;
			
			return false;
		}
	
}
