This is the continuation of SpawnMob for Bukkit that was originally made/developed by xmlns.

Changelog
V3.0
Almost totally re-coded.
Virtually no bugs, the ocelot bugs are Bukkit bugs not this plugins.
Added new configuration (YAML)

V2.1.0
For Minecraft 1.1
New /sm kill (/sm kill (all-animals-monsters) radius)
Added the ability to spawn baby animals
Added the abilty to spawn hostile wolves AKA "mad" wolves (/sm wolf mad)
Added the ability to spawn mobs on players (/sm (baby) mob player (amount))
Added the ability to spawn a mob riding a player and vice-versa. (/sm creeper;player - Will spawn a creeper riding player. /sm player;creeper - Will make player ride a creeper.)
Added the ability to "make" players ride players (/sm player;player)
Fixed the wolf spawning bug , magmacube spawning bug, creeper spawning bug, and the villager spawning bug.
Temporarily removed the Friendly plugin...
Known new bug(s)
You cannot spawn a sheep or colored sheep without an amount (/sm sheep - Results in error. /sm sheep 1 - No error.)
There are probably more...

V2.0
For Minecraft 1.0
Added support for PermissionsEX
Added the Friendly plugin
Added the new 1.0 mobs
The "MagmaCubes" work just like "Slimes" EX. /sm magmacube:10 1 - Spawn a big magmacube
DO NOT SPAWN ENDER DRAGONS IN YOUR MAIN WORLDS!!!!! If you do use /sm kill all or /sm undo or /sm kill enderdragon
Known new bug(s)
May stop animal breeding, if anyone knows a fix please tell me it!

V1.9.9
Fixed the "You can't use a ':' like that" error.
Changed /spawnmob sheep:color amount to /spawnmob sheep color amount.
Fixed slime spawning.
fixed errors with /mspawn.

V1.9.8_TEMPRELEASE
This is a temp release, please don't nag me about errors/things not working.
Broke colored sheep spawning it will display "You can't use a ':' like that" this is my fault and it will be fixed in the next release
Slime size spawning works but will display "You can't use a ':' like that" this is my fault and it will be fixed in the next release

V1.9.8
Updated for Minecraft 1.8.1
Added Enderman, CaveSpider, and SilverFish
Added ability to spawn colored sheep /spawnmob sheep:COLOR
Revamped and optimized code a bit
Changed the way that slimes are spawned, you can use a number to spawn them or these sizes: Tiny, small, medium, large, huge, or colossal.
Changed /spawnmob ecreeper and /spawnmob twolf back to /spawnmob creeper electric and /spawnmob wolf tamed, permissions: spawnmob.creeper.electric and spawnmob.wolf.tamed

V1.9.7A
Added support for SuperPerms (AKA Default permissions)
/spawnmob kill all checks to see if wolves are tamed, if a wolf is not tamed it kills it
/spawnmob kill twolf - Kill tamed wolves (permissions - spawnmob.kill.twolf)

V1.9.7
Standard update, nothing special.

V1.9.6
Fixed bugs to do with spawning electric creepers and tamed wolves
Updated for Minecraft 1.7.2

V1.9.5
Added Mob Kits (/spawnmob kit)
All you have to do is run SpawnMob and type /spawnmob kit list (or /spawnmob kit <kitname>)
Fixed the bugs with the spawning of wolves and creepers
/spawnmob wolf tamed is changed to /spawnmob tamedwolf or /spawnmob twolf
/spawnmob creeper electric is changed to /spawnmob electriccreeper or /spawnmob ecreeper
I may add the sheep color spawning later

V1.9.4
Support for MC 1.6+
/spawnmob wolf <number> now works
If you enter '/spawnmob wolf' with no number a error occurs (same with creepers)

V1.9.3
Attempted to fix bugs that were made in V1.9.2...
/spawnmob wolf does not work now, and I probably wont fix it for a while...
/spawnmob wolf tamed still works

V1.9.2
Added the ability to spawn multiple tamed wolves
Added the ability to spawn electrified creepers
Fixed the /spawnmob kill bug (Yes there was a bug.)
Added a limit for how many mobs you can spawn at once, you can change it in the SpawnMob.properties file

V1.9.1
Added the ability to spawn tamed wolves, permissions - spawnmob.wolf.tamed
You can now use any number for /mspawn delay
Cleaned up the code a bit
Built on CB 740
Tested on CB 740

V1.9
Updated for Minecraft Beta 1.5
Built on CB 732
Tested on CB 732

V1.8.4B
Fixes the /spawnmob kill all says invalid mob type bug/error.

V1.8.3
Added /spawnmob kill MobName for killing individual animals/monsters (Example: /spawnmob kill pig)
Re-added the showing of the /mspawn check info on right click of a mobspawner
Updated some of the /mspawn delay information
NEW PERMISSIONS
Added some more stuff, but I forgot what it was :P

V1.8.3
Updated for Minecraft Beta 1.4
Allows to /mspawn "monsters", wolves, and giants.
You can now spawn wolves (Cheaters :P )

V1.8.2
Updated for CB600
Fixed most if not all Permissions bugs 
(I hope, I tested it a bit and it seemed to fix them)

V1.8.1
Config file now includes mobspawners-have-drops=true/false
Mobs spawn on the ground, not in it.
Slimes no longer cause a error in the console when using /spawnmob slime

V1.8
Added support for use with ops.txt.
(New config file with use-permissions in it)
Works with current permissions.

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

   
