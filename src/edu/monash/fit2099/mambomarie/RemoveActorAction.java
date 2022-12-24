package edu.monash.fit2099.mambomarie;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action that remove the MamboMarie(after 30 turns) from the map
 * @author leong
 *
 */
public class RemoveActorAction extends Action{
	
	/**
	 * Method to execute the action. The actor will be removed. 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		map.removeActor(actor);
		return menuDescription(actor);
	}
	
	/**
	 * Description of what has happened.
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return actor + " has disappeared.";
	}

}
