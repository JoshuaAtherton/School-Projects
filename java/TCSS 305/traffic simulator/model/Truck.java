/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class to control the movement of a Truck vehicle.
 * 
 * @author Josh atherton
 * @version 1
 */
public class Truck extends AbstractVehicle {
    
    /** Field to hold the time vehicle stays dead. */
    private static final int MY_DEATH_TIME = 0;
    
    /** To generate random numbers. */
    private final Random myRandom;
    
    /** 
     * The constructor a Truck vehicle.
     * 
     * @param theX the x coordinate of the vehicle
     * @param theY the y coordinate of the vehicle
     * @param theDir the direction of the vehicle
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
        myRandom = new Random();
    }
    
    /**
     * This method checks if the light is valid for the truck to travel on.
     * 
     * @param theTerrain the current terrain to travel.
     * @param theLight the color of light to pass through.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (!(theTerrain == Terrain.CROSSWALK && theLight == Light.RED)) {
            canPass = true;
        }
        return canPass;
    }
    
    /**
     * This method picks the direction of travel for the Truck taking its 
     * preferences into account.
     * 
     * @param theNeighbors a map containing the terrain that surrounds the vehicle 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction directionToGo;
        final List<Direction> possibleDir = new ArrayList<>();
         
         // get left direction if valid add to possibleDir
        if (validDir(theNeighbors.get(getDirection().left()))) { 
            possibleDir.add(this.getDirection().left());
        }
         
        // get right direction if valid add to possibleDir
        if (validDir(theNeighbors.get(getDirection().right()))) {
            possibleDir.add(this.getDirection().right());
        }
    
        // get forward direction if valid add to possibleDir
        if (validDir(theNeighbors.get(getDirection()))) { 
            possibleDir.add(this.getDirection());
        }
        
        //if no direction go reverse otherwise pick a random direction
        if (possibleDir.isEmpty()) {
            directionToGo = this.getDirection().reverse();
        } else {
            directionToGo = possibleDir.get(myRandom.nextInt(possibleDir.size())); 
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
