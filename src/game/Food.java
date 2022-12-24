package game;

import edu.monash.fit2099.engine.Item;

/**Food which can be eaten by Humans and PLayers and restore their hitpoints.
 * @author Chantelle Loh
 *
 */

public class Food extends Item implements SellableInterface{
	private int recoverPoints = 6;

	/**
	  * Using the super class's constructor to set the name, displayChar and portability
	 * Using the super class's attribute allowableActions , added EatingAction
	 * to allow this item to be able to be eaten.
	 */
	public Food() {
		super("Food", 'f', true);
		super.allowableActions.add(new PlayerEatingAction(recoverPoints));
		// TODO Auto-generated constructor stub
	}
	
	
	/** Getter for private attribute recoverPoints
	 * @return recoverPoints, an amount which will heal Actors' hitpoints
	 */
	public int getRecoverPoints() {
		return recoverPoints;
	}


	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 100;
	}
}
