package com.jordanneil23.SpawnMob.M;

import java.util.List;

import org.bukkit.entity.*;

import com.jordanneil23.SpawnMob.H.MobHandler;

public class KillMobs {

	public static LivingEntity EntityCheck(LivingEntity e, String s){
		String mobType = e.toString().toLowerCase().replace("craft", "");
		if(mobType.equals(s))
		{
			return e;
		}
		return null;
	}
	public static void Kill(Player p, String type, String args) {
		ReallyKill(p, type, args, false);
		return;
	}
    public static void Kill(Player p, String type) {
		ReallyKill(p, type, "50", true);
		return;
	}
	
	public static void ReallyKill(Player p, String type, String args, boolean z) {
		int i = 0;
		if (z == true || MobHandler.convertStringtoInt(args) != 0)
		{
			i = Math.max(1, 50);
		}else{
			i = args.length() > 1 ? Math.max(1, Integer.parseInt(args)) : Math.max(1, 50);
		}
		List<Entity> w = p.getNearbyEntities(i, i, i);
        for (Entity m : w)
        {
					if (m instanceof Animals) {
						if (m instanceof Bat && (type.equalsIgnoreCase("Bat") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						}else if (m instanceof Chicken && (type.equalsIgnoreCase("Chicken") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						} else if (m instanceof Cow && (type.equalsIgnoreCase("Cow") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						}else if (m instanceof Horse && (type.equalsIgnoreCase("Horse") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						}else if (m instanceof IronGolem && (type.equalsIgnoreCase("IronGolem"))) {
							m.remove();
						}else if (m instanceof Pig && (type.equalsIgnoreCase("Pig") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						} else if (m instanceof Sheep && (type.equalsIgnoreCase("Sheep") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						} else if (m instanceof MushroomCow && (type.equalsIgnoreCase("MushroomCow") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						}else if (m instanceof MushroomCow && (type.equalsIgnoreCase("Ocelot") || type.equalsIgnoreCase("Cat") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
								m.remove();
						} else if (m instanceof Ocelot && (type.equalsIgnoreCase("Ocelot") || type.equalsIgnoreCase("Cat") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							if (((Ocelot) m).isTamed() == false) {
								m.remove();
					        	}else{}
						}else if (m instanceof Ocelot && (type.equalsIgnoreCase("TOcelot") || type.equalsIgnoreCase("TCat"))) {
							 if (((Ocelot) m).isTamed() == true) {
								 m.remove();
						        	}else{}
						} else if (m instanceof Snowman && ((type.equalsIgnoreCase("SnowMan") || type.equalsIgnoreCase("SnowGolem")) || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						} else if (m instanceof Rabbit && ((type.equalsIgnoreCase("Rabbit") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals")))) {
							m.remove();
						} else if (m instanceof Wolf && ((type.equalsIgnoreCase("Wolf") || type.equalsIgnoreCase("Wolves")) || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							if (((Wolf) m).isTamed() != true) {
								m.remove();
					        	}else{}
						}else if (m instanceof Wolf && type.equalsIgnoreCase("TWolf")) {
							 if (((Wolf) m).isTamed() == true) {
								 m.remove();
						        	}else{}
						}
					}
					else if (m instanceof Monster) {
						if (m instanceof Zombie && (type.equalsIgnoreCase("Zombie") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						}else if (m instanceof Blaze && (type.equalsIgnoreCase("Blaze") || type.equalsIgnoreCase("All"))) {
							m.remove();
						} else if (m instanceof Creeper && (type.equalsIgnoreCase("Creeper") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof PigZombie && ((type.equalsIgnoreCase("PigZombie") || type.equalsIgnoreCase("Pig_zombie"))  || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Giant && (type.equalsIgnoreCase("Giant") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof MagmaCube && ((type.equalsIgnoreCase("Magma_Cube") || type.equalsIgnoreCase("MagmaCube")) || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Skeleton && (type.equalsIgnoreCase("Skeleton") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Spider && (type.equalsIgnoreCase("Spider") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Slime && (type.equalsIgnoreCase("Slime") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Silverfish && (type.equalsIgnoreCase("Silverfish") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof CaveSpider && (type.equalsIgnoreCase("CaveSpider") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Enderman && (type.equalsIgnoreCase("EnderMan") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Witch && (type.equalsIgnoreCase("Witch") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Endermite && (type.equalsIgnoreCase("Endermite") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						} else if (m instanceof Guardian && (type.equalsIgnoreCase("Guardian") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						}
					}
					else if (m instanceof WaterMob) {
						if (m instanceof Squid && (type.equalsIgnoreCase("Squid") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
							m.remove();
						} else if (m instanceof Guardian && (type.equalsIgnoreCase("Guardian") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
							m.remove();
						}
					}
				else if (m instanceof Flying) {
					if (m instanceof Ghast && (type.equalsIgnoreCase("Ghast") || type.equalsIgnoreCase("All"))) {
						m.remove();
					}
				} 
				if (m instanceof EnderDragon && ((type.equalsIgnoreCase("EnderDragon") || type.equalsIgnoreCase("Ender_Dragon") || type.equalsIgnoreCase("Dragon")) || (type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters")))) {
					m.remove();
				}
				if (m instanceof Wither && (type.equalsIgnoreCase("Wither") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
					m.remove();
				}
				if (m instanceof Villager && (type.equalsIgnoreCase("Villager") || type.equalsIgnoreCase("NPC") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Animals"))) {
					m.remove();
				} else if (m instanceof Slime && (type.equalsIgnoreCase("Slime") || type.equalsIgnoreCase("All") || type.equalsIgnoreCase("Monsters"))) {
					m.remove();
				} 
			}
			return;
        }
	}