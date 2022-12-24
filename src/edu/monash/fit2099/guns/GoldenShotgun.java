package edu.monash.fit2099.guns;

/**
 * An upgraded shotgun with more damage. Craftable using the Gold object.
 * @author leong
 *
 */
public class GoldenShotgun extends GunWeaponItem{
	
	/**
	 * Constructor
	 */
	public GoldenShotgun() {
		super("golden shotgun", '=', 40, "shoot",10);
		super.allowableActions.add(new FireShotgunAction(null,this,display));	
	}

	/**
	 * Getter to obtain the shotgun's price. 
	 */
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 500;
	}

}
