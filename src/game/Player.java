package game;

import java.util.ArrayList;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.guns.FireSniperAction;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();

	private String currentlyAiming = null;
	private int concentration = 0;
	private int money = 100;
	private NewWorld world = new NewWorld(new Display());
	private Wallet wallet = new Wallet();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}
	
	// need to refactor , this is method 1 quit game
	public void setWorld(NewWorld world){
		this.world = world;
	}
	
	/** Check if we are targeting the same target, or else lost concentration
	 * @param target target to be checked
	 */
	public void previousTarget(String target) {
		if(target != currentlyAiming) {
			currentlyAiming = target;
			resetConcentration();
		}
	}
	
	/**
	 * Reset the concentration back to 0 (default)
	 */
	public void resetConcentration() {
		concentration = 0;
	}
	
	/**
	 * Add the concentration to the Player
	 */
	public void addConcentration() {
		concentration = Math.min(2,concentration + 1);
	}
	
	/**Getter for concentration
	 * @return concentration
	 */
	public int getConcentration() {
		return concentration;
	}
		
	@Override
	public void hurt(int points) {
		// TODO Auto-generated method stub
		super.hurt(points);
		resetConcentration();
	}
	
	/**Getter for money
	 * @return money
	 */
	public int getMoney() {
		return this.money;
	}
	
	/** Deduct spending and update money
	 * @param spend spending
	 */
	protected void spendMoney(int spend) {
		wallet.deduction(spend);
	}
	
	
	/**Add earnings and update money
	 * @param earnings amount earned
	 */
	protected void earnMoney(int earnings) {
		wallet.earn(earnings);
	}
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		String inventories = "";
		ArrayList<String> inventoryString = new ArrayList<String>();
		
		// add wallet
		if (!this.inventory.contains(wallet)){
			this.inventory.add(wallet);
		}
		//update value
		if (wallet.getAmount() >0) {
			this.money = wallet.getAmount();
		}
		
		
		for (Item i : this.inventory) {
		
			inventoryString.add(i.toString());
			inventories += "# ";	
			inventories += i.toString();
			inventories += "\n";

		}
		System.out.println("##########     STATUS BAR    ##########");
		System.out.println("# Hitpoint left :" + this.hitPoints + "		      #");
		System.out.println("# Money left:" + this.money + "     		      #");
		System.out.println("# Inventory List:		      #");
		System.out.println(inventories);

		System.out.println("#######################################");

		// Handle multi-turn Actions
		if (!(lastAction instanceof FireSniperAction)) {
			resetConcentration();
		}
		
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		
		
		// filter out action that required specific items to be inside the Player's inventory 

		ArrayList<Action> toRemoved = new ArrayList<Action>();
		for (Action action:actions) {
			if (action instanceof MenuActionInterface) {
				// check if inventory
				if(!inventoryString.contains(((MenuActionInterface) action).getAssociated())) {
					toRemoved.add(action);
				}
			}
		}
		// To avoid concurrent modification error 
		for (Action act:toRemoved) {
			actions.remove(act);
		}
		
		// for quit game option to appear in menu
		actions.add(new EndingGameAction(world));
		
		
		return menu.showMenu(this, actions, display);
	}
	
}

