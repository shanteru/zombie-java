package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/** A ripen crop which can be harvest.
 * @author Chantelle Loh
 *
 */
public class RipenCrops extends Item {

	/**
	 * Constructor.
	 * 
	 * Using the super class's constructor to set the name, displayChar and portability
	 * Using the super class's attribute allowableActions , added HarvestingAction
	 * to allow this item to be able to be harvest
	 * @param location location of the RipenCrop
	 */
	public RipenCrops(Location location) {
		super("RipenCrop", 'C', false);
		super.allowableActions.add(new HarvestingAction(location));
		// TODO Auto-generated constructor stub
	}
	
	
}
