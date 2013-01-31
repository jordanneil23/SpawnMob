package com.jordanneil23.SpawnMob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import com.jordanneil23.SpawnMob.Kits.*;
import com.jordanneil23.SpawnMob.Listeners.*;

/**
 * SpawnMob - Main
 * @version 2.0
 * @author jordanneil23
 */
@SuppressWarnings("unused")
public class SpawnMob extends JavaPlugin {
	public static File Friendlyconfig;
	public static FileConfiguration fconfig;
	private final SpawnerListener blockListener = new SpawnerListener(this);
	private final PListener PlayerListener = new PListener(this);
	public java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
    private final CommandHandler handler = new CommandHandler();
    private static final String CONFIG_FILE_NAME = "plugins/SpawnMob/SpawnMob.properties";
    static SpawnMob sm1;
    public static boolean permissions = true;
    public boolean mobspawnerdrops = true;
    public boolean friendly = true;
    public static String spawnlimit;
    
    public void onEnable() {
    	sm1 = this;
    	File confFile = new File(CONFIG_FILE_NAME);
		if(!confFile.exists()) {
			writeConfigFile();
		}
    	loadProps();
    	PluginManager pm = getServer().getPluginManager();
    	if (permissions){
    	PermissionsHandler.setupPermissions();
    	}
    	if (mobspawnerdrops){
            pm.registerEvents(blockListener, this);
		}
    	pm.registerEvents(PlayerListener, this);
        if (permissions){
    		PluginDescriptionFile pdfFile = this.getDescription();
    		log.info("[SpawnMob] Version " + pdfFile.getVersion() + " enabled.");
    		Kit.setKitPerms();
        }else{
        	log.info("[SpawnMob] Using ops.txt!");
        }
        
    	if (friendly){
            log.info(String.format("[SpawnMob] Friendly NOT enabled, this is NOT a bug. Friendly has been temporarily removed from SpawnMob." ));
    	}
    	
    }
    
    public void onDisable() {
    	File confFile = new File(CONFIG_FILE_NAME);
		if(!confFile.exists()) {
			writeConfigFile();
		}
    	PluginDescriptionFile pdfFile = this.getDescription();
    	log.info( "[SpawnMob] Version " + pdfFile.getVersion() + " disabled.");
    	log.info("[SpawnMob] Friendly Version 1.0 disabled.");
    }
    
    public void loadProps() {
	try {
		Properties props = new Properties();
		props.load(new FileReader("plugins/SpawnMob/SpawnMob.properties"));
		permissions = props.getProperty("use-permissions").contains("true") ? true : false;
		mobspawnerdrops = props.getProperty("mobspawners-have-drops").contains("true") ? true : false;
		spawnlimit = props.getProperty("spawn-limit");
		friendly = props.getProperty("friendly-enabled").contains("true") ? true : false;
	} catch (IOException ex) {
		System.out.println("[SpawnMob] Unable to load the properites!");
	}
}
    public void writeConfigFile() {
		log.info(String.format("[SpawnMob] Saving config file, please restart after this is done"));
		File file = new File("plugins/SpawnMob");
		if(!file.exists()) {
		new File("plugins/SpawnMob").mkdir();
		}
		Properties props = new Properties();
		props.setProperty("use-permissions", "true");
		props.setProperty("mobspawners-have-drops", "true");
		props.setProperty("spawn-limit", "300");
		props.setProperty("friendly-enabled", "true");
		try {
			props.store(new FileOutputStream(CONFIG_FILE_NAME), null);
		} catch (FileNotFoundException e) {
			log.info(String.format("[SpawnMob] FileNotFoundException while saving config file"));
		} catch (IOException e) {
			log.info(String.format("[SpawnMob] IOException while saving config file"));
		}		
	}
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
    		return handler.perform(sender, command, args);
    }
}

