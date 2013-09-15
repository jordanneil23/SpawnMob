package com.jordanneil23.SpawnMob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import com.jordanneil23.SpawnMob.K.Kit;
import com.jordanneil23.SpawnMob.M.Mob;
import com.jordanneil23.SpawnMob.M.MobHandler;
import com.jordanneil23.SpawnMob.M.Professions;
import com.jordanneil23.SpawnMob.M.KillMobs;
/**
 * Help ints: 
 *  General command help - 1
 *  Kill help - 2
 *  Kit help - 3
 *  mspawn help - 4
 *  mspawn delay help - 5
 */
public class Commands {
    private String mobList[] = {
    		"Bat, Blaze, CaveSpider, Chicken, Cow, Creeper, EnderMan, Horse, IronGolem", 
    		"EnderDragon, Ghast, Giant, MagmaCube, Ocelot (Cat) Pig, PigZombie, ",
    		"Sheep, SilverFish, Snowman, Skeleton, Slime, Spider, Squid, Villager, ",
    		"Witch, Wither, Wolf, Zombie," }; 
    private String mobz[] = {
    		"Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderMan", 
    		"EnderDragon", "Ghast", "Giant", "Pig", "PigZombie", "Sheep",
    		"SilverFish", "Skeleton", "Slime", "Spider", "Squid", "Villager", "Wolf",
    		"Zombie", "Twolf", "All", "Monsters", "Animals", "Wolves", "Ender_Dragon",
    		"Dragon", "Pig_Zombie", "Magma_Cube", "MagmaCube", "SnowMan", "SnowGolem", 
    		"Ocelot", "Cat", "Tcat", "Tocelot", "IronGolem", "Bat","Witch","Wither","Horse"}; 
    private String animals[] = {"Chicken", "Cow","Horse", "Ocelot" , "Pig", "Sheep", "Wolf", "Cat", "Villager"};
    private String customMobs[] = { "Wolf", "Creeper", "Magma_Cube", "MagmaCube", "NPC", "Ocelot", "Cat", "Sheep", "Slime", "Villager"};
	int count = 1;
	   public boolean perform(CommandSender sender, Command command, String[] args) {
	    	int[] ignore = {8, 9};
	        Player p = (Player) sender;
	        Player p1 = p;
	        Location loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
			loc.setY(1 + loc.getY());
	        Location loc2 = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
			loc2.setY(1 + loc2.getY());
	        Mob mob = Mob.COW;
	        
	        Player p2 = null;
	        boolean other = false;
	        Professions pro = Professions.FARMER;
	        boolean chk = false;
	        boolean endman = false;
	        boolean snowman = false;
      
	        if (command.getName().equalsIgnoreCase("SpawnMob")|| command.getName().equalsIgnoreCase("SMob") || command.getName().equalsIgnoreCase("SM"))
		    {
		        String[] split1 = args[0].split(":");
		        String[] split0 = new String[1];
		        
		        /** @Note Kill [From here stopping at the Baby spawning is original code from 2.3, need to re-code this soon.  **/
            	if (args[0].equalsIgnoreCase("Kill"))
        		{
            		if (args.length == 1)
          		    {
            			Help(p, command, 2);
          		        return false;
          		    }
            		boolean isKillableMob = MobHandler.isArrMatch(mobz, args[1]);
                  	String mt = null;
                	if (MobHandler.Check(args[0]) != null){
                         mt = MobHandler.Check(args[0]).toString();
                	}else{
                		mt = args[1];
                	}
                  	if (!(PermissionsHandler.playerhas(p, "spawnmob.kill", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.kill." + args[1].toLowerCase(), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
              			p.sendMessage(ChatColor.RED + "You can't use this command.");
              			return false;
              		} 

                  	if (isKillableMob == false)
                  	{
                  		p.sendMessage(ChatColor.RED + "Invalid mob, try again.");
                  		return false;
                  	}
                  	if (args[1].toUpperCase().toUpperCase() == "SILVERFISH" || args[1].toUpperCase().toUpperCase() == "SQUID")
    				{
    					chk = true;
    				}
    				if (args[1].toUpperCase() == "ENDERMAN" || (args[1].toUpperCase() == "SNOWMAN" || args[1].toUpperCase() == "SNOWGOLEM")){
    					endman = true;
    					chk = true;
    				}
    				if(args[1].toUpperCase() == "SNOWMAN" || args[1].toUpperCase() == "SNOWGOLEM"){
    					snowman = true;
    					chk = true;
    				}
        			if (args[1].equalsIgnoreCase("Wolf") || args[1].equalsIgnoreCase("Wolves")){
            			p.sendMessage(ChatColor.BLUE + "Killed all wolves, not including tamed ones. /spawnmob kill twolf kills tamed wolves.");
            			if (args.length >= 3){
            			KillMobs.Kill(p, mt, args[2]);
            			return true;
            			}
            			KillMobs.Kill(p, mt);
                        return true;
            		}
            		if (args[1].equalsIgnoreCase("TWolf")){
            			p.sendMessage(ChatColor.BLUE + "Killed all tamed wolves.");
            			if (args.length >= 3){
                			KillMobs.Kill(p, "twolf", args[2]);
                			return true;
                			}
                			KillMobs.Kill(p, "twolf");
                        return true;
            		}
            		
            		if (args[1].equalsIgnoreCase("All")){
            			p.sendMessage(ChatColor.BLUE + "Killed all mobs, not including tamed wolves.");
            			if (args.length >= 3){
                			KillMobs.Kill(p, "all", args[2]);
                			return true;
                			}
                			KillMobs.Kill(p, "all");
                    	return true;
                    }
            		
            		p.sendMessage(ChatColor.BLUE + "Killed all " + (endman ? "endermen" : snowman ? "snowmen" : args[1].toLowerCase()) + (chk ? "s." : " that were close to you."));
            		if (args.length >= 3){
            		KillMobs.Kill(p, mt, args[2]);
            		return true;
            		}
            		KillMobs.Kill(p, mt);
        			return true;
        		}
	            /** @Note Undo  **/
        		else if (args[0].equalsIgnoreCase("Undo"))
        		{
        			p.sendMessage(ChatColor.BLUE + "Killed all mobs, not including tamed wolves.");
        			if (args.length >= 2){
            			KillMobs.Kill(p, "all", args[2]);
            			return true;
            			}
            			KillMobs.Kill(p, "all");
        			return true;
        		}
	            /** @Note Kits  **/
                else if (args[0].equalsIgnoreCase("Kit")){
                	if (args.length <= 1)
          		    {
            			Help(p, command, 3);
          		        return false;
          		    }
                			if (!(PermissionsHandler.playerhas(p, "spawnmob.kits", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.kits.*", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true )){
                				p.sendMessage(ChatColor.RED + "You can't use this command.");
                            	return false;
                        	}
                	if(args[1].equalsIgnoreCase("List")){
                		Kit.listKits(p);
                		return true;
                	}
                	boolean success = false;
                	if (args.length > 2){
                	try {
                        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                        	if ((Kit.spawn(args[1], p, loc)) == true){
                        		success = true;
                        	}
                        }
                        if(success == true){
                        p.sendMessage(ChatColor.BLUE + "Spawned the Mob Kit: " + args[1]);
                        return true;
                        } else {
            		    	  p.sendMessage(ChatColor.RED + "Invalid mob type or mob kit.");
              		    	  return false;
                        }
                    } catch (java.lang.NumberFormatException e2) {
                        p.sendMessage(ChatColor.RED + "Malformed integer.");
                        return false;
                    }
                	}else{
                		if ((Kit.spawn(args[1], p, loc)) == true){
                    		success = true;
                    	}
                		if(success == true){
                			p.sendMessage(ChatColor.BLUE + "Spawned the Mob Kit: " + args[1]);
                            return true;
                		} else {
          		    	  p.sendMessage(ChatColor.RED + "Invalid mob type or mob kit.");
          		    	  return false;
                		}
                    }
                }
	           /** @Note List Mobs  **/
                else if (args[0].equalsIgnoreCase("List")){
                	if(args.length < 2)
                	{
                		String[] mobs = mobList;
                    	ArrayList<String> mobs2 = new ArrayList<String>(Arrays.asList(mobs));
        		        p.sendMessage(ChatColor.BLUE + "Mob list:");
        		        for (Iterator<String> iter2 = mobs2.iterator(); iter2.hasNext();)
        		        {
        		        p.sendMessage(ChatColor.BLUE + " " + iter2.next() + " ");
        		        }
        		        return true;
                	}
                	else if (args[1].equalsIgnoreCase("Babies") || args[1].equalsIgnoreCase("Baby"))
                	{
                		String[] babies = animals;
                		ArrayList<String> babiez = new ArrayList<String>(Arrays.asList(babies));
        		        p.sendMessage(ChatColor.BLUE + "Mob list:");
        		        for (Iterator<String> iter2 = babiez.iterator(); iter2.hasNext();)
        		        {
        		        p.sendMessage(ChatColor.BLUE + " " + iter2.next() + " ");
        		        }
        		        return true;
                	}
                }else
	        	if(args[0].equalsIgnoreCase("Baby")){
	        		
	        	}
	        	if (MobHandler.isArrMatch(customMobs, args[0])){
	        		
	        	}else{
	        		
	        	}
		    }
	        else if (command.getName().equalsIgnoreCase("SpawnMobLoc") || command.getName().equalsIgnoreCase("SML") || command.getName().equalsIgnoreCase("SMobL")){
	        	
	        }
	        else if (command.getName().equalsIgnoreCase("mspawn")){
        	
        }
		return false;
    }
    
    public void Help(Player p, Command command, int h){
    	p.sendMessage(ChatColor.BLUE + "- SpawnMob Help -");
    	p.sendMessage(ChatColor.BLUE + "<> - Mandatory");
    	p.sendMessage(ChatColor.BLUE + "() - Optional");
    	if(h == 1){
    	    p.sendMessage(ChatColor.BLUE + "Command shortcuts: /spawnmob, /smob, /sm");
    	    p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " <mob> (amount) - Spawn a mob");
    	    p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " list (babies) - List mobs/babies that can be spawned.");
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " (baby) <mob name> (amount) - Spawn mobs or baby animals! (You can only spawn baby ANIMALS not MONSTERS)");
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " (baby) <mob name> (amount) <player> - Spawn mobs or baby animals onto a players location! ");
            
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " mob;mob - Spawn a mob riding a mob.");
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " player;mob - Make a player ride a mob.");
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " mob;player - Make a mob ride a player.");
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " player;player - Make a player ride a player.");
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kill - Type for more info");
            p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kit - Type for more info");
            p.sendMessage(ChatColor.BLUE + "/mspawn - Type for more info");
            return;
    	}
    	if(h == 2){
		    p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kill <all-animals-monsters-mobname-twolf> (radius)  - Kill all mobs within a radius. Default is 50.");
  		    p.sendMessage(ChatColor.BLUE + "For a list of mobnames use /" + command.getName().toLowerCase() + " list");
    		return;
    	}
    	if(h == 3){
    		p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kit list - List the available mob kits");
    		p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kit <kitname> - Spawn a mob kit");
    		return;
    	}
    	if(h == 4){
    		p.sendMessage(ChatColor.BLUE + "/mspawn <mob name> - Set a mob spawner to spawn a mob.");
    		p.sendMessage(ChatColor.BLUE + "For a list of mobnames use /" + command.getName().toLowerCase() + " list");
            p.sendMessage(ChatColor.BLUE + "/mspawn check - See a spawner's info.");
            p.sendMessage(ChatColor.BLUE + "/mspawn delay <number> - Sets a mob spawner's spawn delay.");
    		return;
    	}
    	if(h == 5){
    		p.sendMessage(ChatColor.BLUE + "/mspawn delay <number> - Sets a mob spawner's spawn delay.");
    		return;
    	}
        return;
    }
    
    public static String capitalCase(String s) {
        return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }
    
}
