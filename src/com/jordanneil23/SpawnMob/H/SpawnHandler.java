package com.jordanneil23.SpawnMob.H;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.jordanneil23.SpawnMob.Main;
import com.jordanneil23.SpawnMob.M.Mob;
import com.jordanneil23.SpawnMob.M.Professions;

public class SpawnHandler {
	public static int count = 1;
	static LivingEntity m = null;
	static Mob mob2 = Mob.COW;
	static boolean other = false;
    Professions pro = Professions.FARMER;
    boolean chk = false;
    boolean endman = false;
    boolean snowman = false;
	
	public static void customMob(int count, String[] args ){
		/**
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
			
			for (int i = 1; i < count; i++)
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
		**/
	}
	
    public static void babyMob(){
    	/** BABIES
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
		 **/
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
