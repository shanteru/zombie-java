package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/** a wallet for players to have money
 * @author Chantelle Loh
 *
 */
public class Wallet extends Item {
	
	private int amount=100;
	private int magicTime =0;
	
	/**
	 * Constructor
	 * to set the name of the Wallet and displayChar and its portability
	 */
	public Wallet() {
		super("wallet", 'w', true);
	}


	/**
	 *so that the amount can increase as time hits multiple of 5
	 */
	@Override 
	public void tick(Location location, Actor actor) {
		if (magicTime % 5 ==0) {
			amount +=10;
		
		
		}
		magicTime +=1 ;
	}
	
	/**Getter of amount of wallet
	 * @return amount of wallet
	 */
	public int getAmount() {
		return amount;
	}
	

	/** To compute and update the amount of wallet
	 * @param spend amount spent
	 * 
	 */
	public void deduction(int spend) {
		int remainder = amount - spend;
		this.amount = remainder;
	}
	
	/** To compute and update the amount of wallet
	 * @param earnings amount earned
	 */
	public void earn(int earnings) {
		int savings = amount + earnings;
		this.amount = savings;
	}
	

}
