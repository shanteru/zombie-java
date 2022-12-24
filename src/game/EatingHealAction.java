package game;

import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Heal after Eating Action for Actors
 * 
 * @author Chantelle Loh
 *
 */
public class EatingHealAction extends Action implements MenuActionInterface{
	private int howMuchToRecover;
	
	/**
	 * Constructor
	 * @param healpoints: amount which heals the Actors' hitpoints
	 * @throws IllegalArgumentException if healpoints is negative
	 */
	public EatingHealAction(int healpoints) {
		if(healpoints < 0) {
			throw new IllegalArgumentException("Healpoints must be positive");
		}
		howMuchToRecover = healpoints;
	}
	
	/**
	 *heal Actor 
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
		
		actor.heal(howMuchToRecover);
		return menuDescription(actor);
	}
	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return a string, e.g. "Human eats food"
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " eats food";
	}

	@Override
	public String getAssociated() {
		// TODO Auto-generated method stub
		return "Food";
	}

}
