package edu.monash.fit2099.guns;

/**
 * An "upgraded" sniper. Craftable using the Gold object. 
 * @author leong
 *
 */
public class GoldenSniper extends GunWeaponItem{
	
	/**
	 * Constructor 
	 */
	public GoldenSniper() {
		super("golden sniper", '*', 40, "shoot",10);
		super.allowableActions.add(new FireSniperAction(null,this,display));
	}
	
	/**
	 * Getter to obtain the price of the golden sniper.
	 */
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 800;
	}

}
