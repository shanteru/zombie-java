package game;

import java.util.ArrayList;
import java.util.Objects;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * A children class of the CraftWeaponAction. It will do same things as its parent,
 * with the extra step of checking if the actor has enough gold in his inventory first. 
 * Will be created in CraftShop.
 * @author leong
 *
 */
public class CraftWeaponAtShopAction extends CraftWeaponAction{
	
	private int goldRequired;
	/**
	 * Constructor
	 * @param originalPart Weapon to crafted 
	 * @param goldRequired gold required to craft this weapon.
	 */
	public CraftWeaponAtShopAction(Craftable originalPart,int goldRequired) {
		super(originalPart);
		Objects.requireNonNull(originalPart, "Expected a Craftable parameter, yet null is given");
		Objects.requireNonNull(goldRequired, "Expected a goldRequired parameter, yet null is given");
		this.goldRequired = goldRequired;
	}
	
	/**
	 * It will first check if the actor has enough gold. If yes, it will removed 
	 * the golds (according to gold required) in the Player's inventory before
	 * the crafting process happens in CraftWeaponAction.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		// check if Player has enough gold first only can execute
		if(getNumOfGold(actor) < goldRequired) {
			return "Sorry, you don't have enough gold required to craft this weapon";
		}
		// remove Gold from the Player
		ArrayList<Item> toRemoved = new ArrayList<Item>();
		for (Item item:actor.getInventory()) {
			if (item.toString() == "Gold") {
				toRemoved.add(item);
				this.goldRequired -= 1;
			}
			if (goldRequired == 0) {
				break;
			}
		}
		for (Item gold:toRemoved) {
			actor.removeItemFromInventory(gold);
		}
		return super.execute(actor, map);
	}
	
	/**
	 * A description of what has happened.
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		int numOfGold = getNumOfGold(actor);
		String message = "Number of Gold player have : " + numOfGold;
		String messageTwo = "Gold required : " + goldRequired;
		return "\n" + message + "\n" + messageTwo + "\n" + super.menuDescription(actor);
	}
	
	private int getNumOfGold(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		int numOfGold = 0;
		for (Item item:actor.getInventory()) {
			if (item.toString() == "Gold") {
				numOfGold += 1;
			}
		}
		return numOfGold;
	}
}
