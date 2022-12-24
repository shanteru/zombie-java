
package game;

import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

/** Harvesting Action for Actor to harvest ripen crops 
 * @author Chantelle Loh
 *
 */
public class HarvestingAction extends Action {
	
	private Food food = new Food();
	private Location location;

	/**
	 * Specific Constructor with one parameter
	 * to cater for the condition where Farmer surroundings have a ripen crops
	 * to be harvest hence a need for available location
	 * @param location target location to harvest ripen crops
	 */
	public HarvestingAction(Location location){
		this.location = location;
	}
	
	
	
	/**
	 *harvest the ripen crops if the item is a RipenCrops object
	 *There are 2 scenarios: One is to harvest ripen crops at actor location, another is at a valid Exit
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 * @throws NullPointerException if map parameter is a null
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		List<Item> items = location.getItems();
		for (Item i : items) {
			if(i instanceof RipenCrops) {
				if(actor instanceof Player) {
					actor.addItemToInventory(food);
				}else if(actor instanceof Farmer) {
					location.addItem(food);
				}
				location.removeItem(i);
				location.setGround(new Dirt());
				break;
				}
			}

		return menuDescription(actor);
	}
	
	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return a string, e.g. "Farmer harvests Food"
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " harvests Food. ";
	}

}

