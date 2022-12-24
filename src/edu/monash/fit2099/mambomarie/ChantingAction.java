package edu.monash.fit2099.mambomarie;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Zombie;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Action for mambo marie to chant. 
 * @author leong
 *
 */
public class ChantingAction extends Action{
	
	/**
	 * Method to execute the action. It will first print a line 
	 * stating the MamboMarie is chanting. Then it will placed 5 Zombies 
	 * randomly on any location which there is no actor standing on it and that
	 * location's ground is Dirt.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		System.out.println(actor + " sings " + "ma-la-xiang-guo");
		int xMax = map.getXRange().max();
		int yMax = map.getYRange().max();
		
		for (int i = 0; i<5; i++) {
			Location spawnLocation;
			do {
				int xRand = ThreadLocalRandom.current().nextInt(0, xMax);
				int yRand = ThreadLocalRandom.current().nextInt(0, yMax);
				spawnLocation = map.at(xRand, yRand);
			}
			while(spawnLocation.containsAnActor() || spawnLocation.getGround().getDisplayChar() != '.');
			spawnLocation.addActor(new Zombie(randomNameGenerator()));
		}
		
		return menuDescription(actor);
	}
	
	private String randomNameGenerator() {
		String source = "abcdefghijklmnopqrstuvwxyz";
		Random rand = new Random();
		String generated = "";
		for (int i=0; i<5; i++) {
			char letter = source.charAt(rand.nextInt(source.length()));
			generated += letter;
		}
		return generated;
	}
	
	/**
	 * Description of the chanting action. 
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return actor + " chants. 5 new Zombies are spawned! ";
	}

}
