
package game;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/** A class that generates FertilizeAction if the Actor stands on a Crop
 * @author Chantelle Loh
 *
 */
public class FertilizeBehaviour extends FarmingBehaviour{

	/**
	 * Constructor.
	 * 
	 *  Sets displayChar of the super class's nearbyGround attribute
	 * so that the owner of this Behaviour is allowed to fertilize crop only
	 * when standing on a ground with displayChar 'c' which is a Crops object
	 */
	public FertilizeBehaviour() {
		super('c');
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns an FertilizeAction that fertilize a crop
	 * crop is able to be fertilize if it is a Crops object with the displayChar of 'c'
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
		Location location = map.locationOf(actor);
		if(location.getGround().getDisplayChar() == 'c') {
			return new FertilizeAction();
		}
		return null;
	}
	
	

}

