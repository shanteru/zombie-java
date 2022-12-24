package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 * @author Chantelle Loh 
 * @author Gan
 *
 */
public class Zombie extends ZombieActor {
	
	private Random rand = new Random();
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
	};
	private Behaviour[] moveBehaviours = {
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	private ZombieLimb limbs = new ZombieLimb();
	private boolean movable = true;
	
	/**
	 * Create a Zombie object 
	 * @param name The name of the Zombie 
	 */
	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}

	/**
	 * Method that returns a ZombieIntrinsicWeapon. 
	 * Before returning the IntrinsicWeapon, if punch is chosen to be returned (random process),
	 * then the probability of punch ZombieIntrinsicWeapon will be adjusted according to the number of 
	 * arms the Zombie still has, according to the formula (number of arms left/maximum number of legs) 
	 * multiply original probability. 
	 * @return a ZombieIntrinsicWeapon object 
	 */
	@Override
	public ZombieIntrinsicWeapon getIntrinsicWeapon() {
		if (rand.nextBoolean()) {
			double probabilityPunch = (limbs.getArmPortion()) * 0.8;
			return new ZombieIntrinsicWeapon(10, "punches",probabilityPunch);
		}
		else {
			return new ZombieIntrinsicWeapon(20, "bites", 0.5);
		}
	}
	
	/**
	 * When the Zombie is hurt, its hitpoints will be reduced. Then, there is a 25% chance that 
	 * its limb will be knocked off, handled by knockOffLimb method. 
	 * @param points number of hitpoints to deduct
	 * @param map The map that the Zombie is in 
	 * @throws IllegalArgumentException if Hurt points is negative
	 * @throws NullPointerException if points parameter is a null
	 * @throws NullPointerException if map parameter is a null
	 */
	public void hurt(int points,GameMap map) {
		if(points < 0) {
			throw new IllegalArgumentException("Hurt points must be positive");
		}
		Objects.requireNonNull(points, "Expected a points parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map a parameter, yet null is given");
		super.hurt(points);
		if (rand.nextDouble() < 1) {
			knockOffLimb(map);
		}
	}

	private void knockOffLimb(GameMap map) {
		// find a spot around the Zombie which no actor is standing on it. Then, call
		// the knockLimbOff function in ZombieLimb class with that location as a parameter.
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
		for (Exit e: exits) {
			Location emptySpot = e.getDestination();
			if ((emptySpot.containsAnActor()))
				continue;
			limbs.knockLimbOff(emptySpot,this.toString());
			break;
		}
		// check if Zombie still alive (at least one arm / leg) 
		checkAlive();
		// check if Zombie still movable (at least one leg) 
		checkLegs();
	}
	
	private void checkAlive() {
		if(!limbs.canFunction()) {
			this.hitPoints = -1;
		}
	}
	
	private void checkLegs() {
		if(limbs.getLegPortion() == 0) {
			movable = false;
		}
	}
	
	/**
	 * Overloading method for getWeapon.
	 * If the weapon gotten is an isntance of WeaponItem, then there is a chance that this weapon will 
	 * be dropped. The probability of dropping the weapon that the Zombie is currently holding depends on 
	 * the number of arms the Zombie still has. 
	 * If no arm left, probability of dropping will be 1.
	 * Only one arm left, probability of dropping will be 0.5.
	 * Two arm left, probability of dropping will be 0. 
	 * A DropItemAction class will be created when the Zombie drops its weapon and then be executed.
	 * Null will be returned. 
	 * If weapon gotten is not an instance of WeaponItem, then just return the weapon gotten.
	 * @param map The map Zombie is in
	 * @return Weapon gotten, null if the Zombie drops the weapon. 0
	 * @throws NullPointerException if map parameter is a null
	 */
	public Weapon getWeapon(GameMap map) {
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		if (super.getWeapon() instanceof WeaponItem) {
			double probability = limbs.getArmPortion();
			if (rand.nextDouble() < probability) {
				return super.getWeapon();
			}
			else {
				Action act = ((Item) super.getWeapon()).getDropAction();
				act.execute(this, map);
				return null;
			}
		}
		return super.getWeapon();
	}
	
	private void changeMovable() {
		// if the zombie only has one leg left, set movable into !movable. 
		if (limbs.getLegPortion() == 0.5) {
			movable = !movable;
		}
	}
	
	private Action gotAction(Behaviour[] behaviourArray, GameMap map) {
		// check if there is any action generated in the among the behaviours given.
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		Objects.requireNonNull(behaviourArray, "Expected a behaviourArray parameter, yet null is given");

		for (Behaviour behaviour : behaviourArray) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;	
		}
		return null;
	}
	
	/**
	 * If a Zombie can attack, it will.  Then, if the Zombie is movable (at least one leg left) 
	 * it will chase any human within 10 spaces or wander if there is no Human is around. However, 
	 * if the Zombie cannot attack and cannot move, then it will do nothing. 
	 * There is 10% chance, Zombie will say Braiinss every turn
	 * If there is a weapon at Zombie's location, unarmed Zombie will pick it up
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 * @throws NullPointerException if map parameter is a null
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		changeMovable();
		//Zombie saying Zombie-like statement
		if (rand.nextDouble()< 0.1) {
			System.out.println( this + " says Braiiinss");
		}
		
		List<Item> item = map.locationOf(this).getItems();
		for (Item i : item ) {
			if (i instanceof Weapon) {
				// for Zombie to pick up the object
				PickUpItemAction pickup = i.getPickUpAction();
				System.out.println(pickup.execute(this, map));
				break;
				}
			}
		
		Action action = gotAction(behaviours,map);
		if (action != null) {
			return action; 
		}
		if (movable) {
			Action moveAction = gotAction(moveBehaviours,map);
			if (moveAction != null) {
				return moveAction; 
			}
		}
		return new DoNothingAction();	
	}
	

	
}

