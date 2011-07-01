package com.jordanneil23.SpawnMob;

import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import com.jordanneil23.SpawnMob.Mob.MobException;
import com.jordanneil23.SpawnMob.TargetBlock;

import net.minecraft.server.EntitySlime;

/**
 *SpawnMob - Commands
 * @author jordanneil23
 */
public class CommandHandler{
    private SpawnMob plugin;
    public void CommandListener(SpawnMob instance) {
        plugin = instance;
    } 
    public boolean perform(CommandSender sender, Command command, String[] args) {
		int spawnlimit = convertStringtoInt(plugin.spawnlimit);
    	int[] ignore = {8, 9};
        Player p = (Player) sender;
        Location loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
        loc.setY(1 + loc.getY());
        if (command.getName().equalsIgnoreCase("spawnmob") || command.getName().toLowerCase().equalsIgnoreCase("sm") || command.getName().toLowerCase().equalsIgnoreCase("smob")) {
        	if (args.length > 0) {
            	if (!plugin.permissions){
            		if (!p.isOp()){
            			p.sendMessage(ChatColor.RED + "You can't do that.");
            			return false;
            		}
            	}
            	if (args[0].equalsIgnoreCase("TamedWolf") || args[0].equalsIgnoreCase("TWolf")){
                    	if (plugin.permissions){
                    	if (!SpawnMob.Permissions.has(p, "spawnmob.wolf.tamed")){
                			p.sendMessage(ChatColor.RED + "You can't do that.");
                			return false;
                		}  
                    	}
                    	if (args.length < 2){
                       	 World world = p.getWorld();
           				 Wolf w = (Wolf) world.spawnCreature(loc, CreatureType.WOLF);
           				 w.setOwner(p);
           				 w.setSitting(false);
           				 w.setAngry(false);
           				 w.setHealth(10);
           				 p.sendMessage(ChatColor.BLUE + "You now have a tamed wolf!");
           				 return true;
                         }
                		int check = convertStringtoInt(args[1]);
                    	if (check > spawnlimit){
                			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was over the set limit!");
                			return false;
                		}
                           	try {
                                   for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                                     World world = p.getWorld();
                                 	 Wolf w = (Wolf) world.spawnCreature(loc, CreatureType.WOLF);
                                 	 w.setOwner(p);
                      				 w.setSitting(false);
                      				 w.setAngry(false);
                      				 w.setHealth(10);
                                   }
                                   if (check == 1){
                                   	p.sendMessage(ChatColor.BLUE + "You now have a tamed wolf!");
                                   	return true;
                                   }
                               	p.sendMessage(ChatColor.BLUE + "You now have " + check + " tamed wolves!");
                               	return true;
                               } catch (java.lang.NumberFormatException e2) {
                                   p.sendMessage(ChatColor.RED + "Malformed integer.");
                                   return false;
                               }
                		}
            	
            	if (args[0].equalsIgnoreCase("ElectricCreeper") || args[0].equalsIgnoreCase("ECreeper")){
                	if (plugin.permissions){
                	if (!SpawnMob.Permissions.has(p, "spawnmob.creeper.electrocuted")){
            			p.sendMessage(ChatColor.RED + "You can't do that.");
            			return false;
            		}  
                	}
                	if (args.length < 2){
                		World world = p.getWorld();
   					 Creeper c = (Creeper) world.spawnCreature(loc, CreatureType.CREEPER);
   					 c.setPowered(true);
   					 p.sendMessage(ChatColor.BLUE + "You spawned a electrocuted creeper!");
   					 return true;  
                	}
            		int check = convertStringtoInt(args[1]);
                	if (check > spawnlimit){
            			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was over the set limit!");
            			return false;
            		}
                	try {
                        for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                        	World world = p.getWorld();
                            Creeper c = (Creeper) world.spawnCreature(loc, CreatureType.CREEPER);
                            c.setPowered(true);
                        }
                        if (check == 1){
                        	p.sendMessage(ChatColor.BLUE + "You spawned a electrocuted creeper!");
                        	return true;
                        }
                    	p.sendMessage(ChatColor.BLUE + "You spawned " + check + " electrocuted creepers!");
                    	return true;
                    } catch (java.lang.NumberFormatException e2) {
                        p.sendMessage(ChatColor.RED + "Malformed integer.");
                        return false;
                    }
            	}
            	
                if (args[0].equalsIgnoreCase("Kill")) {
                	if (args.length == 0 || args.length == 1){
                		p.sendMessage(ChatColor.BLUE + "/spawnmob kill <all-animals-monsters-mobname>");
                		return false;
                	}
                	
                	Mob mob3 = Mob.fromName(args[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(args[1]));
                    if (plugin.permissions){
                	if (!SpawnMob.Permissions.has(p, "spawnmob.kill")){
                        p.sendMessage(ChatColor.RED + "You are not authorized kill mobs.");
                        return false;
                	}
                    }
                    if (args.length == 1){
                    	p.sendMessage(ChatColor.BLUE + "/spawnmob kill <all-animals-monsters-mobname>");
                    	return false;
                    }
                    if (args[1].equalsIgnoreCase("All")){
                    	if (plugin.permissions){
                    	if (!(SpawnMob.Permissions.has(p, "spawnmob.kill.all"))){
            				p.sendMessage(ChatColor.RED + "You're not authorized to do that.");
            				return false;
                			}
                    	}
                        		p.sendMessage(ChatColor.BLUE + "Killed all mobs. (Not including wolves.)");
                        		plugin.Kill(p.getWorld(), args[1]);
                                return true;
            		} else if (args[1].equalsIgnoreCase("Monsters")){
            			if (plugin.permissions){
            			if (!(SpawnMob.Permissions.has(p, "spawnmob.kill.monsters") || SpawnMob.Permissions.has(p, "spawnmob.kill.all"))){
                        p.sendMessage(ChatColor.RED + "You're not authorized to do that.");
        				return false;
            		}
            			}
            			p.sendMessage(ChatColor.BLUE + "Killed all monsters.");
            			plugin.Kill(p.getWorld(), args[1]);
                        return true;
            		} else if (args[1].equalsIgnoreCase("Animals")){
            			if (plugin.permissions){
            			if (!(SpawnMob.Permissions.has(p, "spawnmob.kill.animals") || SpawnMob.Permissions.has(p, "spawnmob.kill.all"))){
            				p.sendMessage(ChatColor.RED + "You're not authorized to do that.");
            				return false;
            			}
            			}
            				p.sendMessage(ChatColor.BLUE + "Killed all animals. (Not including wolves.)");
            				plugin.Kill(p.getWorld(), args[1]);
                            return true;
            		}
                    if (plugin.permissions){
                    if (!(SpawnMob.Permissions.has(p, "spawnmob.kill." + args[1]) || SpawnMob.Permissions.has(p, "spawnmob.kill.all"))){
        				p.sendMessage(ChatColor.RED + "You're not authorized to do that.");
        				return false;
            			} 
                    }
                    	if (mob3 == null && !(args[1].equalsIgnoreCase("Animals") || args[1].equalsIgnoreCase("Monsters") || args[1].equalsIgnoreCase("All"))) {
                            p.sendMessage(ChatColor.RED + "Invalid mob type.");
                            return false;
                        }
                    if (args[1].equalsIgnoreCase("Sheep") || args[1].equalsIgnoreCase("Squid")){
            			p.sendMessage(ChatColor.BLUE + "Killed all " + args[1] + ".");
            			plugin.Kill(p.getWorld(), args[1]);
                        return true;
            		}
            		if (args[1].equalsIgnoreCase("Wolf") || args[1].equalsIgnoreCase("Wolves")){
            			p.sendMessage(ChatColor.BLUE + "Killed all wolves, including tamed ones.");
            			plugin.Kill(p.getWorld(), args[1]);
                        return true;
            		}
                        p.sendMessage(ChatColor.BLUE + "Killed all " + args[1] + "s.");
                        plugin.Kill(p.getWorld(), args[1]);
                        return true;
                } else if (args[0].equalsIgnoreCase("Undo")) {
                	if (plugin.permissions){
                	if (!SpawnMob.Permissions.has(p, "spawnmob.kill.all")) {
                    	p.sendMessage(ChatColor.RED + "You can't do that.");
                    	return false;
                    }
                	}
                        p.sendMessage(ChatColor.BLUE + "Undid SpawnMob");
                        plugin.Kill(p.getWorld(), "all");
                        return true;
                }else if (args[0].equalsIgnoreCase("Kit")){
                	if (args.length > 1){
                		if(plugin.permissions){
                			if (!SpawnMob.Permissions.has(p, "spawnmob.kits")){
                        		p.sendMessage(ChatColor.RED + "You can't do that.");
                            	return false;
                        	}
                		}
                	if(args[1].equalsIgnoreCase("List")){
                		Kit.loadAllKits(p);
                		return true;
                	}
            		if(plugin.permissions){
            			if (!SpawnMob.Permissions.has(p, "spawnmob.kits." + args[1])){
                    		p.sendMessage(ChatColor.RED + "You can't do that.");
                        	return false;
                    	}
            		}
                	boolean success = false;
                	if (args.length > 2){
                	try {
                        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                        	if ((Kit.loadSettings(args[1], p, loc)) == true){
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
                		if ((Kit.loadSettings(args[1], p, loc)) == true){
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
                	}else{
                		p.sendMessage(ChatColor.RED + "/spawnmob kit list - List the available mob kits");
                		p.sendMessage(ChatColor.RED + "/spawnmob kit <kitname> - Spawn a mob kit");
                	}
                }
                if (args.length > 0 && args.length < 3) {
                    String[] split1 = args[0].split(":");
                    String[] split0 = new String[1];
                    LivingEntity spawned1 = null;
                    Mob mob2 = null;
                    if (split1.length == 1 && !split1[0].equalsIgnoreCase("Slime")) {
                        split0 = args[0].split(";");
                        split1[0] = split0[0];
                    }
                    if (split1.length == 2) {
                        args[0] = split1[0] + "";
                    }
                    Mob mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
                    if (mob == null) {
                    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
                    	return false;
                    }
                    if (plugin.permissions){
                    if(!(SpawnMob.Permissions.has(p, "spawnmob.spawnmob." + mob.getName().toLowerCase()) || SpawnMob.Permissions.has(p, "spawnmob." + mob.getName().toLowerCase()))){
                        p.sendMessage(ChatColor.RED + "You can't spawn this mob/mob type.");
                        return false;
                    }
                    }
                    LivingEntity spawned = null;
                    if (args.length != 2) {
                    try {
                        spawned = mob.spawn(p, plugin, loc);
                    } catch (MobException e) {
                        p.sendMessage(ChatColor.RED + "Unable to spawn mob.");
                        return false;
                    }
                    }
                    if (split0.length == 2) {
                        mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
                        if (mob2 == null) {
                            p.sendMessage(ChatColor.RED + "Invalid mob type.");
                            return false;
                        }
                        try {
                            spawned1 = mob2.spawn(p, plugin, loc);
                        } catch (MobException e) {
                            p.sendMessage(ChatColor.RED + "Unable to spawn mob.");
                            return false;
                        }
                        spawned1.setPassenger(spawned);
                    }
                    if (split1.length == 2 && mob.getName().toLowerCase() == "Slime") {
                        try {
                            ((EntitySlime) spawned).b(Integer.parseInt(split1[1]));
                        } catch (Exception e) {
                            p.sendMessage(ChatColor.RED + "Malformed size.");
                            return false;
                        }
                    }
                    if (args.length == 2) {
                    	int check = convertStringtoInt(args[1]);
                    	if (check > spawnlimit){
                			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was over the set limit!");
                			return false;
                		}
                        try {
                            for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                                spawned = mob.spawn(p, plugin, loc);
                                if (split1.length > 1 && mob.getName().toLowerCase() == "Slime") {
                                    try {
                                        ((EntitySlime) spawned).b(Integer.parseInt(split1[1]));
                                    } catch (Exception e) {
                                        p.sendMessage(ChatColor.RED + "Malformed size.");
                                        return false;
                                    }
                                }
                                if (split0.length == 2) {
                                    if (mob2 == null) {
                                    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
                                        return false;
                                    }
                                    try {
                                        spawned1 = mob2.spawn(p, plugin, loc);
                                    } catch (MobException e) {
                                        p.sendMessage(ChatColor.RED + "Unable to spawn mob.");
                                        return false;
                                    }
                                    spawned1.setPassenger(spawned);
                                }
                            }
                            p.sendMessage(ChatColor.BLUE + args[1] + " " + mob.getName().toLowerCase() + mob.s + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + " spawned.");
                        } catch (MobException e1) {
                            p.sendMessage(ChatColor.RED + "Unable to spawn mobs.");
                            return false;
                        } catch (java.lang.NumberFormatException e2) {
                            p.sendMessage(ChatColor.RED + "Malformed integer.");
                            return false;
                        }
                    } else {
                        p.sendMessage(ChatColor.BLUE + mob.getName().toLowerCase() + (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "") + " spawned.");
                    }
                    return true;
                } else {
                    p.sendMessage(ChatColor.BLUE + "/spawnmob <Mob Name> (Amount)");
                    p.sendMessage(ChatColor.BLUE + "/spawnmob kill <all-animals-monsters-mobname>");
                    p.sendMessage(ChatColor.BLUE + "/spawnmob kit - Type for more info");
                    p.sendMessage(ChatColor.BLUE + "/mspawn - Type for more info");
                    return false;
                }
            } else {
                p.sendMessage(ChatColor.BLUE + "/spawnmob <Mob Name> (Amount)");
                p.sendMessage(ChatColor.BLUE + "/spawnmob kill <all-animals-monsters-mobname>");
                p.sendMessage(ChatColor.BLUE + "/spawnmob kit - Type for more info");
                p.sendMessage(ChatColor.BLUE + "/mspawn - Type for more info");
                return false;
            }
        } else if (command.getName().toLowerCase().equalsIgnoreCase("mspawn")) {
            if (args.length > 0) {
                CreatureType mt = CreatureType.fromName(args[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(args[0]));
                org.bukkit.block.Block blk = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock();
                if (args[0].equalsIgnoreCase("Check") || args[0].equalsIgnoreCase("Info")) {
                	if (plugin.permissions){
                	if (!SpawnMob.Permissions.has(p, "spawnmob.mspawn.check")) {
                        p.sendMessage(ChatColor.RED + "You are not authorized to check a spawner.");
                        return false;
                    }
                	}
                    if (blk == null) {
                        p.sendMessage(ChatColor.RED + "You must be looking at a Mob Spawner.");
                        return false;
                    }
                    if (blk.getTypeId() != 52) {
                        p.sendMessage(ChatColor.RED + "You must be looking at a Mob Spawner.");
                        return false;
                    }
                    CreatureType mob1 = ((org.bukkit.block.CreatureSpawner) blk.getState()).getCreatureType();
                    int del = ((org.bukkit.block.CreatureSpawner) blk.getState()).getDelay();
                    p.sendMessage(ChatColor.BLUE + "This spawners mob type is " + mob1 + ".");
                    p.sendMessage(ChatColor.BLUE + "This spawners delay is set to " + del + ".");
                    return true;
                } else if (args[0].equalsIgnoreCase("Delay")) {
                	if (args.length != 2){
                        p.sendMessage(ChatColor.BLUE + "/mspawn delay <Number>");
                        p.sendMessage(ChatColor.BLUE + "Sets a mob spawners delay to spawn things.");
                        return false;
                    }
                    	if (plugin.permissions){
                    	if (!SpawnMob.Permissions.has(p, "spawnmob.mspawn.delay")) {
                            p.sendMessage(ChatColor.RED + "You are not authorized to set a spawners delay.");
                            return false;
                        }
                    	}
                        if (blk == null) {
                            p.sendMessage(ChatColor.RED + "You must be looking at a Mob Spawner.");
                            return false;
                        }
                        if (blk.getTypeId() != 52) {
                            p.sendMessage(ChatColor.RED + "You must be looking at a Mob Spawner.");
                            return false;
                        }
                        if (checkIfNumber(args[1]) == false) {
                            p.sendMessage(ChatColor.RED + "You must enter a number.");
                            return false;
                        }
                        String Del1 = args[1];
                        int Del = Integer.parseInt(Del1);
                        ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(Del);
                        p.sendMessage(ChatColor.BLUE + "This spawners delay is now set to " + Del + ".");
                        return true;
                }
                if (mt == null) {
                    p.sendMessage(ChatColor.RED + "Invalid mob type.");
                    return false;
                }
                if (plugin.permissions){
                if (!(SpawnMob.Permissions.has(p, "spawnmob.mspawn." + mt.getName().toLowerCase()) || SpawnMob.Permissions.has(p, "spawnmob.mspawn.allmobs"))) {
                    p.sendMessage(ChatColor.RED + "You are not authorized to do that.");
                    return false;
                }
                }
                    if (blk == null) {
                        p.sendMessage(ChatColor.RED + "You must be looking at a Mob Spawner.");
                        return false;
                    }
                if (blk.getTypeId() != 52) {
                    p.sendMessage(ChatColor.RED + "You must be looking at a Mob Spawner.");
                    return false;
                }
                ((org.bukkit.block.CreatureSpawner) blk.getState()).setCreatureType(mt);
                p.sendMessage(ChatColor.BLUE + "Mob spawner set as " + mt.getName().toLowerCase().toLowerCase() + ".");
            } else {
                p.sendMessage(ChatColor.BLUE + "/mspawn <Mob Name> - Set a mob spawner to spawn a mob");
                p.sendMessage(ChatColor.BLUE + "/mspawn check - See a spawners info.");
                p.sendMessage(ChatColor.BLUE + "/mspawn delay - Type for more info");
                return false;
            }

        }
		return false;
    }
 public boolean checkIfNumber(String in) {
        try {
            Long.parseLong(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
 public static int convertStringtoInt(String args) {
	    int aInt = Integer.parseInt(args);
		return aInt;
	  }
    private static String capitalCase(String s) {
        return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }
}
