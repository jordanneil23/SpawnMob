package com.jordanneil23.SpawnMob;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionsHandler {
	public static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	public static PermissionManager PermissionsEX = null;
	public static boolean PermsEX = false;
	public static boolean SuperPerms = true;
    

    public static void setupPermissions() {
        PermsEX = Main.permsex;  //Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx");
        SuperPerms = Main.superperms;
            if (PermsEX != false || SuperPerms != false) {
            	if (PermsEX == true){
            		PermissionsEX = PermissionsEx.getPermissionManager();
            		 log.info("[SpawnMob] Using PermissionsEX.");
            		 SuperPerms = false;
            		 return;
            	}else if (SuperPerms == true){
            			log.info("[SpawnMob] Using SuperPerms.");
            			SuperPerms = true;
            			return;
            		}
            	return;
            	}else{
            	Logger.getLogger("Minecraft").warning("[SpawnMob] Permission system not detected! Using ops.txt!");
                Logger.getLogger("Minecraft").warning("[SpawnMob] Please go into the SpawnMob.properties and set use-permissions to false.");
                Main.permissions = false;
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
    		if (((SuperPerms ? !p.hasPermission(perm) : (PermsEX ? !PermissionsEX.has(p, perm) : p.isOp()))))
    			return false;
    		else
    			return true;
    	} 
		return true;
    }
}

