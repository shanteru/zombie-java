package game;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
/**
 * An action that craft a new weapon from a CraftableWeaponItem object 
 * @author leong
 */
public class CraftWeaponAction extends Action implements MenuActionInterface{
	
	private Craftable originalPart;
	
	/**
	 * Create a CraftWeaponItem object. 
	 * @param originalPart original zombie part to craft
	 */
	public CraftWeaponAction(Craftable originalPart) {
		Objects.requireNonNull(originalPart, "Expected an Craftable parameter, yet null is given");
		this.originalPart = originalPart;
	}
	
	private String craftInventory(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		for (Item item:actor.getInventory()) {
			if (item.toString() == originalPart.toString()) {
				actor.removeItemFromInventory(item);
				break;
			}
		}
		WeaponItem newItem = originalPart.craftWeapon();
		actor.addItemToInventory(newItem);
		return newItem.toString();
	}
	
	/**
	 * Perform the Action.If the action is executed under the condition where the 
	 * actor actually does not have the weapon, then a message "Player choose to 
	 * craft a weapon that he does not have will appear". Otherwise, depending on 
	 * the CraftableWeaponItem name, either a new ZombieClub or a new Zombie Leg will
	 * be crafted. 
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what has been crafted to the use	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");

		String newPart = craftInventory(actor);
		return menuDescription(actor) + newPart + " is created.";
	}
	
	/**
	 * Display menu for the user. 
	 * When the Player step on the item, it will first check if the Player has the item or not.
	 * If not, then display a message telling Player that he will not be able to craft the weapon
	 * as he does not have it. If yes, return the weapon crafting message. 
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return actor + " uses " + originalPart.toString() + " to craft a new weapon.";
	}

	@Override
	public String getAssociated() {
		// TODO Auto-generated method stub
		return originalPart.toString();
	}
	
}
