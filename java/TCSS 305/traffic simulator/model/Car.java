/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Map;

/**
 * Class to control the movement of a Car vehicle.
 * 
 * @author Josh atherton
 * @version 1
 */
public class Car extends AbstractVehicle {
    
    /** Field to hold the time vehicle stays dead. */
    private static final int MY_DEATH_TIME = 5;
    
    /** 
     * This constructs a Car vehicle.
     * 
     * @param theX the x coordinate of the vehicle
     * @param theY the y coordinate of the vehicle
     * @param theDir the direction of the vehicle
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }

    /**
     * This method checks if the light is valid for the car to travel on.
     * 
     * @param theTerrain the current terrain to travel.
     * @param theLight the color of light to pass through.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = true;
        if ((theTerrain == Terrain.CROSSWALK && theLight == Light.RED) 
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW)
                        || (theTerrain == Terrain.LIGHT && theLight == Light.RED)) {
            canPass = false;
        }
        return canPass;
    }
    
    /**
     * This method picks the direction of travel for the vehicle taking its 
     * preferences into account.
     * 
     * @param theNeighbors a map containing the terrain that surrounds the vehicle 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        final Direction directionToGo;
        
        // get forward direction if valid go that way
        if (validDir(theNeighbors.get(getDirection()))) { 
            directionToGo = this.getDirection();
        
        // get left direction if valid go left
        } else if (validDir(theNeighbors.get(getDirection().left()))) { 
            directionToGo = this.getDirection().left();
        
        // get right direction if valid go right
        } else if (validDir(theNeighbors.get(getDirection().right()))) {
            directionToGo = this.getDirection().right();
        
        //if no direction go reverse    
        } else {
            directionToGo = this.getDirection().reverse();
        }
        return directionToGo;
    }
    /**
     * check to see if the terrain type is a valid choice.
     * 
     * @param theTerrain the terrain type to check if can travel on it.
     * @return boolean if you can pass on the tile
     */
    public boolean validDir(final Terrain theTerrain) {
        boolean result = false;
        if (theTerrain == Terrain.STREET 
                        || theTerrain == Terrain.CROSSWALK 
                        || theTerrain == Terrain.LIGHT) {
            result = true;
        }
        return result;
    }

}