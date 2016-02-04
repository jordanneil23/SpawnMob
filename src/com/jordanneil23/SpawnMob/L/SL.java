package com.jordanneil23.SpawnMob.L;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import com.jordanneil23.SpawnMob.Main;
import com.jordanneil23.SpawnMob.H.PermissionsHandler;

/**
 * SpawnMob - Block Listener
 * @author jordanneil23
 */
public class SL implements Listener{
	 
	 public SL(Main plugin){
	   Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	   Main.log.info("[SpawnMob] Listeners enabled.");
	 }
	 
	 
	 @EventHandler
	 public void onPlayerJoin(PlayerJoinEvent event)
	 {
	   Player p = event.getPlayer();
	   if((PermissionsHandler.playerhas(p, "spawnmob.update", Main.ops) || PermissionsHandler.playerhas(p, "spawnmob.*", Main.ops)) && Main.update)
	   {
	     p.sendMessage("An update is available: " + Main.name + ", a " + Main.type + " for " + Main.version + " available at " + Main.link);
	     p.sendMessage("Type /spawnmob update if you would like to download and install this update.");
	   }
	 }
	 
	 @EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteract(PlayerInteractEvent event) 
	 {
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			 Block block = event.getClickedBlock();
		 		if(block.getType() == Material.MOB_SPAWNER)
		 {
		 Player p = event.getPlayer();
		 if (PermissionsHandler.playerhas(p, "spawnmob.mspawn.check", Main.ops))
		 {
		 	String mob = ((org.bukkit.block.CreatureSpawner) block.getState()).getCreatureTypeName();
		 	int del = ((org.bukkit.block.CreatureSpawner) block.getState()).getDelay();
		 	event.getPlayer().sendMessage("This spawners mob type is " + mob + ".");
		 	event.getPlayer().sendMessage("This spawners delay is set to " + del + ".");
		 } else {return;}
		} else {return;}
		return;
	 }
	 return;
	 }
	 
	 /** Disabled, if people want this back I will make a plugin dedicated to Mob Spawners.
	@EventHandler(priority = EventPriority.MONITOR)
	 public void onBlockBreak(BlockBreakEvent event) {
		 if (Main.spawners == false){return;}
		 if(event.isCancelled()){return;}
		 // ^^ Thanks trc202 
		 Block block = event.getBlock();
		 if (Main.spawners == true)
		 {
		 if(block.getType() == Material.MOB_SPAWNER)
	 		{
			CreatureSpawner m = ((CreatureSpawner) block.getState());
			
			Location pos = new Location(event.getPlayer().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ());
			ItemStack stack = new ItemStack(Material.MOB_SPAWNER, 1);
			stack.setDurability(m.getSpawnedType().getTypeId());
			//Main.log.info("Debug info:" + stack.getDurability());
     		event.getPlayer().getWorld().dropItemNaturally(pos, stack);
     		m.update();
     		return;
	 		}
		 } else 
		 {
		    return;
	     }
	 }
	 
	@EventHandler(priority = EventPriority.MONITOR)
	 public void onBlockPlace(BlockPlaceEvent event) {
		if (Main.spawners == false){return;}
		if(event.isCancelled()){return;}
		Block block = event.getBlock();
		if(block.getType() == Material.MOB_SPAWNER)
 		{
			ItemStack item = event.getItemInHand();
			
            setSpawner(event.getBlockPlaced(), item.getDurability());
 		}
		
	}
	**/
}
