package com.jordanneil23.SpawnMob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Kit {

	  public static ArrayList kit = new ArrayList();
	  public static ArrayList kit2 = new ArrayList();

	  private static String config_comment = "SpawnMob Kits Configuration File.";


		  
		  public static void saveDefaultSettings() { Properties props = new Properties();
		  props.setProperty("#To spawn many of the same mob repeat the mob name like this-", "");
		  props.setProperty("#Example", "cow,cow,cow,wolf,wolf,wolf,pig,pig,pig");
		  props.setProperty("#Kits - ", "(Kits go here)");
		  props.setProperty("#Letter case does not matter", "");
		  props.setProperty("default", "pig,cOw,CiCkEn,WOLF");
		    try {
		    	File Kits = new File("plugins/SpawnMob/Kits");
		    	if(!Kits.exists()){
		    		Kits.mkdir();
		    	}
		      OutputStream propOut = new FileOutputStream(new File("plugins/SpawnMob/Kits/kits.properties"));
		      props.store(propOut, config_comment);
		    } catch (IOException ioe) {
		      System.out.print(ioe.getMessage());
		    }
		  }
		  
		  public static boolean loadSettings(String kitname, Player p, Location loc)
		  {
		    Properties props = new Properties();
		    try {
		      props.load(new FileInputStream("plugins/SpawnMob/Kits/kits.properties"));
		      if (props.containsKey(kitname)) {
		        String suspects = props.getProperty(kitname, "");
		        if (suspects.length() > 0) {
		          String[] mobs = suspects.toUpperCase().split(",");
		          if (mobs.length > 0)
		          {
		            kit = new ArrayList(Arrays.asList(mobs));
		            GiveKit(p, loc);
		            return true;
		          }
		          else {
		            p.sendMessage("No mobs could be spawned, this mob kit is empty!");
		            return false;
		          }
		        } else {
		        	p.sendMessage("No mobs could be spawned, this mob kit is empty!");
		        	return false;
		        }
		      } else {
		    	  return false;
		      }
		    } catch (IOException ioe) {
		      saveDefaultSettings();
		      return false;
		    }
		  }
		  
		  public static void loadAllKits(Player p)
		  {
		    Properties props = new Properties();
		    try {
		        props.load(new FileInputStream("plugins/SpawnMob/Kits/kits.properties"));
		        Set<Object> suspects = props.keySet();
		        kit2 = new ArrayList(Arrays.asList(suspects)); 
		        p.sendMessage(ChatColor.BLUE + "Mob Kits:");
		        for (Iterator iter2 = kit2.iterator(); iter2.hasNext();){
		        p.sendMessage(ChatColor.BLUE + "" + iter2.next());
		        }
		    } catch (IOException ioe) {
		      saveDefaultSettings();
		    }
		  }
		  
			public static void GiveKit(Player p, Location loc){
		        World world = p.getWorld();
				for (Iterator iter = kit.listIterator(); iter.hasNext();){
			       world.spawnCreature(loc, CreatureType.valueOf(iter.next().toString().toUpperCase()));
				}
			}
}
