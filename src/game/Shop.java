package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Item;

/**
 * A shop which allows player to buy and sell item.
 * @author leong
 *
 */
public class Shop extends Item{
	Display display = new Display();
	
	/**
	 * 
	 */
	public Shop() {
		super("Shop",'S',false);
		super.allowableActions.add(new BuyFromShopAction(display));
		super.allowableActions.add(new SellToShopAction(display));
	}
}
