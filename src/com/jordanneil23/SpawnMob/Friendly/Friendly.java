package com.jordanneil23.SpawnMob.Friendly;

import org.bukkit.entity.*;

public class Friendly {
	
	public static boolean shouldTarget(Entity e, String nodeData, boolean attacked) {
		if (nodeData != null) {
			if (nodeData.equalsIgnoreCase("Passive")) {
				return false;
			} else if (nodeData.equalsIgnoreCase("Neutral")) {
				if (attacked) {
					return true;
				} else {
					return false;
				}
			} else if (nodeData.equalsIgnoreCase("Aggressive")) {
				return true;
			}
		}
		if (e instanceof Monster) {
			return true;
		} else {
			return false;
		}
	}
    public static boolean shouldExplode(Entity e, String nodeData, boolean explode) {
		if (nodeData != null) {
			if (nodeData.equalsIgnoreCase("Yes")) {
				return true;
			} else if (nodeData.equalsIgnoreCase("No")) {
					return false;
			}
		}
		return false;
    }
    public static boolean pickupBlocks(Entity e, String nodeData, boolean pickup) {
		if (nodeData != null) {
			if (nodeData.equalsIgnoreCase("Yes")) {
				return true;
			} else if (nodeData.equalsIgnoreCase("No")) {
					return false;
			}
		}
		return false;
    }

	public static CreatureType getCreatureType(Entity entity) {
		if (entity instanceof LivingEntity) {
			if (entity instanceof Creature) {
				if (entity instanceof Animals) {
					if (entity instanceof Chicken) {
						return CreatureType.CHICKEN;
					} else if (entity instanceof Cow) {
						return CreatureType.COW;
					} else if (entity instanceof Pig) {
						return CreatureType.PIG;
					} else if (entity instanceof Sheep) {
						return CreatureType.SHEEP;
					} else if (entity instanceof MushroomCow) {
						return CreatureType.MUSHROOM_COW;
					} else if (entity instanceof Villager) {
						return CreatureType.VILLAGER;
					} else if (entity instanceof Wolf) {
						return CreatureType.WOLF;
					}
				}
				else if (entity instanceof Monster) {
					if (entity instanceof Zombie) {
						return CreatureType.ZOMBIE;
					} else if (entity instanceof Creeper) {
						return CreatureType.CREEPER;
					} else if (entity instanceof PigZombie) {
						return CreatureType.PIG_ZOMBIE;
					} else if (entity instanceof Giant) {
						return CreatureType.GIANT;
					} else if (entity instanceof Skeleton) {
						return CreatureType.SKELETON;
					} else if (entity instanceof Spider) {
						return CreatureType.SPIDER;
					} else if (entity instanceof Slime) {
						return CreatureType.SLIME;
					} else if (entity instanceof EnderDragon) {
						return CreatureType.ENDER_DRAGON;
					} else if (entity instanceof Silverfish) {
						return CreatureType.SILVERFISH;
					} else if (entity instanceof CaveSpider) {
						return CreatureType.CAVE_SPIDER;
					} else if (entity instanceof Enderman) {
						return CreatureType.ENDERMAN;
					}
				}
				else if (entity instanceof WaterMob) {
					if (entity instanceof Squid) {
						return CreatureType.SQUID;
					}
				}
			}
			else if (entity instanceof Flying) {
				if (entity instanceof Ghast) {
					return CreatureType.GHAST;
				}else if (entity instanceof Blaze) {
					return CreatureType.BLAZE;
				}
			}
		}
		return null;
	}
	
	public CreatureType findType(String mob) {
		for (CreatureType creaturetype : CreatureType.values()) {
			if (creaturetype.name().equalsIgnoreCase(mob))
				return creaturetype;
			else if (creaturetype.name().replaceAll("_", "").equalsIgnoreCase(mob))
				return creaturetype;
		}
		return null;
	}
}
