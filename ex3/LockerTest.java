/* Imports*/
import org.junit.*;
import oop.ex3.spaceship.*;
import java.util.HashMap;
import java.util.Map;
import static oop.ex3.spaceship.ItemFactory.createSingleItem;
import static org.junit.Assert.*;

/**
 * a class to Test the Locker class.
 */
public class LockerTest {

    /** constant for the tests.*/
    private static Item[] allLegalTypesArr = ItemFactory.createAllLegalItems();
    private Locker lock4test;
    private static Item firstItem = createSingleItem(allLegalTypesArr[0].getType());
    private static Item secondItem = createSingleItem(allLegalTypesArr[1].getType());
    private static Item thirdItem = createSingleItem(allLegalTypesArr[2].getType());

    /**
     * the first test of the class
     */
    @Test
    public void Test1() {
        lock4test = new Locker(1000);
        LongTermStorage LTS = new LongTermStorage();
        /* checking the basics, adding and removing*/
        assertEquals("test 1 failed, adding an item failed.", 0, lock4test.addItem(firstItem, 1));
        /* testing a removal of negative number.*/
        assertEquals("test 2 failed, can not removed a negative number",
                -1, lock4test.removeItem(firstItem, -1));
        assertEquals("test 3 failed,removing an item failed.", 0, lock4test.removeItem(firstItem, 1));
        /* trying to remove item that is not in the locker.*/
        assertEquals("test 4 failed, removed item while item is not in locker.",
                -1, lock4test.removeItem(firstItem, 1));
        /* adding something that the locker and LTS dont have room for*/
        assertEquals("test 5 failed, adding items that are too big for the locker.",
                -1, lock4test.addItem(firstItem, 4000));
        assertEquals("test 6 failed,adding an item failed.", 0, lock4test.addItem(firstItem, 1));
        assertEquals("test 7 failed,getCapacity()test failed, bad max capacity.",
                1000, lock4test.getCapacity());
        assertEquals("test 8 failed,adding a different item failed.",
                0, lock4test.addItem(secondItem, 10));
        assertEquals("test 9 failed,getItemCount(),test failed, has only 1 item of this type in locker",
                1, lock4test.getItemCount(firstItem.getType()));
        assertEquals("test 10 failed,getAvailableCapacity() test failed, should have 968 capacity."
                , 968, lock4test.getAvailableCapacity());
        assertEquals("test 11 failed,getItemCount() test failed, should have 10 items.", 10, lock4test.getItemCount(secondItem.getType()));
    }

    /**
     * the second test of the class
     */
    @Test
    public void Test2() {
        lock4test = new Locker(1000);
        LongTermStorage LTS = new LongTermStorage();
        lock4test.addItem(secondItem, 170);
        /*testing the division between LTS and Locker.*/
        assertEquals("test 12 failed, should have divided between locker and LTS",
                66, lock4test.getItemCount(secondItem.getType()));
        assertEquals("test 13 failed, should have divided between locker and LTS"
                , 802, lock4test.getAvailableCapacity());
        lock4test.addItem(secondItem, 170);
        assertEquals("test 14 failed, should have divided between locker and LTS",
                66, lock4test.getItemCount(secondItem.getType()));
        assertEquals("test 15 failed, should have divided between locker and LTS",
                802, lock4test.getAvailableCapacity());
        lock4test.addItem(firstItem, 250);
        assertEquals("test 16 failed, should have divided between locker and LTS",
                100, lock4test.getItemCount(firstItem.getType()));
        assertEquals("test 17 failed, should have divided between locker and LTS",
                602, lock4test.getAvailableCapacity());
        lock4test.addItem(thirdItem, 100);
        assertEquals("test 18 failed, should have divided between locker and LTS",
                40, lock4test.getItemCount(thirdItem.getType()));
        /*testing trying to add item, while the LTS and the locker has capacity for it combined,
        but the Locker does not have, should fail.
         */
        assertEquals("test 19 failed, cant add, not enough capacity.", -1, lock4test.addItem(firstItem, 202));
    }

    /**
     * the third test of the class
     */
    @Test
     public void Test3(){
        lock4test = new Locker(1000);
        LongTermStorage LTS = new LongTermStorage();
        /* testing the .getInventory()*/
        lock4test.addItem(secondItem, 170);
        lock4test.addItem(firstItem, 250);
        lock4test.addItem(thirdItem, 100);
        Map<String, Integer> compareMap = new HashMap<String, Integer>();
        compareMap.put("helmet, size 1",66);
        compareMap.put("baseball bat",100);
        compareMap.put("helmet, size 3",40);
        assertEquals("test 20 failed, getInventory() failed, returned bad map.", compareMap, lock4test.getInventory());
        /* Testing contradicting items, Baseball bat and Football.*/
        Item fourthitem = createSingleItem(allLegalTypesArr[4].getType());
        lock4test = new Locker(1000);
        lock4test.addItem(firstItem, 1);
        assertEquals("test 21 failed, added the item while having contradicting item inside the locker",
                -2, lock4test.addItem(fourthitem, 1));
        lock4test.removeItem(firstItem,1);
        /* checking the other way, adding the baseball while having football inside.*/
        assertEquals("test 22 failed, did not add the item after contradicting item removed."
                , 0, lock4test.addItem(fourthitem, 1));
        assertEquals("test 23 failed, added the item while having contradicting item inside the locker"
                , -2, lock4test.addItem(firstItem, 1));

        /* should add the item successfully, although it is adding 0 items.*/
        assertEquals("test 24 failed",0,lock4test.addItem(secondItem, 0));
        assertEquals("test 25 failed, the capacity should not change"
                , 996, lock4test.getAvailableCapacity());


    }
}

