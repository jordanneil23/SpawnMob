package com.jordanneil23.SpawnMob;

import java.io.File;

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
public static boolean spawners = true;
public static int spawnlimit;
public static File configfile;
public static FileConfiguration sconfig;


public void onEnable() {
	configfile = new File("plugins/SpawnMob/config.yml");
	sconfig = new YamlConfiguration();
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
	
	PluginDescriptionFile pdf = this.getDescription();
	log.info("[SpawnMob] Version " + pdf.getVersion() + " enabled.");
}

public void onDisable(){
	ConfigurationHandler.saveYamls();
	PluginDescriptionFile pdf = this.getDescription();
	log.info("[SpawnMob] Version " + pdf.getVersion() + " disabled.");
}

@Override
public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		return handler.perform(sender, command, args);
}

}
