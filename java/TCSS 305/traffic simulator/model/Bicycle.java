/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class to control the movement of a Bicycle vehicle.
 * 
 * @author Josh atherton
 * @version 1
 */
public class Bicycle extends AbstractVehicle {
    
    /** Field to hold the time vehicle stays dead. */
    private static final int MY_DEATH_TIME = 15;
    
    /** To generate random numbers. */
    private final Random myRandom;
    
     /** 
     * This constructor create a Bicycle object.
     * 
     * @param theX the x coordinate of the vehicle
     * @param theY the y coordinate of the vehicle
     * @param theDir the direction of the vehicle
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
        myRandom = new Random();
    }
   
    /**
    * This method checks if the light is valid for the Bicycle to travel on.
    * 
    * @param theTerrain the current terrain to travel.
    * @param theLight the color of light to pass through.
    */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = true;
        if ((theTerrain == Terrain.CROSSWALK && theLight == Light.RED) 
                     || theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW
                     || theTerrain == Terrain.LIGHT && theLight == Light.RED
                     || theTerrain == Terrain.LIGHT && theLight == Light.YELLOW) {
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
        final List<Direction> possibleDir = new ArrayList<>();
       
        // get forward direction if valid 
        if (validDir(theNeighbors.get(getDirection()))) { 
            possibleDir.add(this.getDirection());
        }
        // get left direction if valid
        if (validDir(theNeighbors.get(getDirection().left()))) { 
            possibleDir.add(this.getDirection().left());
        } 
        // get right direction if valid 
        if (validDir(theNeighbors.get(getDirection().right()))) {
            possibleDir.add(this.getDirection().right());
        }
        
        //if no direction go reverse otherwise pick a random direction
        if (possibleDir.isEmpty()) {
            directionToGo = this.getDirection().reverse(); 

        // a trail is adjacent to vehicle && direction is in possible direction list
        } else if (theNeighbors.containsValue(Terrain.TRAIL) 
                        && possibleDir.contains(trailDir(theNeighbors, possibleDir))) {  
            directionToGo = trailDir(theNeighbors, possibleDir);
               
        // pick a random forward, left, right dir
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
                        || theTerrain == Terrain.LIGHT
                        || (theTerrain == Terrain.CROSSWALK && theTerrain == Terrain.LIGHT)
                        || theTerrain == Terrain.TRAIL) {
            result = true;
        }
        return result;
    }
   
   /**
    * Helper method to get the key associated with the map value of terrain == trail.
    * 
    * @param theNeighbors a map full of neighboring tiles to vehicle.
    * @param thePossibleDir an array list containing possible directions able to go.
    * @return the location that the trail is located at.
    */
    public Direction trailDir(final Map<Direction, Terrain> theNeighbors,
                                          final List<Direction> thePossibleDir) {
        Direction trailLocation = null;
       
        for (final Direction key : theNeighbors.keySet()) {
            if (theNeighbors.get(key) == Terrain.TRAIL) {
                trailLocation = key;
            }
        }
        return trailLocation; 
    }
}