package game;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/** A crop that can grow and ripens. 
 * @author Chantelle Loh
 *
 */
public class Crops extends Ground {
	private int age = 0;

	/**
	 * Constructor.
	 * 
	 * sets the displayChar of Crops object to be 'c'
	 */
	public Crops(){
		super('c');
	}
	
	
	/**
	 * Override the super class's method tick for Crops to experience time,
	 * it will add a RipenCrops item and change displayChar to 'C' 
	 * to indicate that the Crops has ripen
	 */
	@Override 
	public void tick(Location location) {

		if (age == 20) {
			displayChar = 'C' ;
			RipenCrops ripenCrops = new RipenCrops(location);
			location.addItem(ripenCrops);
			
		}
		age++;
		
	}
	
	/**
	 * To increase the age so that it will hasten the ripen of crops
	 */
	public void fertilize() {
		int newAge = age + 10;
		age = Math.min(newAge, 20);
		
	}
	
	/**
	 * This method is to allow Actors to be able to harvest the ripened crops
	 * at surrounding exits 
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		Actions harvest = new Actions();
		if(location.getGround().getDisplayChar() =='C') {
			harvest.add( new HarvestingAction(location));
		}
		return harvest;
	}

}
