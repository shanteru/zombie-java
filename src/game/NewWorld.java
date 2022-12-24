package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;
import edu.monash.fit2099.mambomarie.MamboMarieGrave;

/**Class representing the game world, including the locations of all Actors, the
 * player, and the playing grid with extra conditions overriding some method from its super class World
 * @author Chantelle Loh
 *
 */
public class NewWorld extends World {
	
	private MamboMarieGrave grave;
	private int endGame = 0;
	
	/**Constructor with 2 parameters
	 * @param display display of the world
	 * @param grave grave in the world
	 */
	public NewWorld(Display display, MamboMarieGrave grave) {
		super(display);
		this.grave = grave;
	}
	
	/**Constructor with one parameter 
	 * @param display display of the world
	 */
	public NewWorld(Display display) {
		super(display);
	}
	
	
	/**
	 * Modified and override stillRunning() to add in conditions for more variation to end game
	 */
	@Override
	protected boolean stillRunning() {	
		boolean boss = grave.getDeathInfo();
		boolean human = false;
		boolean zombie = false;
		for (Actor actor: super.actorLocations) {
			if (!(actor instanceof Player)) {
				if (actor instanceof Human){
					human = true;
				}
				else if(actor instanceof Zombie) {
					zombie = true;
				}
			}
			
		}

		if(!human) {
			setEndGame(1);
			return false;
		}
		else if(boss && !zombie){
			setEndGame(2);
			return false;
		}
		else if(getEndGame() ==3) {
			return false;
		}
		return super.stillRunning();
	}
	
	/**
	 * To print out a display message as it run
	 */
	@Override
	public void run() {
		super.run();
		if (getEndGame() == 1 ){
			super.display.println("---player loses---");
		}
		else if (getEndGame()==2){
			super.display.println("---player win---");
		}
		else if (getEndGame()== 3) {
			super.display.println("Thanks for playing :)");
		}
		
	}


	/**Getter for endGame attribute
	 * @return value of endGame
	 */
	public int getEndGame() {
		return endGame;
	}


	/**Setter for the endGame attribute
	 * @param endGame value of endGame to be set
	 */
	public void setEndGame(int endGame) {
		this.endGame = endGame;
	}
	
}
