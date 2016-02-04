package com.jordanneil23.SpawnMob.H;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.jordanneil23.SpawnMob.Main;

public class ConfigurationHandler {
	
	public static File file = new File("plugins" + File.separator + "SpawnMob" + File.separator + "config.yml");
	public static FileConfiguration config = new YamlConfiguration();
	
	
	public static void firstRun() {
        if(!file.exists()){
        	file.getParentFile().mkdirs();
        }
        	config.options().header(
        			"AutoUpdate: When set to true it will auto download a new version of SpawnMob if there is one, this is set to false by default" + '\n'
        			+ "AutoConfig: When set to true it will auto detect if PermissionsEx is enabled, if so SpawnMob will use PermissionsEx" + '\n'
        			+ "OpsOnly: When set to true and AutoConfig is false SpawnMob will only use Ops.txt" + '\n'
        			+ "PermissionsEx: When set to true SpawnMob will use PermissionsEx" + '\n'
        			+ "SuperPerms: SpawnMob will use the built in 'Permissions Manager'" + '\n'
        			+ "Spawn_Limit: Should limit how many mobs can be spawned per command. Buggy, and doesn't really work.");
        	if(!config.isSet("SpawnMob.AutoUpdate")){
        		config.set("SpawnMob.AutoUpdate", false);
        	}
        	if(!config.isSet("SpawnMob.Permissions.AutoConfig")){
        		config.set("SpawnMob.Permissions.AutoConfig", true);
        	}
        	if(!config.isSet("SpawnMob.Permissions.OpsOnly")){
        		config.set("SpawnMob.Permissions.OpsOnly", true);
        	}
        	if(!config.isSet("SpawnMob.Permissions.PermissionsEx")){
        		config.set("SpawnMob.Permissions.PermissionsEx", false);
        	}
        	if(!config.isSet("SpawnMob.Permissions.SuperPerms")){
        		config.set("SpawnMob.Permissions.SuperPerms", false);
        	}
        	if(!config.isSet("SpawnMob.Permissions.Spawn_Limit")){
        		config.set("SpawnMob.Spawn-Limit", 300);
        	}
        	try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	
	public static void setConfig(String n, Object b){
		config.set(n, b);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void loadYamls() {
        try {
        	config.load(file);
        	Main.autoupdate = config.getBoolean("SpawnMob.AutoUpdate");
        	Main.AutoConfig = config.getBoolean("SpawnMob.Permissions.AutoConfig");
        	Main.ops = config.getBoolean("SpawnMob.Permissions.OpsOnly");
        	Main.permsex = config.getBoolean("SpawnMob.Permissions.PermissionsEX");
        	Main.superperms = config.getBoolean("SpawnMob.Permissions.SuperPerms");
			Main.spawnlimit = config.getInt("SpawnMob.Spawn-Limit");
			Main.log.info("[SpawnMob] Configuration files loaded.");
			return;
        } catch (Exception e) {
        	Main.log.info("[SpawnMob] Configuration files not loaded, error:");
        	e.printStackTrace();
        	return;
        }
    }
    public static void saveYamls(){
    	try {
			config.save(ConfigurationHandler.file);
		} catch (IOException e) {
			Main.log.warning("[SpawnMob] Could not save configs.");
			e.printStackTrace();
		}
    }
    public static boolean Data(String nodeData) {
		if (nodeData != null) {
			if (nodeData.equalsIgnoreCase("true")) {
				return false;
			}else if (nodeData.equalsIgnoreCase("false")){
				return false;
			}
		}
		return false;
	}
}


