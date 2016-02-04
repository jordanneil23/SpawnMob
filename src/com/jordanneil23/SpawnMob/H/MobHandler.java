package com.jordanneil23.SpawnMob.H;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import com.jordanneil23.SpawnMob.M.HorseColors;
import com.jordanneil23.SpawnMob.M.HorseStyles;
import com.jordanneil23.SpawnMob.M.HorseVariants;
import com.jordanneil23.SpawnMob.M.Mob;
import com.jordanneil23.SpawnMob.M.Mob.MobException;
import com.jordanneil23.SpawnMob.M.Professions;
import com.jordanneil23.SpawnMob.M.SheepColors;

public class MobHandler {
	static int[] ignore = {8, 9};
	private static int sz = 1;
	public static String sheepclrs[] = { "Black, Blue, Brown, Cyan, Gray, Green", "LigtBlue, Lime, Magenta, Orange, Pink", "Purple, Red, Silver, White, Yellow" };
	public static String horsevars[] = { "Donkey, Horse, Mule, Skeleton, Undead", };
	public static String horseclrs[] = { "Black, Brown, Chestnut, Creamy, DarkBrown, Gray, White" };
	public static String horsestyles[] = { "BlackDots, None, White, WhiteDots, Whitefield" };
	public static String sizes[] = { "Tiny, Small, Medium, Large, Huge, Colossal" };
	//BEGIN SPAWNING
	
	//SPAWN
	public static LivingEntity spawn(Mob mob, Player p, Location l){
		LivingEntity m = null;
		try {
			m = mob.spawn(p, l);
		} catch (MobException e1) {
			p.sendMessage("Unable to spawn mob.");
		}
		return m;
	}
	//SPAWN BABY
	public static LivingEntity spawnbaby(Mob mob, Player p, Location l){

		LivingEntity m = null;
		try {
			m = mob.spawn(p, l);
			((Ageable) m).setBaby();
		} catch (MobException e) {
			e.printStackTrace();
		}
		return m;
	}
	//SPAWN CAT
	public static LivingEntity spawnCat(Mob mob, Player p, Location loc, boolean tamed){
		LivingEntity o = spawn(Mob.OCELOT, p, loc);
		if (tamed == true){
			 ((Ocelot) o).setOwner(p);
			 return o;
		} 
		return o;
	}
	//SPAWN CREEPER
	public static LivingEntity spawnCreeper(Player p, Location loc, boolean electric){
		LivingEntity c = spawn(Mob.CREEPER, p, loc);
		if (electric == true){
			 ((Creeper) c).setPowered(true);
			 return c;
		} 
		return c;
	}
	//SPAWN HORSE
	public static LivingEntity spawnHorse(HorseColors clr, HorseVariants var, HorseStyles sty, boolean tamed, boolean color, boolean variant, boolean style, Player p, Location loc){
		
		if (style == true){
			try {
				LivingEntity h = sty.spawn(p, loc);
				if(color == true){
					((Horse) h).setColor(org.bukkit.entity.Horse.Color.valueOf(clr.getName()));
					if(tamed== true){
						((Horse) h).setTamed(true);
						((Horse) h).setOwner(p);
						return h;
					}
					return h;
				}else if(tamed == true){
					((Horse) h).setTamed(true);
					((Horse) h).setOwner(p);
					return h;
				}
				return h;
			} catch (Exception e) {
				return MobHandler.spawn(Mob.HORSE, p, loc);
			}
		}else
		if (variant == true){
			try {
				LivingEntity h = var.spawn(p, loc);
				if(tamed == true){
					((Horse) h).setTamed(true);
					((Horse) h).setOwner(p);;
					return h;
				}
				return h;
			} catch (Exception e) {
				return MobHandler.spawn(Mob.HORSE, p, loc);
			}
		}else
		if (color == true){
			try {
				LivingEntity h = clr.spawn(p, loc);
				 if(tamed == true){
					 ((Horse) h).setTamed(true);
					 ((Horse) h).setOwner(p);
					 return h;
				 }
				 return h;
			} catch (Exception e) {
				return MobHandler.spawn(Mob.HORSE, p, loc);
			}
		}else{
			return MobHandler.spawn(Mob.HORSE, p, loc);	
		}
	}
	//SPAWN SHEEP
	public static LivingEntity spawnSheep(SheepColors color1, Player p, Location l, boolean color){
		if (color == true){
			try {
				return color1.spawn(p, l);
			} catch (Exception e) {
				return MobHandler.spawn(Mob.SHEEP, p, l);
			}
		}else{
			return MobHandler.spawn(Mob.SHEEP, p, l);
		}	
	}
	//SPAWN SLIME
	public static LivingEntity spawnSlime(Mob mob, Player p, Location l, String args, boolean size){
		if (mob.equals(Mob.MAGMA_CUBE)){
				MagmaCube m1;
				try {
					m1 = (MagmaCube) mob.spawn(p, l);
					if(size==true){
						setSize(p, l, m1, args);
					}
				} catch (MobException e1) {
					p.sendMessage("Could not spawn MagmaCube.");
				}
			}else if(mob.equals(Mob.SLIME)){
				Slime s1;
				try {
					s1 = (Slime) mob.spawn(p, l);
					if(size==true){
					setSize(p, l, s1, args);
					}
				} catch (MobException e1) {
					p.sendMessage("Could not spawn Slime.");
				}
			}
			return null;
		}
	//SPAWN SHEEP
	public static LivingEntity spawnVillager(Professions pro, Player p, Location l, boolean prof){
			if (prof == true){
				try {
					return pro.spawn(p, l);
				} catch (Exception e) {
					return MobHandler.spawn(Mob.VILLAGER, p, l);
				}
			}else{
				return MobHandler.spawn(Mob.VILLAGER, p, l);
			}	
		}
	//SPAWN WOLF
    public static LivingEntity spawnWolf(Mob mob, Player p, Location loc, boolean tamed){
    	LivingEntity w = spawn(Mob.WOLF, p, loc);
		if (tamed == true){
			 ((Wolf) w).setOwner(p);
			 return w;
		} 
		return w;
    }
    //SPAWN WITHERSKELETON
    public static LivingEntity spawnWitherSkel(Player p, Location loc){
    	LivingEntity s = spawn(Mob.SKELETON, p, loc);
    	((Skeleton) s).setSkeletonType(SkeletonType.WITHER);
    	EntityEquipment e = s.getEquipment();
    	e.setItemInHand(new ItemStack(Material.STONE_SWORD));
		return s;
    }
    
    // END SPAWNING
    
    //SET SLIME/MAGMACUBE SLIME
	public static void setSize(Player p, Location loc, LivingEntity spawned, String args){
		if (isInt(args) == false)
		{
			if (!(args.equalsIgnoreCase("Tiny") || args.equalsIgnoreCase("Small")|| args.equalsIgnoreCase("Medium")|| args.equalsIgnoreCase("Large")|| args.equalsIgnoreCase("Huge")|| args.equalsIgnoreCase("Colossal")))
			{
				p.sendMessage(ChatColor.RED + "Not a valid size, spawning at deafault size.");
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
	
   
	
}