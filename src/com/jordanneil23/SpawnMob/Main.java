package com.jordanneil23.SpawnMob;

import java.io.File;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.jordanneil23.SpawnMob.C.Commands;
import com.jordanneil23.SpawnMob.C.cCommands;
import com.jordanneil23.SpawnMob.C.sCommands;
import com.jordanneil23.SpawnMob.H.ConfigurationHandler;
import com.jordanneil23.SpawnMob.H.PermissionsHandler;
import com.jordanneil23.SpawnMob.L.SL;

public class Main extends JavaPlugin {
	public static boolean permissions = true;
	public static boolean permsex = false;
	public static boolean superperms = false;
	public static boolean spawners = true;
	public static int spawnlimit;
	public static File configfile;
	public static FileConfiguration sconfig;
	public static File kitfile;
	public static FileConfiguration skit;
	public static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	private final Commands pHandler = new Commands();
	private final sCommands sHandler = new sCommands();
	private final cCommands cHandler = new cCommands();
	
	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		configfile = new File("plugins/SpawnMob/config.yml");
		sconfig = new YamlConfiguration();
		new SL(this);
		try {
			ConfigurationHandler.firstRun();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		//WILL IMPLEMENT BUKKIT UPDATE API LATER
		ConfigurationHandler.loadYamls();
		if(permissions == true)
			PermissionsHandler.setupPermissions();
		else
			log.info("[SpawnMob] Using ops.txt!");
			log.info("[SpawnMob] Version " + pdf.getVersion() + " enabled.");
	}
	public void onDisable(){
		ConfigurationHandler.saveYamls();
		PluginDescriptionFile pdf = this.getDescription();
		log.info("[SpawnMob] Version " + pdf.getVersion() + " disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(sender instanceof Player){
		return pHandler.perform(sender, command, args);
		}
		else if(sender instanceof ConsoleCommandSender)
		{
			sender.sendMessage("Console commands are not supported YET.");
			return sHandler.perform(sender, command, args);
		}
		else if(sender instanceof BlockCommandSender)
		{
			sender.sendMessage("Commandblocks are not supported YET.");
			return cHandler.perform(sender, command, args);
		}
		else
		{
			return false;
		}
}

}
