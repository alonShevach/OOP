/* Imports*/

import org.junit.*;
import oop.ex3.spaceship.*;
import static oop.ex3.spaceship.ItemFactory.createSingleItem;
import static org.junit.Assert.*;

/**
 * A class that tests the LongTermStorage Class.
 */
public class LongTermTest {

    /** an array of the legal items for the locker.*/
    private static Item[] allLegalTypesArr = ItemFactory.createAllLegalItems();

    /**
     * the Tests for the Class.
     */
    @Test
    public void mainTests(){
        LongTermStorage longTermStor = new LongTermStorage();
        Item firstitem = createSingleItem(allLegalTypesArr[2].getType());
        Item seconditem = createSingleItem(allLegalTypesArr[3].getType());
        Item thirditem = createSingleItem(allLegalTypesArr[4].getType());
        /* testing the basics, adding items.*/
        assertEquals("test 1 failed, failed to add item", 0, longTermStor.addItem(firstitem, 200));
        /*the first item has 5 volume, so the LTS should not have room now*/
        assertEquals("test 2 failed, added item while LTS is full"
                , -1, longTermStor.addItem(seconditem, 1));
        assertEquals("test 2 failed, added item while LTS is full", -1, longTermStor.addItem(thirditem, 1));
        /*Testing the reset inventory*/
        longTermStor.resetInventory();
        assertEquals("test 4 failed, getAvailableCapacity() failed."
                , 1000, longTermStor.getAvailableCapacity());
        assertEquals("test 5 failed", 0, longTermStor.addItem(seconditem, 1));
        assertEquals("test 6 failed, getAvailableCapacity() failed."
                , 990, longTermStor.getAvailableCapacity());
        /* adding a item with n=0 should not change the capacity, but should succeed adding.*/
        assertEquals("test 7 failed, did not add 0 items."
                ,0,longTermStor.addItem(seconditem, 0));
        assertEquals("test 8 failed, changed the capacity although adding 0 items."
                , 990,longTermStor.getAvailableCapacity());
        assertEquals("test 9 failed, .getCapacity() failed.", 1000, longTermStor.getCapacity());

    }
}