package com.jordanneil23.SpawnMob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

/**
 * SpawnMob
 * @version 1.8
 * @author jordanneil23
 * @author xmlns
 */
public class SpawnMob extends JavaPlugin {
	private final SpawnerListener blockListener = new SpawnerListener(this);
	public java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	public static PermissionHandler Permissions;
    private final CommandHandler handler = new CommandHandler();
    private final CommandHandlerNoPerms nopermshandler = new CommandHandlerNoPerms();
    private static final String CONFIG_FILE_NAME = "plugins/SpawnMob/SpawnMob.properties";
    
    public void onEnable() {
    	File confFile = new File(CONFIG_FILE_NAME);
		if(!confFile.exists()) {
			writeConfigFile();
		}
    	loadProps();
    	if (permissions){
    	setupPermissions();
    	PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
        handler.CommandListener(this);
    	}else{
    		PluginDescriptionFile pdfFile = this.getDescription();
    		log.info("[SpawnMob] Version " + pdfFile.getVersion() + " enabled.");
    		log.info("[SpawnMob] Using ops.txt");
    		PluginManager pm = getServer().getPluginManager();
            pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
            nopermshandler.CommandListenerNoPerms(this);
    	}
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
    	if (permissions){
            return handler.perform(sender, command, args); 
    	} else {
    		return nopermshandler.perform(sender, command, args); 
    	}
    }
    
private void setupPermissions() {
    Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
    if (SpawnMob.Permissions == null) {
        if (test != null) {
            SpawnMob.Permissions = ((Permissions)test).getHandler();
            log.info("[SpawnMob] Permission system found, plugin enabled");
        } else {
            log.info("[SpawnMob] Permission system not detected, plugin disabled");
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }
}
    public boolean isMonster(LivingEntity e){
        return (e instanceof Creeper) || (e instanceof Monster) || (e instanceof Skeleton) || (e instanceof Spider) || (e instanceof Zombie) || (e instanceof PigZombie) || (e instanceof Ghast) || (e instanceof Giant) || (e instanceof Slime);
    }

    public boolean isAnimal(LivingEntity e){
        return (e instanceof Chicken) || (e instanceof Cow) || (e instanceof Monster) || (e instanceof Sheep) || (e instanceof Squid) || (e instanceof Pig);
    }
    
    public boolean isHuman(LivingEntity e){
        return (e instanceof Player);
    }


	public void KillMobs(World world, String type){
        List<?> mobs = world.getLivingEntities();
        for(Iterator<?> iterator = mobs.iterator(); iterator.hasNext();)
        {
            LivingEntity m = (LivingEntity)iterator.next();
            if(isAnimal(m) && (type.equals("animals") || type.equals("all")))
            {
                m.setHealth(0);
            } else
            if(isMonster(m) && (type.equals("monsters") || type.equals("all")))
            {
                m.setHealth(0);
            }
    }
	}
}

