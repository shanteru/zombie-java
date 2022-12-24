package edu.monash.fit2099.mambomarie;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.WanderBehaviour;
import game.ZombieActor;
import game.ZombieCapability;

/**
 * MamboMarie class.
 * @author leong
 *
 */
public class MamboMarie extends ZombieActor{
	
	private MamboMarieGrave grave;
	private WanderBehaviour wander = new WanderBehaviour();
	private int age = 0;
	
	/**
	 * Constructor 
	 * @param grave grave object which spawn the MamboMarie 
	 */
	public MamboMarie(MamboMarieGrave grave) {
		super("Mambo Marie",'M',250,ZombieCapability.UNDEAD);
		Objects.requireNonNull(grave, "Expected a grave parameter, yet null is given");
		this.grave = grave;
	}
	
	/**
	 * When MamboMarie is dead(hitpoints less than 0) , it will inform its grave 
	 * to turns the grave into a Dirt.
	 */
	@Override
	public void hurt(int points) {
		super.hurt(points);
		if (this.hitPoints <= 0) {
			grave.informMamboDeath(); // so that it can transform itself to ground 
		}
	}
	
	/**
	 * MamboMarie play turn. Each turn, the number of turn that it has been on the map 
	 * is being added by 1. When the number of turn it has been on the map reach 30, it 
	 * will disappear from the map, whereas every 10 turns, it will chant and create 5 zombies.
	 * If it can't disappear and chant, it will just wander randomly.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		age++;
		if (age == 30) {
			grave.informMamboDisappear(); // inform the ground so that grave knows now, can generate a new Mambo anytime
			// remove self action
			return new RemoveActorAction();
		}
		
		if (age%10 == 0) {
			// chant 
			return new ChantingAction();
		} 
		
		Action wanderAction = wander.getAction(this, map);
		if (wanderAction != null) {
			return wanderAction;
		}
		
		return new DoNothingAction();
	}

}
