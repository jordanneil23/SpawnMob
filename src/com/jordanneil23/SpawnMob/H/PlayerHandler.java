package com.jordanneil23.SpawnMob.H;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class PlayerHandler {
	
	public static boolean isPlayerOnline(String args){
		 boolean online = Bukkit.getServer().getOnlinePlayers().contains(args.trim());
		 if(online == true){
			 return true;
		 }else{
			 return false;
		 }
	 }
	public static Player getOnlinePlayer(String args){
		 Player p = null;
		 Player p2 = null;
		 Iterator<? extends Player> localIterator = Bukkit.getOnlinePlayers().iterator();
		 
        for(int i = 1; i < args.length(); i++)
        {
        	for (localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext();){
        		p2 = (Player) localIterator;
        		if(p2.getName().equalsIgnoreCase(args.trim())){
        			p = p2;
        			return p;
        		}else{
        			return p;
        		}
        		
        	}
        }
		return p;
	 }
	
	public static int convertStringtoInt(String args)  throws NumberFormatException {
		   try{
		    int aInt = Integer.parseInt(args);
			return aInt;
		   }catch (Exception e) {
	           return 1;
	       }
		  }

}
