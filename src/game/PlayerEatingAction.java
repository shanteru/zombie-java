
package game;

import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**Eating Action specifically for Player
 * @author Chantelle Loh
 *
 */
public class PlayerEatingAction extends EatingHealAction {

	/**Constructor
	 * Using super class's constructor to set healpoints
	 * @param healpoints, amount which heal the Player's hitpoints
	 */
	public PlayerEatingAction(int healpoints) {
		super(healpoints);
	}

	/**
	 * eat the food if the item from inventory is a Food object
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 * @throws NullPointerException if actor parameter is a null
	 * @throws NullPointerException if map parameter is a null
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		// TODO Auto-generated method stub
		List<Item> item = actor.getInventory();
		for (Item i : item) {
			if (i instanceof Food) {
				actor.removeItemFromInventory(i);
				return super.execute(actor, map);
			}
		}
		return " Player choose to eat Food that he does not have ";
	}
	
	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see EatingHealAction#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return super class's menuDescription(Actor) method
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return super.menuDescription(actor);
	}
	
	
}


