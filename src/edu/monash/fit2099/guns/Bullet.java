package edu.monash.fit2099.guns;

import java.util.Objects;

import game.PortableItem;
import game.SellableInterface;

/**
 * Base class for all the bullets.
 * @author leong
 *
 */
public abstract class Bullet extends PortableItem implements SellableInterface{

	private int reloadAmount;
	
	/**
	 * Constructor 
	 * @param name name of the bullet 
	 * @param character character representing the bullet 
	 * @param reloadCount capacity of the bullet 
	 */
	public Bullet(String name,char character,int reloadCount) {
		super(name,character);
		Objects.requireNonNull(reloadCount, "Expected an integer parameter, yet null is given");
		this.reloadAmount = reloadCount;
	}
	
	/**
	 * Getter to obtain the capacity of the bullet.
	 * @return value of reloadAmount
	 */
	public int getReloadAmount() {
		return reloadAmount;
	}
}
