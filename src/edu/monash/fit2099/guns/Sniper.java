package edu.monash.fit2099.guns;

import edu.monash.fit2099.engine.WeaponItem;
import game.Craftable;

/**
 * A GunWeaponItem that can shoot any Zombie appears on the map. 
 * @author leong
 *
 */
public class Sniper extends GunWeaponItem implements Craftable{
	
	/**
	 * Constructor
	 */
	public Sniper() {
		super("sniper", '>', 20, "shoot",2);
		super.allowableActions.add(new FireSniperAction(null,this,display));
	}

	/**
	 * Getter to obtain the price of the sniper 
	 */
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 500;
	}
	
	/**
	 * Method to craft a Golden Sniper from it. 
	 */
	@Override
	public WeaponItem craftWeapon() {
		// TODO Auto-generated method stub
		return new GoldenSniper();
	}

}
