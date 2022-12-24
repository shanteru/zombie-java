package game;

import java.util.Objects;

import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Corpse class. Created after human is killed. It will be removed after the Corpse has resurrected. 
 * @author leong
 *
 */
public class Corpse extends PortableItem{
	
	private int turns = 7;
	private boolean freeze = false;
	
	/**
	 * Create a Corpse object. 
	 * @param name name of the dead human who becomes the Corpse
	 */
	public Corpse(String name) {
		super(name,'%');
	}
	 
	/**
	 * Inform the corpse item the passage of time (turn). The default turns for a Corpse to resurrect is 7.
	 * If in the current tick, turns == 0, then the function will check if there is any actor on the place 
	 * where the Corpse is placed. If yes, set current location to East South spot (x + 1, y + 1). If x and y
	 * exceeds the map limit, current x / y will be set to the beginning (using remainder). Then, A new 
	 * Zombie object will be placed in that location. 
	 * If turn != 0, turn will be decreased by 1. 
	 * @param currentLocation location of the Corpse 
	 */
	@Override
	public void tick(Location currentLocation) {

		Objects.requireNonNull(currentLocation, "Expected a location parameter, yet null is given");
		if(turns == 0) {
			GameMap map = currentLocation.map();
			currentLocation.removeItem(this); 
			int i = 1;
			int currentX = currentLocation.x();
			int currentY = currentLocation.y();
			while (currentLocation.containsAnActor()) { // avoid crashing, we cannot place a new actor on spot that contains an actor 
				currentX = (currentX + i) % (map.getXRange().max()); // if X value exceeds max width, put it at the most left
				currentY = (currentY + i) % (map.getYRange().max()); // if Y value exceeds the max height, put it at the bottom
				currentLocation = currentLocation.map().at(currentX + i, currentY + 1);
				i += 1;
			}
			currentLocation.addActor(new Zombie(name));
		}
		else {
			if(!freeze) {
				turns -=1;
			}
		}
	}

	@Override
	public PickUpItemAction getPickUpAction() {
		// TODO Auto-generated method stub
		return new PickFreezeItemAction(this);
	}

	@Override
	public DropItemAction getDropAction() {
		// TODO Auto-generated method stub
		return new DropDefreezeItemAction(this);
	}
	
	public void setFreeze(boolean freezeOrNot) {
		Objects.requireNonNull(freezeOrNot, "Expected a boolean parameter, yet null is given");
		this.freeze = freezeOrNot;
	}
}

