package game;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import edu.monash.fit2099.engine.Location;
/**
 * Class to represent Zombie's Limb. 
 * @author gan
 *
 */
public class ZombieLimb {
	
	static final double maxLimb = 2.0;
	private Map<String,Double> partCount = new HashMap<String,Double>();
	
	/**
	 * Create a ZombieLimb object. 
	 * Number of Arms and Legs will be initialised to 2.
	 */
	public ZombieLimb() {
		partCount.put("Arm",maxLimb);
		partCount.put("Leg",maxLimb);
	}
	
	/**
	 * Method to knockOff the Zombie Limb (Arm or Leg). Whenever a Zombie's Arm is knocked off,
	 * the number of arm the Zombie has is reduced by 1, and a new Zombie Arm Object will be created 
	 * to be placed in the Location given. Whenever a Zombie's Leg is knocked off,
	 * the number of leg the Zombie has is reduced by 1, and a new Zombie Leg Object will be created 
	 * to be placed in the Location given. 
	 * @param emptySpot Location which to placed to dropped Zombie's arm/leg.
	 * @param zombieName Zombie's name
	 */
	public void knockLimbOff(Location emptySpot,String zombieName) {
		Objects.requireNonNull(emptySpot, "Expected a location parameter, yet null is given");
		Objects.requireNonNull(zombieName, "Expected a string parameter, yet null is given");
		Random rand = new Random();
		String part = "Nothing";
		if (rand.nextBoolean()) {
			part = hittingLimb("Arm",emptySpot);
		}
		else {
			part = hittingLimb("Leg",emptySpot);
		}
		System.out.println(part + " of " + zombieName +" has been knocked off. The Zombie now has " 
						   + getPart("Arm") + " arm(s) and " + getPart("Leg") + " leg(s).");
	}
	
	private String hittingLimb(String part,Location emptySpot) {
		// If the Zombie still has the part which is to be knocked off, 
		// reduce the number of part by 1, and return the part name. 
		// Else, return a string Nothing.
		Objects.requireNonNull(part, "Expected a String parameter, yet null is given");
		Objects.requireNonNull(emptySpot, "Expected a Location parameter, yet null is given");

		if (partCount.get(part) != 0) {
			reducePart(part);
			placeZombieLimb(emptySpot,part);
			return part;
		}
		return "Nothing";
	}
	
	private void placeZombieLimb(Location emptySpot, String limbPart) {
		Objects.requireNonNull(emptySpot, "Expected a Location parameter, yet null is given");
		Objects.requireNonNull(limbPart, "Expected a String parameter, yet null is given");
		// placing the new ZombieArm of ZombieLeg created on the location given
		if (limbPart == "Arm") {
			emptySpot.addItem(new ZombieArm());
		}
		else {
			emptySpot.addItem(new ZombieLeg());
		}
	}
	
	private void reducePart(String part){
		Objects.requireNonNull(part, "Expected a String parameter, yet null is given");
		partCount.put(part,getPart(part) - 1.0);
	}
	
	private double getPart(String part) {
		Objects.requireNonNull(part, "Expected a String parameter, yet null is given");
		return partCount.get(part);
	}
	
	/**
	 * Obtain the portion of leg the Zombie still has 
	 * @return the portion of legs the Zombie still has 
	 */
	public double getLegPortion(){
		double returnValue = (getPart("Leg")/maxLimb);
		return returnValue;
	}
	
	/**
	 * Obtain the portion of arm the Zombie still has 
	 * @return the portion of arms the Zombie still has 
	 */
	public double getArmPortion(){
		return getPart("Arm")/maxLimb;
	}
	
	/**
	 * Check if the Zombie is still alive 
	 * @return a boolean indicating if the Zombie still alive
	 */
	public boolean canFunction(){
		return ((getPart("Leg") != 0) || (getPart("Arm") !=0));
	}
	
}
