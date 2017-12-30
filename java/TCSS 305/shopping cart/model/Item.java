/*
 * TCSS 305 Assignment 2 - Shopping cart
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * This program creates an item. 
 * 
 * @author Josh Atherton
 * @version 1
 */
public final class Item {
    
    /** To format BigDecimal values to us currency value for display. */
    private final NumberFormat myMoneyFormater = NumberFormat.getCurrencyInstance(Locale.US);
    
    /** Field to hold the name of the item. */
    private final String myItemName;
    
    /** Field to hold the price of the item. */
    private final BigDecimal myItemPrice;
    
    /** Field to hold the number of items to be bulk. */
    private final int myBulkQuantity;
    
    /** Field to hold the bulk price. */
    private final BigDecimal myBulkPrice;
     
    /**
     * Constructor that takes a name and a price as arguments. The name is a String and the 
     * price is a BigDecimal.
     * 
     * @param theName is the name of the item
     * @param thePrice is the price of this item
     */
    public Item(final String theName, final BigDecimal thePrice) {
        myItemName = theName;
        myItemPrice = thePrice;
        myBulkQuantity = 0;
        myBulkPrice = null;
    }

    /**
     * Constructor that takes a name, a single-item price, a bulk quantity, and a bulk price 
     * as arguments. The name is a String, the quantity is an int and the prices are 
     * BigDecimal s.
     * 
     * @param theName is the name of the item
     * @param thePrice is the price of the item.
     * @param theBulkQuantity the quantity to be considered bulk.
     * @param theBulkPrice the price for bulk amounts.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity, 
                final BigDecimal theBulkPrice) {
        myItemName = theName;
        myItemPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
    }

    /**
     * Getter method to return the name for this Item. 
     * 
     * @return the name of the item.
     */
    public String getName() {
        return myItemName;
    }
    
    /**
     * Returns the single item price for this Item.
     *  
     * @return the price of the item.
     */
    public BigDecimal getPrice() {    
        return myItemPrice;
    }
    
    /**
     * Returns the bulk quantity for this Item.
     * 
     * @return the number of item for bulk pricing.
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }
    
    /**
     * Returns the bulk price for this Item.
     * 
     * @return the bulk price for the item.
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Returns True if the Item has bulk pricing; false otherwise.
     * 
     * @return if the item has a bulk price or not.
     */
    public boolean isBulk() {
        boolean bulkItem = false;
        if (myBulkPrice != null) {
            bulkItem = true;
        }
        return bulkItem;
    }
    
    /**
     * Returns a String representation of this Item: name, followed by a comma and a space, 
     * followed by price. If the item has a bulk price, you should append an extra space and 
     * a parenthesized description of the bulk pricing that has the bulk quantity, the word 
     * “for” and the bulk price. 
     * 
     * @return a formated string. 
     */
    @Override
    public String toString() { // ex) X, $19.99 (5 for $89.99)
        final StringBuilder builder = new StringBuilder(128); 
        builder.append(myItemName);
        builder.append(", ");
        builder.append(myMoneyFormater.format(myItemPrice)); // use money formatter
        if (isBulk()) {
            builder.append(" (");
            builder.append(myBulkQuantity);
            builder.append(" for ");
            builder.append(myMoneyFormater.format(myBulkPrice));
            builder.append(')');
        }
        
        return builder.toString();
    }

    /** 
     * Returns true if the specified object is equivalent to this Item, and false otherwise. 
     * Two items are equivalent if they have exactly equivalent names, prices, bulk quantities
     * and bulk prices. This method must properly override java.lang.Object.equals().
     * 
     * @return if objects are equivalent or not using true or false.
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean returnValue = false;
        
        // have the same memory location
        if (this == theOther) { 
            returnValue = true;
            
        // object is not null && are from the same class
        } else if (theOther != null && this.getClass() == theOther.getClass()) {
            //cast the other item down to an item
            final Item otherItem = (Item) theOther;
            
                          // check that they have the same name
            returnValue = Objects.equals(myItemName, otherItem.myItemName)
                          
                          // check that they have the same price
                          && Objects.equals(myItemPrice, otherItem.myItemPrice)
                          
                          // check that bulk quantity is the same
                          //or Integer.compare(myBulkQuantity, otherItem.myBulkQuantity) == 0
                          && myBulkQuantity == otherItem.myBulkQuantity        
                          
                          // check that the bulk prices are the same
                          && Objects.equals(myBulkPrice, otherItem.myBulkPrice); 
        }
        return returnValue;      
    }

    /**
     * Returns an integer hash code for this item. This method must override 
     * java.lang.Object.hashCode() and be consistent with equals().
     * 
     * @return number representation of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myItemName, myItemPrice, myBulkQuantity, myBulkPrice);
    }

}
