package game;

import java.util.Objects;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 * @author Chantelle Loh 
 * @author Gan
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}
	
	/**
	 * 

	 * The method to execute the action. Firstly, it will obtain a weapon from the actor inventory(if have).
	 * Then, there is a 50% chance of the attack action not being executed (meaning the actor misses the target). 
	 * However, if the actor does able to make an attack, hurt method on the target will be called. Lastly,
	 * call checkConscious method to see if the target is still alive (in order to place the Corpse if the target
	 * is dead).
	 * @param actor the actor who is attacking 
	 * @param map the map the actor is on 
	 * @throws NullPointerException if actor parameter is a null
	 * @throws NullPointerException if map parameter is a null 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
				
		Weapon weapon = actor.getWeapon();
		
		if((rand.nextDouble() > 0.5)) {
			return actor + " misses " + target + ".";
		}
		
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		
		hurtingTarget(damage,map);
		
		result += checkConscious(map);
		return result;
	}
	
	protected void hurtingTarget(int damage,GameMap map) {
		if(target instanceof Zombie) { //special instance, hurt on zombie can cause dropping of limb
			((Zombie) target).hurt(damage, map);
		}
		else {
			target.hurt(damage);
		}
	}
	
	/**
	 * Check if the target is conscious (hitpoint >0). If not, remove the actor and add a Corpse at 
	 * the target's location. It will also drop all the item of the dead target. 
	 * @param map
	 * @return A message indicating status 
	 */
	protected String checkConscious(GameMap map) {
		if (!target.isConscious()) {
						
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			
			this.addCorpse(map);
			map.removeActor(target);	
			
			String result = System.lineSeparator() + target + " is killed.";
			return result;
		}
		return "";
	}
	
	/**
	 * Add a corpse at the location where the target dies. It will first 
	 * check if the target is human. If yes, then a Corpse that will resurrect will
	 * be placed on the map. Else, a Corpse that will not resurrect will be placed
	 * instead. 
	 * @param map
	 */
	protected void addCorpse(GameMap map) { // map not used here 
		Item corpse;
		if(target instanceof Human) {
			corpse = new Corpse(target.toString());
		}
		else {
			corpse = new PortableItem(target.toString(), '%');
		}
		map.locationOf(target).addItem(corpse);
	}
	
	/**
	 * Give a description of what has happened. 
	 * @return a String descrption eg," Player attacks Zombie"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
