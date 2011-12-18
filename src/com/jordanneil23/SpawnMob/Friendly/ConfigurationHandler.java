package com.jordanneil23.SpawnMob.Friendly;

import java.io.IOException;

import com.jordanneil23.SpawnMob.SpawnMob;

public class ConfigurationHandler {
    
    public static void firstRun() throws Exception {
        if(!SpawnMob.Friendlyconfig.exists()){
        	SpawnMob.Friendlyconfig.getParentFile().mkdirs();
        	// Default Settings
        	SpawnMob.fconfig.set("Friendly.Mobs.PIG.Day.Nature", "Passive");
            SpawnMob.fconfig.set("Friendly.Mobs.PIG.Night.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.COW.Day.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.PIG.Night.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.SHEEP.Day.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.SHEEP.Night.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.CHICKEN.Day.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.CHICKEN.Night.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.MUSHROOM_COW.Day.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.MUSHROOM_COW.Night.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.VILLAGER.Day.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.VILLAGER.Night.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.SQUID.Day.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.SQUID.Night.Nature", "Passive");
			SpawnMob.fconfig.set("Friendly.Mobs.PIG_ZOMBIE.Night.Nature", "Neutral");
			SpawnMob.fconfig.set("Friendly.Mobs.SPIDER.Day.Nature", "Neutral");
			SpawnMob.fconfig.set("Friendly.Mobs.SPIDER.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.CAVE_SPIDER.Day.Nature", "Neutral");
			SpawnMob.fconfig.set("Friendly.Mobs.CAVE_SPIDER.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.ZOMBIE.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.ZOMBIE.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.SKELETON.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.SKELETON.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.CREEPER.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.CREEPER.Day.Explode", "Yes");
			SpawnMob.fconfig.set("Friendly.Mobs.CREEPER.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.CREEPER.Night.Explode", "Yes");
			SpawnMob.fconfig.set("Friendly.Mobs.SLIME.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.SLIME.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.GHAST.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.GHAST.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.WOLF.Day.Nature", "Neutral");
			SpawnMob.fconfig.set("Friendly.Mobs.WOLF.Night.Nature", "Neutral");
			SpawnMob.fconfig.set("Friendly.Mobs.ENDER_DRAGON.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.ENDER_DRAGON.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.SILVERFISH.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.SILVERFISH.Night.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.ENDERMAN.Day.Nature", "Neutral");
			SpawnMob.fconfig.set("Friendly.Mobs.ENDERMAN.Day.PickupBlocks", "Yes");
			SpawnMob.fconfig.set("Friendly.Mobs.ENDERMAN.Night.Nature", "Neutral");
			SpawnMob.fconfig.set("Friendly.Mobs.ENDERMAN.Night.PickupBlocks", "Yes");
			SpawnMob.fconfig.set("Friendly.Mobs.BLAZE.Day.Nature", "Aggressive");
			SpawnMob.fconfig.set("Friendly.Mobs.BLAZE.Night.Nature", "Aggressive");
			SpawnMob.fconfig.save(SpawnMob.Friendlyconfig);
        }
    }
    
    public static void saveYamls() {
        try {
        	SpawnMob.fconfig.save(SpawnMob.Friendlyconfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadYamls() {
        try {
        	SpawnMob.fconfig.load(SpawnMob.Friendlyconfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
