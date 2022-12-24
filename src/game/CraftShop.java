package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.guns.Shotgun;
import edu.monash.fit2099.guns.Sniper;

/**
 * A shop which allows Player to craft a sniper and a shotgun into golden one.
 * @author leong
 *
 */
public class CraftShop extends Item{
	public CraftShop() {
		super("Craft Shop",'^',false);
		super.allowableActions.add(new CraftWeaponAtShopAction(new Sniper(),2));
		super.allowableActions.add(new CraftWeaponAtShopAction(new Shotgun(),2));
	}

}
