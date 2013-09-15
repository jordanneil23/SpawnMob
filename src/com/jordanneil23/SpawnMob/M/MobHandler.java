package com.jordanneil23.SpawnMob.M;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.DyeColor;

import com.jordanneil23.SpawnMob.Main;
import com.jordanneil23.SpawnMob.M.Mob;
import com.jordanneil23.SpawnMob.M.Mob.MobException;

public class MobHandler {
	static int[] ignore = {8, 9};
	private static int sz = 1;
	
	public static LivingEntity spawn(Mob mob, Player p, Location l){
		LivingEntity m = null;
		try {
			m = mob.spawn(p, l);
		} catch (MobException e1) {
			p.sendMessage("Unable to spawn mob.");
		}
		return m;
	}
	
	public static LivingEntity spawnCreeper(Player p, Location loc, boolean electric){
		LivingEntity c = spawn(Mob.CREEPER, p, loc);
		if (electric == true){
			 ((Creeper) c).setPowered(true);
			 return c;
		} 
		return c;
	}

public static LivingEntity spawnWolf(Mob mob, Player p, Location l, boolean t, boolean m){
	if (mob.equals(Mob.WOLF) == true){
		Wolf w = null;
		try {
			w = (Wolf) mob.spawn(p, l);
			if(t){w.setOwner(p);}
			else
			if(m){w.setAngry(true); w.setTarget(p);}
		} catch (MobException e1) {
			p.sendMessage("Could not spawn Wolf.");
		}
		return w;
		
	}
	return null;
}

public static LivingEntity spawnSheep(Mob mob, Player p, Location l, String args, boolean color){
	if (mob.equals(Mob.SHEEP)){
		Sheep s = null;
		try{
			
			s = (Sheep) mob.spawn(p, l);
			if(color){
				boolean iscolor = isArrMatch(sheepcolors, args);
				if (iscolor == true){
					s.setColor(DyeColor.valueOf(args.toUpperCase()));
				}
			}
		} catch (MobException e1){
			p.sendMessage("Could not spawn Sheep.");
		}
		return s;
	}
	return null;
}

public static LivingEntity spawnSlime(Mob mob, Player p, Location l, String args){
if (mob.equals(Mob.MAGMA_CUBE)){
		MagmaCube m1;
		try {
			m1 = (MagmaCube) mob.spawn(p, l);
			setSize(p, l, m1, args);
		} catch (MobException e1) {
			p.sendMessage("Could not spawn MagmaCube.");
		}
	}else if(mob.equals(Mob.SLIME)){
		Slime s1;
		try {
			s1 = (Slime) mob.spawn(p, l);
			setSize(p, l, s1, args);
		} catch (MobException e1) {
			p.sendMessage("Could not spawn Slime.");
		}
	}
	return null;
}

public static LivingEntity spawnCat(Mob mob, Player p, Location l, boolean t){
	if(mob.equals(Mob.OCELOT)){
		LivingEntity o;
		try {
			o = mob.spawn(p, l);
			if(t){((Tameable) o).setOwner(p);}
		} catch (MobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	try {
		mob.spawn(p, l);
	} catch (MobException e1) {
		p.sendMessage("Unable to spawn mob.");
	}
	return null;
}

	public static LivingEntity spawnbaby(Mob mob, Player p, Location l){
		LivingEntity m = null;
		try {
			m = mob.spawn(p, l);
			((Ageable) m).setBaby();
		} catch (MobException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	public static void setSize(Player p, Location loc, LivingEntity spawned, String args){
		if (convertStringtoInt(args) != 0)
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
		else { sz = convertStringtoInt(args); }
		if(sz == 0) return; // No data was specified; leave at default
		if(spawned instanceof Slime){
		Slime slime = (Slime) spawned;
		slime.setSize(sz);
		}else if (spawned instanceof MagmaCube){
			MagmaCube mc = (MagmaCube) spawned;
			mc.setSize(sz);
		}
		return;
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
	
	public static String sheepcolors[] = 
		{ 
				"BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN", 
				"LIGHT_BLUE", "LIME", "MAGENTA", "ORANGE", "PINK", 
				"PURPLE", "RED", "SILVER", "WHITE", "YELLOW"
		};
	
	public static boolean isArrMatch(String[] array, String name)
	{
		for (String mobName : array){
			if (mobName.equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public static int convertStringtoInt(String args)  throws NumberFormatException {
		   try{
		    int aInt = Integer.parseInt(args);
			return aInt;
		   }catch (Exception e) {
	           return 1;
	       }
		  }
	
	public static boolean isInt(String args)  throws NumberFormatException {
		   try{
		    Integer.parseInt(args);
			return true;
		   }catch (Exception e) {
	           return false;
	       }
		  }
	
    public static boolean spawnCheck(Player p, int count)
	{
		if (count == 0)
		{
			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was invalid.");
			return false;
		}
		else if (count > Main.spawnlimit)
		{
			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was over the set limit!");
			return false;
		}
		return true;
	}
	
}