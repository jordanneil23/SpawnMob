package com.jordanneil23.SpawnMob.H;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jordanneil23.SpawnMob.H.TargetBlock;

public class LocationHandler {
	//Spawn on the block that player is looking at
	public static Location getLoc(CommandSender sender){
		Player p = (Player) sender;
		int[] ignore = {8, 9};
		try{
		Location loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();	
		loc.setY(1 + loc.getY());
		return loc;
		}
		catch(NullPointerException e){
			p.sendMessage("No block in sight!, spawning on you. (Hopefully)");
			return p.getLocation();
		}
	}
	//Spawn on specified player
	public static Location getLoc(CommandSender sender, Player p){
		Player player = (Player) sender;
		if(PlayerHandler.isPlayerOnline(p.getName()) == true){
			return p.getLocation();
		}else{
			player.sendMessage("Player not found, maybe you misspelled something? Spawning mob(s) on you instead.");
			return player.getLocation();
		}
	}
	//Spawn on a set of coordinates
	public static Location getLoc(CommandSender sender, String x,  String y,  String z){
		Player p = (Player) sender;
		if(MobHandler.isInt(x) || MobHandler.isInt(x) || MobHandler.isInt(x)){
			Player player = (Player) sender;
			World w = player.getWorld();
			Location loc = new Location(w,PlayerHandler.convertStringtoInt(x),PlayerHandler.convertStringtoInt(y),PlayerHandler.convertStringtoInt(z));
			return loc;
		}else{
			p.sendMessage(ChatColor.RED + "Coordinates were not valid, valid coordinates are: X Y Z");
			p.sendMessage(ChatColor.RED + "Coordinates also have to be numbers, spawning where you're looking.");
			return getLoc(p);
		}
	}
	

}
