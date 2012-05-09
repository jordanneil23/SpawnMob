package com.jordanneil23.SpawnMob;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermissionsHandler {
	public static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	public static PermissionManager PermissionsEX = null;
	public static PermissionHandler Permissions = null;
	public static boolean PermsEX = false;
	public static boolean Perms = false;
	public static boolean SuperPerms = true;
    

    public static void setupPermissions() {
        Perms = Bukkit.getServer().getPluginManager().isPluginEnabled("Permissions");
        PermsEX = Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx");
        if (Permissions == null) {
            if (Perms != false || PermsEX != false && SuperPerms != false) {
            	if (PermsEX == true){
            		PermissionsEX = PermissionsEx.getPermissionManager();
            		 log.info("[SpawnMob] Using PermissionsEX.");
            		 SuperPerms = false;
            	}else{
            		if (Perms == true){
                    	Plugin IT = Bukkit.getServer().getPluginManager().getPlugin("Permissions");
                        Permissions = ((Permissions)IT).getHandler();
                        log.info("[SpawnMob] Using Permissions/SuperpermBridge.");
                        SuperPerms = false;
            		}
            	}
            } else {
            	if (SuperPerms == false || SuperPerms == true){
            		if (SuperPerms == true){
            			log.info("[SpawnMob] Using SuperPerms.");
            			SuperPerms = true;
            		}else{}
            	}else{
            	Logger.getLogger("Minecraft").warning("[SpawnMob] Permission system not detected! Using ops.txt!");
                Logger.getLogger("Minecraft").warning("[SpawnMob] Please go into the SpawnMob.properties and set use-permissions to false.");
                Main.permissions = false;
            	}
            }
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
    
    public static boolean playerhas(Player p, String perm, boolean perms){
    	if (perms == false)
    	{
    		if (!p.isOp())
    			return false;
    		else 
    			return true;
    	}
    	if (perms == true)
    	{
    		if (((SuperPerms ? !p.hasPermission(perm) : (PermsEX ? !PermissionsEX.has(p, perm) : !Permissions.has(p, perm)))))
    			return false;
    		else
    			return true;
    	} 
		return true;
    }
}

