package edu.monash.fit2099.guns;

import java.util.Objects;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.WeaponItem;
import game.SellableInterface;

/**
 * Base class for all gun-related weapon.
 * @author leong
 *
 */
public abstract class GunWeaponItem extends WeaponItem implements SellableInterface{

	private int bulletCount;
	/**
	 * Used to display options when shooting. 
	 */
	protected Display display = new Display();
	
	/**
	 * Constructor 
	 * @param name name of the weapon 
	 * @param displayChar display character of the weapon 
	 * @param damage damage of the weapon
	 * @param verb verb used to describe the weapon
	 * @param noOfBullet number of bullet the weapon originally has 
	 */
	public GunWeaponItem(String name, char displayChar, int damage, String verb,int noOfBullet) {
		super(name, displayChar, damage,verb);
		this.bulletCount = noOfBullet;
	}
	
	/**
	 * Increase the number of bullet the gun has. 
	 */
	void reload(int noOfBullet) {
		Objects.requireNonNull(noOfBullet, "Expected an integer parameter, yet null is given");
		bulletCount += noOfBullet;
	}
	
	/**
	 * Obtain the bullet that the gun has.
	 * @return value of bulletCount
	 */
	public int getBulletCount() {
		return bulletCount;
	}
	
	/**
	 * Reduce the number of bullet by 1 (Eg. the gun just fired). 
	 */
	public void deductBullet() {
		bulletCount -= 1;
	}
}
