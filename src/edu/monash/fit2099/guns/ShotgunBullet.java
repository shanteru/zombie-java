package edu.monash.fit2099.guns;

/**
 * Bullet for the shotgun.
 * @author leong
 *
 */
public class ShotgunBullet extends Bullet{
	
	/**
	 * Constructor
	 */
	public ShotgunBullet() {
		super("shotgun bullet",'b',10);
		super.allowableActions.add(new ReloadAction("shotgun",this));
	}
	
	/**
	 * Getter to obtain the price of the bullet 
	 */
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 30;
	}	
	
}
