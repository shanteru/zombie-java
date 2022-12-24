package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

/**Vehicle to transport player between maps
 * @author Chantelle Loh
 *
 */
public class Vehicle extends Item{
	
	/**Constructor
	 * @param name name of Vehicle
	 * @param displayChar displayChar of Vehicle
	 * @param portable portability of Vehicle
	 */
	public Vehicle(String name, char displayChar, boolean portable){
		super(name,displayChar,portable);
		}
		
	/**To add an action into allowableActions of a Vehicle Item
	 * @param action action to be added 
	 */
	public void addAction(Action action){
		this.allowableActions.add(action);
		}
}
