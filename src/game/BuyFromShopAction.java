package game;

import java.util.HashMap;
import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.guns.ShotgunBullet;
import edu.monash.fit2099.guns.SniperBullet;

/**
 * Action that allows player to buy from the shop.
 * @author leong
 *
 */
public class BuyFromShopAction extends Action{
	
	private HashMap<Character,SellableInterface> itemList = new HashMap<Character,SellableInterface>();
	private Display display;
	private String menu;
	
	/**
	 * Constructor. It will initialise the items in the menu that the Player will see 
	 * when he chooses to buy.
	 * @param display display object that is used to prompt Player for input 
	 */
	public BuyFromShopAction(Display display) {
		Objects.requireNonNull(display, "Expected a display parameter, yet null is given");
		itemList.put('a',new SniperBullet());
		itemList.put('b',new ShotgunBullet());
		itemList.put('c', new Food());
		this.display = display;
		this.menu = createMenu();
	}
	
	/**
	 * Method to execute the action. It will first print out the default menu.
	 * Then, the player will be asked to choose the item he wants to buy. 
	 * If the player does not have enough money, a "sorry" message will be returned
	 * and nothing will be done. Else, The price of the item is obtained and the player's
	 * wallet will be deducted according to the price. The item will also be added 
	 * into the player's inventory.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");

		// 1. print out the menu 
		System.out.println(menu);
		
		// 2. get user input 
		char userInput;
		do {
			userInput = display.readChar();
		} while (!itemList.containsKey(userInput));
		
		// 3. check Player's money 
		SellableInterface item = itemList.get(userInput);
		Player player = (Player) actor;
		if (player.getMoney() < item.getPrice()) {
			return "Sorry, you don't have enough money to buy this item!";
		}
		int price = item.getPrice();
		actor.addItemToInventory((Item) item);
	
		//Ka-ching! Transaction complete ( wallet gets thinner )
		player.spendMoney(price);
		
		return "Player has purchase a " + item.toString();
	}
	
	private String createMenu() {
		String menu = "Select the item that you wish to buy ";
		for (char character: itemList.keySet()) {
			menu += "\n";
			menu += character + ": " + itemList.get(character).toString();
		}
		return menu;
	}
	
	/**
	 * @return description of what has happened.
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return "Buy from Shop";
	}

}
