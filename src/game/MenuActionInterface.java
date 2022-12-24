package game;

/**
 * Interface that will be implemented by Actions which requires certain item.
 * For instance, the FireSniperAction requires the Player to have a Sniper in 
 * his inventory.Thus, FireSniperAction would implement this interface.
 * It is useful when it comes to filtering actions that are being shown to user.
 * @author leong
 *
 */
public interface MenuActionInterface {
	public String getAssociated();
}
