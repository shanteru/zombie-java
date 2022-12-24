
package game;

import java.util.List;
import java.util.Objects;
import java.util.Random;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

/** Action for Sowing a Crop
 * @author Chantelle Loh
 *
 */
public class SowCropAction extends Action{
	private Location location;


	/**
	 * Constructor.
	 * Set the location to sow a crop to a valid location.
	 * @param location: the target location for sowing crop
	 */
	public SowCropAction(Location location){
		this.location = location;
	}
	
	/**
	 * set the ground of a location to Crops object 
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
		location.setGround((new Crops()));		
		return menuDescription(actor);
	}
	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return a string, e.g. "Farmer sow crops"
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return  actor + " sow crops. ";
	}

}


