
package game;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

/**
 * Class representing a sub-type of Human which is Farmer
 * 
 * @author Chantelle Loh
 *
 */
public class Farmer extends Human{
	private Behaviour[] behaviours = {
			new HarvestingBehaviour(),
			new FertilizeBehaviour(),
			new SowCropBehaviour(),	
			};

	
	/**Constructor.
	 * The default constructor creates default Farmers
	 * 
	 * @param name the farmer's display name
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
		}
	
	/**
	 * it will iterate the list of Behaviours, if behaviour is not null, it will get the corresponding action.
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Farmer is
	 * @param display the Display where the Farmer's utterances will be displayed
	 * @throws NullPointerException if map parameter is a null
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		// TODO Auto-generated method stub
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
			
		}
		return super.playTurn(actions, lastAction, map, display);	
	}
	}


