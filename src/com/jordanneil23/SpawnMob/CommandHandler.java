/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jordanneil23.SpawnMob;

import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.CreatureType;

import com.jordanneil23.SpawnMob.Mob.MobException;
import com.jordanneil23.SpawnMob.TargetBlock;

import net.minecraft.server.World;
import net.minecraft.server.EntitySlime;

/**
 *Spawnmob Commands
 * @author androw
 * @author jordanneil23
 */
public class CommandHandler{
    private SpawnMob plugin;

    public void CommandListener(SpawnMob instance) {
        plugin = instance;
    }

    public boolean perform(CommandSender sender, Command command, String[] args) {
        int[] ignore = {8, 9};
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("spawnmob") || command.getName().equalsIgnoreCase("sm") || command.getName().equalsIgnoreCase("smob")) {
            if (0 < args.length) {
                if (args[0].equalsIgnoreCase("Kill")) {
                    if (SpawnMob.Permissions.has(p, "spawnmob.kill")) {
                        if (args[1].equalsIgnoreCase("Monsters")) {
                            plugin.KillMobs(p.getWorld(), "monsters");
                            p.sendMessage("Killed all monsters");
                            return true;
                        } else if (args[1].equalsIgnoreCase("Animals")) {
                            plugin.KillMobs(p.getWorld(), "animals");
                            p.sendMessage("Killed all animals");
                            return true;
                        } else if (args[1].equalsIgnoreCase("All")) {
                            p.sendMessage("Killed all mobs");
                            plugin.KillMobs(p.getWorld(), "all");
                            return true;
                        }
                    } else {
                        p.sendMessage("You are not authorized kill mobs.");
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("Undo")) {
                    if (SpawnMob.Permissions.has(p, "spawnmob.kill")) {
                        p.sendMessage("Undid SpawnMob");
                        plugin.KillMobs(p.getWorld(), "all");
                        return true;
                    }
                }
                if (0 < args.length && args.length < 3) {
                    String[] split1 = args[0].split(":");
                    String[] split0 = null;
                    CraftEntity spawned1 = null;
                    Mob mob2 = null;
                    if (split1.length == 1 && !split1[0].equalsIgnoreCase("Slime")) {
                        split0 = args[0].split(";");
                        split1[0] = split0[0];
                    }
                    if (split1.length == 2) {
                        args[0] = split1[0] + "";
                    }
                    Mob mob = Mob.fromName(split1[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split1[0]));
                    if (mob == null) {
                        p.sendMessage("Invalid mob type.");
                        return false;
                    }
                    if (!SpawnMob.Permissions.has(p, "spawnmob.spawnmob." + mob.name.toLowerCase())) {
                        p.sendMessage("You can't spawn this mob.");
                        return false;
                    }
                    World world = ((org.bukkit.craftbukkit.CraftWorld) p.getWorld()).getHandle();
                    CraftEntity spawned = null;
                    try {
                        spawned = mob.spawn(p, plugin);
                    } catch (MobException e) {
                        p.sendMessage("Unable to spawn mob.");
                        return false;
                    }
                    Location loc = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock().getLocation();
                    spawned.teleportTo(loc);
                    world.a(spawned.getHandle());
                    if (split0.length == 2) {
                        mob2 = Mob.fromName(split0[1].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(split0[1]));
                        if (mob2 == null) {
                            p.sendMessage("Invalid mob type.");
                            return false;
                        }
                        try {
                            spawned1 = mob2.spawn(p, plugin);
                        } catch (MobException e) {
                            p.sendMessage("Unable to spawn mob.");
                            return false;
                        }
                        spawned1.teleportTo(spawned);
                        spawned1.getHandle().setPassengerOf(spawned.getHandle());
                        world.a(spawned1.getHandle());
                    }
                    if (split1.length == 2 && mob.name == "Slime") {
                        try {
                            ((EntitySlime) spawned.getHandle()).b(Integer.parseInt(split1[1]));
                        } catch (Exception e) {
                            p.sendMessage("Malformed size.");
                            return false;
                        }
                    }
                    if (args.length == 2) {
                        try {
                            for (int i = 1; i < Integer.parseInt(args[1]); i++) {
                                spawned = mob.spawn(p, plugin);
                                spawned.teleportTo(loc);
                                if (split1.length > 1 && mob.name == "Slime") {
                                    try {
                                        ((EntitySlime) spawned.getHandle()).b(Integer.parseInt(split1[1]));
                                    } catch (Exception e) {
                                        p.sendMessage("Malformed size.");
                                        return false;
                                    }
                                }
                                world.a(spawned.getHandle());
                                if (split0.length == 2) {
                                    if (mob2 == null) {
                                        p.sendMessage("Invalid mob type.");
                                        return false;
                                    }
                                    try {
                                        spawned1 = mob2.spawn(p, plugin);
                                    } catch (MobException e) {
                                        p.sendMessage("Unable to spawn mob.");
                                        return false;
                                    }
                                    spawned1.teleportTo(spawned);
                                    spawned1.getHandle().setPassengerOf(spawned.getHandle());
                                    world.a(spawned1.getHandle());
                                }
                            }
                            p.sendMessage(args[1] + " " + mob.name.toLowerCase() + mob.s + (split0.length == 2 ? " riding " + mob2.name.toLowerCase() + mob2.s : "") + " spawned.");
                        } catch (MobException e1) {
                            p.sendMessage("Unable to spawn mobs.");
                            return false;
                        } catch (java.lang.NumberFormatException e2) {
                            p.sendMessage("Malformed integer.");
                            return false;
                        }
                    } else {
                        p.sendMessage(mob.name + (split0.length == 2 ? " riding a " + mob2.name.toLowerCase() : "") + " spawned.");
                    }
                    return true;
                } else {
                    p.sendMessage("/spawnmob <Mob Name> (Amount)");
                    p.sendMessage("/spawnmob kill <all-animals-monsters>");
                    p.sendMessage("/mspawn - Type for more info");
                    return false;
                }
            } else {
                p.sendMessage("/spawnmob <Mob Name> (Amount)");
                p.sendMessage("/spawnmob kill <all-animals-monsters>");
                p.sendMessage("/mspawn - Type for more info");
                return false;
            }
        } else if (command.getName().equalsIgnoreCase("mspawn")) {
            if (0 < args.length) {
                CreatureType mt = CreatureType.fromName(args[0].equalsIgnoreCase("PigZombie") ? "PigZombie" : capitalCase(args[0]));
                org.bukkit.block.Block blk = (new TargetBlock(p, 300, 0.2, ignore)).getTargetBlock();
                if (args[0].equalsIgnoreCase("Check") || args[0].equalsIgnoreCase("Info")) {
                    if (!SpawnMob.Permissions.has(p, "spawnmob.mspawn.check")) {
                        p.sendMessage("You are not authorized to check a spawner.");
                        return false;
                    }
                    if (blk == null) {
                        p.sendMessage("You must be looking at a Mob Spawner.");
                        return false;
                    }
                    if (blk.getTypeId() != 52) {
                        p.sendMessage("You must be looking at a Mob Spawner.");
                        return false;
                    }
                    CreatureType mob = ((org.bukkit.block.CreatureSpawner) blk.getState()).getCreatureType();
                    int del = ((org.bukkit.block.CreatureSpawner) blk.getState()).getDelay();
                    p.sendMessage("This spawners mob type is " + mob + ".");
                    p.sendMessage("This spawners delay is set to " + del + ".");
                    return true;
                } else if (args[0].equalsIgnoreCase("Delay")) {
                    if (0 < args.length) {
                        if (!SpawnMob.Permissions.has(p, "spawnmob.mspawn.delay")) {
                            p.sendMessage("You are not authorized to set a spawners delay.");
                            return false;
                        }
                        if (blk == null) {
                            p.sendMessage("You must be looking at a Mob Spawner.");
                            return false;
                        }
                        if (blk.getTypeId() != 52) {
                            p.sendMessage("You must be looking at a Mob Spawner.");
                            return false;
                        }
                        if (args[1].equalsIgnoreCase("-10")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-10);
                            p.sendMessage("This spawners delay is now set to -10.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-9")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-9);
                            p.sendMessage("This spawners delay is now set to -9.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-8")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-8);
                            p.sendMessage("This spawners delay is now set to -8.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-7")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-7);
                            p.sendMessage("This spawners delay is now set to -7.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-6")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-6);
                            p.sendMessage("This spawners delay is now set to -6.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-5")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-5);
                            p.sendMessage("This spawners delay is now set to -5.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-4")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-4);
                            p.sendMessage("This spawners delay is now set to -4.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-3")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-3);
                            p.sendMessage("This spawners delay is now set to -3.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-2")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-2);
                            p.sendMessage("This spawners delay is now set to -2.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("-1")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(-1);
                            p.sendMessage("This spawners delay is now set to -1.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("0")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(0);
                            p.sendMessage("This spawners delay is now set to normal.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("1")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(1);
                            p.sendMessage("This spawners delay is now set to 1.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("2")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(2);
                            p.sendMessage("This spawners delay is now set to 2.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("3")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(3);
                            p.sendMessage("This spawners delay is now set to 3.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("4")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(4);
                            p.sendMessage("This spawners delay is now set to 4.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("5")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(5);
                            p.sendMessage("This spawners delay is now set to 5.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("6")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(6);
                            p.sendMessage("This spawners delay is now set to 6.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("7")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(7);
                            p.sendMessage("This spawners delay is now set to 7.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("8")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(8);
                            p.sendMessage("This spawners delay is now set to 8.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("9")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(9);
                            p.sendMessage("This spawners delay is now set to 9.");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("10")) {
                            ((org.bukkit.block.CreatureSpawner) blk.getState()).setDelay(10);
                            p.sendMessage("This spawners delay is now set to 10.");
                            return true;
                        }

                    } else {
                        p.sendMessage("/mspawn delay - <A Number Between -10 to 10>");
                        p.sendMessage("Sets a mob spawners delay to spawn things.");
                        p.sendMessage("-10 being the fastest, 10 being the slowest.");
                        return false;
                    }
                }
                if (mt == null) {
                    p.sendMessage("Invalid mob type.");
                    return false;
                }
                if (!SpawnMob.Permissions.has(p, "spawnmob.mspawn." + mt.name().toLowerCase()) || !SpawnMob.Permissions.has(p, "spawnmob.mspawn.allmobs")) {
                    p.sendMessage("You are not authorized to do that.");
                    return false;
                }
                    if (blk == null) {
                        p.sendMessage("You must be looking at a Mob Spawner.");
                        return false;
                    }
                if (blk.getTypeId() != 52) {
                    p.sendMessage("You must be looking at a Mob Spawner.");
                    return false;
                }
                ((org.bukkit.block.CreatureSpawner) blk.getState()).setCreatureType(mt);
                p.sendMessage("Mob spawner set as " + mt.getName().toLowerCase() + ".");
            } else {
                p.sendMessage("/mspawn <Mob Name> - Set a mob spawner to spawn a mob");
                p.sendMessage("/mspawn check - See a spawners info.");
                p.sendMessage("/mspawn delay - Type for more info");
                p.sendMessage("Right clicking a mobspawner also acts like /mspawn check");
                return false;
            }

        }
        return false;
    }

    private static String capitalCase(String s) {
        return s.toUpperCase().charAt(0) + s.toLowerCase().substring(1);
    }
}
