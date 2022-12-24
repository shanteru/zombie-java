package edu.monash.fit2099.guns;

/**
 * Object to store a tuple (coordinates). 
 * @author leong
 *
 */
public class Pair {
	
	private int x;
	private int y;
	
	/**
	 * Constructor
	 * @param theX x coordinate
	 * @param theY y coordinate 
	 */
	public Pair(int theX,int theY ) {
		x = theX;
		y = theY;
	}
	
	/**
	 * Get the x coordinate.  
	 * @return x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the y coordinate.  
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}
}
