package com.jordanneil23.SpawnMob.K;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.jordanneil23.SpawnMob.Main;
import com.jordanneil23.SpawnMob.C.Commands;
import com.jordanneil23.SpawnMob.H.PermissionsHandler;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Kit {
	public static ArrayList kit = new ArrayList();
	public static ArrayList kit2 = new ArrayList();
	public static FileConfiguration kits;
	public static File file = new File("plugins" + File.separator + "SpawnMob" + File.separator + "kits.yml");
	public static FileConfiguration config = new YamlConfiguration();
	   
	  
	 public static void firstRun(){
		 if(!file.exists()){
	         file.getParentFile().mkdirs();
			 config.options().header(
	        			"THE EASIEST WAY TO MAKE A KIT IS IN GAME USING /SpawnMob addkit moname,mobename,mobname" + '\n'
	        			+ "Please only use this file to EDIT kits so no errors will occur!");
			 String[] defaults = {"cow", "CoW", "cOw", "COW" };
			 config.set("Kits.example.Mobs", defaults);
			 try {
					config.save(file);
					Main.log.info("[SpawnMob] Successfully saved the default kits.yml file!");
				} catch (IOException e) {
					Main.log.warning("[SpawnMob] Could not save the default kits.yml file! Error:");
					e.printStackTrace();
				}
		 }
		 loadKits();
	   }
	 
	 public static void setKits(Player p, String name, String[] mobs) {
		 loadKits();
	        List<String> m = new ArrayList<String>();
	        kit = new ArrayList(Arrays.asList(mobs));
	        for (Iterator iter = kit.listIterator(); iter.hasNext();){
			       m.add(iter.next().toString().toLowerCase());
				}
	        if (!config.isSet("Kits." + name.toLowerCase())) {
	            config.set("Kits." + name.toLowerCase() +".Mobs", m);
	            try {
	                config.save(file);
	                p.sendMessage(Commands.b + "Successfully added a kit named:" + name);
	            } catch (IOException e) {
	            	p.sendMessage(Commands.r + "Could not add kit: " + name + "! Error:");
	                e.printStackTrace();
	            }
	        }else{
	        	p.sendMessage(Commands.r + "Kit '" + name + "' already exists. You can only edit kits by editing the kit file right now. Sorry.");
	        }
	    }
	 
	 public static void loadKits(){
		 try{
			 config.load(file);
			 return;
		 }catch(Exception e1){
		 e1.printStackTrace();
		 return;
		 }
	 }
	   
		  public static boolean spawn(String k, Player p, Location loc)
		  {
			  loadKits();
			  String kitname = k.toLowerCase();
			  World world = p.getWorld();
		      if (config.isSet("Kits." + kitname)) {
		          Object[] mobs = config.getList("Kits." + kitname + ".Mobs").toArray();
		          if (mobs.length != 0)
		          {
		            kit = new ArrayList(Arrays.asList(mobs));
					for (Iterator iter = kit.listIterator(); iter.hasNext();){
				       world.spawnEntity(loc, EntityType.valueOf(iter.next().toString().toUpperCase()));
					}
		            return true;
		          }
		          else {
		            p.sendMessage(Commands.r + "No mobs could be spawned, this mob kit is empty!");
		            return false;
		          }
		      } else {
		    	  p.sendMessage(Commands.r + "Kit '"+ kitname +"' not found!");
		    	  return false;
		      }
		    }
		  
		  public static void listKits(Player p)
		  {
			  loadKits();
			  Object[] mobs = config.getConfigurationSection("Kits").getKeys(false).toArray();
	          if (mobs.length != 0)
	          {
	            kit = new ArrayList(Arrays.asList(mobs));
	            p.sendMessage("Mob Kits:");
				for (Iterator iter = kit.listIterator(); iter.hasNext();){
					p.sendMessage(iter.next().toString());
				}
	          }
	          else
	          {
	        	  p.sendMessage(Commands.r + "There are no kits!");
	          }
	            return;
		  }
		  
		  public static void setKitPerms()
		  {
			  loadKits();
			  Object[] mobs = config.getConfigurationSection("Kits").getKeys(false).toArray();
	          if (mobs.length != 0)
	          {
	            kit = new ArrayList(Arrays.asList(mobs));
				for (Iterator iter = kit.listIterator(); iter.hasNext();)
				{
		        PermissionsHandler.setPerms("spawnmob.kit." + iter.next().toString());
		        return;
		        }
			  }
	          return;
		  }
		  
}

