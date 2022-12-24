package edu.monash.fit2099.guns;

import edu.monash.fit2099.engine.WeaponItem;
import game.Craftable;

/**
 * A GunWeaponItem that can shoot a few Zombie at a time, given the direction.
 * @author leong
 *
 */
public class Shotgun extends GunWeaponItem implements Craftable{
	
	/**
	 * Constructor
	 */
	public Shotgun() {
		super("shotgun", '-', 20, "shoot",2);
		super.allowableActions.add(new FireShotgunAction(null,this,display));	
	}
	
	/**
	 * Getter to obtain the price of the bullet 
	 */
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 300;
	}

	/**
	 * Method to craft a Golden Shotgun from it 
	 */
	@Override
	public WeaponItem craftWeapon() {
		// TODO Auto-generated method stub
		return new GoldenShotgun();
	}

}
