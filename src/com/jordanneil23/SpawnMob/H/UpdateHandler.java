package com.jordanneil23.SpawnMob.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateHandler {
	public static boolean isUpdate( String v) {
		boolean update = false;
		try {
		URL oracle = new URL("https://dl.dropboxusercontent.com/u/16652022/version.txt");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
        	 if(inputLine.equals(v)){
  			   update = false;
  		   }else{
  			   update = true;
  		   }
        in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	if(update == false){
		return false;
	}else{
		return true;
	}
	}
	
}
