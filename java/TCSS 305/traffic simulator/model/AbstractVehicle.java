/**
 * TCSS 305 - Easy Street
 */
package model;

import java.util.Locale;
import java.util.Map;

/**
 * An abstract interface to for objects that can move in four directions.
 * This controls several of the rules child objects must follow.
 * 
 * @author Josh atherton
 * @version 1
 */
public abstract class AbstractVehicle implements Vehicle {

    /** This Vehicle object's x-coordinate. */
    private int myX;
    
    /** This Vehicle object's original x-coordinate. */
    private final int myXOriginal;
    
    /** This Vehicle object's y-coordinate. */
    private int myY;
    
    /** This Vehicle object's original y-coordinate. */
    private final int myYOriginal;
    
    /** The direction this object would like to move. */
    private Direction myDir;
    
    /** The original direction this object would like to move. */
    private final Direction myDirOriginal;
    
    /** a field to hold the death time of each vehicle. */
    private final int myDeathTime;     
          
    /** Store how long vehicle has been dead. */
    private int myCurrentDeathTimeCounter;          
    
    
    /**
     * The constructor that creates a vehicle.
     * 
     * @param theX the x coordinate for the vehicle
     * @param theY the y coordinate for the vehicle
     * @param theDir the direction for the vehicle
     * @param theDeathTime the time a vehicle stays dead
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir, 
                              final int theDeathTime) {
        super();                                                           
        myX = theX;
        myY = theY;
        myDir = theDir;
        myDeathTime = theDeathTime;
        
        myXOriginal = theX;
        myYOriginal = theY;
        myDirOriginal = theDir;
    }
    
    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not this object may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);
    
    /* 
     * Called when this Vehicle collides with the specified other Vehicle.
     * 
     * @param theOther The other object. 
     */
    @Override
    public void collide(final Vehicle theOther) {

        if (isAlive() && theOther.isAlive() && myDeathTime > theOther.getDeathTime()) { 
            myCurrentDeathTimeCounter++;
        }
    }
                     
    /* 
     * The amount of time(pokes) that a vehicle remains dead.
     * 
     * @return the time a vehicle remains dead
     */
    @Override
    public int getDeathTime() {

        return myDeathTime;
    }

    /* 
     * Returns the name of the image file for this vehicle.
     * 
     * @return the name of the image file. ex) "car.gif"
     */
    @Override
    public String getImageFileName() {                      
        final String vehicleImage;
        if (isAlive()) {
            vehicleImage = getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".gif";
        } else {
            vehicleImage = 
                      getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + "_dead.gif";
        }
        return vehicleImage;
    }

    /**
     * Returns this Vehicle object's direction.
     * 
     * @return the direction.
     */
    @Override
    public Direction getDirection() {
        
        return myDir;
    }

    /**
     * Returns this Vehicle object's x-coordinate.
     * 
     * @return the x-coordinate.
     */
    @Override
    public int getX() {
        
        return myX;
    }

    /**
     * Returns this Vehicle object's y-coordinate.
     * 
     * @return the y-coordinate.
     */
    @Override
    public int getY() {
        
        return myY;
    }

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     * 
     * @return true if the object is alive, false otherwise.
     */
    @Override
    public boolean isAlive() {     
        // if is alive the deadTime counter should = zero
        boolean alive = false;
        if (myCurrentDeathTimeCounter == 0) {
            alive = true;
        }
        return alive;
    }

    /* 
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     * When a dead vehicle revives, it must set its direction to be a random direction. 
     */
    @Override
    public void poke() {
        // if dead use the poke counter to see for how many cycles to stay dead
        if (!isAlive()) {
            if (myCurrentDeathTimeCounter >= myDeathTime) {  
                setDirection(Direction.random());
                myCurrentDeathTimeCounter = 0;
            } else {
                myCurrentDeathTimeCounter++;
            }
        }
    }
        
    /* 
     * Moves this vehicle back to its original position.
     * To implement reset in the parent class: When the object is constructed, 
     * remember its initial position and direction for restoration later if reset is called. 
     */
    @Override
    public void reset() {
        // all vehicles back to their initial state
        // if object is fully immutable just call the original object
        myX = myXOriginal;
        myY = myYOriginal;
        myDir = myDirOriginal;
        myCurrentDeathTimeCounter = 0;

    }

    /**
     * Sets this object's facing direction to the given value.
     * 
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(final Direction theDir) {
        
        myDir = theDir;
    }

    /**
     * Sets this object's x-coordinate to the given value.
     * 
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(final int theX) {
        
        myX = theX;
    }

    /**
     * Sets this object's y-coordinate to the given value.
     * 
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(final int theY) {
        
        myY = theY;
    }
    
    /**
     * A string representing the vehicle.
     * 
     * @return a formatted string when vehicle object called
     */
    @Override
    public String toString() {
        final StringBuilder vehicleOutput = new StringBuilder();
        if (isAlive()) {
            vehicleOutput.append(getClass().getSimpleName().toLowerCase(Locale.ENGLISH));
        } else {
            vehicleOutput.append(
                          getClass().getSimpleName().toLowerCase(Locale.ENGLISH));
            vehicleOutput.append("Death Time: ");
            vehicleOutput.append(myDeathTime);
            vehicleOutput.append(" Timer: ");
            vehicleOutput.append(myCurrentDeathTimeCounter);
        }
        return vehicleOutput.toString();
    }

}
