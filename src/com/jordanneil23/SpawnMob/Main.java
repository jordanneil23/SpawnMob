package com.jordanneil23.SpawnMob;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.jordanneil23.SpawnMob.C.Commands;
import com.jordanneil23.SpawnMob.H.UpdateHandler;
import com.jordanneil23.SpawnMob.L.SL;

public class Main extends JavaPlugin {
	public static boolean permissions = true;
	public static boolean permsex = false;
	public static boolean superperms = false;
	public static boolean spawners = true;
	public static int spawnlimit;
	public static File configfile;
	public static FileConfiguration sconfig;
	public static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	private final Commands handler = new Commands();
	
	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		boolean update = UpdateHandler.isUpdate(pdf.getVersion());
		new SL(this);
		if(update == true){
			log.info("[SpawnMob] Version " + pdf.getVersion() + " enabled.");
			log.info("[SpawnMob] UPDATE DETECTED, PLEASE DOWNLOAD THIS NEW UPDATE.");
			log.info("[SpawnMob] You can download the update from http://goo.gl/rit3mY");
		}else{
			log.info("[SpawnMob] Version " + pdf.getVersion() + " enabled.");
			log.info("[SpawnMob] You have the current version.");
		}
	}
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		return handler.perform(sender, command, args);
}

}
