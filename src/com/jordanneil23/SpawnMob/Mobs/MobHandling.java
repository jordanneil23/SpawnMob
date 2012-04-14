package com.jordanneil23.SpawnMob.Mobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import com.jordanneil23.SpawnMob.CommandHandler;
import com.jordanneil23.SpawnMob.SpawnMob;
import com.jordanneil23.SpawnMob.Mobs.Mob.MobException;

public class MobHandling {
	private static SpawnMob plugin;
	public static int sz = 1; //Slime and MagmaCube Size
	static DyeColor dye = DyeColor.WHITE;
	
    public static String sheepcolors[] = 
	{ 
			"BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN", 
			"LIGHT_BLUE", "LIME", "MAGENTA", "ORANGE", "PINK", 
			"PURPLE", "RED", "SILVER", "WHITE", "YELLOW"
	};
	/* Spawning  */
	public static LivingEntity spawn(Mob mob, Player p, Location loc){
    	LivingEntity spawned = null;
    	try {
            spawned = mob.spawn(p, plugin, loc);
        } catch (MobException e) {
            p.sendMessage(ChatColor.RED + "Unable to spawn mob.");
            return spawned;
        }
        return spawned;
    }
    
	/* Creeper  */
	public static Creeper setforCreeper(Player p, Location loc, boolean electric){
		Creeper c = (Creeper) spawn(Mob.CREEPER, p, loc);
		if (electric == true){
			 c.setPowered(true);
			 return c;
		} 
		return c;
	}
	/* Wolf  */
	public static Wolf setforWolf(Player p, Location loc, boolean mad, boolean tamed){
		 Wolf w = (Wolf) spawn(Mob.WOLF, p, loc);
		 if(tamed == true){
			 w.setOwner(p);
			 return w;
		 }
		 if(mad == true){
			 w.setTarget(p);
			 w.setAngry(true);
			 return w;
		 }
		 return w;
	}
	public static Ocelot setforOcelot(Player p, Location loc, boolean tamed){
		 Ocelot o = (Ocelot) spawn(Mob.OCELOT, p, loc);
		 if(tamed == true){
			 o.setOwner(p);
			 return o;
		 }
		 return o;
	}
	/* Villager  */
	public static Villager setforVillager(Player p, Location loc){
		/**
		 * @Note You can't set their "jobs/colors" yet. I am trying to find a work-around for this..
		 */
		     World world = p.getWorld();
			 Villager v = (Villager) world.spawnCreature(loc, EntityType.VILLAGER);
			 return v;
	}
	/* Check if a string is a EntityType that has a "_" in it. */
	public static EntityType Check(String m)
	{
		if (m.equalsIgnoreCase("EnderDragon") || m.equalsIgnoreCase("Ender_Dragon") || m.equalsIgnoreCase("Dragon"))
		{
			return EntityType.ENDER_DRAGON;
		}else
			if (m.equalsIgnoreCase("Mushroom_Cow") || m.equalsIgnoreCase("MushroomCow") || m.equalsIgnoreCase("Mooshroom"))
			{
				return EntityType.MUSHROOM_COW;
			}else
				if (m.equalsIgnoreCase("Cave_Spider") || m.equalsIgnoreCase("CaveSpider") || m.equalsIgnoreCase("BlueSpider"))
				{
					return EntityType.CAVE_SPIDER;
				}else
					if (m.equalsIgnoreCase("Pig_Zombie") || m.equalsIgnoreCase("PigZombie") || m.equalsIgnoreCase("ZombiePig"))
					{
						return EntityType.PIG_ZOMBIE;
					}else
						if (m.equalsIgnoreCase("Magma_Cube") || m.equalsIgnoreCase("MagmaCube"))
						{
							return EntityType.MAGMA_CUBE;
						}
		return null;
	}
    /* Slime Handling  */
	public static void setforSlime(Player p, Location loc, LivingEntity spawned, String args){
		/**
		 * @Note: The sizes may not look right on spawn, this is not a bug within SpawnMob it is a bug within Minecraft
		 */
		if (CommandHandler.convertStringtoInt(args) != 0)
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
	/* MagmaCube Handling */
	public static void setforMagmaCube(Player p, Location loc, LivingEntity spawned, String args){
		if (CommandHandler.convertStringtoInt(args) != 0)
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
	public static LivingEntity setforSheep(Player p, Location loc, String args, boolean hasclr)
	{
		boolean iscolor = CommandHandler.isArrMatch(sheepcolors, args);
		World world = p.getWorld();
		LivingEntity m = world.spawnCreature(loc, EntityType.SHEEP);
		Sheep sheep = (Sheep) m;
		if (hasclr == true && iscolor == true)
		{
			dye = DyeColor.valueOf(args.toUpperCase());
		}
		sheep.setColor(dye);
		return sheep;
	}
	
	 public static Player getOnlinePlayer(String args){
		 Player p = null;
		 Player players[] = Bukkit.getServer().getOnlinePlayers();
         for(int i = 1; i < args.length(); i++)
         {
             for(int k = 0; k < players.length; k++)
             {
                 if(players[k].getName().equalsIgnoreCase(args.trim()))
                 {
                	 p = players[k];
                     return p;
                 }
             }
         }
		return p;
	 }
}
