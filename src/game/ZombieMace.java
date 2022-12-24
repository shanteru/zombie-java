package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * Zombie Mace is a weapon that can be crafted by using Zombie Leg. 
 * @author Gan 
 *
 */
public class ZombieMace extends WeaponItem{
	
	/**
	 * Create a new ZombieMace WeaponItem. 
	 */
	public ZombieMace() {
		super("ZombieMace",'M',20,"hit(with Zombie Mace)");
	}

}
