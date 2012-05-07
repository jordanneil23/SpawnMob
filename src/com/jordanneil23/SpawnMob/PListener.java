package com.jordanneil23.SpawnMob;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * SpawnMob - Player Listener
 * @author jordanneil23
 */
public class PListener implements Listener{
	 public static SpawnMob plugin;
	 public PListener(SpawnMob instance) {
    	 plugin = instance;
    } 
	 
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) 
	 {
		Player p = event.getPlayer();
		Action RClick = Action.RIGHT_CLICK_BLOCK;
		if (event.getAction() == RClick)
		{
			 Block block = event.getClickedBlock();
		 		if(block.getType() == Material.MOB_SPAWNER)
		 {
		 if (PermissionsHandler.playerhas(p, "spawnmob.mspawn.check", SpawnMob.permissions))
		 {
			EntityType mob = ((org.bukkit.block.CreatureSpawner) block.getState()).getSpawnedType();
		 	int del = ((org.bukkit.block.CreatureSpawner) block.getState()).getDelay();
		 	p.sendMessage("This spawners mob type is " + mob + ".");
		 	p.sendMessage("This spawners delay is set to " + del + ".");
		 } else {return;}
		} else {return;}
		return;
	 }
	 return;
	 }
}
