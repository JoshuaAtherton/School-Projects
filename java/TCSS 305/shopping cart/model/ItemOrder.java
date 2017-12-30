/*
 * TCSS 305 Assignment 2 - Shopping cart
 */
package model;
/**
 * This program is used to order an Item. Representing the item and Quantity to purchase.
 * 
 * @author Josh Atherton
 * @version 1
 */
public final class ItemOrder {

    /** Field to hold the item. */
    private final Item myItem;
    
    /** Field to hold the quantity purchased. */
    private final int myQuantity;
     
    /**
     * Constructor that creates an item order for the given quantity of the given Item. 
     * The quantity is an int.
     * 
     * @param theItem the item that is being purchased.
     * @param theQuantity this is the quantity of the item to purchase.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        
        myItem = theItem;
        myQuantity = theQuantity; 
    }

    /**
     * Returns a reference to the Item in this ItemOrder.
     * 
     * @return the Item.
     */
    public Item getItem() { 
        
        return myItem;
    }
    
    /**
     * Returns the quantity for this ItemOrder.
     * 
     * @return the Quantity.
     */
    public int getQuantity() {
        
        return myQuantity;
    }

    /**
     * Returns a String representation of this ItemOrder: You may use any format 
     * that seems reasonable to you for this String.
     * 
     * @return the item and the quantity in a formatted string.
     */
    @Override
    public String toString() {
        return "Item: " + myItem + ", Quantity: " + myQuantity;
    }

}
