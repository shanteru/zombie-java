# A Zombie Apocalypse Game in Java 

Main driver code is in ```src/game``` folder

I contributed in coding the game logic and design which can be found in ```design-docs``` 

```doc``` contains all the java doc of each classes and methods

## Game Features

### Enemies

- Zombies are a hostile threat to the Villagers. They heal from attacks and utilise weapons to diversify attack methods. 

- Mambo Marie, a Voodoo priestess boss, significantly increases the challenge by summoning zombies. Defeating her is essential to quell the outbreak. She will constantly respawn as long her grave is not destroyed. 

### Player Interactions

- A crafting system for weapon creation and farmers who cultivate health-restoring crops.

- Combat mechanics include zombies losing limbs, impacting their effectiveness and limbs acting as craft material.

- Range Weapons are available: 
  - a shotgun - strategic area damage at close range with a 75% hit chance
  -  a sniper rifle - offers increased damage and hit probability with aiming, including a guaranteed one-hit kill after two aiming rounds.

### Travelling

- A Town map acts as a safe zone, offering shops for purchasing advanced weapons and essential materials for crafting, accessible via vehicle in the Wild. 

- Time behaves the same way when you are in Town, the village will be at risk of zombie invasions if you do not return on time. 

## Reflection after the Project

1. Some inheritance and enumerable could have been better as an interface and implement certain capabilities (interface)
2. There are certain hint of downcasting which did suggest there may be a better design implementation which completely eliminates downcasting
3. Though most parts are extensible, however with the lack of capabilities interfaces, there are still some rigid areas.





