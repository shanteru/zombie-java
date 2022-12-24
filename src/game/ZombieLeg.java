package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * ZombieLeg CraftableWeaponItem will be created when the Zombie's Leg is knocked off.Player can use it 
 * to craft a Zombie Mace, as it is a subclass of CraftableWeaponItem. 
 * @author Gan
 */
public class ZombieLeg extends CraftableWeaponItem{
	
	/**
	 * Create a Zombie Leg CraftableWeaponItem. 
	 */
	public ZombieLeg() {
		super("Zombie Leg", 'L', 10, "hit with leg");
	}

	@Override
	public WeaponItem craftWeapon() {
		// TODO Auto-generated method stub
		return new ZombieMace();
	}
	
}
