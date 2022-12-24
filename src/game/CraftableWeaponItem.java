package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A base class for weapons that are craftable. 
 * @author Gan
 *
 */
public abstract class CraftableWeaponItem extends WeaponItem implements Craftable{
	
	/**
	 * 
	 * @param weaponName The name of the weapon that can be crafted.
	 * @param weaponRep The representation of the weapon on map. 
	 * @param damage amount of damage this weapon does.
	 * @param verb verb to use for this weapon. 
	 */
	public CraftableWeaponItem(String weaponName, char weaponRep,int damage,String verb) {
		super(weaponName,weaponRep,damage,verb);
		super.allowableActions.add(new CraftWeaponAction(this));
	}
	
}
