package com.jordanneil23.SpawnMob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import com.jordanneil23.SpawnMob.TargetBlock;
import com.jordanneil23.SpawnMob.Kits.*;
import com.jordanneil23.SpawnMob.Mobs.*;

/**
 * 
 * SpawnMob
 * @version 2.1
 * @Commands SpawnMob, Kit, Kill, List, MSpawn
 * @author jordanneil23
 * 
 **/

public class CommandHandler{
	int count = 1;
	
    private String mobList[] = { 
    		"Blaze, CaveSpider, Chicken, Cow, Creeper, EnderMan,", 
    		"EnderDragon, Ghast, Giant, MagmaCube, Pig, PigZombie, Sheep,",
    		"SilverFish, Snowman, Skeleton, Slime, Spider, Squid, Villager, Wolf,",
    		"Zombie", "Ocelot"}; 
    private String mobz[] = {
    		"Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderMan", 
    		"EnderDragon", "Ghast", "Giant", "Pig", "PigZombie", "Sheep",
    		"SilverFish", "Skeleton", "Slime", "Spider", "Squid", "Villager", "Wolf",
    		"Zombie", "Twolf", "All", "Monsters", "Animals", "Wolves", "Ender_Dragon",
    		"Dragon", "Pig_Zombie", "Magma_Cube", "MagmaCube", "SnowMan", "SnowGolem", "Ocelot", "Cat"}; 

    private String animals[] = {"Chicken", "Cow", "Pig", "Sheep", "Wolf", "Ocelot", "Villager"};
    private String customMobs[] = { "Wolf", "Creeper", "Sheep", "Ocelot", "Cat"};
	public boolean perform(CommandSender sender, Command command, String[] args) {
    	int[] ignore = {8, 9};
        Player p = (Player) sender;
        Location loc = null;
        Mob mob2 = null;
        LivingEntity rider = null;
		boolean chk = false;
		boolean endman = false;
		boolean snowman = false;
		boolean cube = false;
		boolean plr = false;
		boolean plr2 = false;
		boolean plr3 = false;
		boolean plr4 = false;
		
        if (command.getName().equalsIgnoreCase("spawnmob")|| command.getName().equalsIgnoreCase("smob") || command.getName().equalsIgnoreCase("sm"))
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
	        boolean isCustomMob = isArrMatch(customMobs, args[0]);
	        /** @Note Babies  **/
	        if(args[0].equalsIgnoreCase("Baby")){
	        	if (args.length >= 3)
    			{
    				if(MobHandling.getOnlinePlayer(args[2]) != null){
    				loc = MobHandling.getOnlinePlayer(args[2]).getLocation();
    				count = args.length >= 4 ? convertStringtoInt(args[3]) : 1;
    				if (spawnCheck(p, count) == false){return false;}
    				plr2 = true;
    				plr4 = true;
    				}else{
    					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
    					loc.setY(1 + loc.getY());
    					count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
    	    			if(args.length >= 3){
    	    				if (spawnCheck(p, count) == false){return false;}
    	    			}
    				}
    			}else{
    				loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
					loc.setY(1 + loc.getY());
    			}
    			if (split1.length == 1) {
                    split0 = args[1].split(";");
                    split1[0] = split0[0];
                }
    			if (split1.length == 2) {
                    args[1] = split1[0] + "";
                }
    			boolean isAnimal = isArrMatch(animals, args[1]);
	        	if(isAnimal == false && split1.length != 2 && !(split0.length >= 2 || split0.length <=2)){
	        		p.sendMessage("You can only spawn baby animals. To see the mobs that can be spawned as babies use /" + command.getName().toLowerCase() + " list babies");
	        		return false;
	        	}
    			Mob mob = null;
    			if(split0.length == 2 && MobHandling.getOnlinePlayer(split0[0]) != null){
    				mob = Mob.COW;
    			}else{
                mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
    			}
    			Animals m = null;
    			if (mob == null)
    			{
    				p.sendMessage(ChatColor.RED + "Invalid mob/player, try again.");
    				return false;
    			}
    				if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.getName().toLowerCase(), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
    					p.sendMessage(ChatColor.RED + "You can't use this command.");
                        return false;
                    }

    			for (int i = 0; i < count; i++)
    			{
    				if(split0.length != 2){
    				m = (Animals) MobHandling.spawn(mob, p, loc);
    				m.setAge(-24000);
    				if (mob.getName().toUpperCase() == "SHEEP")
    				{
    					chk = true;
    				}
    				}
					if (split0.length == 2) {
						mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
					    if (mob2 == null && MobHandling.getOnlinePlayer(split0[1]) == null) {
					    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
					        return false;
					    }
						    if(MobHandling.getOnlinePlayer(split0[0]) != null){
						    	plr2 = true;
						    	m.setPassenger(MobHandling.getOnlinePlayer(split0[0]));
						    }
						    else{
						    	if(MobHandling.getOnlinePlayer(split0[1]) != null){
							    	if(MobHandling.getOnlinePlayer(split0[1]) != null){
							    		plr = true;
							    		MobHandling.getOnlinePlayer(split0[1]).setPassenger(m);
							    	}
							    }else{
							    	rider = MobHandling.spawn(mob2, p, loc);
								    rider.setPassenger(m);
							    }
					    }
					}
    			}
    			
    			if(split0.length == 2 && plr2 == true){
    				p.sendMessage(ChatColor.BLUE + (plr4 ? "You spawned a " + mob.getName().toLowerCase() + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + " on " + args[1] + "'s location!" : "You made " + split0[0]) + (plr3 ? " ride " + split0[1] : (split0.length == 2 ? " ride a " + split0[1] : "")) + "!");
    			    return true;
    			}
    			if (count == 1)
    			{
    				p.sendMessage(ChatColor.BLUE + "You spawned a baby " + mob.getName().toLowerCase() + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + "!");
    			    return true;
    			}
    			else
    			{
    				p.sendMessage(ChatColor.BLUE + "You spawned " + args[1] + " baby " + (snowman ? "snowmen" : mob.getName().toLowerCase()) + (chk ? "" : mob.s) + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
	                return true;
    			}
	        }else
	        	/** @Note Kill  **/
            	if (args[0].equalsIgnoreCase("Kill"))
        		{
            		boolean isKillableMob = isArrMatch(mobz, args[1]);
            		if (args.length == 1)
          		    {
            			Help(p, command, 2);
          		        return false;
          		    }
            		
                  	String mt = null;
                	if (MobHandling.Check(args[0]) != null){
                         mt = MobHandling.Check(args[0]).toString();
                	}else{
                		mt = args[1];
                	}
                  	if (!(PermissionsHandler.playerhas(p, "spawnmob.kill", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.kill." + args[1].toLowerCase(), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
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
                		if(SpawnMob.permissions){
                			if (!(PermissionsHandler.playerhas(p, "spawnmob.kits", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.kits.*", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true )){
                				p.sendMessage(ChatColor.RED + "You can't use this command.");
                            	return false;
                        	}
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
                }
	           /** @Note Begin spawning Custom mobs, there may be bugs here!!  **/
                if (isCustomMob == true)
                {
            		if (args[0].equalsIgnoreCase("Wolf"))
            		{
            			boolean tamed = false;
            			boolean mad = false;
            			boolean t2 = false;
            			if (args.length >= 2)
            			{
            				if(args[1].equalsIgnoreCase("Tamed")){
                				tamed = true;
            				}
            				if(args[1].equalsIgnoreCase("Mad")){
                				mad = true;
            				}
            				if(tamed == true || mad == true){
            					
            						if(args.length > 2 && MobHandling.getOnlinePlayer(args[2]) != null){
                    					t2 = true;
                    					loc = MobHandling.getOnlinePlayer(args[2]).getLocation();
                                    	count = args.length >= 4 ? convertStringtoInt(args[3]) : 1;
                    				}else{
            					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
            					loc.setY(1 + loc.getY());
            					count = args.length >= 2 ? convertStringtoInt(args[2]) : 1;
            					}
            				}
            				else
            				if(MobHandling.getOnlinePlayer(args[1]) != null){
                				loc = MobHandling.getOnlinePlayer(args[1]).getLocation();
                            	count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
            				}else{
            					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
            					loc.setY(1 + loc.getY());
            					if(tamed != true || mad != true){
            					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
            					}
            				}
            			}else{
            				loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
        					loc.setY(1 + loc.getY());
        					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
            			}
            				if(!(PermissionsHandler.playerhas(p, "spawnmob."   + (tamed ? "wolf.tamed" : mad ? "wolf.mad" : "wolf"), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
            					p.sendMessage(ChatColor.RED + "You can't use this command.");
                                return false;
                            }
            				if (spawnCheck(p, count) == false){return false;}
                        Wolf w = null;
                        for (int i = 0; i < count; i++){
                        	if(t2 == true){
                        		w = MobHandling.setforWolf(MobHandling.getOnlinePlayer(args[2]), loc, mad, tamed);
                        	}else{
                        	w = MobHandling.setforWolf(p, loc, mad, tamed);
                        	}
                        	if (split0.length == 2) {
        						mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
        					    if (mob2 == null && MobHandling.getOnlinePlayer(split0[1]) == null) {
        					    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
        					        return false;
        					    }
        					    if(MobHandling.getOnlinePlayer(split0[0]) != null){
        					    	plr2 = true;
        					    	w.setPassenger(MobHandling.getOnlinePlayer(split0[0]));
        					    }
        					    else{
            					    if(MobHandling.getOnlinePlayer(split0[1]) != null){
            					    		plr = true;
            					    		Player r = MobHandling.getOnlinePlayer(split0[1]);
            					    		r.setPassenger(w);
            					    }else{
            					    rider = MobHandling.spawn(mob2, p, loc);
            					    rider.setPassenger(w);
            					    }
        					    }
        					}
                        }
                        if(plr2 == true){
            				p.sendMessage(ChatColor.BLUE + (plr4 ? "You spawned " +(count == 1 ? "a wolf " : args[2] + " wolves ")  + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + " on " + args[1] + "'s location!" : "You made " + split0[0]) + (plr3 ? " ride " + split0[1] : (split0.length == 2 ? " ride a " + split0[1] : "")) + "!");
            			    return true;
            			}
            			if (count == 1){
            				p.sendMessage(ChatColor.BLUE + "You spawned a " + (tamed ? "tamed " : mad ? "mad " : "") + "wolf" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            			else{
            				p.sendMessage(ChatColor.BLUE + "You spawned " + count + " " + (tamed ? "tamed " : mad ? "mad " : "") + "wolves" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            		}else if (args[0].equalsIgnoreCase("Ocelot") || args[0].equalsIgnoreCase("Cat"))
            		{
            			boolean tamed = false;
            			boolean t2 = false;
            			if (args.length >= 2)
            			{
            				if(args[1].equalsIgnoreCase("Tamed")){
                				tamed = true;
            				}
            				if(tamed == true){
            					
            						if(args.length > 2 && MobHandling.getOnlinePlayer(args[2]) != null){
                    					t2 = true;
                    					loc = MobHandling.getOnlinePlayer(args[2]).getLocation();
                                    	count = args.length >= 4 ? convertStringtoInt(args[3]) : 1;
                    				}else{
            					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
            					loc.setY(1 + loc.getY());
            					count = args.length >= 2 ? convertStringtoInt(args[2]) : 1;
            					}
            				}
            				else
            				if(MobHandling.getOnlinePlayer(args[1]) != null){
                				loc = MobHandling.getOnlinePlayer(args[1]).getLocation();
                            	count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
            				}else{
            					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
            					loc.setY(1 + loc.getY());
            					if(tamed != true){
            					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
            					}
            				}
            			}else{
            				loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
        					loc.setY(1 + loc.getY());
        					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
            			}
            				if(!(PermissionsHandler.playerhas(p, "spawnmob."   + (tamed ? "ocelot.tamed" : "ocelot"), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
            					p.sendMessage(ChatColor.RED + "You can't use this command.");
                                return false;
                            }
            				if (spawnCheck(p, count) == false){return false;}
                        Ocelot o = null;
                        for (int i = 0; i < count; i++){
                        	if(t2 == true){
                        		o = MobHandling.setforOcelot(MobHandling.getOnlinePlayer(args[2]), loc, tamed);
                        	}else{
                        	o = MobHandling.setforOcelot(p, loc, tamed);
                        	}
                        	if (split0.length == 2) {
        						mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
        					    if (mob2 == null && MobHandling.getOnlinePlayer(split0[1]) == null) {
        					    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
        					        return false;
        					    }
        					    if(MobHandling.getOnlinePlayer(split0[0]) != null){
        					    	plr2 = true;
        					    	o.setPassenger(MobHandling.getOnlinePlayer(split0[0]));
        					    }
        					    else{
            					    if(MobHandling.getOnlinePlayer(split0[1]) != null){
            					    		plr = true;
            					    		Player r = MobHandling.getOnlinePlayer(split0[1]);
            					    		r.setPassenger(o);
            					    }else{
            					    rider = MobHandling.spawn(mob2, p, loc);
            					    rider.setPassenger(o);
            					    }
        					    }
        					}
                        }
                        if(plr2 == true){
            				p.sendMessage(ChatColor.BLUE + (plr4 ? "You spawned " +(count == 1 ? "a ocelot " : args[2] + " ocelots ")  + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + " on " + args[1] + "'s location!" : "You made " + split0[0]) + (plr3 ? " ride " + split0[1] : (split0.length == 2 ? " ride a " + split0[1] : "")) + "!");
            			    return true;
            			}
            			if (count == 1){
            				p.sendMessage(ChatColor.BLUE + "You spawned a " + (tamed ? "tamed " : "") + "ocelot" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            			else{
            				p.sendMessage(ChatColor.BLUE + "You spawned " + count + " " + (tamed ? "tamed " : "") + "ocelots" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            		}
            		else if (args[0].equalsIgnoreCase("Creeper"))
            		{
            			boolean electric = false;
            			
            			if (args.length >= 2)
            			{
            				if(args[1].equalsIgnoreCase("Electric")){
            				electric = true;
            				if(electric == true)if(args.length > 2){
        						if(MobHandling.getOnlinePlayer(args[2]) != null){
                					loc = MobHandling.getOnlinePlayer(args[2]).getLocation();
                                	count = args.length >= 4 ? convertStringtoInt(args[3]) : 1;
                				
        					}else{
            					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
            					loc.setY(1 + loc.getY());
            					count = args.length >= 2 ? convertStringtoInt(args[2]) : 1;
            				}
            				}
            				else
            				if(MobHandling.getOnlinePlayer(args[1]) != null){
                			loc = MobHandling.getOnlinePlayer(args[1]).getLocation();
                            count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
            				}else{
            					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
            					loc.setY(1 + loc.getY());
            					if(electric != true){
                					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
                					}
            				}
            			}else{
            				if(MobHandling.getOnlinePlayer(args[1]) != null){
                				loc = MobHandling.getOnlinePlayer(args[1]).getLocation();
                				count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
                				if (spawnCheck(p, count) == false){return false;}
                				plr2 = true;
                				plr4 = true;
                				}else{
                					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
                    				loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
                					loc.setY(1 + loc.getY());
                					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
                				}
            			}
            			}else{
            				loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
        					loc.setY(1 + loc.getY());
        					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
            			}
            			
            				if(!(PermissionsHandler.playerhas(p, "spawnmob."   + (electric ? "creeper.electric" : "creeper"), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
            					p.sendMessage(ChatColor.RED + "You can't use this command.");
                                return false;
                            }
            				if (spawnCheck(p, count) == false){return false;}
        				
        				Creeper c = null;
        				
        				for (int i = 0; i < count; i++){
            				c = MobHandling.setforCreeper(p, loc, electric);
            				if (split0.length == 2) {
        						mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
        					    if (mob2 == null && MobHandling.getOnlinePlayer(split0[0]) != null) {
        					    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
        					        return false;
        					    }
        					    if(MobHandling.getOnlinePlayer(split0[0]) != null){
        					    	plr2 = true;
        					    	c.setPassenger(MobHandling.getOnlinePlayer(split0[0]));
        					    }
        					    else{
        					    	if(MobHandling.getOnlinePlayer(split0[0]) != null){
            					    		plr = true;
            					    		Player r = MobHandling.getOnlinePlayer(split0[1]);
            					    		r.setPassenger(c);
            					    }else{
            					    rider = MobHandling.spawn(mob2, p, loc);
            					    rider.setPassenger(c);
            					    }
        					    }
        					}
        				}
        				if(plr2 == true){
            				p.sendMessage(ChatColor.BLUE + (plr4 ? "You spawned " +(count == 1 ? "a creeper " : args[2] + " creepers ")  + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + " on " + args[1] + "'s location!" : "You made " + split0[0]) + (plr3 ? " ride " + split0[1] : (split0.length == 2 ? " ride a " + split0[1] : "")) + "!");
            			    return true;
            			}
            			if (count == 1){
            				p.sendMessage(ChatColor.BLUE + "You spawned a" + (electric ? "n electric" : "") + " creeper" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            			else{
            				p.sendMessage(ChatColor.BLUE + "You spawned " + count + " " + (electric ? "electric " : "") + "creepers" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            		}else 
            			if (args[0].equalsIgnoreCase("Sheep"))
            		{
            			boolean color = false;
            			if (args.length >= 2)
            			{
            				if (isArrMatch(MobHandling.sheepcolors, args[1]) == true){
                				color = true;
                				}
            				if(color == true){
            					if(args.length > 2){
            						if(MobHandling.getOnlinePlayer(args[2]) != null){
                    					loc = MobHandling.getOnlinePlayer(args[2]).getLocation();
                                    	count = args.length >= 4 ? convertStringtoInt(args[3]) : 1;
                    				}
            						else
                    				{
            							loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
                    					loc.setY(1 + loc.getY());
                    					count = args.length >= 2 ? convertStringtoInt(args[2]) : 1;
                    				}
            					}
            				}else
            				if(MobHandling.getOnlinePlayer(args[1]) != null){
                				loc = MobHandling.getOnlinePlayer(args[1]).getLocation();
                            	count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
            				}else{
            					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
            					loc.setY(1 + loc.getY());
            					if(color != true){
                					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
                					}
            				}
            			}else{
            				loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
        					loc.setY(1 + loc.getY());
        					count = args.length >= 1 ? convertStringtoInt(args[1]) : 1;
            			}
            			
            				if(!(PermissionsHandler.playerhas(p, "spawnmob."   + (color ? "sheep.colors" : "sheep"), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
            					p.sendMessage(ChatColor.RED + "You can't use this command.");
                                return false;
                            }
            				if (spawnCheck(p, count) == false){return false;}
        				
        				LivingEntity s = null;
        				for (int i = 0; i < count; i++){
            				s = MobHandling.setforSheep(p, loc, args[1], color);
            				if (split0.length == 2) {
        						mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
        					    if (mob2 == null && MobHandling.getOnlinePlayer(split0[1]) == null) {
        					    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
        					        return false;
        					    }
        					    if(MobHandling.getOnlinePlayer(split0[0]) != null){
        					    	plr2 = true;
        					    	s.setPassenger(MobHandling.getOnlinePlayer(split0[0]));
        					    }
        					    else{
        					    if(MobHandling.getOnlinePlayer(split0[1]) != null){
        					    		plr = true;
        					    		Player r = MobHandling.getOnlinePlayer(split0[1]);
        					    		r.setPassenger(s);
        					    }else{
        					    rider = MobHandling.spawn(mob2, p, loc);
        					    rider.setPassenger(s);
        					    }
        					    }
        					}
        				}
        				if(plr2 == true){
            				p.sendMessage(ChatColor.BLUE + (plr4 ? "You spawned " +(count == 1 ? "a sheep " : args[2] + " sheep ")  + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + " on " + args[1] + "'s location!" : "You made " + split0[0]) + (plr3 ? " ride " + split0[1] : (split0.length == 2 ? " ride a " + split0[1] : "")) + "!");
            			    return true;
            			}
            			if (count == 1){
            				p.sendMessage(ChatColor.BLUE + "You spawned a" + (color ? "n " + args[1] + " " : "") + " sheep" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            			else{
            				p.sendMessage(ChatColor.BLUE + "You spawned " + count + " " + (color ? args[1] + " " : "") + "sheep" + (split0.length == 2 && plr == true ? "riding player " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
            				return true;
            			}
            		}
            		return true;
        	}
                /** @Note Begin spawning normal mobs  **/
        		else
        		{
        			if (args.length >= 2)
        			{
        				if(MobHandling.getOnlinePlayer(args[1]) != null){
        				loc = MobHandling.getOnlinePlayer(args[1]).getLocation();
        				count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
        				if (spawnCheck(p, count) == false){return false;}
        				plr2 = true;
        				plr4 = true;
        				}else{
        					loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
        					loc.setY(1 + loc.getY());
        					count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
                			if(args.length >=2){
                				if (spawnCheck(p, count) == false){return false;}
                			}
        				}
        			}else{
        				loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
    					loc.setY(1 + loc.getY());
        			}
        			if (split1.length == 1) {
                        split0 = args[0].split(";");
                        split1[0] = split0[0];
                    }
        			if (split1.length == 2) {
                        args[0] = split1[0] + "";
                    }
        			Mob mob = null;
        			if(split0.length == 2){
        				if(MobHandling.getOnlinePlayer(split0[0]) != null){
        				mob = Mob.COW;
        				}
        				else{
        					mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
        				}
        			}else{
                    mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
        			}
        			
        			LivingEntity m = null;
        			if (mob == null)
        			{
        				p.sendMessage(ChatColor.RED + "Invalid mob/player, try again.");
        				return false;
        			}
        			if(!(split0.length == 2)){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.getName().toLowerCase(), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
             			   p.sendMessage(ChatColor.RED + "You can't use this command.");
                            return false;
                         }
        			}else{
        				/*if(!(PermissionsHandler.playerhas(p, "spawnmob.playeronplayer", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
              			   p.sendMessage(ChatColor.RED + "You can't use this command.");
                             return false;
                          }*/
        			}
        		
        			for (int i = 0; i < count; i++)
        			{
        				if(split0.length != 2){
        				m = MobHandling.spawn(mob, p, loc); 
        				if (mob.getName().toUpperCase() == "SILVERFISH" || mob.getName().toUpperCase() == "SQUID")
        				{
        					chk = true;
        				}
        				if (mob.getName().toUpperCase() == "SLIME")
        				{
        					cube = true;
        				}
        				if (mob.getName().toUpperCase() == "MAGMACUBE" || mob.getName().toUpperCase() == "MAGMA_CUBE")
        				{
        					cube = true;
        				}
        				if (mob.getName().toUpperCase() == "ENDERMAN"){
            				endman = true;
        					chk = true;
        				}
        				if(mob.getName().toUpperCase() == "SNOWMAN" || mob.getName().toUpperCase() == "SNOWGOLEM"){
        					snowman = true;
        					chk = true;
        				}
        				if (cube == true && (split1.length == 2 || split1.length > 1)){
        					if (mob.getName().toUpperCase() == "SLIME" || mob.getName().toUpperCase() == "MAGMACUBE"|| mob.getName().toUpperCase() == "MAGMA_CUBE")
            				{
        						if (mob.getName().toUpperCase() == "MAGMACUBE" || mob.getName().toUpperCase() == "MAGMA_CUBE"){
        							MobHandling.setforMagmaCube(p, loc, m, split1[1]);
        						}
        						if(mob.getName().toUpperCase() == "SLIME"){
            					MobHandling.setforSlime(p, loc, m, split1[1]);
        						}
                            }
        					else
                            {
                            	p.sendMessage("You can't use a ':' like that!");
                            }
        				}
        				}
        				if (split0.length == 2) {
    						mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
    					    if (mob2 == null && MobHandling.getOnlinePlayer(split0[1]) == null) {
    					    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
    					        return false;
    					    }
    					    if(MobHandling.getOnlinePlayer(split0[0]) != null && MobHandling.getOnlinePlayer(split0[1]) != null){
    					    		plr2 = true;
    					    		plr3 = true;
    						    	MobHandling.getOnlinePlayer(split0[1]).setPassenger(MobHandling.getOnlinePlayer(split0[0]));
    					    }else{
        					    if(MobHandling.getOnlinePlayer(split0[0]) != null){
        					    	plr2 = true;
        					    	m = MobHandling.spawn(mob2, p, loc);
        					    	m.setPassenger(MobHandling.getOnlinePlayer(split0[0]));
        					    }
        					    else{
        					    	if(MobHandling.getOnlinePlayer(split0[1]) != null){
            					    		plr = true;
            					    		Player r = MobHandling.getOnlinePlayer(split0[1]);
            					    		r.setPassenger(m);
            					    }else{
            					    	m = MobHandling.spawn(mob, p, loc);
                					    rider = MobHandling.spawn(mob2, p, loc);
                					    rider.setPassenger(m);
            					    }
        					    }
        					}
        				}
        			}
        			if(plr2 == true){
        				p.sendMessage(ChatColor.BLUE + (plr4 ? "You spawned " +(count == 1 ? "a " : args[2]) + mob.getName().toLowerCase() + (chk ? "" : mob.s) + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + " on " + args[1] + "'s location!" : "You made " + split0[0]) + (plr3 ? " ride " + split0[1] : (split0.length == 2 ? " ride a " + split0[1] : "")) + "!");
        			    return true;
        			}
        			if (count == 1){
        				p.sendMessage(ChatColor.BLUE + "You spawned a " + mob.getName().toLowerCase() + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "")) + "!");
        			    return true;
        			}
        			else{
        				p.sendMessage(ChatColor.BLUE + "You spawned " + args[1] + " " + (endman ? "endermen" : snowman ? "snowmen" : mob.getName().toLowerCase()) + (chk ? "" : mob.s) + (split0.length == 2 && plr == true ? " riding " + split0[1] : (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "")) + "!");
        				return true;
        			}
        			    
        		}
        }
        /** @Note MSpawn  **/
        else if (command.getName().equalsIgnoreCase("mspawn"))
    	{
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
            	if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn.check", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You can't use this command.");
                    return false;
                }
            	
            	p.sendMessage(ChatColor.BLUE + "This spawner's mob type is " + ((org.bukkit.block.CreatureSpawner) blk.getState()).getCreatureTypeName().toString() + ".");
            	p.sendMessage(ChatColor.BLUE + "This spawners delay is set to " + ((org.bukkit.block.CreatureSpawner)blk.getState()).getDelay() + ".");
            }
            else if (args[0].equalsIgnoreCase("Delay"))
            {
            	if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn.delay", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You can't use this command.");
                    return false;
                }
            	
            	if (args.length != 2)
            	{
            		Help(p, command, 5);
            		return false;
            	}

            	int delay = convertStringtoInt(args[1]);
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
            	if (MobHandling.Check(args[0]) != null){
                	mt = MobHandling.Check(args[0]);
            	}else{
            		mt = EntityType.fromName(args[0].substring(0,1).toUpperCase() + args[0].substring(1).toLowerCase());
            	}
                if (mt == null)
                {
                	p.sendMessage(ChatColor.RED + "Creature type not found: " + args[0]);
                	return false;
                }
                
                if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn." + mt.getName().toLowerCase(), SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.allmobs", SpawnMob.permissions) == true || PermissionsHandler.playerhas(p, "spawnmob.*", SpawnMob.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You can't use this command.");
                    return false;
                }
                
                ((org.bukkit.block.CreatureSpawner)blk.getState()).setCreatureTypeByName(mt.toString());
                p.sendMessage(ChatColor.BLUE + "Mob spawner set as " + mt.getName() + ".");
            }
            return true;
        }
		return false;
    }
    /** @Note Help menus **/
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
    
    public static boolean isArrMatch(String[] array, String name)
	{
		for (String mobName : array){
			if (mobName.equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
    
    private boolean spawnCheck(Player p, int count)
	{
		if (count == 0)
		{
			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was invalid.");
			return false;
		}
		else if (count > convertStringtoInt(SpawnMob.spawnlimit))
		{
			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was over the set limit!");
			return false;
		}
		return true;
	}
 
   public static int convertStringtoInt(String args)  throws NumberFormatException {
	   try{
	    int aInt = Integer.parseInt(args);
		return aInt;
	   }catch (Exception e) {
           return 0;
       }
	  }
 
    public static String capitalCase(String s) {
        return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }
}
