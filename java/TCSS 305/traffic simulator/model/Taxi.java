/**
 * TCSS 305 - Easy Street
 */
package model;

/**
 * Class to control the movement of a Taxi vehicle.
 * 
 * @author Josh atherton
 * @version 1
 */
public class Taxi extends Car {
    
    /** Field to hold that amount of time to wait before running light. */
    private static final int MY_WAIT_TIME = 3;
    
    /** A counter to count the cycles at a stop light. */
    private int myCounter;
    
    /** 
     * This constructs a Taxi vehicle.
     * 
     * @param theX the x coordinate of the vehicle
     * @param theY the y coordinate of the vehicle
     * @param theDir the direction of the vehicle
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir);
        myCounter = 0;
    }
    
    /**
     * This method checks if the light is valid for the Taxi to travel on.
     * 
     * @param theTerrain the current terrain to travel.
     * @param theLight the color of light to pass through.
     */
    @Override                              
    public boolean canPass(final Terrain theTerrain, final Light theLight) {  
        boolean canPass = true;
        if (rightLightColor(theTerrain, theLight)) {
            
            // increment the counter if sitting at a red crosswalk light
            if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
                myCounter++;
                
            }
            
            // reset time waited if light turns before MY_WAIT_TIME
            if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
                myCounter = 0;
            }
            
            // do not move we are at a light
            canPass = false;
            
            // we have waited long enough go through crosswalk light
            if (myCounter > MY_WAIT_TIME) {
                canPass = true;
                myCounter = 0;
            }
        }
        return canPass;          
    }
    
    /**
     * This is a helper method to decide if the taxi can travel the current terrain type 
     * with the current light color.
     * 
     * @param theTerrain the current terrain to travel.
     * @param theLight the current light color
     * @return if the terrain can be traveled
     */
    public boolean rightLightColor(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain == Terrain.LIGHT && theLight == Light.RED 
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.RED);
        
    }
    
}