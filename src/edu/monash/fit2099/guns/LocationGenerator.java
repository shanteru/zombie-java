package edu.monash.fit2099.guns;

import java.util.ArrayList;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * A location generator which takes in an actor original location, 
 * and generate locations which the shotgun would cover according to 
 * the Location given. 
 * @author leong
 *
 */
public class LocationGenerator {

	private int x;
	private int y;
	
	/**
	 * Constructor
	 * @param originalX actor's x coordinate 
	 * @param originalY actor's y coordinate 
	 */ 
	public LocationGenerator(int originalX,int originalY) {
		x = originalX;
		y = originalY;
	}
	
	/**
	 * Get locations which the shotgun damage would cover according to the direction given.
	 * @param map map that the actor is currently in 
	 * @param direction direction given to generate locations 
	 * @return an array of locations (will be used by shotgun)
	 */
	public ArrayList<Location> getLocations(GameMap map,Direction direction){
		int maxX = map.getXRange().max();
		int maxY = map.getYRange().max();
		ArrayList<Location> generatedLocation = new ArrayList<Location>();
		ArrayList<Pair> coordinates = new ArrayList<Pair>();
		switch(direction) {
		case NORTH:
			coordinates = getNorth(map);
			break;
		case SOUTH:
			coordinates = getSouth(map);
			break;
		case WEST:
			coordinates = getWest(map);
			break;
		case EAST:
			coordinates = getEast(map);
			break;
		case NORTHEAST:
			coordinates = getNorthEast(map);
			break;
		case NORTHWEST:
			coordinates = getNorthWest(map);
			break;
		case SOUTHEAST:
			coordinates = getSouthEast(map);
			break;
		case SOUTHWEST:
			coordinates = getSouthWest(map);
			break;
		}
		for (Pair coord:coordinates) {
			if (coord.getX() <= maxX & coord.getY() <= maxY) { // to prevent index out of range 
				generatedLocation.add(map.at(coord.getX(),coord.getY()));
			}
		}
		return generatedLocation;
	}
	
	private ArrayList<Pair> getNorth(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
	
		coordinates.add(new Pair (x-3,y-3));
		coordinates.add(new Pair (x-2,y-3));
		coordinates.add(new Pair (x-1,y-3));
		coordinates.add(new Pair (x,y-3));
		coordinates.add(new Pair (x+1,y-3));
		coordinates.add(new Pair (x+2,y-3));
		coordinates.add(new Pair (x+3,y-3));

		coordinates.add(new Pair (x-2,y-2));
		coordinates.add(new Pair (x-1,y-2));
		coordinates.add(new Pair (x,y-2));
		coordinates.add(new Pair (x+1,y-2));
		coordinates.add(new Pair (x+2,y-2));
		
		coordinates.add(new Pair (x-1,y-1));
		coordinates.add(new Pair (x,y-1));
		coordinates.add(new Pair (x+1,y-1));
		
		return coordinates;
	}
	
	private ArrayList<Pair> getSouth(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
	
		coordinates.add(new Pair (x-3,y+3));
		coordinates.add(new Pair (x-2,y+3));
		coordinates.add(new Pair (x-1,y+3));
		coordinates.add(new Pair (x,y+3));
		coordinates.add(new Pair (x+1,y+3));
		coordinates.add(new Pair (x+2,y+3));
		coordinates.add(new Pair (x+3,y+3));

		coordinates.add(new Pair (x-2,y+2));
		coordinates.add(new Pair (x-1,y+2));
		coordinates.add(new Pair (x,y+2));
		coordinates.add(new Pair (x+1,y+2));
		coordinates.add(new Pair (x+2,y+2));
		
		coordinates.add(new Pair (x-1,y+1));
		coordinates.add(new Pair (x,y+1));
		coordinates.add(new Pair (x+1,y+1));
		
		return coordinates;
	}
	
	private ArrayList<Pair> getEast(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
	
		coordinates.add(new Pair (x+3,y-3));
		coordinates.add(new Pair (x+3,y-2));
		coordinates.add(new Pair (x+3,y-1));
		coordinates.add(new Pair (x+3,y));
		coordinates.add(new Pair (x+3,y+1));
		coordinates.add(new Pair (x+3,y+2));
		coordinates.add(new Pair (x+3,y+3));

		coordinates.add(new Pair (x+2,y-2));
		coordinates.add(new Pair (x+2,y-1));
		coordinates.add(new Pair (x+2,y));
		coordinates.add(new Pair (x+2,y+1));
		coordinates.add(new Pair (x+2,y+2));
		
		coordinates.add(new Pair (x+1,y-1));
		coordinates.add(new Pair (x+1,y));
		coordinates.add(new Pair (x+1,y+1));
		
		return coordinates;
	}
	
	private ArrayList<Pair> getWest(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
	
		coordinates.add(new Pair (x-3,y-3));
		coordinates.add(new Pair (x-3,y-2));
		coordinates.add(new Pair (x-3,y-1));
		coordinates.add(new Pair (x-3,y));
		coordinates.add(new Pair (x-3,y+1));
		coordinates.add(new Pair (x-3,y+2));
		coordinates.add(new Pair (x-3,y+3));

		coordinates.add(new Pair (x-2,y-2));
		coordinates.add(new Pair (x-2,y-1));
		coordinates.add(new Pair (x-2,y));
		coordinates.add(new Pair (x-2,y+1));
		coordinates.add(new Pair (x-2,y+2));
		
		coordinates.add(new Pair (x-1,y-1));
		coordinates.add(new Pair (x-1,y));
		coordinates.add(new Pair (x-1,y+1));
		
		return coordinates;
	}
	
	private ArrayList<Pair> getNorthEast(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
		
		coordinates.add(new Pair (x+3,y));
		coordinates.add(new Pair (x+2,y));
		coordinates.add(new Pair (x+1,y));
		
		coordinates.add(new Pair (x,y-1));
		coordinates.add(new Pair (x+1,y-1));
		coordinates.add(new Pair (x+2,y-1));
		coordinates.add(new Pair (x+3,y-1));

		coordinates.add(new Pair (x,y-2));
		coordinates.add(new Pair (x+1,y-2));
		coordinates.add(new Pair (x+2,y-2));
		coordinates.add(new Pair (x+3,y-2));
		
		coordinates.add(new Pair (x,y-3));
		coordinates.add(new Pair (x+1,y-3));
		coordinates.add(new Pair (x+2,y-3));
		coordinates.add(new Pair (x+3,y-3));
		
		return coordinates;
	}
	
	private ArrayList<Pair> getNorthWest(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
		
		coordinates.add(new Pair (x-3,y));
		coordinates.add(new Pair (x-2,y));
		coordinates.add(new Pair (x-1,y));
		
		coordinates.add(new Pair (x,y-1));
		coordinates.add(new Pair (x-1,y-1));
		coordinates.add(new Pair (x-2,y-1));
		coordinates.add(new Pair (x-3,y-1));

		coordinates.add(new Pair (x,y-2));
		coordinates.add(new Pair (x-1,y-2));
		coordinates.add(new Pair (x-2,y-2));
		coordinates.add(new Pair (x-3,y-2));
		
		coordinates.add(new Pair (x,y-3));
		coordinates.add(new Pair (x-1,y-3));
		coordinates.add(new Pair (x-2,y-3));
		coordinates.add(new Pair (x-3,y-3));
		
		return coordinates;
	}
	
	private ArrayList<Pair> getSouthWest(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
		
		coordinates.add(new Pair (x-3,y));
		coordinates.add(new Pair (x-2,y));
		coordinates.add(new Pair (x-1,y));
		
		coordinates.add(new Pair (x-3,y+1));
		coordinates.add(new Pair (x-2,y+1));
		coordinates.add(new Pair (x-1,y+1));
		coordinates.add(new Pair (x,y+1));

		coordinates.add(new Pair (x-3,y+2));
		coordinates.add(new Pair (x-2,y+2));
		coordinates.add(new Pair (x-1,y+2));
		coordinates.add(new Pair (x,y+2));
		
		coordinates.add(new Pair (x,y+3));
		coordinates.add(new Pair (x-1,y+3));
		coordinates.add(new Pair (x-2,y+3));
		coordinates.add(new Pair (x-3,y+3));
		
		return coordinates;
	}
	
	private ArrayList<Pair> getSouthEast(GameMap map) {
		ArrayList<Pair> coordinates = new ArrayList<Pair>(); 
		
		coordinates.add(new Pair (x+3,y));
		coordinates.add(new Pair (x+2,y));
		coordinates.add(new Pair (x+1,y));
		
		coordinates.add(new Pair (x+3,y+1));
		coordinates.add(new Pair (x+2,y+1));
		coordinates.add(new Pair (x+1,y+1));
		coordinates.add(new Pair (x,y+1));

		coordinates.add(new Pair (x+3,y+2));
		coordinates.add(new Pair (x+2,y+2));
		coordinates.add(new Pair (x+1,y+2));
		coordinates.add(new Pair (x,y+2));
		
		coordinates.add(new Pair (x,y+3));
		coordinates.add(new Pair (x+1,y+3));
		coordinates.add(new Pair (x+2,y+3));
		coordinates.add(new Pair (x+3,y+3));
		
		return coordinates;
	}
}
