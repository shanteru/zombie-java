package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * ZombieArm CraftableWeaponItem will be created when the Zombie's Arm is knocked off.Player can use it 
 * to craft a Zombie Club, as it is a subclass of CraftableWeaponItem. 
 * @author Gan
 */
public class ZombieArm extends CraftableWeaponItem {
	
	/**
	 * Create a Zombie Arm CraftableWeaponItem. 
	 */
	public ZombieArm() {
		super("Zombie Arm",'A',10,"hit with arm");
	}

	@Override
	public WeaponItem craftWeapon() {
		// TODO Auto-generated method stub
		return new ZombieClub();
	}

}


