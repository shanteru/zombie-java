
package game;

import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**A class that generates HarvestingAction
 * @author Chantelle Loh
 *
 */
public class HarvestingBehaviour extends FarmingBehaviour{
	
	/**
	 * Constructor
	 * Sets displayChar of the super class's nearbyGround attribute
	 * so that the owner of this Behaviour can harvest when object displayChar is
	 *  'C' which is a RipenCrops object
	 */
	public HarvestingBehaviour() {
		super('C');
		// TODO Auto-generated constructor stub
	}
	
	
	private Exit correctItem(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		List<Exit> exits = nearbyExit(actor, map);
		for(Exit e :exits) {
			List<Item> items = e.getDestination().getItems();
			for(Item i : items) {
			if (i.getDisplayChar() == nearbyGround) {
				return e;
				}
			}
		}
		return null;
	}
	/**
	 * Returns an HarvestingAction that harvest a ripen crop
	 * Owner of this Behaviour is able to harvest a ripen crop when standing next to it or on it.
	 * ripen crop is able to be harvest if it is a RipenCrops object with the displayChar of 'C'
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
		Exit chosenPlace = correctItem(actor,map);
		Location location = map.locationOf(actor);
		if (chosenPlace != null) {
			return new HarvestingAction(chosenPlace.getDestination());
		}
		else if(location.getGround().getDisplayChar() == 'C'){
			return new HarvestingAction(location);
		}
		
		return null;
	}

}


