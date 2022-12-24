package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;

/** An action for quit game
 * @author Chantelle Loh
 *
 */
public class EndingGameAction extends Action {

	
	NewWorld world = new NewWorld(new Display());
	
	/**Constructor
	 * @param world targeted world to be ended
	 */
	public EndingGameAction(NewWorld world) {
		
		this.world = world;
	}

	/**
	 *set the endGame in NewWorld to be 3, for it to end
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		world.setEndGame(3);
		return menuDescription(actor);
	}

	/**
	 *String decription of what is this action will be of eg,"Player quit game."
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " quit game. ";
	}
	
	@Override
	public String hotkey() {
		return "*";
	}
	
	


}
