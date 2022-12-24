package edu.monash.fit2099.mambomarie;

import java.util.Objects;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Dirt;

/**
 * A special kind of ground (grave) that the MamboMarie comes from 
 * @author leong
 *
 */
public class MamboMarieGrave extends Ground{
	
	private boolean mamboStop = false;
	private boolean mamboDie = false;
	private Random rand = new Random();
	
	/**
	 * Constructor 
	 */
	public MamboMarieGrave() {
		super('l');
	}
	
	/**
	 * Each turn, the grave will be tick. If the MamboMarie is not on the map,
	 * there is a 5% chance of it appearing on the map each turn. If the MamboMarie 
	 * is dead, then the grave will disappear and a Dirt ground will replace it.
	 */
	@Override
	public void tick(Location location) {
		Objects.requireNonNull(location, "Expected a Location parameter, yet null is given");
		if(mamboDie) {
			location.setGround(new Dirt()); // grave is forever removed 
		}
		if(!mamboStop) { // means can generate new Mambo (Mambo not on the map)
			if(rand.nextDouble() < 0.05) {
				location.addActor(new MamboMarie(this));
				mamboStop = true; // stop generating Mambo
			}
		}
		super.tick(location);
	}
	
	/**
	 * Called in MamboMarie class when the MamboMarie is dead. 
	 */
	public void informMamboDeath() {
		mamboDie = true;
	}
	
	/**
	 * Called in MamboMarie class when the MamboMarie disappear (after 30 turns on the map). 
	 */
	public void informMamboDisappear() {
		mamboStop = false;
	}
	
	/**
	 * @return false when asked if actor can enter the grave.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return false;
	}
	
	/**
	 * Getter to obtain MamboMarie status.
	 * @return boolean of MamboMarie death, true if she died
	 */
	public boolean getDeathInfo() {
		// TODO Auto-generated method stub
		return mamboDie;
	}


}
