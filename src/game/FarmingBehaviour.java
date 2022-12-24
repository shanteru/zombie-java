
package game;

import java.util.*;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;


/**An abstract class to act as parent class for all Farming- related Behaviour class
 * @author Chantelle Loh
 *
 */
public abstract class FarmingBehaviour implements Behaviour {
	
	/**
	 * The displayChar of an item or ground desired.
	 */

	protected char nearbyGround;
	
	
	/**Constructor.
	 * sets the nearbyGround attribute
	 * @param nearbyChar desired displayChar which satisfy the needs.
	 * @throws NullPointerException if nearbyChar parameter is a null
	 */
	public FarmingBehaviour(char nearbyChar) {
		Objects.requireNonNull(nearbyChar, "Expected a nearbyChar parameter, yet null is given");
		this.nearbyGround = nearbyChar;
	}
	
	/**
	 * To iterate the list of Exits available to an Actor
	 * @param actor The actor involved as the owner of this Behaviour.
	 * @param map The map the actor is on.
	 * @return exits which are available to the actor
	 * @throws NullPointerException if actor parameter is a null
	 * @throws NullPointerException if map parameter is a null
	 */
	public List<Exit> nearbyExit(Actor actor, GameMap map){
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);
		return exits;
	}
	
	
	
	/**
	 * get an Action actor can perform if conditions are met.
	 * @see Behaviour#getAction(Actor, GameMap)
	 * @param actor The actor involved as the owner of this Behaviour
	 * @param map The map the actor is on.
	 */
	@Override
	public abstract Action getAction(Actor actor, GameMap map) ;
}
	



	
	




