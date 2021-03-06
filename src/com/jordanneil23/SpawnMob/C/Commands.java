package com.jordanneil23.SpawnMob.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.block.*;

import com.jordanneil23.SpawnMob.Main;
import com.jordanneil23.SpawnMob.H.LocationHandler;
import com.jordanneil23.SpawnMob.H.MobHandler;
import com.jordanneil23.SpawnMob.H.TargetBlock;
import com.jordanneil23.SpawnMob.H.PermissionsHandler;
import com.jordanneil23.SpawnMob.H.PlayerHandler;
import com.jordanneil23.SpawnMob.K.Kit;
import com.jordanneil23.SpawnMob.M.HorseColors;
import com.jordanneil23.SpawnMob.M.HorseStyles;
import com.jordanneil23.SpawnMob.M.HorseVariants;
import com.jordanneil23.SpawnMob.M.KillMobs;
import com.jordanneil23.SpawnMob.M.Mob;
import com.jordanneil23.SpawnMob.M.Professions;
import com.jordanneil23.SpawnMob.M.SheepColors;



public class Commands {
	public static ChatColor b = ChatColor.BLUE;
    public static ChatColor r = ChatColor.RED;

	public boolean perform(CommandSender sender, Command command, String[] args) {
		Player p = (Player) sender;
		Mob mob = Mob.COW;
		Player p2 = null;
		boolean onplayer = false;
		int count = 1;
		Mob mob2 = Mob.COW;
        LivingEntity m = null;
        boolean coords = false;
        boolean chk = false;
        boolean endman = false;
        boolean snowman = false;
        
		String customMobs[] = { "Creeper", "Horse", "Magma_Cube", "MagmaCube", "Ocelot", "Cat", "ElderGuardian", "Elder_Guardian", "Sheep", "Slime", "Villager", "NPC", "Wolf", "WitherSkeleton"};
		String animals[] = {"Chicken", "Cow", "Horse", "Ocelot" , "Pig", "Sheep", "Wolf", "Cat", "Villager"};
		
		if (command.getName().toLowerCase().equalsIgnoreCase("SpawnMob")|| command.getName().toLowerCase().equalsIgnoreCase("SMob") || command.getName().toLowerCase().equalsIgnoreCase("SM"))
	    {
			if (args.length == 0) {
        		Help(p, command, 1);
        		return false;
        	}
			String rdr = args[0];
			String[] split = rdr.split(";");
			String rider = split[0];
			String ridee = "Cow";
			if(split.length == 2){
				ridee = split[1];
			}
	        Location loc = LocationHandler.getLoc(sender);
			if(args[0].equalsIgnoreCase("Help")){
        		Help(p, command, 1);
        		return true;
        	}
			else if(args[0].equalsIgnoreCase("List")){
				p.sendMessage(ChatColor.BLUE + "Mobs: " + Mob.listmobs().toString());
				return true;
			}
			else 
				
				/*
        		 *  ADD KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *  ADD KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *  ADD KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *  ADD KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *  ADD KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 */
				
				if(args[0].equalsIgnoreCase("AddKit")){
				if (args.length <= 1)
      		    {
        			Help(p, command, 7);
      		        return false;
      		    }
				if (!(PermissionsHandler.playerhas(p, "spawnmob.kit.add", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.kits.*", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true )){
            		p.sendMessage(r + "You can't use this command.");
                	return false;
            	}
				if (args.length <= 2){
					p.sendMessage(r + "You need to specify mobs to add a kit. Ex:");
					p.sendMessage(r + "/sm addkit name mob,mob,mob");
					return false;
				}
				String kitname = args[1];
				String s = args[2];
				String[] mobs = {s};
				if (s.contains(",")){
					mobs = s.split(",");
				}
				Kit.setKits(p, kitname, mobs);
				
				return true;
				
			}
			else 
				/*
        		 *   KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *   KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *   KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *   KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 *   KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND KIT COMMAND 
        		 */
				
				if(args[0].equalsIgnoreCase("Kit")){			
        		if (args.length <= 1)
      		    {
        			Help(p, command, 3);
      		        return false;
      		    }
            	if (!(PermissionsHandler.playerhas(p, "spawnmob.kits", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.kits.*", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true )){
            		p.sendMessage(r + "You can't use this command.");
                	return false;
            	}
            	
            	if(args[1].equalsIgnoreCase("List")){
            		Kit.listKits(p);
            		return true;
            	}
            	//Custom tags
            	else //With amount
    			if((args.length >= 4 && MobHandler.isInt(args[2])) 
    				//No amount
    			|| (args.length >= 3 && !MobHandler.isInt(args[2]))){
    				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                		p.sendMessage(r + "You don't have permission to do that.");
                        return false;
                    }
    				//With amount
    				if(args.length >= 3 && MobHandler.isInt(args[2])){
    					if(args[3].equalsIgnoreCase("Location") || args[3].equalsIgnoreCase("Loc")){
    						
    						loc = LocationHandler.getLoc(sender, args[4], args[5], args[6]);
                			coords = true;
    					}else if(args[3].equalsIgnoreCase("onPlayer") || args[3].equalsIgnoreCase("onP")){
    						p2 = PlayerHandler.getOnlinePlayer(args[4]);
            				loc = LocationHandler.getLoc(sender, p2);
                			onplayer = true;                			
    					}
    				}//End with amount
    				//Without amount
    				else if(MobHandler.isInt(args[2]) == false){
    					if(args[2].equalsIgnoreCase("Loc") || args[2].equalsIgnoreCase("Location")){
                				loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                				coords = true;                				
    					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
    						p2 = PlayerHandler.getOnlinePlayer(args[3]);
            				loc = LocationHandler.getLoc(sender, p2);
                			onplayer = true;                			
    					}
            		}//End without amount
    			}//End custom tags
            	count = args.length >= 3 && MobHandler.isInt(args[2]) == true ? MobHandler.convertStringtoInt(args[2]) : 1;
            	boolean success = false;
            	if(!(PermissionsHandler.playerhas(p, "spawnmob.kits." + args[1], Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
            		p.sendMessage(r + "You don't have permission to spawn the kit '" + args[1] + "'.");
                    return false;
                }
                    for (int i = 0; i < count; i++) {
                    	if ((Kit.spawn(args[1], p, loc)) == true){
                    		success = true;
                    	}
                    }
                    if(success == true){
                    p.sendMessage(b + "Spawned the Mob Kit: " + args[1] + (count >=3  && MobHandler.isInt(args[2]) == true ? " " + args[2] + " times." : "."));
                    return true;
                    } else {
        		    	  p.sendMessage(r + "Invalid mob type or mob kit.");
          		    	  return false;
                    }
        	}else 
        		/*
        		 *  KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND
        		 *  KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND
        		 *  KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND
        		 *  KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND
        		 *  KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND KILL COMMAND
        		 */
        		if(args[0].equalsIgnoreCase("Kill")){
        		
        		if (args.length == 1)
      		    {
        			Help(p, command, 2);
      		        return false;
      		    }
        		rdr = args[1];
				split = rdr.split(";");
        		boolean isKillableMob = MobHandler.isArrMatch(Mob.killableMobs(), args[1]);
              	String mt = null;
            	if (MobHandler.Check(args[0]) != null){
                     mt = MobHandler.Check(args[0]).toString();
            	}else{
            		mt = args[1];
            	}
              	if (!(PermissionsHandler.playerhas(p, "spawnmob.kill", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.kill." + args[1].toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
          			p.sendMessage(r + "You can't use this command.");
          			return false;
          		} 

              	if (isKillableMob == false)
              	{
              		p.sendMessage(r + "Invalid mob, try again.");
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
        			p.sendMessage(b + "Killed all wolves that were close to you, not including tamed ones. /spawnmob kill twolf kills tamed wolves.");
        			if (args.length >= 3){
        			KillMobs.Kill(p, mt, args[2]);
        			return true;
        			}
        			KillMobs.Kill(p, mt);
                    return true;
        		}
        		if (args[1].equalsIgnoreCase("TWolf")){
        			p.sendMessage(b + "Killed all tamed wolves that were close to you.");
        			if (args.length >= 3){
            			KillMobs.Kill(p, "twolf", args[2]);
            			return true;
            			}
            			KillMobs.Kill(p, "twolf");
                    return true;
        		}
        		
        		if (args[1].equalsIgnoreCase("All")){
        			p.sendMessage(b + "Killed all mobs that were close to you, not including tamed wolves.");
        			if (args.length >= 3){
            			KillMobs.Kill(p, "all", args[2]);
            			return true;
            			}
            			KillMobs.Kill(p, "all");
                	return true;
                }
        		
        		p.sendMessage(b + "Killed all " + (endman ? "endermen" : snowman ? "snowmen" : args[1].toLowerCase()) + (chk ? "s." : " that were close to you."));
        		if (args.length >= 3){
        		KillMobs.Kill(p, mt, args[2]);
        		return true;
        		}
        		KillMobs.Kill(p, mt);
    			return true;
        		
        	}else 
        		
        		/*
        		 * BABY ANIMAL SPAWNING
        		 * BABY ANIMAL SPAWNING
        		 * BABY ANIMAL SPAWNING
        		 * BABY ANIMAL SPAWNING
        		 * BABY ANIMAL SPAWNING
        		 */
        		
        		if(args[0].equalsIgnoreCase("Baby")){
        		if(args.length == 1)
        		{
        			Help(p, command, 1);
        			return false;
        		}
        		rdr = args[1];
    			split = rdr.split(";");
    			rider = split[0];
    			
    			if(split.length == 2){
    				ridee = split[1];
       				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
       					if(PlayerHandler.isPlayerOnline(rider)){
       						
       					}else{
       						if(MobHandler.isArrMatch(animals, rider) == false){
           	        			p.sendMessage(r + "The mob must be a animal if used this way!");
           	        			return false;
           	        		}
       					}
       				}else{
       					if(MobHandler.isArrMatch(animals, rider) == false){
       	        			p.sendMessage(r + "The mob must be a animal if used this way!");
       	        			return false;
       	        		}
       				}
    			}else{
    				if(MobHandler.isArrMatch(animals, rider) == false){
            			p.sendMessage(r + "The mob must be a animal if used this way!");
            			return false;
            		}
    			}
        				//Custom tags
            			if((args.length >= 4 && MobHandler.isInt(args[2])) || (args.length >= 3 && !MobHandler.isInt(args[2]))){
            				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                        		p.sendMessage(r + "You don't have permission to do that.");
                                return false;
                            }
            				//With amount
            				if(args.length >= 4 && MobHandler.isInt(args[2])){
            					if(args[3].equalsIgnoreCase("Location") || args[3].equalsIgnoreCase("Loc")){
            						loc = LocationHandler.getLoc(sender, args[4], args[5], args[6]);
                        			coords = true;
            					}else if(args[3].equalsIgnoreCase("onPlayer") || args[3].equalsIgnoreCase("onP")){
            						p2 = PlayerHandler.getOnlinePlayer(args[4]);
                    				loc = LocationHandler.getLoc(sender, p2);
                        			onplayer = true;                			
            					}
            				}//End with amount
            				//Without amount
            				else if(MobHandler.isInt(args[2]) == false){
            					if(args[2].equalsIgnoreCase("Loc") || args[2].equalsIgnoreCase("Location")){
                        				loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                        				coords = true;                				
            					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
            						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                    				loc = LocationHandler.getLoc(sender, p2);
                        			onplayer = true;                			
            					}
                    		}//End without amount
            			}//End custom tags
                		    count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
                    		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                       		
                       		if(!(PermissionsHandler.playerhas(p, "spawnmob.baby." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                           		p.sendMessage(r + "You don't have permission to do that.");
                                   return false;
                               }

                       		for (int i = 0; i < count; i++)
                   			{
                       			if(split.length == 2){
                       				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                       					if(count > 1){
                       						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Player ride Mobs!");
                       					}
                       					if(PlayerHandler.isPlayerOnline(rider)){
                               				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                        					    if (mob2 == null) {
                        					    	p.sendMessage(r + "INVALID RIDING MOB");
                        					        return false;
                        					    }else{
                        					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a baby " + mob2.getName().toLowerCase());
                        							MobHandler.spawnbaby(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                        							return true;
                        					    }
                       					}else{
                       					 m = MobHandler.spawnbaby(mob, p, loc);
                       					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                       					 p.sendMessage(b + "You made a baby " + mob.getName().toLowerCase() + " ride " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase());
                       					 return true;
                       					}
                       				}else{
                       				m = MobHandler.spawnbaby(mob, p, loc);
                       				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                					    if (mob2 == null) {
                					    	p.sendMessage(r + "INVALID RIDING MOB");
                					        return false;
                					    }else{
                							MobHandler.spawn(mob2, p, loc).setPassenger(m);
                					    }
                       				}
                       			}else{
                       				m = MobHandler.spawnbaby(mob, p, loc);
                       			}
                   			}
                       		if(split.length == 2){
                       			if(count >1){
                       				p.sendMessage(b + "You spawned " + count + " " + mob.getName().toLowerCase()  + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + " riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s"));
                       				return true;
                       			}
                       			p.sendMessage(b + "You spawned a " + mob.getName().toLowerCase() + " riding " + mob2.getName().toLowerCase());
                       			return true;
                       		}
                       		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + " " + mob.getName().toLowerCase().toLowerCase() + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                				return true;
        			
        	}
			/*
			 * CUSTOM MOB SPAWNING
			 *  CUSTOM MOB SPAWNING
			 *   CUSTOM MOB SPAWNING
			 *    CUSTOM MOB SPAWNING
			 *     CUSTOM MOB SPAWNING
    		 */
        	else if(MobHandler.isArrMatch(customMobs, split[0])){
    			
        		//Creepers
        		if(split[0].equalsIgnoreCase("Creeper")){
        			boolean electric = false;
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Electric")){
                						electric = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 4){
                    				if(args[4].equalsIgnoreCase("Electric")){
                						electric = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("Electric")){
        						electric = true;
        					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Electric")){
                    						electric = true;
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Electric")){
                						electric = true;
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Electric")){
        						electric = true;
        					}
                		}//End without amount
        			}//End custom tags
        			
        			
        			/*
        			 * NORMAL MOB SPAWNING
        			 *  NORMAL MOB SPAWNING
        			 *   NORMAL MOB SPAWNING
        			 *    NORMAL MOB SPAWNING
        			 *     NORMAL MOB SPAWNING
        			 *      NORMAL MOB SPAWNING
            		 */
        			
        			
        			//Begin spawning
        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
                		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if (mob == null)
               			{
               				p.sendMessage(r + "INVALID MOB");
               				return false;
               			}
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a creeper");
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnCreeper(p, loc, electric);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a creeper ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnCreeper(p, loc, electric);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnCreeper(p, loc, electric);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (electric ? " electric " : "") +  " creepers  riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a " + (electric ? " electric " : "") + " creeper riding a " + mob2.getName().toLowerCase()+ (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (electric == true ? " electric" : "") + " creeper " + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;
            	}
        		/*
    			 * HORSES
    			 *  HORSES
    			 *   HORSES
    			 *    HORSES
    			 *     HORSES
    			 *      HORSES
        		 */
        		else if(split[0].equalsIgnoreCase("Horse")){
        			boolean tamed = false;
        			boolean variant = false;
        			boolean color = false;
        			boolean style = false;
        			HorseVariants var = HorseVariants.HORSE;
        			HorseStyles sty = HorseStyles.NONE;
        			HorseColors clr = HorseColors.BROWN;
        			
        			if(args.length >= 2 && args[1].equalsIgnoreCase("Help")){
        				Help(p, command, 6);
        				return true;
        			}
        			 if(args.length >= 2 && args[1].equalsIgnoreCase("List")){
  	                    	ArrayList<String> clrs = new ArrayList<String>(Arrays.asList(MobHandler.horseclrs));
  	                    	ArrayList<String> vars = new ArrayList<String>(Arrays.asList(MobHandler.horsevars));
  	                    	ArrayList<String> stys = new ArrayList<String>(Arrays.asList(MobHandler.horsestyles));
  	        		        p.sendMessage(b + "Horse Colors:");
  	        		        for (Iterator<String> iter2 = clrs.iterator(); iter2.hasNext();)
  	        		        {
  	        		        	p.sendMessage(b + " " + iter2.next() + " ");
  	        		        }
  	        		        p.sendMessage(b + "Horse Variations:");
  	        		        for (Iterator<String> iter2 = vars.iterator(); iter2.hasNext();)
 	        		        {
  	        		    	   p.sendMessage(b + " " + iter2.next() + " ");
 	        		        }
  	        		        p.sendMessage(b + "Horse Styles:");
  	        		        for (Iterator<String> iter2 = stys.iterator(); iter2.hasNext();)
	        		            {
  	        		        	p.sendMessage(b + " " + iter2.next() + " ");
	        		            }
  	        		        return true;
  					}
        			
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                    					if(args.length >= 8){
                    						//Begin colors,styles,variants
                    					if(args[7].equalsIgnoreCase("Style")){
                    						if(args.length == 8){
                    							p.sendMessage(r + "You forgot the Style.");
                                                return false;
                    						}
                    						sty = HorseStyles.fromName(args[8]);
                        					style = true;
                        					if(args.length >= 10){
                        						if(args[9].equalsIgnoreCase("Color")){
                        							if(args.length == 10){
                            							p.sendMessage(r + "You forgot the color.");
                                                        return false;
                            						}
                        							clr = HorseColors.fromName(args[10]);
                        							color = true;
                        						}
                        					}
                    					}else if(args[7].equalsIgnoreCase("Color")){
                    						if(args.length == 8){
                    							p.sendMessage(r + "You forgot the color.");
                                                return false;
                    						}
                    						clr = HorseColors.fromName(args[8]);
                        					color = true;
                    					}else if(args[7].equalsIgnoreCase("Variant")){
                    						if(args.length == 8){
                    							p.sendMessage(r + "You forgot the variant.");
                                                return false;
                    						}
                    						var = HorseVariants.fromName(args[8]);
                        					variant = true;
                    					}//End colors,styles,variants
                    					}
                					}
                    				//Begin colors,styles,variants
                    				else if(args[6].equalsIgnoreCase("Style")){
                    					if(args.length == 7){
                							p.sendMessage(r + "You forgot the style.");
                                            return false;
                						}
                    					sty = HorseStyles.fromName(args[7]);
                        					style = true;
                        					if(args.length >= 9){
                        						if(args[8].equalsIgnoreCase("Color")){
                        							if(args.length == 9){
                            							p.sendMessage(r + "You forgot the color.");
                                                        return false;
                            						}
                        							clr = HorseColors.fromName(args[9]);
                        							color = true;
                        						}
                        					}
                    					}else if(args[6].equalsIgnoreCase("Color")){
                    						if(args.length == 7){
                    							p.sendMessage(r + "You forgot the color.");
                                                return false;
                    						}
                    						clr = HorseColors.fromName(args[7]);
                        					color = true;
                    					}else if(args[6].equalsIgnoreCase("Variant")){
                    						if(args.length == 7){
                    							p.sendMessage(r + "You forgot the color.");
                                                return false;
                    						}
                    						var = HorseVariants.fromName(args[7]);
                        					variant = true;
                    					}//End colors,styles,variants
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                    					if(args.length >= 6){
                    						//Begin colors,styles,variants
                    						 if(args[5].equalsIgnoreCase("Style")){
                    							 if(args.length == 6){
                         							p.sendMessage(r + "You forgot the style.");
                                                     return false;
                         						}
                    							 //args[6]
                    							 sty = HorseStyles.fromName(args[6]);
                             					style = true;
                             					if(args.length >= 8){
                             						if(args[7].equalsIgnoreCase("Color")){
                             							if(args.length == 8){
                                							p.sendMessage(r + "You forgot the color.");
                                                            return false;
                                						}
                             							clr = HorseColors.fromName(args[8]);
                             							color = true;
                             						}
                             					}
                         					}else if(args[5].equalsIgnoreCase("Color")){
                         						if(args.length == 6){
                        							p.sendMessage(r + "You forgot the color.");
                                                    return false;
                        						}
                         						clr = HorseColors.fromName(args[6]);
                             					color = true;
                         					}else if(args[5].equalsIgnoreCase("Variant")){
                         						if(args.length == 6){
                        							p.sendMessage(r + "You forgot the variant.");
                                                    return false;
                        						}
                         						var = HorseVariants.fromName(args[5]);
                             					variant = true;
                         					}//End colors,styles,variants
                    					}
                					}//Begin colors,styles,variants
                    				else if(args[4].equalsIgnoreCase("Style")){
                    					if(args.length == 5){
                							p.sendMessage(r + "You forgot the style.");
                                            return false;
                						}
                    					//args[5]
                    					sty = HorseStyles.fromName(args[5]);
                     					style = true;
                     					if(args.length >= 7){
                     						if(args[6].equalsIgnoreCase("Color")){
                     							if(args.length == 7){
                        							p.sendMessage(r + "You forgot the color.");
                                                    return false;
                        						}
                     							clr = HorseColors.fromName(args[7]);
                     							color = true;
                     						}
                     					}
                 					}else if(args[4].equalsIgnoreCase("Color")){
                 						if(args.length == 5){
                							p.sendMessage(r + "You forgot the color.");
                                            return false;
                						}
                 						clr = HorseColors.fromName(args[5]);
                     					color = true;
                 					}else if(args[4].equalsIgnoreCase("Variant")){
                 						if(args.length == 5){
                							p.sendMessage(r + "You forgot the variant.");
                                            return false;
                						}
                 						var = HorseVariants.fromName(args[5]);
                     					variant = true;
                 					}//End colors,styles,variants
                    			}
        					}else if(args[2].equalsIgnoreCase("Tamed")){
        						tamed = true;
        						if(args.length >= 4){
            						//Begin colors,styles,variants
        							 if(args[3].equalsIgnoreCase("Style")){
        								 if(args.length == 4){
                 							p.sendMessage(r + "You forgot the style.");
                                             return false;
                 						}
        								 //args[4]
        								 sty = HorseStyles.fromName(args[4]);
                      					style = true;
                      					if(args.length >= 6){
                      						if(args[5].equalsIgnoreCase("Color")){
                      							if(args.length == 5){
                         							p.sendMessage(r + "You forgot the color.");
                                                     return false;
                         						}
                      							clr = HorseColors.fromName(args[6]);
                      							color = true;
                      						}
                      					}
                  					}else if(args[3].equalsIgnoreCase("Color")){
                  						if(args.length == 4){
                 							p.sendMessage(r + "You forgot the color.");
                                             return false;
                 						}
                  						clr = HorseColors.fromName(args[4]);
                      					color = true;
                  					}else if(args[3].equalsIgnoreCase("Variant")){
                  						if(args.length == 4){
                 							p.sendMessage(r + "You forgot the variant.");
                                             return false;
                 						}
                  						var = HorseVariants.fromName(args[4]);
                      					variant = true;
                  					}//End colors,styles,variants
            					}
        					}//Begin colors,styles,variants
            				else if(args[2].equalsIgnoreCase("Style")){
            					if(args.length == 3){
         							p.sendMessage(r + "You forgot the style.");
                                     return false;
         						}
            					//args[3]
            					sty = HorseStyles.fromName(args[3]);
             					style = true;
             					if(args.length >= 5){
             						if(args[4].equalsIgnoreCase("Color")){
             							if(args.length == 5){
                 							p.sendMessage(r + "You forgot the color.");
                                             return false;
                 						}
             							clr = HorseColors.fromName(args[5]);
             							color = true;
             						}
             					}
         					}else if(args[2].equalsIgnoreCase("Color")){
         						if(args.length == 3){
         							p.sendMessage(r + "You forgot the color.");
                                     return false;
         						}
         						clr = HorseColors.fromName(args[3]);
             					color = true;
         					}else if(args[2].equalsIgnoreCase("Variant")){
         						if(args.length == 3){
         							p.sendMessage(r + "You forgot the variant.");
                                     return false;
         						}
         						var = HorseVariants.fromName(args[3]);
             					variant = true;
         					}//End colors,styles,variants
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Tamed")){
                        					tamed = true;
                        					if(args.length >= 7){
                        						//Begin colors,styles,variants
                        					if(args[6].equalsIgnoreCase("Style")){
                        						if(args.length == 7){
                         							p.sendMessage(r + "You forgot the style.");
                                                     return false;
                         						}
                        						//args[7]
                        						sty = HorseStyles.fromName(args[7]);
                            					style = true;
                            					if(args.length >= 9){
                            						if(args[8].equalsIgnoreCase("Color")){
                            							if(args.length == 9){
                                 							p.sendMessage(r + "You forgot the color.");
                                                             return false;
                                 						}
                            							clr = HorseColors.fromName(args[9]);
                            							color = true;
                            						}
                            					}
                        					}else if(args[6].equalsIgnoreCase("Color")){
                        						if(args.length == 7){
                         							p.sendMessage(r + "You forgot the color.");
                                                     return false;
                         						}
                        						clr = HorseColors.fromName(args[7]);
                            					color = true;
                        					}else if(args[6].equalsIgnoreCase("Variant")){
                        						if(args.length == 7){
                         							p.sendMessage(r + "You forgot the variant.");
                                                     return false;
                         						}
                        						var = HorseVariants.fromName(args[7]);
                            					variant = true;
                        					}//End colors,styles,variants
                        					}
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 4){
                    				if(args[3].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                    					if(args.length >= 5){
                    						//Begin colors,styles,variants
                    					if(args[4].equalsIgnoreCase("Style")){
                    						if(args.length == 5){
                     							p.sendMessage(r + "You forgot the style.");
                                                 return false;
                     						}
                    						sty = HorseStyles.fromName(args[5]);
                        					style = true;
                        					if(args.length >= 7){
                        						if(args[6].equalsIgnoreCase("Color")){
                        							if(args.length == 7){
                             							p.sendMessage(r + "You forgot the color.");
                                                         return false;
                             						}
                        							clr = HorseColors.fromName(args[7]);
                        							color = true;
                        						}
                        					}
                    					}else if(args[4].equalsIgnoreCase("Color")){
                    						if(args.length == 5){
                     							p.sendMessage(r + "You forgot the color.");
                                                 return false;
                     						}
                    						clr = HorseColors.fromName(args[5]);
                        					color = true;
                    					}else if(args[4].equalsIgnoreCase("Variant")){
                    						if(args.length == 5){
                     							p.sendMessage(r + "You forgot the variant.");
                                                 return false;
                     						}
                    						var = HorseVariants.fromName(args[8]);
                        					variant = true;
                    					}//End colors,styles,variants
                    					}
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Tamed")){
        						tamed = true;
        						if(args.length >= 3){
            						//Begin colors,styles,variants
            					if(args[2].equalsIgnoreCase("Style")){
            						if(args.length == 3){
             							p.sendMessage(r + "You forgot the style.");
                                         return false;
             						}
            						sty = HorseStyles.fromName(args[3]);
                					style = true;
                					if(args.length >= 5){
                						if(args[4].equalsIgnoreCase("Color")){
                							if(args.length == 5){
                     							p.sendMessage(r + "You forgot the color.");
                                                 return false;
                     						}
                							clr = HorseColors.fromName(args[5]);
                							color = true;
                						}
                					}
            					}else if(args[2].equalsIgnoreCase("Color")){
            						if(args.length == 3){
             							p.sendMessage(r + "You forgot the color.");
                                         return false;
             						}
            						clr = HorseColors.fromName(args[3]);
                					color = true;
            					}else if(args[2].equalsIgnoreCase("Variant")){
            						if(args.length == 3){
             							p.sendMessage(r + "You forgot the variant.");
                                         return false;
             						}
            						var = HorseVariants.fromName(args[3]);
                					variant = true;
            					}//End colors,styles,variants
            					}
        					}//Begin colors,styles,variants
        					if(args[1].equalsIgnoreCase("Style")){
        						if(args.length == 2){
         							p.sendMessage(r + "You forgot the style.");
                                     return false;
         						}
        						sty = HorseStyles.fromName(args[2]);
            					style = true;
            					if(args.length >= 4){
            						if(args[3].equalsIgnoreCase("Color")){
            							if(args.length == 4){
                 							p.sendMessage(r + "You forgot the color.");
                                             return false;
                 						}
            							clr = HorseColors.fromName(args[4]);
            							color = true;
            						}
            					}
        					}else if(args[1].equalsIgnoreCase("Color")){
        						if(args.length == 2){
         							p.sendMessage(r + "You forgot the color.");
                                     return false;
         						}
        						clr = HorseColors.fromName(args[2]);
            					color = true;
        					}else if(args[1].equalsIgnoreCase("Variant")){
        						if(args.length == 2){
         							p.sendMessage(r + "You forgot the variant.");
                                     return false;
         						}
        						var = HorseVariants.fromName(args[2]);
            					variant = true;
        					}//End colors,styles,variants
        					else if(args[2].equalsIgnoreCase("List")){
         						if(args.length == 1)
         	                	{
         	                    	ArrayList<String> clrs = new ArrayList<String>(Arrays.asList(MobHandler.horseclrs));
         	                    	ArrayList<String> vars = new ArrayList<String>(Arrays.asList(MobHandler.horsevars));
         	                    	ArrayList<String> stys = new ArrayList<String>(Arrays.asList(MobHandler.horsestyles));
         	        		        p.sendMessage(b + "Horse Colors:");
         	        		        for (Iterator<String> iter2 = clrs.iterator(); iter2.hasNext();)
         	        		        {
         	        		        	p.sendMessage(b + " " + iter2.next() + " ");
         	        		        }
         	        		        p.sendMessage(b + "Horse Variations:");
         	        		        for (Iterator<String> iter2 = vars.iterator(); iter2.hasNext();)
        	        		        {
         	        		    	   p.sendMessage(b + " " + iter2.next() + " ");
        	        		        }
         	        		        p.sendMessage(b + "Horse Styles:");
         	        		        for (Iterator<String> iter2 = stys.iterator(); iter2.hasNext();)
       	        		            {
         	        		        	p.sendMessage(b + " " + iter2.next() + " ");
       	        		            }
         	        		        return true;
         	                	}
         					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning
        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
                		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }
                   		if(tamed == true){
                   		p.sendMessage("SpawnMob is not able to spawn tamed horses yet, sorry for the inconvinience.");
                   		}
                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a horse");
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnHorse(clr, var, sty, tamed, color, variant, style, p, loc);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a horse ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnHorse(clr, var, sty, tamed, color, variant, style, p, loc);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnHorse(clr, var, sty, tamed, color, variant, style, p, loc);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (tamed == true ? " tamed" : "") +  (color == true ? " " + clr.getName().toLowerCase() + " " : "") + (style == true ? " " + sty.getName().toLowerCase() + " " : "") + (variant == true ? " " + var.getName().toLowerCase() : " horses ") + " riding " + count + " " + mob2.getName().toLowerCase() + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                       			return true;
                   			}
                   			p.sendMessage(b + "You spawned a " + (tamed == true ? " tamed" : "") +  (color == true ? " " + clr.getName().toLowerCase() + " " : "") + (style == true ? " " + sty.getName().toLowerCase() + " " : "") + (variant == true ? " " + var.getName().toLowerCase() : " horse") + " riding a " + mob2.getName().toLowerCase()+ (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (tamed == true ? " tamed" : "") + (color == true ? " " + clr.getName().toLowerCase() + " " : "") + (style == true ? " " + sty.getName().toLowerCase() + " " : "") + (variant == true ? " " + var.getName().toLowerCase() : " horse") + (count == 1 ? " " : "s ") + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;
            	}
        		//MagmaCubes
        		else if(split[0].equalsIgnoreCase("Magma_Cube") || split[0].equalsIgnoreCase("MagmaCube")){
        			boolean size = false;
        			String sz = "";
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Size")){
                    					if(args.length == 7){
                 							p.sendMessage(r + "You forgot the size.");
                                             return false;
                 						}
                    					sz = args[7];
                    					size = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Size")){
                    					if(args.length == 5){
                 							p.sendMessage(r + "You forgot the size.");
                                             return false;
                 						}
                    					size = true;
                    					sz = args[5];
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("Size")){
        						if(args.length == 3){
         							p.sendMessage(r + "You forgot the size.");
                                     return false;
         						}
        						sz = args[3];
        						size = true;
        					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Size")){
                        					if(args.length == 6){
                     							p.sendMessage(r + "You forgot the size.");
                                                 return false;
                     						}
                        					size = true;
                        					sz = args[6];
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Size")){
                    					if(args.length == 5){
                 							p.sendMessage(r + "You forgot the size.");
                                             return false;
                 						}
                    					size = true;
                    					sz = args[5];
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Size")){
        						if(args.length == 2){
         							p.sendMessage(r + "You forgot the size.");
                                     return false;
         						}
        						size = true;
        						sz = args[2];
        					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning

        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
                		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a " + mob2.getName().toLowerCase());
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnSlime(mob, p, loc, sz, size);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a slime ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnSlime(mob, p, loc, sz, size);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnSlime(mob, p, loc, sz, size);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (size ? sz + " sized " : "") +  " magmacubes  riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a " + (size ? sz + " sized " : "") +  " magmacube riding a " + mob2.getName().toLowerCase()+ (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (size ? sz + " sized" : "") + " magmacube " + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   		
            				return true;
            	}
        		//Ocelots
        		else if(split[0].equalsIgnoreCase("Ocelot") || split[0].equalsIgnoreCase("Cat")){
        			boolean tamed = false;
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("Tamed")){
        						tamed = true;
        					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Tamed")){
                        					tamed = true;
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Tamed")){
        						tamed = true;
        					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning
        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
                		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a " + mob2.getName().toLowerCase());
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnCat(mob, p, loc, tamed);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a ocelot ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnCat(mob, p, loc, tamed);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m =  MobHandler.spawnCat(mob, p, loc, tamed);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (tamed ? " tamed " : "") +  " ocelots  riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a " + (tamed ? " tamed " : "") +  " ocelot riding a " + mob2.getName().toLowerCase()+ (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (tamed == true ? " tamed" : "") + (count == 0 ? " ocelot " : "ocelots") + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;	
            	}
        		//Sheep
        		else if(split[0].equalsIgnoreCase("Sheep")){
        			boolean color = false;
        			SheepColors clr = SheepColors.WHITE;
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Color")){
                    					if(args.length == 7){
                							p.sendMessage(r + "You forgot the color.");
                                            return false;
                						}
                    					clr = SheepColors.fromName(args[7]);
                						color = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Color")){
                    					if(args.length == 5){
                							p.sendMessage(r + "You forgot the color.");
                                            return false;
                						}
                    					color = true;
                    					clr = SheepColors.fromName(args[5]);
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("Color")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the color.");
                                    return false;
        						}
        						clr = SheepColors.fromName(args[3]);
        						color = true;
        					}else if(args[2].equalsIgnoreCase("List")){
         						if(args.length == 1)
         	                	{
         	                    	ArrayList<String> clrs = new ArrayList<String>(Arrays.asList(MobHandler.sheepclrs));
         	        		        p.sendMessage(b + "Sheep Colors:");
         	        		        for (Iterator<String> iter2 = clrs.iterator(); iter2.hasNext();)
         	        		        {
         	        		        	p.sendMessage(b + " " + iter2.next() + " ");
         	        		        }
         	        		        return true;
         	                	}
         					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Color")){
                        					if(args.length == 6){
                    							p.sendMessage(r + "You forgot the color.");
                                                return false;
                    						}
                        					color = true;
                        					clr = SheepColors.fromName(args[6]);
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Color")){
                    					if(args.length == 5){
                							p.sendMessage(r + "You forgot the color.");
                                            return false;
                						}
                    					color = true;
                    					clr = SheepColors.fromName(args[5]);
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Color")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the color.");
                                    return false;
        						}
        						color = true;
        						clr = SheepColors.fromName(args[2]);
        					}else if(args[1].equalsIgnoreCase("List")){
         						if(args.length == 1)
         	                	{
         	                    	ArrayList<String> clrs = new ArrayList<String>(Arrays.asList(MobHandler.sheepclrs));
         	        		        p.sendMessage(b + "Sheep Colors:");
         	        		        for (Iterator<String> iter2 = clrs.iterator(); iter2.hasNext();)
         	        		        {
         	        		        	p.sendMessage(b + " " + iter2.next() + " ");
         	        		        }
         	        		        return true;
         	                	}
         					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning

        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
                		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a sheep");
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnSheep(clr, p, loc, color);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a sheep ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnSheep(clr, p, loc, color);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnSheep(clr, p, loc, color);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (color ? clr.getName().toLowerCase() : "") +  " wolves "  + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + " riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s"));
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a" + (color ? clr.getName().toLowerCase() : "") + " wolf riding " + mob2.getName().toLowerCase());
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (color == true ? clr.getName().toLowerCase() : "") + " sheep " + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;
            	}
        		//Slimes
        		else if(split[0].equalsIgnoreCase("Slime")){
        			boolean size = false;
        			String sz = "";
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Size")){
                    					if(args.length == 7){
                							p.sendMessage(r + "You forgot the size.");
                                            return false;
                						}
                    					sz = args[7];
                    					size = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Size")){
                    					if(args.length == 3){
                							p.sendMessage(r + "You forgot the size.");
                                            return false;
                						}
                    					size = true;
                    					sz = args[5];
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("Size")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the size.");
                                    return false;
        						}
        						sz = args[3];
        						size = true;
        					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Size")){
                        					if(args.length == 6){
                    							p.sendMessage(r + "You forgot the Size.");
                                                return false;
                    						}
                        					size = true;
                        					sz = args[6];
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Size")){
                    					if(args.length == 5){
                							p.sendMessage(r + "You forgot the size.");
                                            return false;
                						}
                    					size = true;
                    					sz = args[5];
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Size")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the size.");
                                    return false;
        						}
        						size = true;
        						sz = args[2];
        					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning

        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
        				mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a slime");
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnSlime(mob, p, loc, sz, size);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a slime ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnSlime(mob, p, loc, sz, size);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnSlime(mob, p, loc, sz, size);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (size ? sz + " sized" : "") +  " slimes "  + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + " riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s"));
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a" + (size ? sz + " sized" : "") + " slime riding " + mob2.getName().toLowerCase());
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (size ? sz + " sized" : "") + " slime " + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;
            	}
        		//Villagers
        		else if(split[0].equalsIgnoreCase("Villager") || split[0].equalsIgnoreCase("NPC")){
        			boolean prof = false;
        			Professions pro = Professions.FARMER;
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Pro")){
                    					if(args.length == 7){
                							p.sendMessage(r + "You forgot the profession.");
                                            return false;
                						}
                    					pro = Professions.fromName(args[7]);
                    					prof = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Pro")){
                    					if(args.length == 5){
                							p.sendMessage(r + "You forgot the profession.");
                                            return false;
                						}
                    					prof = true;
                    					pro = Professions.fromName(args[5]);
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("Pro")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the profession.");
                                    return false;
        						}
        						pro = Professions.fromName(args[3]);
        						prof = true;
        					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Pro")){
                        					if(args.length == 6){
                    							p.sendMessage(r + "You forgot the profession.");
                                                return false;
                    						}
                        					prof = true;
                        					pro = Professions.fromName(args[6]);
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Pro")){
                    					if(args.length == 5){
                							p.sendMessage(r + "You forgot the profession.");
                                            return false;
                						}
                    					prof = true;
                    					pro = Professions.fromName(args[5]);
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Pro")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the profession.");
                                    return false;
        						}
        						prof = true;
        						pro = Professions.fromName(args[2]);
        					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning

        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
                		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a " + mob2.getName().toLowerCase());
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnVillager(pro, p, loc, prof);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a villager ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnVillager(pro, p, loc, prof);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnVillager(pro, p, loc, prof);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (prof ? pro.getName().toLowerCase() : " villager ") + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + " riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s"));
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a" + (prof ? pro.getName().toLowerCase() : " villager ") + " riding " + mob2.getName().toLowerCase());
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (prof == true ? " " + pro.getName().toLowerCase() : " villager") + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;
            		
            	}
        		//Wolves
        		else if(split[0].equalsIgnoreCase("Wolf")){
        			boolean tamed = false;
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			if(args.length >= 7){
                    				if(args[6].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 5){
                    				if(args[4].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                					}
                    			}
        					}else if(args[2].equalsIgnoreCase("Tamed")){
        						tamed = true;
        					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				if(args.length >= 6){
                        				if(args[5].equalsIgnoreCase("Tamed")){
                        					tamed = true;
                    					}
                        			}
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			if(args.length >= 4){
                    				if(args[3].equalsIgnoreCase("Tamed")){
                    					tamed = true;
                					}
                    			}
        					}else if(args[1].equalsIgnoreCase("Tamed")){
        						tamed = true;
        					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning
        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
        				
                		mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                           				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                    					    if (mob2 == null) {
                    					    	p.sendMessage(r + "INVALID RIDING MOB");
                    					        return false;
                    					    }else{
                    					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a wolf");
                    							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                    					    }
                   					}else{
                   					 m = MobHandler.spawnWolf(mob, p, loc, tamed);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a wolf ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnWolf(mob, p, loc, tamed);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnWolf(mob, p, loc, tamed);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + (tamed ? "tamed" : "") +  " wolves "  + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + " riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s"));
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a" + (tamed ? "tamed" : "") + " wolf riding " + mob2.getName().toLowerCase());
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (tamed == true ? " tamed" : "") + (count == 1 ? " wolf " : " wolves ") + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;
            	}else if(split[0].equalsIgnoreCase("WitherSkeleton")){
        			//Custom tags
        			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
        				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                    		p.sendMessage(r + "You don't have permission to do that.");
                            return false;
                        }
        				//With amount
        				if(args.length >= 3 && MobHandler.isInt(args[1])){
        					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
        						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                    			coords = true;
                    			
        					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
        						if(args.length == 3){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[3]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			
        					}
        				}//End with amount
        				//Without amount
        				else if(MobHandler.isInt(args[1]) == false){
        					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the coordinates.");
                                    return false;
        						}
                    				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                    				coords = true;
                    				
        					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
        						if(args.length == 2){
        							p.sendMessage(r + "You forgot the player's name.");
                                    return false;
        						}
        						p2 = PlayerHandler.getOnlinePlayer(args[2]);
                				loc = LocationHandler.getLoc(sender, p2);
                    			onplayer = true;
                    			
        					}
                		}//End without amount
        			}//End custom tags
        			//Begin spawning
        				count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
        				
 
                   		if(!(PermissionsHandler.playerhas(p, "spawnmob.witherskeleton", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                       		p.sendMessage(r + "You don't have permission to do that.");
                               return false;
                           }

                   		for (int i = 0; i < count; i++)
               			{
                   			if(split.length == 2){
                   				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
                   					if(count > 1){
                   						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
                   					}
                   					if(PlayerHandler.isPlayerOnline(rider)){
                   						p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName() + " ride a wither skeleton");
                   						m = MobHandler.spawnWitherSkel(p, loc);
                   						m.setPassenger(PlayerHandler.getOnlinePlayer(rider));
                    							return true;
                   					}else{
                   					 m = MobHandler.spawnWitherSkel(p, loc);
                   					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
                   					 p.sendMessage(b + "You made a witherskeleton ride " + PlayerHandler.getOnlinePlayer(ridee).getName());
                   					 return true;
                   					}
                   				}else{
                   				m = MobHandler.spawnWitherSkel(p, loc);
                   				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
            					    if (mob2 == null) {
            					    	p.sendMessage(r + "INVALID RIDING MOB");
            					        return false;
            					    }else{
            							MobHandler.spawn(mob2, p, loc).setPassenger(m);
            					    }
                   				}
                   			}else{
                   				m = MobHandler.spawnWitherSkel(p, loc);
                   			}
               			}
                   		if(split.length == 2){
                   			if(count >1){
                   				p.sendMessage(b + "You spawned " + count + " wither skeletons "  + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + " riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s"));
                   				return true;
                   			}
                   			p.sendMessage(b + "You spawned a wither skeleton riding " + mob2.getName().toLowerCase());
                   			return true;
                   		}
                   		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + (count == 1 ? " wither skeleton " : " wither skeletons ") + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (split.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") +  (coords == true ? " onto custom coordinates" : "") + "!");
            				return true;
            	}
        	}//End of custom mobs
			/*
			 * 
			 * 
			 * Normal mobs
			 * 
			 */
			//Start of normal mobs
			else{
				rdr = args[0];
    			split = rdr.split(";");
				//Custom tags
    			if((args.length >= 3 && MobHandler.isInt(args[1])) || (args.length >= 2 && !MobHandler.isInt(args[1]))){
    				if(!(PermissionsHandler.playerhas(p, "spawnmob.other", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                		p.sendMessage(r + "You don't have permission to do that.");
                        return false;
                    }
    				//With amount
    				if(args.length >= 3 && MobHandler.isInt(args[1])){
    					if(args[2].equalsIgnoreCase("Location") || args[2].equalsIgnoreCase("Loc")){
    						if(args.length == 3){
    							p.sendMessage(r + "You forgot the coordinates.");
                                return false;
    						}
    						loc = LocationHandler.getLoc(sender, args[3], args[4], args[5]);
                			coords = true;
    					}else if(args[2].equalsIgnoreCase("onPlayer") || args[2].equalsIgnoreCase("onP")){
    						if(args.length == 3){
    							p.sendMessage(r + "You forgot the player's name.");
                                return false;
    						}
    						p2 = PlayerHandler.getOnlinePlayer(args[3]);
            				loc = LocationHandler.getLoc(sender, p2);
                			onplayer = true;                			
    					}
    				}//End with amount
    				//Without amount
    				else if(MobHandler.isInt(args[1]) == false){
    					if(args[1].equalsIgnoreCase("Loc") || args[1].equalsIgnoreCase("Location")){
    						if(args.length == 2){
    							p.sendMessage(r + "You forgot the coordinates.");
                                return false;
    						}
                				loc = LocationHandler.getLoc(sender, args[2], args[3], args[4]);
                				coords = true;                				
    					}else if(args[1].equalsIgnoreCase("onPlayer") || args[1].equalsIgnoreCase("onP")){
    						if(args.length == 2){
    							p.sendMessage(r + "You forgot the player's name.");
                                return false;
    						}
    						p2 = PlayerHandler.getOnlinePlayer(args[2]);
            				loc = LocationHandler.getLoc(sender, p2);
                			onplayer = true;                			
    					}
            		}//End without amount
    			}//End custom tags
        		    count = args.length >= 2 ? MobHandler.convertStringtoInt(args[1]) : 1;
        		    if(PlayerHandler.isPlayerOnline(rider)){
        		    }
        		    else{
                    mob = Mob.fromName(rider.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(rider));
               		if (mob == null)
           			{
           				p.sendMessage(r + "INVALID MOB");
           				return false;
           			}
        		    }
               		if(!(PermissionsHandler.playerhas(p, "spawnmob." + mob.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                   		p.sendMessage(r + "You don't have permission to do that.");
                           return false;
                       }

               		for (int i = 0; i < count; i++)
           			{
               			
               			if(split.length == 2){
               				if(PlayerHandler.isPlayerOnline(ridee) || PlayerHandler.isPlayerOnline(rider)){
               					if(count > 1){
               						p.sendMessage(r + "You can't put a amount while making Mobs ride Players or Players ride Mobs!");
               					}
               					if(PlayerHandler.isPlayerOnline(rider)){
                       				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
                					    if (mob2 == null) {
                					    	p.sendMessage(r + "INVALID RIDING MOB");
                					        return false;
                					    }else{
                					    	p.sendMessage(b + "You made " + PlayerHandler.getOnlinePlayer(rider).getName().toLowerCase() + " ride a " + mob2.getName().toLowerCase());
                							MobHandler.spawn(mob2, p, loc).setPassenger(PlayerHandler.getOnlinePlayer(rider));
                							return true;
                					    }
               					}else{
               						
               					 m = MobHandler.spawn(mob, p, loc);
               					 PlayerHandler.getOnlinePlayer(ridee).setPassenger(m);
               					 p.sendMessage(b + "You made a " + mob.getName().toLowerCase() + " ride " + PlayerHandler.getOnlinePlayer(ridee).getName().toLowerCase());
               					 return true;
               					}
               				}else{
               				m = MobHandler.spawn(mob, p, loc);
               				mob2 = Mob.fromName(ridee.equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(ridee));
        					    if (mob2 == null) {
        					    	p.sendMessage(r + "INVALID RIDING MOB");
        					        return false;
        					    }else{
        							MobHandler.spawn(mob2, p, loc).setPassenger(m);
        					    }
               				}
               			}else{
               				m = MobHandler.spawn(mob, p, loc);
               			}
           			}
               		if(split.length == 2){
               			if(count >1){
               				p.sendMessage(b + "You spawned " + count + " " + mob.getName().toLowerCase()  + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + " riding " + count + " " + mob2.getName().toLowerCase()  + (count == 1 || mob2.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob2.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob2.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s"));
               				return true;
               			}
               			p.sendMessage(b + "You spawned a " + mob.getName().toLowerCase() + " riding " + mob2.getName().toLowerCase());
               			return true;
               		}
               		p.sendMessage(b + "You spawned " + (count == 1 ? "a" : count) + " " + mob.getName().toLowerCase().toLowerCase() + (count == 1 || mob.getName().toLowerCase().equalsIgnoreCase("Ender_Man") || mob.getName().toLowerCase().equalsIgnoreCase("SilverFish")|| mob.getName().toLowerCase().equalsIgnoreCase("Squid")  ? "" : "s") + (onplayer == true ? " onto player " + p2.getName().toLowerCase() : "") + (coords == true ? " onto custom coordinates" : "") + "!");
        				return true;
			
        	}//End of normal mobs
	    }
		else if (command.getName().toLowerCase().equalsIgnoreCase("MobSpawn")|| command.getName().toLowerCase().equalsIgnoreCase("MSpawn") || command.getName().toLowerCase().equalsIgnoreCase("MS"))
	    {
			int[] ignore = {8, 9};
			Location loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
			loc.setY(1 + loc.getY());
        	if (args.length == 0)
            {
                Help(p, command, 4);
                return false;
            }
            org.bukkit.block.Block blk = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock();
            if (blk == null || blk.getType() != Material.MOB_SPAWNER )
            {
            	p.sendMessage(r + "You must be looking at a Mob Spawner.");
            	return false;
            }
            BlockState blockState = ((CreatureSpawner) blk.getState());
            CreatureSpawner spawner = ((CreatureSpawner) blk.getState());
            if (args[0].equalsIgnoreCase("Check") || args[0].equalsIgnoreCase("Info"))
            {
            	if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn.check", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
            		p.sendMessage(r + "You don't have permission to do that.");
                    return false;
                }
            	
            	p.sendMessage(b + "This spawner's mob type is " + spawner.getCreatureTypeName().toString() + ".");
            	p.sendMessage(b + "This spawners delay is set to " + spawner.getDelay() + ".");
            }
            else if (args[0].equalsIgnoreCase("Delay"))
            {
            	if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn.delay", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
            		p.sendMessage(r + "You don't have permission to do that.");
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
            		p.sendMessage(r + "You must enter a number.");
            		return false;
           		}
            	
            	spawner.setDelay(delay);
            	blockState.update();
            	p.sendMessage(b + "This spawner's delay is now set to " + delay + ".");
            }
            else
            {
            	Mob mt = null;
            	
            	mt = Mob.fromName(args[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(args[0]));
            	
                if (mt == null)
                {
                	p.sendMessage(r + "Creature type not found: " + args[0]);
                	return false;
                }
                
                if(!(PermissionsHandler.playerhas(p, "spawnmob.mspawn." + mt.toString().toLowerCase(), Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.*", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.mspawn.allmobs", Main.ops) == true || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops) == true)){
                	p.sendMessage(r + "You don't have permission to do that.");
                    return false;
                }
                
                spawner.setCreatureTypeByName(mt.getName().toUpperCase());
                blockState.update();
                p.sendMessage(b + "Mob spawner set as " + args[0] + ".");
            }
            return true;
	    }
		return false;
	}
	
	public static void Help(Player p, Command command, int h){
    	
    	if(h == 1){
    		p.sendMessage(b + "- SpawnMob Help -");
        	p.sendMessage(b + "<> - Mandatory");
        	p.sendMessage(b + "() - Optional");
    	    p.sendMessage(b + "Command shortcuts: /spawnmob, /smob, /sm");
    	    p.sendMessage(b + "FOR MORE IN-DEPTH INSTRUCTION OF HOW TO USE THE COMMANDS GO TO http://goo.gl/rit3mY");
    	    p.sendMessage(b + "/" + command.getName().toLowerCase() + " <mob> (amount) - Spawn mobs.");
    	    p.sendMessage(b + "/" + command.getName().toLowerCase() + " <mob> (amount) <onp> <PlayerName> - Spawn mobs on a player.");
    	    p.sendMessage(b + "/" + command.getName().toLowerCase() + " <mob> (amount) <location> <coordinates> - Spawn mobs on specified coordinates.");
    	    p.sendMessage(b + "/" + command.getName().toLowerCase() + " wolf (amount) (location or onp) (coordinates or PlayerName) (tamed) - spawn a wolf, if tamed is put in the wolf will be tamed if tamed and onp is put in the wolf will be tamed to the name put in PlayerName");
    	    p.sendMessage(b + "/" + command.getName().toLowerCase() + " ocelot(or cat) (amount) (location or onp) (coordinates or PlayerName) (tamed) - spawn a ocelot, if tamed is put in the ocelot will be tamed if tamed and onp is put in the ocelot will be tamed to the name put in PlayerName");
    	    p.sendMessage(b + "/" + command.getName().toLowerCase() + " help horse - Type for information about horse spawning");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " mob;mob - Spawn a mob riding a mob.");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " player;mob - Make a player ride a mob.");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " mob;player - Make a mob ride a player.");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " player;player - Make a player ride a player.");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " kill - Type for more info");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " kit - Type for more info");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " addkit - Type for more info");
            p.sendMessage(b + "/mspawn - Type for more info");
            return;
    	}
    	if(h == 2){
    		p.sendMessage(b + "- SpawnMob KILL Help -");
        	p.sendMessage(b + "<> - Mandatory");
        	p.sendMessage(b + "() - Optional");
		    p.sendMessage(b + "/" + command.getName().toLowerCase() + " kill <all-animals-monsters-mobname-twolf> (radius)  - Kill all mobs within a radius. Default is 50.");
  		    p.sendMessage(b + "For a list of mobnames use /" + command.getName().toLowerCase() + " list");
    		return;
    	}
    	if(h == 3){
    		p.sendMessage(b + "- SpawnMob KIT Help -");
        	p.sendMessage(b + "<> - Mandatory");
        	p.sendMessage(b + "() - Optional");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " kit list - List the available mob kits");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " kit <kitname> - Spawn a mob kit");
    		return;
    	}
    	if(h == 4){
    		p.sendMessage(b + "- SpawnMob MOBSPAWNER Help -");
        	p.sendMessage(b + "<> - Mandatory");
        	p.sendMessage(b + "() - Optional");
    		p.sendMessage(b + "Command shortcuts: /mobspawn, /mspawn, /ms");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " <mob name> - Set a mob spawner to spawn a mob.");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " check - See a spawner's info.");
            p.sendMessage(b + "/" + command.getName().toLowerCase() + " delay <number> - Sets a mob spawner's spawn delay.");
            p.sendMessage(b + "For a list of mobnames use /spawnmob list");
    		return;
    	}
    	if(h == 5){
    		p.sendMessage(b + "- SpawnMob MOBSPAWN DELAY Help -");
        	p.sendMessage(b + "<> - Mandatory");
        	p.sendMessage(b + "() - Optional");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " delay <number> - Sets a mob spawner's spawn delay.");
    		return;
    	}
    	if(h == 6){
    		p.sendMessage(b + "- SpawnMob HORSE Help -");
        	p.sendMessage(b + "<> - Mandatory");
        	p.sendMessage(b + "() - Optional");
        	p.sendMessage(b + "Some of these things will also work with wolves and ocelots. (tamed)");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) <onp> - Spawn a horse on anon player.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) <onp> <tamed> - Spawn a tamed horse on anon player.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) <loc> <x, y, z,> - Spawn a horse on specific coordinates.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) <loc> <x, y, z,> <tamed> - Spawn a tamed horse on specific coordinates.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) (onp or loc) (playername or x, y, z) <variant> - Spawn a specific type of horse.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) (onp or loc) (playername or x, y, z) <color> - Spawn a specific color of horse.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) (onp or loc) (playername or x, y, z) <style> - Spawn a specific type of horse.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " horse (amount) (onp or loc) (playername or x, y, z) <style> <color> - Spawn a specific style and color of horse. ");
    		p.sendMessage(b + "For a list of styles, colors, and variants type /" + command.getName().toLowerCase() + " horse list");
    		return;
    	}
    	if(h == 7){
    		p.sendMessage(b + "- SpawnMob ADDKIT Help -");
        	p.sendMessage(b + "<> - Mandatory");
        	p.sendMessage(b + "() - Optional");
        	p.sendMessage(b + "THIS IS NOT WORKING YET, WILL BE WORKING NEXT RELEASE.");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " addkit <kitname> <mobname> - Create a kit.");
    		p.sendMessage(b + "To create a kit with multiple mobs in it add another mobname like the following:");
    		p.sendMessage(b + "/" + command.getName().toLowerCase() + " addkit test cow,pig,horse,wolf,zombie,giant");
    		p.sendMessage(b + "You can add as many mobs as you like as long as you don't add spaces.");
    		p.sendMessage(b + "The kitnames and mobnames will be saved in lower case to prevent errors, so capitalization doesn't matter.");
    		return;
    	}
        return;
    }

	public static String capitalCase(String s) {
        return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }
}
