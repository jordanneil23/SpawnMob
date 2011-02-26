package com.jordanneil23.SpawnMob;

import java.util.HashMap;

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
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import java.util.*;

/**
 * SpawnMob for Bukkit
 *
 * @author jordanneil23
 * @author xmlns
 */
public class SpawnMob extends JavaPlugin {
	private final SpawnerListener blockListener = new SpawnerListener(this);
	public java.util.logging.Logger log = java.util.logging.Logger.getLogger("Minecraft");
	public static PermissionHandler Permissions = null;
    private final SMPlayerListener playerListener = new SMPlayerListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    
    public void onEnable() {
    	PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_COMMAND, this.playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_RIGHTCLICKED, blockListener, Event.Priority.Normal, this);
        PluginDescriptionFile pdfFile = this.getDescription();
        log.info(String.format("[SpawnMob]" + " Version " + pdfFile.getVersion() + " enabled." ));
        setupPermissions();
    }
    
    public void onDisable() {
    	PluginDescriptionFile pdfFile = this.getDescription();
    	log.info( "[SpawnMob]" + " Version " + pdfFile.getVersion() + " disabled.");
    }
    
    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
    
    private void setupPermissions() {
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

        if (SpawnMob.Permissions == null) {
            if (test != null) {
                this.getServer().getPluginManager().enablePlugin(test); // This line.
                SpawnMob.Permissions = ((Permissions)test).getHandler();
            } else {
                log.info("[SpawnMob] Permissions not detected, defaulting to OP");
            }
        }
    }
    
    public static boolean playerCanUse(Player p, String command){
    	if(SpawnMob.Permissions != null){
    		return SpawnMob.Permissions.permission(p, command);
    	}else{
    		return p.isOp();
    	}
    }
    
    public boolean isMonster(LivingEntity e)
    {
        return (e instanceof Creeper) || (e instanceof Monster) || (e instanceof Skeleton) || (e instanceof Spider) || (e instanceof Zombie) || (e instanceof PigZombie) || (e instanceof Ghast) || (e instanceof Giant) || (e instanceof Slime);
    }

    public boolean isAnimal(LivingEntity e)
    {
        return (e instanceof Chicken) || (e instanceof Cow) || (e instanceof Monster) || (e instanceof Sheep) || (e instanceof Squid) || (e instanceof Pig);
    }
    
    public boolean isHuman(LivingEntity e)
    {
        return (e instanceof Player);
    }


	public void KillMobs(World world, String type)
    {
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
            } else
                if(isHuman(m) && (type.equals("players")))
                {
                    m.setHealth(0);
                }
        }

    }
}
