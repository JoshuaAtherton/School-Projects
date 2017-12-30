/*
 * TCSS 305 Assignment 2 - Shopping Cart
 */

package view;

import java.awt.EventQueue;

import utility.FileLoader;

/**
 * ShoppingMain provides the main method for a simple shopping cart GUI.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman (Formatting and Comments)
 * @author Alan Fowler (Numerous changes including use of BigDecimal and file input)
 * 
 * @version Autumn 2016
 */

public final class ShoppingMain {
    
    /**
     * The path and name of the inventory file.
     */
    private static final String INVENTORY_FILE = "files/tacoma.txt";   

    /**
     * A private constructor, to prevent external instantiation.
     */
    private ShoppingMain() {
    }

    /**
     * The main() method - displays and runs the shopping cart GUI.
     * 
     * @param theArgs Command line arguments, ignored by this program.
     */
    public static void main(final String... theArgs) { 
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShoppingFrame(FileLoader.readItemsFromFile(INVENTORY_FILE));
            }
        });
    } // end main()

} // end class ShoppingMain
