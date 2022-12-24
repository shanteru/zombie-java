package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Zombie Club is a weapon, can be crafted by using Zombie Arm. 
 * @author Gan 
 *
 */
public class ZombieClub extends WeaponItem {
	
	/**
	 * Create a new ZombieClub weapon item.
	 */
	public ZombieClub() {
		super("ZombieClub",'l',15,"hit(with Zombie Club)");
	}

}
