/* Imports*/
import org.junit.*;
import oop.ex3.spaceship.*;
import static oop.ex3.spaceship.ItemFactory.createSingleItem;
import static org.junit.Assert.*;

/**
 * A class testing the SpaceshipDepository, Testing locker and LTS combined.
 */
public class SpaceshipDepositoryTest {

    /** constants for the Tests.*/
    private static Item[] allLegalTypesArr = ItemFactory.createAllLegalItems();
    private Locker lock4test;
    private static Item firstItem = createSingleItem(allLegalTypesArr[0].getType());
    private static Item secondItem = createSingleItem(allLegalTypesArr[1].getType());
    private static Item thirdItem = createSingleItem(allLegalTypesArr[4].getType());
    private LongTermStorage longTermStor;

    /**
     * things to execute before each test runs.
     */

    @Before
    public void Beforetest(){
        lock4test = new Locker(800);
        longTermStor = new LongTermStorage();
    }

    /**
     * Running the tests for Locker
     */
    @Test
    public void LockerTests(){
        System.out.println("##Locker Test Prints##");
        LockerTest TestLocker = new LockerTest();
        TestLocker.Test1();
        TestLocker.Test2();
        TestLocker.Test3();
    }

    /**
     * running the LTS Tests.
     */
    @Test
    public void LTSTests(){
        System.out.println("##LTS Test Prints##");
        LongTermTest TestLTS = new LongTermTest();
        TestLTS.mainTests();
    }

    /**
     * test for the combinations of Locker and LTS.
     */
    @Test
    public void CombinationTests1(){
        System.out.println("## CombinationTests1 Prints ##");
        assertEquals("test 1 failed, failed to add item, should add to the Locker and Lts"
                , 1, lock4test.addItem(firstItem, 200));
        assertEquals("test 2 failed, failed to add item, should add to the Locker and Lts", 1, lock4test.addItem(secondItem, 200));
        assertEquals("test 3 failed, LTS capacity did not changed, although adding to the LTS"
                , 319, longTermStor.getAvailableCapacity());
        lock4test.removeItem(secondItem, 50);
        /* checking if having room in the locker for 20 percent, but not enough room in LTS*/
        assertEquals("test 4 failed, should not add, there is no room in the LTS."
                , -1, lock4test.addItem(secondItem, 200));
        assertEquals("test 5 failed, should not add, there is no room in the LTS."
                , -1, longTermStor.addItem(secondItem, 200));
        longTermStor.resetInventory();
        assertEquals("test 6 failed, should add, the LTS is reset."
                , 1, lock4test.addItem(secondItem, 200));
    }

    /**
     * tests for the combinations of Locker and LTS.
     */
    @Test
    public void CombinationTests2(){
        System.out.println("## CombinationTests2 Prints ##");
        lock4test.addItem(firstItem, 1);
        assertEquals("test 7 failed, added the item while having contradicting item inside the locker",
                -2, lock4test.addItem(thirdItem, 1));
        lock4test.removeItem(firstItem,1);
        /* checking the other way, adding the baseball while having football inside.*/
        assertEquals("test 8 failed, did not add the item after contradicting item removed."
                , 0, lock4test.addItem(thirdItem, 1));
        assertEquals("test 9 failed, added the item while having contradicting item inside the locker"
                , -2, lock4test.addItem(firstItem, 1));
        assertEquals("test 10 failed, LTS capacity should not change"
                , 1000, longTermStor.getAvailableCapacity());

    }
}