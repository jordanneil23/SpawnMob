package com.jordanneil23.SpawnMob.H;

import java.io.IOException;

import com.jordanneil23.SpawnMob.Main;

public class ConfigurationHandler {
    
    public static void firstRun() throws Exception {
        if(!Main.configfile.exists()){
        	Main.configfile.getParentFile().mkdirs();
        	Main.sconfig.set("SpawnMob.Permissions", Main.permissions);
        	Main.sconfig.set("SpawnMob.PermissionsEX", Main.permsex);
        	Main.sconfig.set("SpawnMob.SuperPerms", Main.superperms);
        	Main.sconfig.set("SpawnMob.MobSpawners_Have_Drops", Main.spawners);
        	Main.sconfig.set("SpawnMob.Spawn-Limit", 300);
        	Main.sconfig.save(Main.configfile);
        }
        if(!Main.kitfile.exists()){
        	Main.kitfile.getParentFile().mkdirs();
        	Main.skit.set("SpawnMob.Permissions", Main.permissions);
        	Main.skit.set("SpawnMob.PermissionsEX", Main.permsex);
        	Main.skit.set("SpawnMob.SuperPerms", Main.superperms);
        	Main.skit.set("SpawnMob.MobSpawners_Have_Drops", Main.spawners);
        	Main.skit.set("SpawnMob.Spawn-Limit", 300);
        	Main.skit.save(Main.kitfile);
        }
    }
    
    public static void saveYamls() {
        try {
        	Main.sconfig.set("SpawnMob.Permissions", Main.permissions);
        	Main.sconfig.save(Main.configfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean loadYamls() {
        try {
        	Main.sconfig.load(Main.configfile);
        	Main.log.info("Configuration files loaded.");
			boolean perms = Main.sconfig.getBoolean("SpawnMob.Permissions");
			boolean permsex1 = Main.sconfig.getBoolean("SpawnMob.PermissionsEX");
			boolean SuperPerms = Main.sconfig.getBoolean("SpawnMob.SuperPerms");
			boolean spawner = Main.sconfig.getBoolean("SpawnMob.MobSpawners_Have_Drops");
			int spawnlimit = Main.sconfig.getInt("SpawnMob.Spawn-Limit");
			
			Main.permissions = perms;
			Main.permsex = permsex1;
			Main.superperms = SuperPerms;
			Main.spawners = spawner;
			Main.spawnlimit = spawnlimit;
			return true;
        } catch (Exception e) {
        	Main.log.info("Configuration files not loaded, error:");
        	e.printStackTrace();
        	return false;
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


