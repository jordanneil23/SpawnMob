This is the continuation of SpawnMob for Bukkit that was originally made/developed by xmlns.

Changelog

V1.8.1
Config file now includes mobspawners-have-drops=true/false
Mobs spawn on the ground, not in it.
Slimes no longer cause a error in the console when using /spawnmob slime

V1.8
Added support for use with ops.txt.
(New config file with use-permissions in it)
Works with current permissions

V1.7B
Fixed the plugin.yml derp

V1.7
Added support for the latest CraftBukkit build! Thanks to niccolaspage and androw :D

V1.6B
Successfully hooked into permissions now :/

V1.6
Fully supports Permissions 2.1

V1.5.8
Fully supports Permissions versions 2.2 and 2.1 (Link for 2.2 under downloads)

V1.5.7
really did fix the "Stupidly Long Plugin Constructor" error on startup
Updated to support permissions 2.1(ACTUALLY fixed in 1.5.8)

V1.5.6
Added a new feature! (Right click a mob spawner :D )
Fixed the "Stupidly Long Plugin Constructor" error on startup
This update saved the plugin from going boom (reason stated here: http://forums.bukkit.org/threads/oops-i-broke-your-plugins.599/#post-70677)

V1.5.5
Built on Bukkit #172 tested on CraftBukkit #353. Should work for later versions.
Fixed the WHOLE plugin from exploding.
Thanks to cjc343 for linking me the post that stated that MobType is changing to CreatureType and so on.
Also added some shortcuts to the plugin (/smob, /sm)

V1.5.4
/mspawn delay -10 to 10 - Adds the ability to make mobspawners spawn mobs faster (-10 being the fastest, 10 being the slowest)
Removed /spawnmob kill players - It was way to buggy and pointless, really
Added /spawnmob undo - Kills all mobs
The code is now even messier (if that's a word :P)

V1.5.3
Added the ability to kill players through /spawnmob kill players (will not kill them on /spawnmob kill all) 
Permissions for /spawnmob kill player - spawnmob.kill.others
Changed the messages you get when not allowed to use one of the commands

V1.5.2
Added /spawnmob kill <all-animals-monsters> - Kills the mobs!
Added /mspawn delay <Number Between 1-20>- SHOULD make the mob spawners spawn mobs slower
Permissions node for /mspawn delay - spawnmob.mspawn.delay
/mspawn check now shows the delay a spawner is set to
The code is very messy, but who cares


V1.5.1

Added the ability to spawn "Monsters"
Actually fixed the /mspawn bug this time
Added /mspawn check - Checks what a mob spawner is set to spawn

V1.5

First attempt at fixing the /mspawn bug

   