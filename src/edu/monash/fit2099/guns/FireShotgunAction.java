package edu.monash.fit2099.guns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.AttackAction;
import game.MenuActionInterface;

/**
 * Action to fire the shotgun.
 * @author leong
 *
 */
public class FireShotgunAction extends AttackAction implements MenuActionInterface{
	
	private HashMap<Character,Direction > keyToDirectionMap = new HashMap<Character, Direction>();
	private Display display;
	private GunWeaponItem gun;
	private String defaultMenu; 
	
	/**
	 * Constructor
	 * @param target actor that is being shoot 
	 * @param gun a GunWeaponItem used to shoot 
	 * @param display display object used to obtain input from user.
	 */
	public FireShotgunAction(Actor target,GunWeaponItem gun,Display display) {
		super(target);
		keyToDirectionMap.put('a', Direction.NORTH);
		keyToDirectionMap.put('b', Direction.SOUTH);
		keyToDirectionMap.put('c', Direction.WEST);
		keyToDirectionMap.put('d', Direction.EAST);
		keyToDirectionMap.put('e', Direction.NORTHEAST);
		keyToDirectionMap.put('f', Direction.NORTHWEST);
		keyToDirectionMap.put('g', Direction.SOUTHEAST);
		keyToDirectionMap.put('h', Direction.SOUTHWEST);
		this.defaultMenu = createMenu();
		Objects.requireNonNull(gun, "Expected a GunWeaponItem parameter, yet null is given");
		Objects.requireNonNull(display, "Expected a display parameter, yet null is given");
		this.gun = gun;
		this.display = display;
	}
	
	/**
	 * Method to execute the action. First, the location of where the actor 
	 * is will be obtained. Then, user will be prompted to insert the direction 
	 * he wants to shoot. Then, using the direction given, a location generator will
	 * generate an array of locations which the shotgun damage will cover. 
	 * After that, each actor standing on these locations have a 75% chance of getting 
	 * hurt. If the target is dead after getting hurt, the target will be removed 
	 * from the map and a Corpse will be added. 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		if (gun.getBulletCount() == 0) {
			return "Fire failed. No more bullet for the shotgun";
		}
		Location locOfActor = map.locationOf(actor);
		int actorX = locOfActor.x();
		int actorY = locOfActor.y();
		
		// Printing out the menu 
		System.out.println(defaultMenu);
		
		// Taking input for direction
		char userDirection;
		do {
			userDirection = display.readChar();
		} while (!keyToDirectionMap.containsKey(userDirection));			
		
		// Generate all the locations in that direction 
		LocationGenerator locGen = new LocationGenerator(actorX,actorY);
		ArrayList<Location> locations = locGen.getLocations(map, keyToDirectionMap.get(userDirection));

 		// For each location, if there is an actor standing on it, there is 75% chance
		// that the actor will be hurt 
 		String result = "Player fire the shotgun! \n";
 		gun.deductBullet();
 		for (Location areaCovered:locations) {
 			if (areaCovered.containsAnActor()) {
 				target = areaCovered.getActor();
 				if (rand.nextDouble() > 0.75) {
 					result += "Player's shot miss " + target.toString();
 					result += "\n";
 					continue;
 				}
 				// if can shot, hurt him, then check if it is dead 

 				hurtingTarget(gun.damage(),map);
 				
 				result += "Player shot " + target.toString() + " for " + gun.damage() + " damage. \n";
 				result += checkConscious(map);
 				System.out.println();
 			}
 		}
 	
		return result;
	}
	
	private String createMenu() {
		String menu = "Select the direction which you want to fire your shotgun";
		for (char character: keyToDirectionMap.keySet()) {
			menu += "\n";
			menu += character + ": " + keyToDirectionMap.get(character).name();
		}
		return menu;
	}
	
	/**
	 * Description that will be shown on the menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return "Player gets the " + gun.toString() + " from inventory. Shotgun bullet left: " + gun.getBulletCount(); 
	}
	
	/**
	 * Get the item associated with this action. Used to filter actions if the player does 
	 * not have the associated items. 
	 */
	@Override
	public String getAssociated() {
		// TODO Auto-generated method stub
		return gun.toString();
	}

}
