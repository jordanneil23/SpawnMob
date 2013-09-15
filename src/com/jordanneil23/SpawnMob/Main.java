package com.jordanneil23.SpawnMob;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
public static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
private final Commands handler = new Commands();
public static boolean permissions = true;
public static boolean permsex = false;
public static boolean superperms = false;
public static boolean spawners = true;
public static int spawnlimit;
public static File configfile;
public static FileConfiguration sconfig;


public void onEnable() {
	PluginDescriptionFile pdf = this.getDescription();
	configfile = new File("plugins/SpawnMob/config.yml");
	sconfig = new YamlConfiguration();
	boolean update = false;
	try {
		update = CheckUpdate.isUpdate(pdf.getVersion());
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		ConfigurationHandler.firstRun();
    } catch (Exception e) {
        e.printStackTrace();
    }
	ConfigurationHandler.loadYamls();
	if(permissions == true)
		PermissionsHandler.setupPermissions();
	else
		log.info("[SpawnMob] Using ops.txt!");
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
	ConfigurationHandler.saveYamls();
	PluginDescriptionFile pdf = this.getDescription();
	log.info("[SpawnMob] Version " + pdf.getVersion() + " disabled.");
}


public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		return handler.perform(sender, command, args);
}

}
