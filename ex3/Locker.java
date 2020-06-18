/* Imports*/
import oop.ex3.spaceship.*;

/**
 * a class extending a Spaceshipdepository, this class represents a
 * locker which is a spaceshipDepository.
 * the locker can be added with items, remove items and has its maximum
 * capacity given by the user.
 */
public class Locker extends SpaceshipDepository {
    /* magic numbers.*/
    private static final double MAXIMAL_PERCENTAGE_FOR_ITEM_IN_LOCKER = 0.5;
    private static final double DEFAULT_FIXED_PERCENTAGE_OF_ITEM = 0.2;

    /** the max capacity of the locker.*/
    private final int MaxCapacity;

    /** the current capacity of the locker.*/
    private int CurrCapacityleft;

    /** a LongTermStorage object, that is the longtermstorage of the
     * lockers, this object is static because the class has 1 longtermstorage.
     */
    private static LongTermStorage longterm = new LongTermStorage();

    /**
     * The constractor of the class, getting its Capacity from the creator.
     * @param Capacity and int that represents the capacith the locker has.
     */
    Locker(int Capacity){
        this.MaxCapacity = Capacity;
        this.CurrCapacityleft = Capacity;
        this.intArray = new int[allLegalTypesArr.length];
    }

    /**
     * a method that is adding an item to the Locker, according to the
     * instructions, if the current item type holds more than 50 percent of the
     * total Locker capacity, the method keep 20 percent of the current
     * type of item in the locker, and puts the rest in the LongTermStorage.
     * @param item Item type item, to add to the Locker
     * @param n non-negative integer, the amount of items from the type to add.
     * @return 0 upon success adding to the locker, -1 if failed,
     *-2 if failed because adding contradicting types, 1 upon success adding,
     *but with using the longtermstorage to keep some of the given items.
     */
    public int addItem(Item item, int n){
        int itemIndex = GetItemIndex(item);
        /* n is a non-negative int.*/
        if (n<0){
            System.out.println("Error: Your request cannot be completed at this time");
            return -1;
        }
        /* checks if it's a legal item*/
        if (itemIndex == -1){
            System.out.println("Error: Your request cannot be completed at this time");
            return -1;
        }
        /* checks if can be added by contradiction. */
        if (!canAddBaseballOrFootball(item)){
            System.out.println("Error: Your request cannot be completed at this time. Problem:" +
                    " the locker cannot contain items of type "+item.getType()+", as it contains a contradicting item");
            return -2;
        }
        int StorageNeeded = n*item.getVolume();
        int itemCapacity = (intArray[itemIndex]+n)*item.getVolume();
        if (StorageNeeded> this.CurrCapacityleft){
            System.out.println("Error: Your request cannot be completed at this time. Problem: no room for " + n + " items of type " + item.getType());
            return -1;
        }
        if (itemCapacity < MAXIMAL_PERCENTAGE_FOR_ITEM_IN_LOCKER *this.MaxCapacity){
            this.CurrCapacityleft -= StorageNeeded;
            this.intArray[itemIndex] += n;
            return 0;
        }
        if (itemCapacity >= MAXIMAL_PERCENTAGE_FOR_ITEM_IN_LOCKER*this.MaxCapacity){
            return divideLockerAndLTS(item, n);
        }
        System.out.println("Error: Your request cannot be completed at this time." +
                " Problem: no room for " + n + " items of type " + item.getType());
        return -1;
    }

    /**
     * a method that is running when a certain type of item is added to the Locker,
     * but the item type is taking 50 percent or more of the max capacity.
     * this method divides the 20 percent needs to be in the locker, and adds the
     * rest to the LongTernStorage.
     * if the longtermstorage does not have room, returns -1, and adds nothing.
     * @param item Item type item, to add to the Locker
     * @param n non-negative integer, the amount of items from the type to add.
     * @return 1 upon adding to both the Locker and the LongTermStorage, -1 upon failure
     * and adding nothing.
     */
    private int divideLockerAndLTS(Item item, int n) {
        int itemIndex = GetItemIndex(item);
        int LongTermItems;
        int lockerItems;
        lockerItems = (int)(DEFAULT_FIXED_PERCENTAGE_OF_ITEM *this.MaxCapacity/(item.getVolume()))-(item.getVolume()*getItemCount(item.getType()));
        LongTermItems = (this.intArray[itemIndex]+n)-lockerItems;
        if (longterm.getAvailableCapacity()< (item.getVolume()*LongTermItems)){
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: no room for "+LongTermItems+"∗ Items of type "+item.getType());
            return -1;
        }
        if (longterm.addItem(item, LongTermItems) == -1){
            return -1;
        }
        this.intArray[itemIndex] += lockerItems;
        this.CurrCapacityleft -= lockerItems*item.getVolume();
        System.out.println("Warning: Action successful," +
                " but has caused items to be moved to storage");
        return 1;
    }

    /**
     * a method that checks if it can add the given item, or not because of contradiction.
     * @param item Item type item, to add to the Locker
     * @return Boolean expression, true if the item can be added to the locker,
     * false otherwise.
     */
    private boolean canAddBaseballOrFootball(Item item){
        if ((!item.getType().equals("football"))&&(!item.getType().equals("baseball bat"))){
            return true;
        }
        String[] typeArr = new String[allLegalTypesArr.length];
        for (int i=0; i<allLegalTypesArr.length; i++){
            if ((this.intArray[i] != 0)&& (((item.getType().equals("football"))&&
                    (allLegalTypesArr[i].getType().equals("baseball bat"))) ||
                    ((item.getType().equals("baseball bat"))&& (allLegalTypesArr[i].getType().equals("football"))))){
                return false;
            }
        }
        return true;
    }

    /**
     * a method that removes a given item from the Locker
     * @param item Item type item, to add to the Locker
     * @param n non-negative integer, the amount of items from the type to remove.
     * @return 0 upon success removing, false otherwise.
     */
    public int removeItem(Item item, int n){
        if (n<0){
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: cannot remove a negative number of items of type "+item.getType());
            return -1;
        }
        int itemIndex = GetItemIndex(item);
        if (itemIndex == -1){
            System.out.println("Error: Your request cannot be completed at this time");
            return -1;
        }
        if (this.intArray[itemIndex]< n){
            System.out.println("Error: Your request cannot be completed at this time." +
                    " Problem: the locker does not contain "+n+" items of type "+item.getType());
            return -1;
        }
        this.intArray[itemIndex] -= n;
        this.CurrCapacityleft += n*(item.getVolume());
        return 0;
    }

    /**
     * a simple method returning the Locker max capacity
     * @return and integer, the locker max capacity
     */
    public int getCapacity(){
        return this.MaxCapacity;
    }
    /**
     * a simple method returning the Locker available capacity
     * @return and integer, the locker available capacity
     */
    public int getAvailableCapacity(){
        return this.CurrCapacityleft;
    }
}
