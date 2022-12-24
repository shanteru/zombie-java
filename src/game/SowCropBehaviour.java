
package game;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/** A class that generates SowCropAction if Actor stands next to a patch of dirt with 
 * 33% probability
 * @author Chantelle Loh
 *
 */
public class SowCropBehaviour extends FarmingBehaviour{
	private Random random = new Random();
	/**
	 * Constructor.
	 * 
	 * Sets displayChar of the super class's nearbyGround attribute
	 * so that the owner of this Behaviour is allowed to sow crop only
	 * on a ground with displayChar of '.' which is Dirt
	 */
	public SowCropBehaviour() {
		super('.');
	}
	


	private Exit correctGround(Actor actor, GameMap map) {
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		// TODO Auto-generated method stub
		List<Exit> exits = nearbyExit(actor, map);
		for(Exit e :exits) {
			if (e.getDestination().getGround().getDisplayChar() == nearbyGround) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Returns an SowCropAction that sow a crop at the patch of dirt next to Actor
	 * Crops is able to be sow only on a patch of dirt and it only has 33% probability of sowing
	 * @see FarmingBehaviour#getAction(Actor, GameMap)
	 * @param actor The actor involved as the owner of this Behaviour
	 * @param map The map the actor is on.
     * @throws NullPointerException if actor parameter is a null
	 * @throws NullPointerException if map parameter is a null
	 * 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		// TODO Auto-generated method stub
		Exit chosenGround = correctGround(actor,map);
		if (chosenGround!= null) {
			if(random.nextDouble()< 0.33){
				 return new SowCropAction(chosenGround.getDestination());
		}
		}
		
		return null;
	}



	
	

}


