package com.jordanneil23.SpawnMob.H;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import com.jordanneil23.SpawnMob.Main;
import com.jordanneil23.SpawnMob.K.Kit;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionsHandler {
	public static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	public static PermissionManager PermissionsEX = null;
    

    public static void setupPermissions() {
        if(Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx") != null && Main.AutoConfig == true){
        	Main.permsex = true;
        	Main.superperms = false;
        	ConfigurationHandler.setConfig("SpawnMob.Permissions.PermissionsEx", true);
        	ConfigurationHandler.setConfig("SpawnMob.Permissions.OpsOnly", false);
        	ConfigurationHandler.setConfig("SpawnMob.Permissions.SuperPerms", false);
        }
            if (Main.permsex != false || Main.superperms != false) {
            	if (Main.permsex == true){
            		if(Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx") == null){
            			log.warning("[SpawnMob] PermissionsEx is set to true in config but was not found!");
            			log.warning("[SpawnMob] Going to use ops.txt!");
            			Main.ops = true;
            			ConfigurationHandler.setConfig("SpawnMob.Permissions.PermissionsEx", false);
                    	ConfigurationHandler.setConfig("SpawnMob.Permissions.OpsOnly", true);
                    	ConfigurationHandler.setConfig("SpawnMob.Permissions.SuperPerms", false);
            			return;
            		}
            		else
            		{
            		PermissionsEX = PermissionsEx.getPermissionManager();
            		 log.info("[SpawnMob] Using PermissionsEx.");
            		 Main.superperms = false;
            		 Main.ops = false;
            		 return;
            		}
            	}else 
            		if (Main.superperms == true){
            			log.info("[SpawnMob] Using SuperPerms.");
            			Kit.setKitPerms();
            			Main.superperms = true;
            			Main.permsex = false;
            			Main.ops = false;
            			return;
            		}
            	
            	return;
            	}
            else
            {
            	log.info("[SpawnMob] In Ops only mode. Only ops will be able to use the command.");
                Main.ops = true;
                return;
            }
    }
    
    public static void setPerms(String s){
    	PluginManager pm = Bukkit.getServer().getPluginManager();
    	pm.addPermission(new Permission(s));
    }
    
    void remPerms(String s){
    	PluginManager pm = Bukkit.getServer().getPluginManager();
    	pm.removePermission(s);
    }
    
    public static boolean playerhas(Player p, String perm, boolean ops){
    	if (ops == true)
    	{
    		if (!p.isOp())
    			return false;
    		else 
    			return true;
    	}
    	if (ops == false)
    	{
    		if (((Main.superperms ? !p.hasPermission(perm) : (Main.permsex ? !PermissionsEX.has(p, perm) : !p.isOp()))))
    			return false;
    		else
    			return true;
    	} 
		return true;
    }
}

