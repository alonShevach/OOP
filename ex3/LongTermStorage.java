/* Imports*/
import oop.ex3.spaceship.*;

/**
 * a class represents the LongTermStorage of the ship,
 * this class is extending the SpaceshipDepository.
 * The locker uses this class when a single item takes more than 50
 * percent of its capacity.
 * this class can be added items, reset, and has a default max capacity.
 */
public class LongTermStorage extends SpaceshipDepository {

    /*magic number*/
    private static final int DEFAULT_MAX_CAPACITY = 1000;

    /** the default maximal longTermStorage capacity.*/
    private final int MaxLongTermStorageCapacity = DEFAULT_MAX_CAPACITY;

    /** the int represents the current capacity of this longtermstorage.*/
    private static int longTermCurrCapacity;


    /**
     * the constructor of the class. creating the longtermstorage,
     * with the default max capacity and creating a current capacity of the
     * maximum
     */
    LongTermStorage(){
        resetInventory();
    }

    /**
     * this method adds a given item to the LongTermStorage, and adding it n times,
     * if it cannot add this to the LongTermStorage it return -1 and does not add.
     * @param item Item type item, to add to the depository
     * @param n non-negative integer, the amount of items from the type to add.
     * @return 0 upon success, -1 otherwise.
     */
    public int addItem(Item item, int n){
        if (n<0){
            System.out.println("Error: Your request cannot be completed at this time");
            return -1;
        }
        if (longTermCurrCapacity < (n*item.getVolume())){
            System.out.println("Error: Your request cannot be completed at this time. Problem: no room for " + n + " items of type " + item + ".");
            return -1;
        }
        int itemIndex = GetItemIndex(item);
        if (itemIndex == -1){
            System.out.println("Error: Your request cannot be completed at this time");
            return -1;
        }
        longTermCurrCapacity -= n*item.getVolume();
        this.intArray[itemIndex] += n;
        return 0;
    }

    /**
     * the method resetting the inventory, making the current capacity the max
     * capacity, and empty the longtermstorage
     */
    public void resetInventory(){
        longTermCurrCapacity = MaxLongTermStorageCapacity;
        this.intArray = new int[allLegalTypesArr.length];
    }

    /**
     * a simple method returning the LongStorage max capacity
     * @return and integer, the LongStorage max capacity
     */
    public int getCapacity(){
        return MaxLongTermStorageCapacity;
    }

    /**
     * a simple method returning the LongStorage available capacity
     * @return and integer, the LongStorage available capacity
     */
    public int getAvailableCapacity(){
        return longTermCurrCapacity;
    }

}
