package game;

import java.util.Objects;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

/**
 * An attack action class for the Zombie. It handles special condition (special attack and actor's healing).
 * @author Gan 
 * @author Chantelle Loh
 *
 */
public class ZombieAttackAction extends AttackAction{

	/**
	 * Constructor.
	 * call super class's constructor 
	 * 
	 * @param target the Actor to attack
	 */
	public ZombieAttackAction(Actor target) {
		super(target);
	}

	/**
	 *The method to execute attack action. it will obtain a weapon from the actor inventory(if have),
	 *(else) it will get ZombieIntrinsicWeapon. There are two intrinsic attack: punches and bites, if bites is
	 *equipped, depend on its hit probability which is 0.5, it will have 50% chance of missing ( lower hit probability
	 *than punches), (if) misses, it will return eg, "Zombie misses target", (else) a successful bite will restore 5 
	 *hitpoints for the Zombie then return the String result instead. 
	 * @param actor the actor who is attacking 
	 * @param map the map the actor is on 
	 * @throws NullPointerException if actor parameter is a null
	 * @throws NullPointerException if map parameter is a null 
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");

		double probability = 0.5;
		Weapon weapon = ((Zombie) actor).getWeapon(map);
		
		if(weapon == null) {
			return "Zombie drops its weapon. No attack this round!";
		}
	
		// here new code(part A.1)
		// for Biting hit probability using the new class
		if(weapon instanceof ZombieIntrinsicWeapon) {
			probability = ((ZombieIntrinsicWeapon) weapon).getProbability();
			if (weapon.verb() == "bites") {				
				if(rand.nextDouble() > probability) {
					return actor + " misses " + target + ".";
				}
				else {
					//successful bite heal 5 hp 
					actor.heal(5);
				}
			}
		}
		else {
			if((rand.nextDouble() > probability)) {
				return actor + " misses " + target + ".";
			}
		} 
		
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		
		result += checkConscious(map);
		return result;
	}
	
//	@Override
//	/**
//	 * Place a Corpse object (will resurrect) on the place where target is dead. 
//	 * 
//	 * @param map The map where the target located 
//	 * @throws NullPointerException if map parameter is a null 
//	 */
//	protected void addCorpse(GameMap map) {
//		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
//		Corpse corpse = new Corpse(target.toString());
//		map.locationOf(target).addItem(corpse);
//	}
	
}
