/*
 * TCSS 305 Assignment 2 - Shopping cart
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.Before;
import org.junit.Test;


/**
 * This program tests the Item class to check that all methods are working properly 
 * without errors.
 * 
 * @author Josh Atherton
 * @version 1
 */
public class ItemTest {
    
    /** Setup an Item. */
    private Item myItem;
    
    /** Setup an Item. */
    private Item myItemPriceDifferent;
    
    /** Setup a bulk Item. */
    private Item myBulkItem;
    
    /** Setup a bulk item with a different bulk price. */
    private Item myDiffBulkItem;
    
    /** Setup a bulk item with a different bulk price. */
    private Item myBulkItemDiffPrice;
        
    /** Field to hold the name of the item. */
    private String myItemName;
    
    /** Field to hold the name of the item a second Item. */
    private String mySecondItemName;
    
    /** Field to hold the price of the item. */
    private BigDecimal myItemPrice;
    
    /** Field to hold the number of items to be bulk. */
    private int myBulkQuantity;
    
    /** Field to hold the number of items to be bulk. */
    private int mySecondBulkQuantity;
    
    /** Field to hold the bulk price. */
    private BigDecimal myBulkPrice;
    
    /** Field to hold the second bulk price. */
    private BigDecimal mySecondBulkPrice;

    /**
     * 
     */
    @Before
    public void setUp() {
        myItemName = "Big Bananas";
        mySecondItemName = "Squirrels";
        myItemPrice = new BigDecimal("5.4555555");
        myBulkQuantity = 10;
        mySecondBulkQuantity = 12;
        myBulkPrice = new BigDecimal("5.0");
        mySecondBulkPrice = new BigDecimal("4.0");
        
        myItem = new Item(myItemName, myItemPrice);
        myItemPriceDifferent = new Item(myItemName, myBulkPrice);
        myBulkItem = new Item(myItemName, myItemPrice, myBulkQuantity, myBulkPrice);
        myDiffBulkItem = new Item(myItemName, myItemPrice, myBulkQuantity, mySecondBulkPrice);
        myBulkItemDiffPrice = new Item(
                            mySecondItemName, myBulkPrice, mySecondBulkQuantity, myItemPrice);
    }
    
    /**
     * Test method for getter to see if correct name is retrieved.
     */
    @Test
    public void testGetName() {
        assertEquals(myItemName, myItem.getName());
    }

    /**
     * Test method to see correct price is returned.
     */
    @Test
    public void testGetPrice() {
        assertEquals(myItemPrice, myItem.getPrice());
    }

    /**
     * Test method to see that the correct bulk quantity is returned.
     */
    @Test
    public void testGetBulkQuantity() {
        assertEquals(myBulkQuantity, myBulkItem.getBulkQuantity());
    }

    /**
     * Test method to see if returning the correct bulk price.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals(myBulkPrice, myBulkItem.getBulkPrice());
    }

    /**
     * Test method to see if returning true or false.
     */
    @Test
    public void testIsBulk() {
        assertEquals(true, myBulkItem.isBulk());
    }

    /**
     * Test method to see if toString displays correctly.
     */
    @Test
    public void testToString() {
        assertEquals("Big Bananas, $5.46", myItem.toString());
    }
    
    /**
     * Test method to see if bulk toString displays correctly.
     */
    @Test
    public void testToStringBulk() {
        assertEquals("Big Bananas, $5.46 (10 for $5.00)", myBulkItem.toString());
    }

    /**
     * Test method to compare the same object.
     */
    @Test
    public void testEqualsSameObject() {
        assertEquals(true, myItem.equals(myItem));
    }
    
    /**
     * Test method to compare non same objects.
     */
    @Test
    public void testEqualsNotSameObject() {
        assertEquals(false, myBulkItem.equals(myItem));
    }
    
    /**
     * Test method to compare items that are not of the same class.
     */
    @Test
    public void testEqualsDifferentClass() {
        assertFalse(myItem.equals(myItemName));
    }
    
    /**
     * Test method to compare null Item passed in.  
     */
    @Test 
    public void testEqualsNull() {
        assertFalse(myItem.equals(null));
    }
    
    /**
     * Test method for same bulk Items compared.    
     */
    @Test
    public void testEqualsSameBulkItems() {
        final Item bulkItemCopy = new Item(
                                      myItemName, myItemPrice, myBulkQuantity, myBulkPrice);
        assertEquals(true, myBulkItem.equals(bulkItemCopy));
    }
    
    /**
    * Test method for different bulk quantity.   //this test already covered in another method
    */
    @Test
    public void testEqualsDifferentBulkQuantity() {
        assertEquals(false, myBulkItem.equals(myBulkItemDiffPrice));
    }
    
    /**
     * Test method to check for Items that have a different price but are otherwise the same. 
     */
    @Test
    public void testEqualsDifferentPrice() {
        assertEquals(false, myBulkItem.equals(myItemPriceDifferent));
    }
    
    /**
     * Test method to check for Items with a different name.     
     */
    @Test
    public void testEqualsDifferentName() {
        assertEquals(false, myBulkItemDiffPrice.equals(myBulkItem));
    }
        
    /**
     * Test method for same Item values other than the bulk price.
     */
    @Test
    public void testEqualsDifferentBulkItemPrice() {
        assertEquals(false, myBulkItem.equals(myDiffBulkItem));
    }
    
    /**
     * Test method to see if hashCode is pointing to same reference for objects.
     */
    @Test
    public void testHashCode() {
        assertEquals(myItem.hashCode(), myItem.hashCode());
    }

}