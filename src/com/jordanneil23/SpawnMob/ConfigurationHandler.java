package com.jordanneil23.SpawnMob;

import java.io.IOException;

public class ConfigurationHandler {
    
    public static void firstRun() throws Exception {
        if(!Main.configfile.exists()){
        	Main.configfile.getParentFile().mkdirs();
        	Main.sconfig.set("SpawnMob.Permssions", Main.permissions);
        	Main.sconfig.set("SpawnMob.MobSpawners_Have_Drops", Main.spawners);
        	Main.sconfig.set("SpawnMob.Spawn-Limit", 300);
        	//Main.sconfig.set("Friendly", "false");
        	Main.sconfig.save(Main.configfile);
        }
    }
    
    public static void saveYamls() {
        try {
        	Main.sconfig.set("SpawnMob.Permssions", Main.permissions);
        	Main.sconfig.save(Main.configfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadYamls() {
        try {
        	Main.sconfig.load(Main.configfile);
        	Main.log.info("Configuration files loaded.");
			boolean perms = Main.sconfig.getBoolean("SpawnMob.Permissions");
			boolean spawner = Main.sconfig.getBoolean("SpawnMob.MobSpawners_Have_Drops");
			int spawnlimit = Main.sconfig.getInt("SpawnMob.Spawn-Limit");
			
			Main.permissions = perms;
			Main.spawners = spawner;
			Main.spawnlimit = spawnlimit;
        } catch (Exception e) {
        	Main.log.info("Configuration files not loaded, error:");
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


