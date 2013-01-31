package com.jordanneil23.SpawnMob.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.jordanneil23.SpawnMob.PermissionsHandler;
import com.jordanneil23.SpawnMob.SpawnMob;

/**
 * SpawnMob - Block Listener
 * @author jordanneil23
 */
public class SpawnerListener implements Listener{
	 public static SpawnMob plugin;
	 public SpawnerListener(SpawnMob instance) {
    	 plugin = instance;
    } 
	 @EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerInteract(PlayerInteractEvent event) 
	 {
		if (event.getAction().equals("RIGHT_CLICK_BLOCK"))
		{
			 Block block = event.getClickedBlock();
		 		if(block.getType() == Material.MOB_SPAWNER)
		 {
		 Player p = event.getPlayer();
		 if (PermissionsHandler.playerhas(p, "spawnmob.mspawn.check", SpawnMob.permissions))
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
	@EventHandler(priority = EventPriority.MONITOR)
	 public void onBlockBreak(BlockBreakEvent event) {
		 if(event.isCancelled()){return;}
		 /* ^^ Thanks trc202 */
		 Block block = event.getBlock();
		 if (plugin.mobspawnerdrops)
		 {
		 if(block.getType() == Material.MOB_SPAWNER)
	 		{
			Location pos = new Location(event.getPlayer().getWorld(), event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ());
     		event.getPlayer().getWorld().dropItemNaturally(pos, new ItemStack(event.getBlock().getTypeId(),1));
     		return;
	 		}
		 } else 
		 {
		    return;
	     }
	 }
}
