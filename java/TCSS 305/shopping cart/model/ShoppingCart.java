/*
 * TCSS 305 Assignment 2 - Shopping cart
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
/**
 * This program collects items in a shopping cart. It then calculates the total.
 * 
 * @author Josh Atherton
 * @version 1
 */ 
public class ShoppingCart {

    /** To hold the Items being purchased. */
    private final Map<Item, Integer> myShoppingCart; 
    
    /** To hold the Items being purchased. */
    private boolean myMembership; 
    
    /**
     * Constructor that creates an empty shopping cart.
     */
    public ShoppingCart() {
        
        myShoppingCart = new HashMap<Item, Integer>();
    }

    /**
     * Adds an order to the shopping cart, replacing any previous order for an equivalent 
     * item with the new order. The parameter is an ItemOrder. (equals() would return true 
     * if used to compare equivalent items) The return type of this method is void.
     * 
     * @param theOrder what is being ordered (the item and quantity).
     */
    public void add(final ItemOrder theOrder) { 
                                                 
        myShoppingCart.put(theOrder.getItem(), theOrder.getQuantity()); 
    }

    /**
     * Sets whether or not the customer for this shopping cart has a store membership (the 
     * parameter is a boolean; true means the customer has a membership, false means the 
     * customer doesn’t). The return is void.
     * 
     * @param theMembership if the customer has a membership or not.
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    /**
     * Returns the total cost of this shopping cart as a BigDecimal. This returned BigDecimal 
     * should have scale of 2 and use the ROUND_HALF_EVEN rounding rule.
     * 
     * @return the total purchase amount for the shopping cart.
     */
    public BigDecimal calculateTotal() {
        //reset shopping cart to zero when recalculating the total
        BigDecimal shoppingCartTotal;
        shoppingCartTotal = BigDecimal.ZERO;
        
        for (final Item key : myShoppingCart.keySet()) { // for each item in cart
            
            // if item is bulk and Member true
            if (key.isBulk() && myMembership) { 
                
                // see if purchasing enough items to be considered bulk (quant - bulkQuant)
                if (myShoppingCart.get(key) - key.getBulkQuantity() >= 0) { 
                    
                    // remainder after bulk purchase (quant % bQuant)
                    final BigDecimal remainder = new BigDecimal(
                                           myShoppingCart.get(key) % key.getBulkQuantity());
                    
                    // # of times can apply bulk amount purchase(quant / bQuant)
                    final BigDecimal bulkAmount = new BigDecimal(
                                           myShoppingCart.get(key) / key.getBulkQuantity());
                    
                    //get the total price for the item
                    // (item price * single items) + (bulk price * number or bulk purchases)
                    shoppingCartTotal = shoppingCartTotal.add(
                                (key.getPrice().multiply(remainder)).add(
                                key.getBulkPrice().multiply(bulkAmount))); 
                    //make sure the order of operations is correct here in execution
                }
                
            } else { // not a member, member false
                
                // the amount purchased times the price (quant * itemPrice = total)
                shoppingCartTotal = shoppingCartTotal.add(new BigDecimal(
                                  myShoppingCart.get(key)).multiply(key.getPrice())); 
            }
        }
        // This return should have scale of 2 and use the ROUND_HALF_EVEN rounding rule.
        return shoppingCartTotal.setScale(2, RoundingMode.HALF_EVEN);  
    }
    
    /**
     * Removes all orders from the cart. The return is void.
     */
    public void clear() {
        myShoppingCart.clear(); 
    }

    /**
     * Returns a String representation of this ShoppingCart: You may use any 
     * format that seems reasonable to you for this String.
     * 
     * @return a formatted string of the shopping cart.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        for (final Item key : myShoppingCart.keySet()) {
            builder.append("Buy: ");
            builder.append(myShoppingCart.get(key));
            builder.append(' ');
            builder.append(key.getName()); 
        }
        return builder.toString();
    }
        
}
