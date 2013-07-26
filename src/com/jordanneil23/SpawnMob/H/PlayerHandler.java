package com.jordanneil23.SpawnMob.H;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerHandler {
	
	public static boolean getOnlinePlayer(String args){
		 Player players[] = Bukkit.getServer().getOnlinePlayers();
       for(int i = 1; i < args.length(); i++)
       {
           for(int k = 0; k < players.length;)
           {
               if(players[k].getName().equalsIgnoreCase(args.trim()))
               {
                   return true;
               }else{
            	   return false;
               }
           }
       }
		return false;
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
