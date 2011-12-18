package com.jordanneil23.SpawnMob.Friendly;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Creature;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EndermanPickupEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

import com.jordanneil23.SpawnMob.SpawnMob;

public class FriendlyMobListener extends EntityListener {
	private List<Creature> attacked = new ArrayList<Creature>();

	public FriendlyMobListener(final SpawnMob SpawnMob) {
	}

	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() instanceof Creature) {
			Creature c = (Creature) event.getEntity();
			attacked.remove(c);
		}
	}

	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		CreatureType cType = Friendly.getCreatureType(event.getEntity());
		if (cType != null) {
			if (event.getEntity().getLocation().getWorld().getTime() < 12000 || event.getEntity().getLocation().getWorld().getTime() == 24000) {
				String natureLightNode = "Friendly.Mobs." + cType.getName().toUpperCase() + ".Day.Nature";
				String data = SpawnMob.fconfig.getString(natureLightNode);
				if (Friendly.shouldTarget(event.getEntity(), data, true)) {
					Creature c = (Creature) event.getEntity();
					if (event.getDamager() instanceof LivingEntity) {
						c.setTarget((LivingEntity) event.getDamager());
						if (!attacked.contains(c)) {
							attacked.add(c);
						}
					}
				}
			} else {
				String natureNightNode = "Friendly.Mobs." + cType.getName().toUpperCase() + ".Night.Nature";
				String data = SpawnMob.fconfig.getString(natureNightNode);
				if (Friendly.shouldTarget(event.getEntity(), data, true)) {
					Creature c = (Creature) event.getEntity();
					if (event.getDamager() instanceof LivingEntity) {
						c.setTarget((LivingEntity) event.getDamager());
						if (!attacked.contains(c)) {
							attacked.add(c);
						}
					}
				}
			}
		}
	}
	public void onEntityExplode(EntityExplodeEvent event) {
		CreatureType cType = Friendly.getCreatureType(event.getEntity());
		if (cType != null) {
			if (cType == CreatureType.CREEPER) {
				if (event.getEntity().getLocation().getWorld().getTime() < 12000 || event.getEntity().getLocation().getWorld().getTime() == 24000) {
					String natureExplodeDayNode = "Friendly.Mobs.CREEPER.Day.Explode";
					String data = SpawnMob.fconfig.getString(natureExplodeDayNode);
					Creature c = (Creature) event.getEntity();
					if (Friendly.shouldExplode(event.getEntity(), data, attacked.contains(c)) != true) {
						event.setCancelled(true);
					}
				} else {
					String natureExplodeNightNode = "Friendly.Mobs.CREEPER.Night.Explode";
					String data = SpawnMob.fconfig.getString(natureExplodeNightNode);
					Creature c = (Creature) event.getEntity();
					if (Friendly.shouldExplode(event.getEntity(), data, attacked.contains(c)) != true) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
	public void onEnderManPickup(EndermanPickupEvent event) {
		CreatureType cType = Friendly.getCreatureType(event.getEntity());
		if (cType != null) {
			if (cType == CreatureType.ENDERMAN) {
				if (event.getEntity().getLocation().getWorld().getTime() < 12000 || event.getEntity().getLocation().getWorld().getTime() == 24000) {
					String naturePickupDayNode = "Friendly.Mobs.ENDERMAN.Day.PickupBlocks";
					String data = SpawnMob.fconfig.getString(naturePickupDayNode);
					Creature c = (Creature) event.getEntity();
					if (Friendly.pickupBlocks(event.getEntity(), data, attacked.contains(c)) != true) {
						event.setCancelled(true);
					}
				} else {
					String naturePickupNightNode = "Friendly.Mobs.CREEPER.Night.PickupBlocks";
					String data = SpawnMob.fconfig.getString(naturePickupNightNode);
					Creature c = (Creature) event.getEntity();
					if (Friendly.pickupBlocks(event.getEntity(), data, attacked.contains(c)) != true) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	
	@Override
	public void onEntityTarget(EntityTargetEvent event) {
		if (event.getReason() == TargetReason.FORGOT_TARGET
				|| event.getReason() == TargetReason.TARGET_DIED) {
			if (event.getEntity() instanceof Creature) {
				Creature c = (Creature) event.getEntity();
				attacked.remove(c);
			}
		} else if (event.getReason() == TargetReason.TARGET_ATTACKED_ENTITY) {
			if (event.getEntity() instanceof Creature) {
				Creature c = (Creature) event.getEntity();
				if (!attacked.contains(c)) {
					attacked.add(c);
				}
			}
		} else if (!(event.getReason() == TargetReason.CUSTOM)) {
			CreatureType cType = Friendly.getCreatureType(event.getEntity());
			if (cType != null) {
				if (event.getEntity().getLocation().getWorld().getTime() < 12000 || event.getEntity().getLocation().getWorld().getTime() == 24000) {
					String natureLightNode = "Friendly.Mobs." + cType.getName().toUpperCase() + ".Day.Nature";
					String data = SpawnMob.fconfig.getString(natureLightNode);
					Creature c = (Creature) event.getEntity();
					if (!Friendly.shouldTarget(event.getEntity(), data,
							attacked.contains(c))) {
						event.setCancelled(true);
					}
				} else {
					String natureNightNode = "Friendly.Mobs." + cType.getName().toUpperCase() + ".Night.Nature";
					String data = SpawnMob.fconfig.getString(
							natureNightNode);
					Creature c = (Creature) event.getEntity();
					if (!Friendly.shouldTarget(event.getEntity(), data, attacked.contains(c))) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}
