package com.jordanneil23.SpawnMob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

/**
 * SpawnMob - Main
 * @version 1.9.1
 * @author jordanneil23
 */
public class SpawnMob extends JavaPlugin {
	private final SpawnerListener blockListener = new SpawnerListener(this);
	private final PListener PlayerListener = new PListener(this);
	public java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	public static PermissionHandler Permissions;
    private final CommandHandler handler = new CommandHandler();
    private static final String CONFIG_FILE_NAME = "plugins/SpawnMob/SpawnMob.properties";
    
    public void onEnable() {
    	File confFile = new File(CONFIG_FILE_NAME);
		if(!confFile.exists()) {
			writeConfigFile();
		}
    	loadProps();
    	PluginManager pm = getServer().getPluginManager();
    	if (permissions){
    	setupPermissions();
    	}
    	if (mobspawnerdrops){
            pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
		}
    	pm.registerEvent(Event.Type.PLAYER_INTERACT, PlayerListener, Priority.Normal, this);
        if (permissions){
    		PluginDescriptionFile pdfFile = this.getDescription();
    		log.info("[SpawnMob] Version " + pdfFile.getVersion() + " enabled.");
        }else{
        	log.info("[SpawnMob] Using ops.txt!");
        }
    		handler.CommandListener(this);
    	}
    
    public void onDisable() {
    	File confFile = new File(CONFIG_FILE_NAME);
		if(!confFile.exists()) {
			writeConfigFile();
		}
    	PluginDescriptionFile pdfFile = this.getDescription();
    	log.info( "[SpawnMob]" + " Version " + pdfFile.getVersion() + " disabled.");
    }

    public boolean permissions = true;
    public boolean mobspawnerdrops = true;
    public void loadProps() {
	try {
		Properties props = new Properties();
		props.load(new FileReader("plugins/SpawnMob/SpawnMob.properties"));
		permissions = props.getProperty("use-permissions").contains("true") ? true : false;
		mobspawnerdrops = props.getProperty("mobspawners-have-drops").contains("true") ? true : false;
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
    
private void setupPermissions() {
    Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
    if (SpawnMob.Permissions == null) {
        if (test != null) {
            SpawnMob.Permissions = ((Permissions)test).getHandler();
            log.info("[SpawnMob] Permission system found, plugin enabled");
        } else {
            log.info("[SpawnMob] Permission system not detected! Please go into the SpawnMob.properties and set use-permissions to false.");
            log.info("[SpawnMob] Please go into the SpawnMob.properties and set use-permissions to false.");
            permissions = false;
        }
    }
}

}

