
package game;

import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.Weapon;

/** A class that generates an EatingHealAction 
 * @author Chantelle Loh
 *
 */
public class EatingBehaviour implements Behaviour {
	/**
	 * Returns an EatingHealAction which recovers the Actor 
	 * if Actor is full health, they won't need to eat food
	 * @see FarmingBehaviour#getAction(Actor, GameMap)
	 * @param actor The actor involved as the owner of this Behaviour
	 * @param map The map the actor is on.
	 * @throws NullPointerException if actor parameter is a null
	 * @throws NullPointerException if map parameter is a null
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		// TODO Auto-generated method stub
		List<Item> item = map.locationOf(actor).getItems();
			for (Item i : item ) {
		
			if (i instanceof Food) {
				// for actor to pick up the object
				if(!((Human) actor).isFullHealth()) {
					map.locationOf(actor).removeItem(i);
					return new EatingHealAction(((Food) i).getRecoverPoints());
				}
			}
			}
		
		return null;
	}

}

