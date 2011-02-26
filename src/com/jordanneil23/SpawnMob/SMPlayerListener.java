package com.jordanneil23.SpawnMob;

import net.minecraft.server.EntitySlime;
import net.minecraft.server.World;

import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

import com.jordanneil23.SpawnMob.TargetBlock;
import com.jordanneil23.SpawnMob.Mob.MobException;


/**
 * SpawnMob Commands
 * @author jordanneil23
 */
public class SMPlayerListener extends PlayerListener {
    private final SpawnMob plugin;

    public SMPlayerListener(SpawnMob instance) {
        plugin = instance;
    }

	public void onPlayerCommand(PlayerChatEvent event){
		int[] ignore = {8, 9};
    	String[] split = event.getMessage().split(" ");
    	if(split[0].equalsIgnoreCase("/spawnmob") || split[0].equalsIgnoreCase("/sm") || split[0].equalsIgnoreCase("/smob")){
    		if (1 < split.length){
    		if (split[1].equalsIgnoreCase("Kill")){
    			if(SpawnMob.playerCanUse(event.getPlayer(), "spawnmob.kill"))
    			{
    			if(split[2].equalsIgnoreCase("Monsters"))
                {
    				plugin.KillMobs(event.getPlayer().getWorld(), "monsters");
    				event.getPlayer().sendMessage("Killed all monsters");
    				return;
                } else
                if(split[2].equalsIgnoreCase("Animals"))
                {
                    plugin.KillMobs(event.getPlayer().getWorld(), "animals");
                    event.getPlayer().sendMessage("Killed all animals");
                    return;
                } else
                if(split[2].equalsIgnoreCase("All"))
                {
                	event.getPlayer().sendMessage("Killed all mobs");
                    plugin.KillMobs(event.getPlayer().getWorld(), "all");
                    return;
                }
    			return;
    		} else 
    		{
    			event.getPlayer().sendMessage("You are not authorized kill mobs.");
    			return;
    		}
    	} else
    		if (split[1].equalsIgnoreCase("Undo"))
    		{
    			event.getPlayer().sendMessage("Undid /spawnmob.");
                plugin.KillMobs(event.getPlayer().getWorld(), "all");
                return;
    		}
	    	if(1 < split.length && split.length < 4 ){
	    		String[] split1 = split[1].split(":");
	    		String[] split0 = null;
	    		CraftEntity spawned1 = null;
				Mob mob2 = null;
	    		if(split1.length == 1 && !split1[0].equalsIgnoreCase("Slime")){
	    			split0 = split[1].split(";");
	    			split1[0] = split0[0];
	    		}
	    		if(split1.length == 2){
	    			split[1] = split1[0] + "";
	    		}
	    		Mob mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
	    		if(mob == null){
		    		event.getPlayer().sendMessage("Invalid mob type.");
		    		return;
	    		}
		    	if(!(SpawnMob.playerCanUse(event.getPlayer(), "spawnmob.spawnmob." + mob.name.toLowerCase()) || SpawnMob.playerCanUse(event.getPlayer(), "spawnmob." + mob.type.type))){
		    		event.getPlayer().sendMessage("You can't spawn this mob.");
		    		return;
		    	}
				World world = ((org.bukkit.craftbukkit.CraftWorld)event.getPlayer().getWorld()).getHandle();
				CraftEntity spawned = null;
				try {
					spawned = mob.spawn(event.getPlayer(), plugin);
				} catch (MobException e) {
					event.getPlayer().sendMessage("Unable to spawn mob.");
					return;
				}
				Location loc = (new TargetBlock(event.getPlayer(), 300, 0.2, ignore)).getTargetBlock().getLocation();
				spawned.teleportTo(loc);
				world.a(spawned.getHandle());
				if(split0.length == 2){
					mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
		    		if(mob2 == null){
			    		event.getPlayer().sendMessage("Invalid mob type.");
			    		return;
		    		}
					try {
						spawned1 = mob2.spawn(event.getPlayer(), plugin);
					} catch (MobException e) {
						event.getPlayer().sendMessage("Unable to spawn mob.");
						return;
					}
					spawned1.teleportTo(spawned);
					spawned1.getHandle().setPassengerOf(spawned.getHandle());
					world.a(spawned1.getHandle());
				}
				if(split1.length == 2 && mob.name == "Slime"){
					try{
						((EntitySlime) spawned.getHandle()).b(Integer.parseInt(split1[1]));
					}catch(Exception e){
						event.getPlayer().sendMessage("Malformed size.");
						return;
					}
				}
				if(split.length == 3){
					try{
    					for(int i = 1; i < Integer.parseInt(split[2]);i++){
    	    				spawned = mob.spawn(event.getPlayer(), plugin);
    	    				spawned.teleportTo(loc);
    	    				if(split1.length > 1 && mob.name == "Slime"){
    	    					try{
    	    						((EntitySlime) spawned.getHandle()).b(Integer.parseInt(split1[1]));
    	    					}catch(Exception e){
    	    						event.getPlayer().sendMessage("Malformed size.");
    	    						return;
    	    					}
    	    				}
    	    				world.a(spawned.getHandle());
    	    				if(split0.length == 2){
    	    		    		if(mob2 == null){
    	    			    		event.getPlayer().sendMessage("Invalid mob type.");
    	    			    		return;
    	    		    		}
    	    					try {
    	    						spawned1 = mob2.spawn(event.getPlayer(), plugin);
    	    					} catch (MobException e) {
    	    						event.getPlayer().sendMessage("Unable to spawn mob.");
    	    						return;
    	    					}
    	    					spawned1.teleportTo(spawned);
    	    					spawned1.getHandle().setPassengerOf(spawned.getHandle());
    	    					world.a(spawned1.getHandle());
    	    				}
    					}
    					event.getPlayer().sendMessage(split[2] + " " + mob.name.toLowerCase() + mob.s + (split0.length == 2 ? " riding " + mob2.name.toLowerCase() + mob2.s : "") + " spawned.");
					}catch(MobException e1){
						event.getPlayer().sendMessage("Unable to spawn mobs.");
						return;
					}catch(java.lang.NumberFormatException e2){
						event.getPlayer().sendMessage("Malformed integer.");
						return;
					}
				}else{
					event.getPlayer().sendMessage(mob.name + (split0.length == 2 ? " riding a " + mob2.name.toLowerCase() : "") + " spawned.");
				}
				return;
			}else{
	    	event.getPlayer().sendMessage("/spawnmob <Mob Name> (Amount)");
	    	event.getPlayer().sendMessage("/spawnmob kill <all-animals-monsters>");
	    	event.getPlayer().sendMessage("Alias' - /smob, /sm");
	    	event.getPlayer().sendMessage("/mspawn - Type for more info");
    		return;
			}
    	}else {
    		event.getPlayer().sendMessage("/spawnmob <Mob Name> (Amount)");
	    	event.getPlayer().sendMessage("/spawnmob kill <all-animals-monsters>");
	    	event.getPlayer().sendMessage("Alias' - /smob, /sm");
	    	event.getPlayer().sendMessage("/mspawn - Type for more info");
	    	return;
    	}
    	}else if(split[0].equalsIgnoreCase("/mspawn")){
    		if(1 < split.length){
        		CreatureType mt = CreatureType.fromName(split[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split[1]));
        		org.bukkit.block.Block blk = (new TargetBlock(event.getPlayer(), 300, 0.2, ignore)).getTargetBlock();
        		if(split[1].equalsIgnoreCase("Check") || split[1].equalsIgnoreCase("Info")){
        			if(!SpawnMob.playerCanUse(event.getPlayer(), "spawnmob.mspawn.check")){
            			event.getPlayer().sendMessage("You are not authorized to check a spawner.");
            			return;
            		}
        			if(blk == null){
            			event.getPlayer().sendMessage("You must be looking at a Mob Spawner.");
            			return;
            		}
            		if(blk.getTypeId() != 52){
            			event.getPlayer().sendMessage("You must be looking at a Mob Spawner.");
            			return;
            		}
        			CreatureType mob = ((org.bukkit.block.CreatureSpawner) blk.getState()).getCreatureType();
        			int del = ((org.bukkit.block.CreatureSpawner) blk.getState()).getDelay();
        			event.getPlayer().sendMessage("This spawners mob type is " + mob + ".");
        			event.getPlayer().sendMessage("This spawners delay is set to " + del + ".");
            		return;
        		} else if(split[1].equalsIgnoreCase("Delay")){
        			if(1 < split.length){
        			if(!SpawnMob.playerCanUse(event.getPlayer(), "spawnmob.mspawn.delay")){
            			event.getPlayer().sendMessage("You are not authorized to set a spawners delay.");
            			return;
            		}
        			if(blk == null){
            			event.getPlayer().sendMessage("You must be looking at a Mob Spawner.");
            			return;
            		}
            		if(blk.getTypeId() != 52){
            			event.getPlayer().sendMessage("You must be looking at a Mob Spawner.");
            			return;
            		}
            		if (split[2].equalsIgnoreCase("-10")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-10);
            			event.getPlayer().sendMessage("This spawners delay is now set to -10.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-9")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-9);
            			event.getPlayer().sendMessage("This spawners delay is now set to -9.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-8")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-8);
            			event.getPlayer().sendMessage("This spawners delay is now set to -8.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-7")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-7);
            			event.getPlayer().sendMessage("This spawners delay is now set to -7.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-6")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-6);
            			event.getPlayer().sendMessage("This spawners delay is now set to -6.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-5")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-5);
            			event.getPlayer().sendMessage("This spawners delay is now set to -5.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-4")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-4);
            			event.getPlayer().sendMessage("This spawners delay is now set to -4.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-3")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-3);
            			event.getPlayer().sendMessage("This spawners delay is now set to -3.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-2")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-2);
            			event.getPlayer().sendMessage("This spawners delay is now set to -2.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("-1")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-1);
            			event.getPlayer().sendMessage("This spawners delay is now set to -1.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("0")) {
            	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(0);
        			event.getPlayer().sendMessage("This spawners delay is now set to normal.");
        			return;
            		}
                    if (split[2].equalsIgnoreCase("1")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(1);
            			event.getPlayer().sendMessage("This spawners delay is now set to 1.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("2")) {
                    	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(2);
            			event.getPlayer().sendMessage("This spawners delay is now set to 2.");
            			return;
                		}
            		if (split[2].equalsIgnoreCase("3")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(3);
        			event.getPlayer().sendMessage("This spawners delay is now set to 3.");
        			return;
            		}
            		if (split[2].equalsIgnoreCase("4")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(4);
        			event.getPlayer().sendMessage("This spawners delay is now set to 4.");
        			return;
            		}
            		if (split[2].equalsIgnoreCase("5")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(5);
        			event.getPlayer().sendMessage("This spawners delay is now set to 5.");
            		}
            		if (split[2].equalsIgnoreCase("6")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(6);
        			event.getPlayer().sendMessage("This spawners delay is now set to 6.");
            		}
            		if (split[2].equalsIgnoreCase("7")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(7);
        			event.getPlayer().sendMessage("This spawners delay is now set to 7.");
        			return;
            		}
            		if (split[2].equalsIgnoreCase("8")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(8);
        			event.getPlayer().sendMessage("This spawners delay is now set to 8.");
        			return;
            		}
            		if (split[2].equalsIgnoreCase("9")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(9);
        			event.getPlayer().sendMessage("This spawners delay is now set to 9.");
        			return;
            		}
            		if (split[2].equalsIgnoreCase("10")) {
                	    ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(10);
        			event.getPlayer().sendMessage("This spawners delay is now set to 10.");
        			return;
            		}
            		return;
        			} else {
        				event.getPlayer().sendMessage("/mspawn delay - <A Number Between -10 to 10>");
        				event.getPlayer().sendMessage("Sets a mob spawners delay to spawn things.");
        				event.getPlayer().sendMessage("-10 being the fastest, 10 being the slowest.");
        				return;
        			}
        		}
        		if(mt == null){
        			event.getPlayer().sendMessage("Invalid mob type.");
        			return;
        		}
        		if(!SpawnMob.playerCanUse(event.getPlayer(), "spawnmob.mspawn." + mt.name().toLowerCase()) || !SpawnMob.playerCanUse(event.getPlayer(), "spawnmob.mspawn.allmobs")){
        			event.getPlayer().sendMessage("You are not authorized to do that.");
        			return;
        		}
        		if(blk == null){
        			event.getPlayer().sendMessage("You must be looking at a Mob Spawner.");
        			return;
        		}
        		if(blk.getTypeId() != 52){
        			event.getPlayer().sendMessage("You must be looking at a Mob Spawner.");
        			return;
        		}
        		((org.bukkit.block.CreatureSpawner) blk.getState()).setCreatureType(mt);
        		event.getPlayer().sendMessage("Mob spawner set as " + mt.getName().toLowerCase() + ".");
        	}else{
        		event.getPlayer().sendMessage("/mspawn <Mob Name> - Set a mob spawner to spawn a mob");
        		event.getPlayer().sendMessage("/mspawn check - See a spawners info.");
        		event.getPlayer().sendMessage("/mspawn delay - Type for more info");
        		event.getPlayer().sendMessage("Right clicking a mobspawner also acts like /mspawn check");
        		return;
        	}
        		
        	}
        }
    private String capitalCase(String s){
    	return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }
}