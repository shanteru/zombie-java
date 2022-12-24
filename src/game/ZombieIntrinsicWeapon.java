package game;

import edu.monash.fit2099.engine.IntrinsicWeapon;
	
/** Class that represents a weapon for an unarmed Zombie
 * @author Chantelle Loh
 *
 */

public class ZombieIntrinsicWeapon extends IntrinsicWeapon{
	private double probability;

	/**
	 * Constructor.
	 * Use super class's constructor to set the damage and verb 
	 * Set the private attribute probability
	 * @param damage : weapon's damage
	 * @param verb : weapon's verb 
	 * @param probability: value indicating the chances for the attack to hit (hit probability)
	 * @throws IllegalArgumentException if probability is not between 0 and 1 
	 */
	public ZombieIntrinsicWeapon(int damage, String verb, double probability) {
		super(damage, verb);
		if(probability < 0  || probability > 1) 
			throw new IllegalArgumentException("Probability of Zombie Intrinsic Weapon should be between 0 and 1");
		this.probability= probability;
		// TODO Auto-generated constructor stub
	}
	
	/**Getter for private attribute probability
	 * @return probability, a value which indicates hit probability
	 */
	public double getProbability() {
		return probability;
	}
}
