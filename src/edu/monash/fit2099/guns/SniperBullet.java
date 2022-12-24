package edu.monash.fit2099.guns;

/**
 * Bullet for sniper
 * @author leong
 *
 */
public class SniperBullet extends Bullet{
	
	/**
	 * Constructor
	 */
	public SniperBullet() {
		super("sniper bullet",'B',10);
		super.allowableActions.add(new ReloadAction("sniper",this));
	}
	
	/**
	 * Getter to obtain the price of the bullet 
	 */
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 50;
	}

}
