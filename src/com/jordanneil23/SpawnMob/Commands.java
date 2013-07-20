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
    		"Witch", "Wither", "Wolf, Zombie," }; 
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
	        Mob mob2 = Mob.COW;
	        Player p2 = null;
	        LivingEntity m = null;
	        boolean other = false;
	        Professions pro = Professions.FARMER;
	        boolean chk = false;
	        boolean endman = false;
	        boolean snowman = false;
      
	        if (command.getName().equalsIgnoreCase("SpawnMob")|| command.getName().equalsIgnoreCase("SMob") || command.getName().equalsIgnoreCase("SM"))
		    {
	        	if (args.length == 0) {
	        		Help(p, command, 1);
	        		return false;
	        	}
	        	if(args[0].equalsIgnoreCase("Help")){
	        		Help(p, command, 1);
	        		return false;
	        	}
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
	        		if(args[1].isEmpty()){
	        			Help(p, command, 1);
	        		}
	        		if(MobHandler.isArrMatch(animals, args[1]) == false){
	        			p.sendMessage(ChatColor.RED + "The mob must be a animal if used this way!");
	        			return false;
	        		}
	        		if(args.length >= 3 && !args[2].isEmpty()){
	        			p2 = MobHandler.getOnlinePlayer(args[2]);
	        			if(p2 != null){
	        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                        return false;
		                    }
	        				loc = p2.getLocation();
	        				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[3]) : 1;
	        			}else{
	        				loc = loc2;
	    					count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
	        			}
	        		}else{
	        			loc = loc2;
	        			count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
	        		}
	        		if (split1.length == 1) {
	                    split0 = args[1].split(";");
	                    split1[0] = split0[0];
	                }
	    			if (split1.length == 2) {
	                    args[1] = split1[0] + "";
	                }
	        		mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
	        		if (mob == null)
	    			{
	    				p.sendMessage(ChatColor.RED + "INVALID MOB");
	    				return false;
	    			}
	        		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
	            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	                    return false;
	                }

	        		for (int i = 0; i < count; i++)
	    			{
	        			m = MobHandler.spawnbaby(mob, p, loc);
	        			if(split1.length == 2){
	        				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
						    if (mob2 == null) {
						    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
						        return false;
						    }
								m.setPassenger(MobHandler.spawn(mob2, p, loc));
								return true;
	        			}
	    			}
	        		p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a" : count) + " baby " + mob.getName().toLowerCase().toLowerCase() + mob.s + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
    				return true;
	        	}
	        	if (MobHandler.isArrMatch(customMobs, args[0])){
	        		count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	        		if (args[0].equalsIgnoreCase("Creeper")){
	        			boolean e = false;
	        			if(args.length >= 2 && !args[1].isEmpty()){
        					p2 = MobHandler.getOnlinePlayer(args[1]);
                			if(p2 != null){
                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        	                        return false;
        	                    }
                				loc = p2.getLocation();
                				count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                			}else
        				if(args[1].equalsIgnoreCase("Electric")){
        					if(args.length >= 3){
        					p2 = MobHandler.getOnlinePlayer(args[2]);
                			if(p2 != null){
                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        	                        return false;
        	                    }
                				loc = p2.getLocation();
                				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[3]) : 1;
                			}else{
                				loc = loc2;
            					count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                			}
        					}
        					e = true;
        				}
        			}else{
            			loc = loc2;
            			count = args.length >= 3 ? MobHandler.convertStringtoInt(args[1]) : 1;
            		}
	        			if(!(PermissionsHandler.playerhas(p, (e == false ? "spawnmob.creeper" : "spawnmob.creeper.electric"), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                    return false;
		                }
	        			for (int i = 0; i < count; i++)
	        			{
	            			m = MobHandler.spawnCreeper(p, loc, e);
	            			if(split1.length == 2){
	            				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
	    					    if (mob2 == null) {
	    					    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
	    					        return false;
	    					    }
	    							m.setPassenger(MobHandler.spawn(mob2, p, loc));
	    							return true;
	            			}
	        			}
	        			p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a " : count) + (e ? " electric " : "") + (count == 1? " creeper" : " creepers") + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
        				return true;
	        		}else if (args[0].equalsIgnoreCase("Ocelot") || args[0].equalsIgnoreCase("Cat")){
	        			boolean t = false;
	        			if(args.length >= 2 && !args[1].isEmpty()){
	        					p2 = MobHandler.getOnlinePlayer(args[1]);
	                			if(p2 != null){
	                				other = true;
	                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
	        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	        	                        return false;
	        	                    }
	                				loc = p2.getLocation();
	                				count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
	                			}else
	        				if(args[1].equalsIgnoreCase("Tamed")){
	        					if(args.length >= 3){
	        					p2 = MobHandler.getOnlinePlayer(args[2]);
	                			if(p2 != null){
	                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
	        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	        	                        return false;
	        	                    }
	                				other = true;
	                				loc = p2.getLocation();
	                				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[3]) : 1;
                                    p1 = p2;
	                			}else{
	                				loc = loc2;
	            					count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
	                			}
	        					}
	        					t = true;
	        				}
	        			}else{
	            			loc = loc2;
	            			count = args.length >= 3 ? MobHandler.convertStringtoInt(args[1]) : 1;
	            		}
	        			
	        			if(!(PermissionsHandler.playerhas(p, (t == false ? "spawnmob.ocelot" : "spawnmob.ocelot.tamed"), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                    return false;
		                }
	        			
	        			for (int i = 0; i < count; i++)
	        			{
	            			m = MobHandler.spawnCat(Mob.OCELOT, p1, loc, t);
	            			if(split1.length == 2){
	            				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
	    					    if (mob2 == null) {
	    					    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
	    					        return false;
	    					    }
	    							m.setPassenger(MobHandler.spawn(mob2, p, loc));
	    							return true;
	            			}
	        			}

            				p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? ChatColor.RED +" two [this is a bug within bukkit that will make it spawn 2]"+ChatColor.BLUE : count) + (t ? ChatColor.RED + " tamed [There is a bug within bukkit that will spawn one tamed ocelot and one not tamedand they will look like a normal ocelot, sorry] " + ChatColor.BLUE : "") + (count == 1 ? "ocelot" : "ocelots") + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
            				return true;
	        		}else if (args[0].equalsIgnoreCase("MagmaCube") || args[0].equalsIgnoreCase("Magma_Cube")){
	        			if(args.length >= 2 && !args[1].isEmpty()){
	        					p2 = MobHandler.getOnlinePlayer(args[1]);
	                			if(p2 != null){
	                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
	        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	        	                        return false;
	        	                    }
	                				other = true;
	                				loc = p2.getLocation();
	                				count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
	                			}else{
	                				loc = loc2;
	            					count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	                			}
	        			}else{
	            			loc = loc2;
	            			count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	            		}
	        			if (split1.length == 2 || split1.length > 1){
	        				loc = loc2;
	            			count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	        				MobHandler.spawnSlime(Mob.MAGMA_CUBE, p, loc, split1[1]);
	        				return true;
	        			}
	        			if(!(PermissionsHandler.playerhas(p, "spawnmob.magmacube", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                    return false;
		                }
	        			for (int i = 0; i < count; i++)
	        			{
	            			m = MobHandler.spawnSlime(Mob.MAGMA_CUBE, p, loc, "");
	            			if(split1.length == 2){
	            				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
	    					    if (mob2 == null) {
	    					    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
	    					        return false;
	    					    }
	    							m.setPassenger(MobHandler.spawn(mob2, p, loc));
	    							return true;
	            			}
	        			}
	        			p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a " : count) + (count == 1 ? " magmacube " : " magmacubes ") + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
        				return true;
	        		}else if (args[0].equalsIgnoreCase("Slime")){
	        			if(args.length >= 2 && !args[1].isEmpty()){
	        				if(args.length >= 3){
	        					p2 = MobHandler.getOnlinePlayer(args[2]);
	                			if(p2 != null){
	                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
	        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	        	                        return false;
	        	                    }
	                				other = true;
	                				loc = p2.getLocation();
	                				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[2]) : 1;
	                			}else{
	                				loc = loc2;
	            					count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	                			}
	            			}
	        			}else{
	            			loc = loc2;
	            			count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	            		}
	        			if (split1.length == 2 || split1.length > 1){
	        				loc = loc2;
	            			count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	        				MobHandler.spawnSlime(Mob.SLIME, p, loc, split1[1]);
	        				return true;
	        			}
	        			if(!(PermissionsHandler.playerhas(p, "spawnmob.slime", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                    return false;
		                }
	        			for (int i = 0; i < count; i++)
	        			{
	            			m = MobHandler.spawnSlime(Mob.SLIME, p, loc, "");
	            			if(split1.length == 2){
	            				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
	    					    if (mob2 == null) {
	    					    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
	    					        return false;
	    					    }
	    							m.setPassenger(MobHandler.spawn(mob2, p, loc));
	    							return true;
	            			}
	        			}
	        			p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a " : count) + (count == 1 ? " slime " : " slimes ") + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
        				return true;
	        		}else if (args[0].equalsIgnoreCase("Sheep")){
	        			boolean c = false;
	        			if(args.length >= 2 && !args[1].isEmpty()){
	        				
        					p2 = MobHandler.getOnlinePlayer(args[1]);
                			if(p2 != null){
                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        	                        return false;
        	                    }
                				other = true;
                				loc = p2.getLocation();
                				count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                			}else
        				if(MobHandler.isArrMatch(MobHandler.sheepcolors, args[1]) == true){
        					if(args.length >= 3){
        					p2 = MobHandler.getOnlinePlayer(args[2]);
                			if(p2 != null){
                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        	                        return false;
        	                    }
                				loc = p2.getLocation();
                				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[3]) : 1;
                			}else{
                				loc = loc2;
            					count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                			}
        					}
        					c = true;
        				}
        			}else{
            			loc = loc2;
            			count = args.length >= 3 ? MobHandler.convertStringtoInt(args[1]) : 1;
            		}
	        				
	        			if(!(PermissionsHandler.playerhas(p, (c == false ? "spawnmob.sheep" : "spawnmob.sheep.colored"), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                    return false;
		                }
	        			
	        			for (int i = 0; i < count; i++)
	        			{
	            			m = MobHandler.spawnSheep(Mob.SHEEP, p, loc, (c ? args[1] : "White"), c);
	            			if(split1.length == 2){
	            				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
	    					    if (mob2 == null) {
	    					    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
	    					        return false;
	    					    }
	    							m.setPassenger(MobHandler.spawn(mob2, p, loc));
	    							return true;
	            			}
	        			}
	        			p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a" : count) + (c ? " sheep" : args[1]) + (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
        				return true;
	        		}else if(args[0].equalsIgnoreCase("Wolf")){
	        			boolean t = false;
	        			boolean mad = false;
	        			if(args.length >= 2 && !args[1].isEmpty()){
        					p2 = MobHandler.getOnlinePlayer(args[1]);
                			if(p2 != null){
                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        	                        return false;
        	                    }
                				other = true;
                				loc = p2.getLocation();
                				count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                			}else
        				if(args[1].equalsIgnoreCase("Tamed")){
        					if(!(PermissionsHandler.playerhas(p, "spawnmob.wolf.tamed", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
    		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
    		                    return false;
    		                }
        					if(args.length >= 3){
        					p2 = MobHandler.getOnlinePlayer(args[2]);
                			if(p2 != null){
                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        	                        return false;
        	                    }
                				other = true;
                				loc = p2.getLocation();
                				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[3]) : 1;
                				p1 = p2;
                			}else{
                				loc = loc2;
            					count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                			}
        					}
                			
        					t = true;
        					
        				}else
            				if(args[1].equalsIgnoreCase("Mad")){
            					if(!(PermissionsHandler.playerhas(p, "spawnmob.wolf.mad", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        		                    return false;
        		                }
            					if(args.length >= 3){
            					p2 = MobHandler.getOnlinePlayer(args[2]);
                    			if(p2 != null){
                    				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
            	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
            	                        return false;
            	                    }
                    				other = true;
                    				loc = p2.getLocation();
                    				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[3]) : 1;
                    				p1 = p2;
                    			}else{
                    				loc = loc2;
                					count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                    			}
            					}
            					mad = true;
            				}
        			}else{
            			loc = loc2;
            			count = args.length >= 3 ? MobHandler.convertStringtoInt(args[1]) : 1;
            		}
	        			if(!(PermissionsHandler.playerhas(p, "spawnmob.wolf", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                    return false;
		                }
	        			for (int i = 0; i < count; i++)
	        			{
	            			m = MobHandler.spawnWolf(Mob.WOLF, p1, loc, t, mad);
	            			if(split1.length == 2){
	            				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
	    					    if (mob2 == null) {
	    					    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
	    					        return false;
	    					    }
	    							m.setPassenger(MobHandler.spawn(mob2, p, loc));
	    							return true;
	            			}
	        			}
	        			p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a " : count) + (t ? " tamed " : (mad ? " mad " : "")) + (count == 1? " wolf " : " wolves ") + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
        				return true;
	        		}else if(args[0].equalsIgnoreCase("Villager") || args[0].equalsIgnoreCase("NPC")){
	        			boolean prof = false;
	        			if(args.length >= 2 && !args[1].isEmpty()){
	        				
        					p2 = MobHandler.getOnlinePlayer(args[1]);
                			if(p2 != null){
                				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
        	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
        	                        return false;
        	                    }
                				other = true;
                				
                				loc = p2.getLocation();
                				count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                			}else{
                			pro = Professions.fromName(capitalCase(args[1]));
                			if(pro != null){
                				if(args.length >= 3){
                					p2 = MobHandler.getOnlinePlayer(args[2]);
                        			if(p2 != null){
                        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
                	                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
                	                        return false;
                	                    }
                        				other = true;
                        				loc = p2.getLocation();
                        				count = args.length >= 4 ? MobHandler.convertStringtoInt(args[3]) : 1;
                        			}else{
                        				loc = loc2;
                    					count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
                        			}
                				}
                    				
                    			}else{
                    				p.sendMessage(ChatColor.RED + "Invalid villager profession!");
                    			}
        				}
        			}else{
            			loc = loc2;
            			count = args.length >= 3 ? MobHandler.convertStringtoInt(args[1]) : 1;
            		}
	        				
	        			if(!(PermissionsHandler.playerhas(p, (prof == false ? "spawnmob.villager" : "spawnmob.villager.profession"), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                    return false;
		                }
	        			
	        			for (int i = 0; i < count; i++)
	        			{
	            			try {
								m = pro.spawn(p, loc);
							} catch (Exception e) {
								MobHandler.spawn(Mob.VILLAGER, p, loc);
							}
	            			if(split1.length == 2){
	            				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
	    					    if (mob2 == null) {
	    					    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
	    					        return false;
	    					    }
	    							m.setPassenger(MobHandler.spawn(mob2, p, loc));
	    							return true;
	            			}
	        			}
	        			p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a" : count) + (prof ? pro.getName() : " villager ") + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
        				return true;
	        		}
	        	}else{
	        		if(args.length >= 2 && args[1].length() != 0){
	        			p2 = MobHandler.getOnlinePlayer(args[1]);
	        			if(p2 != null){
	        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
		                		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
		                        return false;
		                    }
	        				loc = p2.getLocation();
	        				count = args.length >= 3 ? MobHandler.convertStringtoInt(args[2]) : 1;
	        			}else{
	        				loc = loc2;
	    					count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	        			}
	        		}else{
	        			loc = loc2;
	        			count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
	        		}
	    			if (split1.length == 2) {
	                    args[1] = split1[0] + "";
	                }
	        		mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
	        		if (mob == null)
	    			{
	    				p.sendMessage(ChatColor.RED + "INVALID MOB");
	    				return false;
	    			}
	        		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
	            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
	                    return false;
	                }

	        		for (int i = 0; i < count; i++)
	    			{
	        			m = MobHandler.spawn(mob, p, loc);
	        			if(split1.length == 2){
	        				mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
						    if (mob2 == null) {
						    	p.sendMessage(ChatColor.RED + "INVALID RIDING MOB");
						        return false;
						    }
								m.setPassenger(MobHandler.spawn(mob2, p, loc));
								return true;
	        			}
	    			}
	        		p.sendMessage(ChatColor.BLUE + "You spawned " + (count == 1 ? "a" : count) + " " + mob.getName().toLowerCase().toLowerCase() + (count == 1 || mob.getName().equalsIgnoreCase("Ender_Man") || mob.getName().equalsIgnoreCase("SilverFish")|| mob.getName().equalsIgnoreCase("Squid")  ? "" : "s") + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (other ? " onto player " + p2 : "") + "!");
    				return true;
	        	}
		    }
	        else if (command.getName().equalsIgnoreCase("mspawn")){
        	loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
			loc.setY(1 + loc.getY());
        	if (args.length == 0)
            {
                Help(p, command, 4);
                return false;
            }
            org.bukkit.block.Block blk = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock();
            if (blk == null || blk.getTypeId() != 52)
            {
            	p.sendMessage(ChatColor.RED + "You must be looking at a Mob Spawner.");
            	return false;
            }

            if (args[0].equalsIgnoreCase("Check") || args[0].equalsIgnoreCase("Info"))
            {
            	if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn.check", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
                    return false;
                }
            	
            	p.sendMessage(ChatColor.BLUE + "This spawner's mob type is " + ((org.bukkit.block.CreatureSpawner) blk.getState()).getCreatureTypeName().toString() + ".");
            	p.sendMessage(ChatColor.BLUE + "This spawners delay is set to " + ((org.bukkit.block.CreatureSpawner)blk.getState()).getDelay() + ".");
            }
            else if (args[0].equalsIgnoreCase("Delay"))
            {
            	if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn.delay", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
                    return false;
                }
            	
            	if (args.length != 2)
            	{
            		Help(p, command, 5);
            		return false;
            	}

            	int delay = MobHandler.convertStringtoInt(args[1]);
            	if (delay < 0)
            	{
            		p.sendMessage(ChatColor.RED + "You must enter a number.");
            		return false;
           		}
            	
            	((org.bukkit.block.CreatureSpawner)blk.getState()).setDelay(delay);
            	p.sendMessage(ChatColor.BLUE + "This spawner's delay is now set to " + delay + ".");
            }
            else
            {
            	EntityType mt = null;
            	if (MobHandler.Check(args[0]) != null){
                	mt = MobHandler.Check(args[0]);
                	
            	}else{
            		mt = EntityType.fromName(args[0].substring(0,1).toUpperCase() + args[0].substring(1).toLowerCase());
            	}
                if (mt == null)
                {
                	p.sendMessage(ChatColor.RED + "Creature type not found: " + args[0]);
                	return false;
                }
                
                if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn." + mt.getName().toLowerCase(), Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.allmobs", Main.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.permissions) == true)){
                	p.sendMessage(ChatColor.RED + "You don't have permission to do that.");
                    return false;
                }
                
                ((org.bukkit.block.CreatureSpawner)blk.getState()).setCreatureTypeByName(mt.toString());
                p.sendMessage(ChatColor.BLUE + "Mob spawner set as " + mt.getName() + ".");
            }
            return true;
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
