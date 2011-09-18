package com.jordanneil23.SpawnMob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
import com.jordanneil23.SpawnMob.TargetBlock;

/**
 *SpawnMob - Commands
 * @author jordanneil23
 */
public class CommandHandler{
	int count = 0;
    private SpawnMob plugin;
    public void CommandListener(SpawnMob instance) {
        plugin = instance;
    }
    private String mobList[] = 
	{ 
    		"CaveSpider", "Chicken", "Cow", "Creeper",  "EnderMan", "Ghast", "Giant", "Monster", 
    		"Pig", "PigZombie",	"Sheep", "SilverFish", "Skeleton", "Slime", "Spider", "Squid", 
    		"Wolf", "Zombie"
	}; 
    private String customMobs[] = { "Wolf", "Creeper" };
	@SuppressWarnings("rawtypes")
	public static ArrayList mobs2 = new ArrayList();
	
    @SuppressWarnings({ "null", "unchecked" })
	public boolean perform(CommandSender sender, Command command, String[] args) {
    	int[] ignore = {8, 9};
        Player p = (Player) sender;
        World world = p.getWorld();
        Location loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
        loc.setY(1 + loc.getY());
        Mob mob2 = null;
        LivingEntity rider = null;
    	
        if (command.getName().equalsIgnoreCase("spawnmob")
        		|| command.getName().equalsIgnoreCase("smob")
        		|| command.getName().equalsIgnoreCase("sm"))
	    {
        	if (args.length == 0) {
        		    p.sendMessage(ChatColor.BLUE + "- Spawnmob Help -");
        		    p.sendMessage(ChatColor.BLUE + "Command shortcuts: spawnmob, smob, sm");
                    p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " <Mob Name> (Amount)");
                    p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kill <all-animals-monsters-mobname>");
                    p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kit - Type for more info");
                    p.sendMessage(ChatColor.BLUE + "/mspawn - Type for more info");
                    return false;
        	}
    		boolean isCustomMob = isArrMatch(this.customMobs, args[0]);
	        String[] split1 = args[0].split(":");
	        String[] split0 = new String[1];
	        
            	if (args[0].equalsIgnoreCase("kill"))
        		{
            		if (args.length == 1)
          		    {
          		    	p.sendMessage(ChatColor.BLUE + "/" + command.getName().toLowerCase() + " kill [all-animals-monsters-mobname-twolf]");
          		        p.sendMessage(ChatColor.BLUE + "Use /" + command.getName().toLowerCase() + " list for a list of mobnames");
          		        return false;
          		    }
            		
            		boolean all = false;
                  	Mob mob = Mob.fromName(args[0]);
                  	
                  	if (!(SpawnMob.playerhas(p, "spawnmob.kill", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.kill." + args[1].toLowerCase(), plugin.permissions) == true || SpawnMob.getPerms("spawnmob.*", p) == true)){
              			p.sendMessage(ChatColor.RED + "You can't use this command.");
              			return false;
              		} 
                  	
                  	if (!(args[1].equalsIgnoreCase("Wolf") || args[1].equalsIgnoreCase("Wolves") || args[1].equalsIgnoreCase("TWolf") || args[1].equalsIgnoreCase("All") || args[1].equalsIgnoreCase("Animals") || args[1].equalsIgnoreCase("Monsters")) && mob == null)
                  	{
                  		p.sendMessage(ChatColor.RED + "Invalid mob, try again.");
                  	}
                  	
        			if (args[1].equalsIgnoreCase("Wolf") || args[1].equalsIgnoreCase("Wolves")){
            			p.sendMessage(ChatColor.BLUE + "Killed all wolves, not including tamed ones. /spawnmob kill twolf kills tamed wolves.");
            			plugin.Kill(p.getWorld(), args[1]);
                        return true;
            		}
        			
            		if (args[1].equalsIgnoreCase("TWolf")){
            			p.sendMessage(ChatColor.BLUE + "Killed all tamed wolves.");
            			plugin.Kill(p.getWorld(), "twolf");
                        return true;
            		}
            		
            		if (args[1].equalsIgnoreCase("All")){
                    	all = true;
                    }
            		
                    plugin.Kill(p.getWorld(), args[1].toLowerCase());
                    p.sendMessage(ChatColor.BLUE + "Killed all " + (all == true ? "mobs" :  args[1].toLowerCase() ) + ", not including tamed wolves.");
        			return true;
        		}
        		else if (args[0].equalsIgnoreCase("Undo"))
        		{
        			p.sendMessage(ChatColor.BLUE + "Killed all mobs, not including tamed wolves.");
        			this.plugin.Kill(p.getWorld(), "all");
        			return true;
        		}
                else if (args[0].equalsIgnoreCase("Kit")){
                	if (args.length == 1)
                	{
                    		p.sendMessage(ChatColor.BLUE + "/spawnmob kit list - List the available mob kits");
                    		p.sendMessage(ChatColor.BLUE + "/spawnmob kit <kitname> - Spawn a mob kit");
                    		return false;
                	}
                		if(plugin.permissions){
                			if (!(SpawnMob.playerhas(p, "spawnmob.kits", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.kits.*", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.*", plugin.permissions) == true )){
                				p.sendMessage(ChatColor.RED + "You can't use this command.");
                            	return false;
                        	}
                		}
                	if(args[1].equalsIgnoreCase("List")){
                		Kit.loadAllKits(p);
                		return true;
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
                }
                else if (args[0].equalsIgnoreCase("List")){
                	String[] mobs = mobList;
    		        mobs2 = new ArrayList<String>(Arrays.asList(mobs));
    		        p.sendMessage(ChatColor.BLUE + "Mob list:");
    		        for (Iterator<String> iter2 = mobs2.iterator(); iter2.hasNext();){
    		        p.sendMessage(ChatColor.BLUE + " " + iter2.next() + " ");
    		        }
    		        return true;
                }
                if (isCustomMob && args.length >= 2)
        		{
            		if (args[0].equalsIgnoreCase("Wolf"))
            		{
            			boolean tamed = false;
            			
            			if (args.length >= 2 && args[1].equalsIgnoreCase("Tamed"))
            			{
            				tamed = true;
            				count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
            			}
            			
            				if(!(SpawnMob.playerhas(p, "spawnmob."   + (tamed ? "wolf.tamed" : "wolf"), plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.allmobs", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.*", plugin.permissions) == true)){
            					p.sendMessage(ChatColor.RED + "You can't use this command.");
                                return false;
                            }
            			
        				if (!spawnCheck(p, count))
        					return false;
                        Wolf w = null;

        				for (int i = 0; i < count; i++)
            			{
            				if (tamed)
            				{
            					w = MobHandling.setforWolf(p, loc);
            				} else {
                				w = (Wolf)world.spawnCreature(loc, CreatureType.WOLF);
                				w.setHealth(10);
                				w.setAngry(false);
            				}
            				if (split0.length == 2) {
            					mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
                                if (mob2 == null) {
                                	p.sendMessage(ChatColor.RED + "Invalid mob type.");
                                    return false;
                                }
                                rider = MobHandling.spawnRider(mob2, p, loc);
                                rider.setPassenger(w);
                                return true;
                            }
            			}
            			
            			if (count == 1)
            				p.sendMessage(ChatColor.BLUE + "You spawned a " + (tamed ? "tamed " : "") + "wolf"+ (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + "!");
            			else
            				p.sendMessage(ChatColor.BLUE + "You spawned " + count + " " + (tamed ? "tamed " : "") + "wolves"+ (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + "!");
            		}
            		else if (args[0].equalsIgnoreCase("Creeper"))
            		{
            			boolean electric = false;
            			
            			if (args.length >= 2 && args[1].equalsIgnoreCase("Electric"))
            			{
            				electric = true;
            				count = args.length >= 3 ? convertStringtoInt(args[2]) : 1;
            			}
            			
            				if(!(SpawnMob.playerhas(p, "spawnmob."   + (electric ? "creeper.electric" : "creeper"), plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.allmobs", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.*", plugin.permissions) == true)){
            					p.sendMessage(ChatColor.RED + "You can't use this command.");
                                return false;
                            }
            			
        				if (!spawnCheck(p, count))
        					return false;
        				
        				Creeper c = null;
        				
        				for (int i = 0; i < count; i++){
            				c = MobHandling.setforCreeper(p, loc, electric);
            				if (split0.length == 2) {
            					mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
                                if (mob2 == null) {
                                	p.sendMessage(ChatColor.RED + "Invalid mob type.");
                                    return false;
                                }
                                rider = MobHandling.spawnRider(mob2, p, loc);
                                rider.setPassenger(c);
                                return true;
                            }
        				}
        				
            			if (count == 1)
            				p.sendMessage(ChatColor.BLUE + "You spawned a" + (electric ? "n electric" : "") + " creeper" + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + "!");
            			else
            				p.sendMessage(ChatColor.BLUE + "You spawned " + count + " " + (electric ? "electric " : "") + "creepers" + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + "!");
            		}
            	}
        		else
        		{
        			if (split1.length == 1) {
                        split0 = args[0].split(";");
                        split1[0] = split0[0];
                    }
        			if (split1.length == 2) {
                        args[0] = split1[0] + "";
                    }
                    Mob mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
        			count = args.length >= 2 ? convertStringtoInt(args[1]) : 1;
        			LivingEntity m = null;
                    
        			if (mob == null && split1.length != 2)
        			{
        				p.sendMessage(ChatColor.RED + "Invalid mob, try again.");
        				return false;
        			}
        			
        				if(!(SpawnMob.playerhas(p, "spawnmob." + mob.getName().toLowerCase(), plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.allmobs", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.*", plugin.permissions) == true)){
        					p.sendMessage(ChatColor.RED + "You can't use this command.");
                            return false;
                        }
        			
        			if (!spawnCheck(p, count))
    					return false;
        			boolean chk = false;
        			boolean endman = false;
        			for (int i = 0; i < count; i++)
        			{
        				m = MobHandling.spawnIt(mob, p, loc); 
        				if (mob.getName().toUpperCase() == "SHEEP" || mob.getName().toUpperCase() == "SILVERFISH" || mob.getName().toUpperCase() == "SQUID")
        				{
        					chk = true;
        				}
        				if (mob.getName().toUpperCase() == "ENDERMAN"){
        					endman = true;
        				}
        				if (split1.length == 2 || split1.length > 1 && mob.getName().toUpperCase() == "SLIME") 
        				{
        					MobHandling.setforSlime(p, loc, m, split1[1]);
                        }
        				if (split1.length == 2 || split1.length > 1 && mob.getName().toUpperCase() == "SHEEP") 
        				{
                        	    MobHandling.setforSheep(m, split1[1]);
                        }
						if (split0.length == 2) {
							mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
						    if (mob2 == null) {
						    	p.sendMessage(ChatColor.RED + "Invalid mob type.");
						        return false;
						    }
						    rider = MobHandling.spawnRider(mob2, p, loc);
						    rider.setPassenger(m);
						}
        			}
        			
        			if (count == 1)
        				p.sendMessage(ChatColor.BLUE + "You spawned a " + mob.getName().toLowerCase() + (split0.length == 2 ? " riding a " + mob2.getName().toLowerCase() : "") + "!");
        			else
        				p.sendMessage(ChatColor.BLUE + "You spawned " + args[1] + " " + (endman ? "endermen" : mob.getName().toLowerCase()) + (chk ? "" : mob.s) + (split0.length == 2 ? " riding " + mob2.getName().toLowerCase().toLowerCase() + mob2.s : "") + "!");
        			    
        		}
        		return true;
        }
        else if (command.getName().equalsIgnoreCase("mspawn"))
    	{
        	if (args.length == 0)
            {
                p.sendMessage(ChatColor.BLUE + "/mspawn <mob name> - Set a mob spawner to spawn a mob.");
                p.sendMessage(ChatColor.BLUE + "/mspawn check - See a spawner's info.");
                p.sendMessage(ChatColor.BLUE + "/mspawn delay - Type for more info.");
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
            	if(!(SpawnMob.playerhas(p, "spawnmob.mspawn.check", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.mspawn.*", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.*", plugin.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You can't use this command.");
                    return false;
                }
            	
            	p.sendMessage(ChatColor.BLUE + "This spawner's mob type is " + ((org.bukkit.block.CreatureSpawner) blk.getState()).getCreatureType() + ".");
            	p.sendMessage(ChatColor.BLUE + "This spawners delay is set to " + ((org.bukkit.block.CreatureSpawner)blk.getState()).getDelay() + ".");
            }
            else if (args[0].equalsIgnoreCase("Delay"))
            {
            	if(!(SpawnMob.playerhas(p, "spawnmob.mspawn.delay", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.mspawn.*", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.*", plugin.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You can't use this command.");
                    return false;
                }
            	
            	if (args.length != 2)
            	{
            		p.sendMessage(ChatColor.BLUE + "/mspawn delay <delay>");
            		p.sendMessage(ChatColor.BLUE + "Sets a mob spawner's spawn delay.");
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
            	CreatureType mt = CreatureType.fromName(args[0].substring(0,1).toUpperCase() + args[0].substring(1).toLowerCase());
                if (mt == null)
                {
                	p.sendMessage(ChatColor.RED + "Creature type not found: " + args[0]);
                	return false;
                }
                
                if(!(SpawnMob.playerhas(p, "spawnmob.mspawn." + mt.getName().toLowerCase(), plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.mspawn.*", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.mspawn.allmobs", plugin.permissions) == true || SpawnMob.playerhas(p, "spawnmob.*", plugin.permissions) == true)){
            		p.sendMessage(ChatColor.RED + "You can't use this command.");
                    return false;
                }
                
                ((org.bukkit.block.CreatureSpawner)blk.getState()).setCreatureType(mt);
                p.sendMessage(ChatColor.BLUE + "Mob spawner set as " + mt.getName() + ".");
            }
            return true;
        }
		return false;
    }

    
    private boolean isArrMatch(String[] array, String name)
	{
		for (String mobName : array)
			if (mobName.equalsIgnoreCase(name))
				return true;
		
		return false;
	}
    
    private boolean spawnCheck(Player p, int count)
	{
		if (count <= 0)
		{
			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was invalid.");
			return false;
		}
		else if (count > convertStringtoInt(this.plugin.spawnlimit))
		{
			p.sendMessage(ChatColor.RED + "The amount of mobs you tried to spawn was over the set limit!");
			return false;
		}
		
		return true;
	}
    
    public static boolean checkIfNumber(String in) {
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
