package com.jordanneil23.SpawnMob;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.jordanneil23.SpawnMob.C.Commands;
import com.jordanneil23.SpawnMob.C.cCommands;
import com.jordanneil23.SpawnMob.C.sCommands;
import com.jordanneil23.SpawnMob.H.ConfigurationHandler;
import com.jordanneil23.SpawnMob.H.PermissionsHandler;
import com.jordanneil23.SpawnMob.H.Updater;
import com.jordanneil23.SpawnMob.H.Updater.ReleaseType;
import com.jordanneil23.SpawnMob.K.Kit;
import com.jordanneil23.SpawnMob.L.SL;

public class Main extends JavaPlugin {
	
	//Config booleans
	public static boolean AutoConfig = true;
	public static boolean autoupdate = false;
	public static boolean ops = true;
	public static boolean permsex = false;
	public static boolean superperms = false;
	//public static boolean spawners = false;
	public static int spawnlimit;
	
	//Updater stuffs
	public static boolean update = false;
	public static String name = "";
	public static ReleaseType type = null;
	public static String version = "";
	public static String link = "";
	
	public static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	private final Commands pHandler = new Commands();
	private final sCommands sHandler = new sCommands();
	private final cCommands cHandler = new cCommands();
	
	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		//Enable the Listener class
		new SL(this);
		//Enable && load configuration files && Permissions
		ConfigurationHandler.firstRun();
		Kit.firstRun();
		ConfigurationHandler.loadYamls();
		PermissionsHandler.setupPermissions();
		
		if(AutoConfig == true){
			log.info("[SpawnMob] AutoConfig is on.");
		}
		if(autoupdate == true){
			log.info("[SpawnMob] AutoUpdate is on.");
		}
		//Updater stuffs
		Updater updater = new Updater(this, 31424, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
		update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
		name = updater.getLatestName();
		version = updater.getLatestGameVersion(); 
		type = updater.getLatestType(); 
		link = updater.getLatestFileLink(); 
		
		if (update == true) {
			//Will not download if the autoupdate setting is false
			if(autoupdate == false)
			{
				log.info("[SpawnMob] Version " + pdf.getVersion() + " enabled.");
				log.info("An update is available: " + name + ", a " + type + " for " + version + " available at " + link);
				log.info("Use /sm update if you would like to automatically update.");
			}
			else
			{
				log.info("[SpawnMob] Version " + pdf.getVersion() + " enabled.");
				log.info("[SpawnMob] An update is available, starting download because AutoUpdate is set to true.");
			}

		}else{
			log.info("[SpawnMob] Version " + pdf.getVersion() + " enabled.");
			log.info("[SpawnMob] You have the current version.");
		}
		
	}
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		ConfigurationHandler.saveYamls();
		log.info("[SpawnMob] Version " + pdf.getVersion() + " disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(sender instanceof Player){
			if((command.getName().equalsIgnoreCase("SpawnMob") || 
					command.getName().equalsIgnoreCase("SM") || 
					command.getName().equalsIgnoreCase("SMob")) &&
					args.length != 0 &&
					args[0].equalsIgnoreCase("Update")){
				
				if(updateSM() == true){
					sender.sendMessage("Downloading update, read the console for further info.");
					return true;
				}else{
					sender.sendMessage("No update was found!");
					return false;
				}
			}
			return pHandler.perform(sender, command, args);
		}
		else if(sender instanceof ConsoleCommandSender)
		{
			if(command.getName().equalsIgnoreCase("SpawnMob") || command.getName().equalsIgnoreCase("SM") || command.getName().equalsIgnoreCase("SMob")){
				if(args.length == 0){
					log.info("[SpawnMob] Console commands:");
					log.info("/" + command.getName() + " update - Update SpawnMob");
					return true;
				}
				if (args[0].equalsIgnoreCase("Update")){
					if(updateSM() == true){
						log.info("");
						return true;
					}else{
						log.info("No update was found!");
						return false;
					}
				}else{
					log.info("[SpawnMob] '" + args[0] + "' is not a valid command.");
					return true;
				}
			}
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
	
	public boolean updateSM(){
		if(update == true){
			new Updater(this, 31424, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
			return true;
		}else{
			return false;
		}
	}

}
