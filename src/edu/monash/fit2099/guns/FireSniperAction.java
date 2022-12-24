package edu.monash.fit2099.guns;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.mambomarie.MamboMarie;
import game.AttackAction;
import game.MenuActionInterface;
import game.Player;
import game.Zombie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Action to fire the sniper.
 * @author leong
 *
 */
public class FireSniperAction extends AttackAction implements MenuActionInterface{
	
	private GunWeaponItem sniper;
	private HashMap<Character, Actor> keyToActorMap = new HashMap<Character, Actor>();
	private HashMap<Integer, Double> concentrationToProbability = new HashMap<Integer, Double>();
	private HashMap<Integer, Integer> concentrationToDamage = new HashMap<Integer, Integer>();
	private Display display;
	
	/**
	 * Constructor.
	 * @param target Target of the attack.
	 * @param theSniper Sniper which is involved in this action.
	 * @param display display object used to retrieve the user input 
	 */
	public FireSniperAction(Actor target, GunWeaponItem theSniper,Display display) {
		super(target);
		Objects.requireNonNull(theSniper, "Expected a GunWeaponItem parameter, yet null is given");
		Objects.requireNonNull(display, "Expected a display parameter, yet null is given");
		this.sniper = theSniper;
		this.display = display;
		concentrationToProbability.put(0, 0.75);
		concentrationToProbability.put(1, 0.90);
		concentrationToProbability.put(2, 1.00); 
		concentrationToDamage.put(0, theSniper.damage());
		concentrationToDamage.put(1, theSniper.damage()*2);
		concentrationToDamage.put(2, Integer.MAX_VALUE);
	}
	
	/**
	 * Method to execute the action. It will first go through the map to find 
	 * all possible targets. Then, all the targets will be shown to user and 
	 * will prompt the user to make a choice on which target to shoot. After 
	 * selecting a target, the user will be prompted again to select his option
	 * on whether to aim or to shoot. If he chooses to aim, the Player's concentration 
	 * will increase by 1. If he chooses to shoot, then the concentration of 
	 * the Player will first be retrieved. Then, according to the concentration level,
	 * the target will be hurt (higher concentration, higher damage and probability). 
	 * After the target is hurt, if it has dead, it will be removed from the map 
	 * and a Corpse will be added. 
	 * @return a string description of what has happened 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");

		if (sniper.getBulletCount() == 0) {
			return "Fire failed. No more bullet for the sniper";
		}
		
		// Find a list of Zombies to show
		ArrayList<Actor> zombieList = getZombieFromMap(map);
		
		if (zombieList.size() == 0) {
			return "There is no zombie for you to shoot in this map";
		}

		// Print the menu 
		displayMenu(zombieList);
		// Choosing the Zombie to kill 
		selectZombie();

		// If aiming target has changed, reset the concentration of the Player 
		((Player) actor).previousTarget(target.toString());	
		
		// Choosing the option
		char userOption = selectOption();
		
		// aim 
		if(userOption == 'a') {
			((Player) actor).addConcentration();
			return "Player is currently aiming at " + target;
		}
		// shoot 
		return shooting(actor,map);
	}
	
	private String shooting(Actor actor,GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		int concentration = ((Player) actor).getConcentration();
		double probability = concentrationToProbability.get(concentration);
		int damage = concentrationToDamage.get(concentration);
		sniper.deductBullet();
		
		// shoot misses 
		if (rand.nextDouble() > probability) {
			return "Sniper's shot misses " + target.toString();
		}
		
		// shoot successful 
		hurtingTarget(damage,map);

		String result = actor + " " + sniper.verb() + " " + target + " for " + damage + " damage.";
		
		// check if target is still alive after shooting 
		result += checkConscious(map);
		return result;
	}
	
	private ArrayList<Actor> getZombieFromMap(GameMap map){
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		ArrayList<Actor> zombieList = new ArrayList<Actor>();
		for (int x : map.getXRange()) {
			for (int y : map.getYRange()) {
				Actor actorGotten = map.at(x, y).getActor();
				if(actorGotten instanceof Zombie || actorGotten instanceof MamboMarie) {
					zombieList.add(actorGotten);
				}
			}
		}
		return zombieList;
	}
	
	private void displayMenu(ArrayList<Actor> zombieList) {
		Objects.requireNonNull(zombieList, "Expected an ArrayList parameter, yet null is given");
		char mychar = 'a';
		for (Actor currentActor: zombieList) {
			System.out.println(mychar + ": " + currentActor.toString());
			// placing the options into keyToActorMap, used later to access actor 
			keyToActorMap.put(mychar, currentActor);
			mychar++;
		}
	}
	
	private void selectZombie() {
		System.out.println("Please select the target to aim/shoot.");
		char userDecision;
		do {
			userDecision = display.readChar();
		} while (!keyToActorMap.containsKey(userDecision));	
		// Setting the target of the action to the Zombie selected 
		target = keyToActorMap.get(userDecision);
	}
	
	private char selectOption() {
		// Step 3a: Show Menu 
		System.out.println("Please select what you want to do with " + target.toString());
		System.out.println("a: Aim");
		System.out.println("b: Shoot");
		char userOption;
		do {
			userOption = display.readChar();
		} while (userOption != 'a' & userOption != 'b');	
		return userOption;
	}
	
	/**
	 * Description that will be shown on the menu.
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return actor + " get the " + sniper.toString() + " from inventory. Sniper bullet left: " + sniper.getBulletCount();
	}
	
	/**
	 * Get the item associated with this action. Used to filter actions if the player does 
	 * not have the associated items. 
	 */
	@Override
	public String getAssociated() {
		// TODO Auto-generated method stub
		return sniper.toString();
	}
	
}
