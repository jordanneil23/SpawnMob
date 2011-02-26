package com.jordanneil23.SpawnMob;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockRightClickEvent;

/**
 * SpawnMob for Bukkit
 *
 * @author jordanneil23
 */
public class SpawnerListener extends BlockListener{
	 public static SpawnMob plugin;
	 public SpawnerListener(SpawnMob instance) {
    	 plugin = instance;
    }
	 public void onBlockRightClick(BlockRightClickEvent event) 
	 {
	 	 Block block = event.getBlock();
	 		if(block.getType() == Material.MOB_SPAWNER && SpawnMob.playerCanUse(event.getPlayer(), "spawnmob.mspawn.check"))
	 		{
	 				CreatureType mob = ((org.bukkit.block.CreatureSpawner) block.getState()).getCreatureType();
         			int del = ((org.bukkit.block.CreatureSpawner) block.getState()).getDelay();
        			event.getPlayer().sendMessage("This spawners mob type is " + mob + ".");
        			event.getPlayer().sendMessage("This spawners delay is set to " + del + ".");
	 		}
	} 
}
