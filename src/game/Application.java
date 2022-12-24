package game;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.guns.Shotgun;
import edu.monash.fit2099.guns.ShotgunBullet;
import edu.monash.fit2099.guns.Sniper;
import edu.monash.fit2099.guns.SniperBullet;
import edu.monash.fit2099.mambomarie.MamboMarieGrave;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		MamboMarieGrave grave = new MamboMarieGrave();
		
		NewWorld newWorld = new NewWorld(new Display(), grave);
		

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####......................######.....................",
		"..............................#########.........####............................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap wildMap = new GameMap(groundFactory, map );
		newWorld.addGameMap(wildMap);
		
		List<String> town = Arrays.asList(
				"............................................................................",
				".........................############################.......................",
				".........................#..........................#...........+...........",
				".........................#..............++..++.++...#.......................",
				"......................####................+...+.....#........+..............",
				"...........................................+++......#.......................",
				"............................................+.......#........++......+......",
				"......................####..........................#...........+...........",
				".........................#..........................#.........++.+.+........",
				".........................#..........................#..........+.++++.......",
				".........................#..........................#..........+..+++.......",
				".........................#..........................#.......................",
				".........................#..................................................",
				".........................#..........................#............+...+......",
				".........................#..........................#.......................",
				".........................############################..........+............",
				"............................................................................");
		
		GameMap townMap = new GameMap(groundFactory, town);
		newWorld.addGameMap(townMap);
		
		Vehicle car = new Vehicle("Car", '~', false);
        car.addAction(new MoveActorAction(townMap.at(30, 11), "to Town!"));
        wildMap.at(42, 16).addItem(car);
        Vehicle truck = new Vehicle("Truck", '~', false);
        
        
        
        truck.addAction(new MoveActorAction(wildMap.at(79,1), "to the Wild!"));
        townMap.at(30,10).addItem(truck);
		
		Actor player = new Player("Player", '@', 15000);
		newWorld.addPlayer(player, wildMap.at(42, 15));

		((Player) player).setWorld(newWorld);
		
		//place some farmers
		wildMap.at(30, 10).addActor(new Farmer("James"));
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy",
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};

		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (wildMap.at(x, y).containsAnActor());
			wildMap.at(x,  y).addActor(new Human(name));	
		}
		
		wildMap.at(0, 0).setGround(grave);
		
		// place a simple weapon
		wildMap.at(74, 20).addItem(new Plank());
		
		// place the gun-related weapon
		townMap.at(30, 6).addItem(new Sniper());
		wildMap.at(42, 16).addItem(new Sniper());
		townMap.at(6, 3).addItem(new Shotgun());
		townMap.at(10, 12).addItem(new ShotgunBullet());
		townMap.at(30, 7).addItem(new SniperBullet());
		
		// place the two shops 
		townMap.at(10,1).addItem(new Shop());
		townMap.at(11,1).addItem(new CraftShop());
		
		// place gold at townMap for testing
		townMap.at(70,2).addItem(new Gold());
		townMap.at(9,3).addItem(new Gold());
		townMap.at(9,4).addItem(new Gold());
		townMap.at(9,5).addItem(new Gold());
		
		// place simple material 
		wildMap.at(3,3).addItem(new Gold());
		wildMap.at(60,3).addItem(new Gold());
		wildMap.at(3,20).addItem(new Gold());
		wildMap.at(60,20).addItem(new Gold());

		// FIXME: Add more zombies!
		wildMap.at(30, 20).addActor(new Zombie("Groan"));
//		wildMap.at(30,  18).addActor(new Zombie("Boo"));
//		wildMap.at(10,  4).addActor(new Zombie("Uuuurgh"));
//		wildMap.at(50, 18).addActor(new Zombie("Mortalis"));
//		wildMap.at(1, 10).addActor(new Zombie("Gaaaah"));
//		wildMap.at(62, 12).addActor(new Zombie("Aaargh"));	
		
//		try {
			newWorld.run();
//		}
//		catch(Exception e){
//			System.out.println(e.getMessage());
//		}
	}
}


	
