package edu.monash.fit2099.guns;

import java.util.Objects;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
import game.MenuActionInterface;

/**
 * Action that reload a GunWeaponItem.
 * @author leong
 *
 */
public class ReloadAction extends Action implements MenuActionInterface{
	
	private String weaponName;
	private Bullet bullet;
	
	/**
	 * Constructor
	 * @param weaponInvolved weapon that is reloading
	 * @param bullet bullet object which is used to reload the weapon
	 */
	public ReloadAction(String weaponInvolved, Bullet bullet) {
		Objects.requireNonNull(weaponInvolved, "Expected an String parameter, yet null is given");
		Objects.requireNonNull(bullet, "Expected an Bullet parameter, yet null is given");
		this.weaponName = weaponInvolved;
		this.bullet = bullet;
	}
	
	/**
	 * The method to execute the action. It will first check if the Player has the weapon
	 * involved. If not, then nothing would be done. Else, the weapon will be reloaded
	 * and the bullet will be remove from the Player's inventory.
	 * @param actor the actor who is attacking 
	 * @param map the map the actor is on 
	 * @throws NullPointerException if actor parameter is a null
	 * @throws NullPointerException if map parameter is a null  
	 * @return description of what has happened
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		String res = "";
		boolean reloaded = false;
		for (Item item:actor.getInventory()) { 
			if (item.toString() == weaponName) {
				((GunWeaponItem) item).reload(bullet.getReloadAmount());
				res = item.toString() + " has been reloaded.";
				reloaded = true; //I might have the bullet but not the weapon involved
			}
		}
		if (!reloaded) {
			return "Player does not have a " + weaponName +". Reload failed.";
		}
		actor.removeItemFromInventory(bullet);
		return res;
	}
	
	/**
	 *Give a description of what will happen when this action is executed.
	 *@param actor the actor who is attacking 
	 *@return description of the action 
	 */
	@Override
	public String menuDescription(Actor actor) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		return "Reload " + weaponName;
	}
	
	/**
	 * @return the item(its name ) that the Player needs in order for this 
	 * action to be shown on menu.
	 */
	@Override
	public String getAssociated() {
		// TODO Auto-generated method stub
		return bullet.toString();
	}

}
