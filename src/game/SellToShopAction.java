package game;

import java.util.HashMap;
import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An action to allow Player to sell its items.
 * @author leong
 *
 */
public class SellToShopAction extends Action{
	
	private HashMap<Character,Item> itemList;
	private Display display;
	
	/**
	 * Constructor
	 * @param display a display object which prompts user for input 
	 */
	public SellToShopAction(Display display) {
		Objects.requireNonNull(display, "Expected a display parameter, yet null is given");
		this.display = display;
	}
	
	/**
	 * A method that execute the action. It will first loop throuhg the player inventory 
	 * to check if the Player possessed any item that is sellable. If not, a "You have
	 * nothing to sell" message would be returned. Else, the player will be shown 
	 * a menu of the things he can sell. After selling the item, the item will be removed 
	 * from the inventory and the player's money will increase. 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");

		showMenu(actor);
		
		if(itemList.size() == 0) {
			return "You don't have any item to sell!";
		}
		
		char userInput;
		do {
			userInput = display.readChar();
		} while (!itemList.containsKey(userInput));
		
		int price = ((SellableInterface) itemList.get(userInput)).getPrice();
		
		actor.removeItemFromInventory(itemList.remove(userInput));
		
		//Ka-ching! you earn some money
	    ((Player) actor).earnMoney(price);

		
		
		return "";
	}
	
	private void showMenu(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		itemList = new HashMap<Character,Item>();
		char character = 'a';
		for(Item item:actor.getInventory()) {
			if (item instanceof SellableInterface) {
				System.out.println(character + ". " +  item.toString() + " - price: " + (((SellableInterface) item).getPrice() * 0.75));
				itemList.put(character, item);
				character++;
			}
		}
	}
	
	/**
	 * Description of what has happened. 
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return "Sell to shop";
	}

}
