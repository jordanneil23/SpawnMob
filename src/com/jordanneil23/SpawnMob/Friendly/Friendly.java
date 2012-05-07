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

	public static EntityType getEntityType(Entity entity) {
		if (entity instanceof LivingEntity) {
			if (entity instanceof Creature) {
				if (entity instanceof Animals) {
					if (entity instanceof Chicken) {
						return EntityType.CHICKEN;
					} else if (entity instanceof Cow) {
						return EntityType.COW;
					} else if (entity instanceof Pig) {
						return EntityType.PIG;
					} else if (entity instanceof Sheep) {
						return EntityType.SHEEP;
					} else if (entity instanceof MushroomCow) {
						return EntityType.MUSHROOM_COW;
					} else if (entity instanceof Villager) {
						return EntityType.VILLAGER;
					} else if (entity instanceof Wolf) {
						return EntityType.WOLF;
					}
				}
				else if (entity instanceof Monster) {
					if (entity instanceof Zombie) {
						return EntityType.ZOMBIE;
					} else if (entity instanceof Creeper) {
						return EntityType.CREEPER;
					} else if (entity instanceof PigZombie) {
						return EntityType.PIG_ZOMBIE;
					} else if (entity instanceof Giant) {
						return EntityType.GIANT;
					} else if (entity instanceof Skeleton) {
						return EntityType.SKELETON;
					} else if (entity instanceof Spider) {
						return EntityType.SPIDER;
					} else if (entity instanceof Slime) {
						return EntityType.SLIME;
					} else if (entity instanceof EnderDragon) {
						return EntityType.ENDER_DRAGON;
					} else if (entity instanceof Silverfish) {
						return EntityType.SILVERFISH;
					} else if (entity instanceof CaveSpider) {
						return EntityType.CAVE_SPIDER;
					} else if (entity instanceof Enderman) {
						return EntityType.ENDERMAN;
					}
				}
				else if (entity instanceof WaterMob) {
					if (entity instanceof Squid) {
						return EntityType.SQUID;
					}
				}
			}
			else if (entity instanceof Flying) {
				if (entity instanceof Ghast) {
					return EntityType.GHAST;
				}else if (entity instanceof Blaze) {
					return EntityType.BLAZE;
				}
			}
		}
		return null;
	}
	
	public EntityType findType(String mob) {
		for (EntityType type : EntityType.values()) {
			if (type.name().equalsIgnoreCase(mob))
				return type;
			else if (type.name().replaceAll("_", "").equalsIgnoreCase(mob))
				return type;
		}
		return null;
	}
}
