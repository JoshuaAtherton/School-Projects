/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class to control the movement of a Atv vehicle.
 * 
 * @author Josh atherton
 * @version 1
 */
public class Atv extends AbstractVehicle {
    
    /** Field to hold the time vehicle stays dead. */
    private static final int MY_DEATH_TIME = 10;
    
    /** To generate random numbers. */
    private final Random myRandom;
    
    /** 
     * This constructs an Atv vehicle.
     * 
     * @param theX the x coordinate of the vehicle
     * @param theY the y coordinate of the vehicle
     * @param theDir the direction of the vehicle
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
        myRandom = new Random();
    }
    
    /**
     * This method checks if the light is valid for the Atv to travel on.
     * 
     * @param theTerrain the current terrain to travel.
     * @param theLight the color of light to pass through.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain != Terrain.WALL) {
            canPass = true;
        }
        return canPass;
    }
    
    /**
     * This method pick the direction of travel for the Atv taking its 
     * preferences into account.
     * 
     * @param theNeighbors a map containing the terrain that surrounds the vehicle 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction directionToGo;
        final List<Direction> possibleDir = new ArrayList<>();
         
         // get left direction if valid add to possibleDir
        if (theNeighbors.get(getDirection().left()) != Terrain.WALL) { 
            possibleDir.add(this.getDirection().left());
        }
         
        // get right direction if valid add to possibleDir
        if (theNeighbors.get(getDirection().right()) != Terrain.WALL) { 
            possibleDir.add(this.getDirection().right());
        }
        // get forward direction if valid add to possibleDir
        if (theNeighbors.get(getDirection()) != Terrain.WALL) { 
            possibleDir.add(this.getDirection());
        }

        directionToGo = possibleDir.get(myRandom.nextInt(possibleDir.size()));
        
        return directionToGo;
    }

}