/* Imports*/
import oop.ex3.spaceship.*;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class that represents a SpaceshipDepository.
 */
public abstract class SpaceshipDepository {
    /* protected variables for the different extending classes.*/
    /** the array representing all the legaltypes from the ex3.resources.*/
    protected static Item[] allLegalTypesArr = ItemFactory.createAllLegalItems();

    /** the intArray, represents the object inside each spaceshipDepository.*/
    protected int[] intArray;

    /**
     * the abstract method addItem, which must be implemented in order
     * to be a depository, this method adds a certain item to the depository.
     * @param item Item type item, to add to the depository
     * @param n non-negative integer, the amount of items from the type to add.
     * @return Locker: 0 upon success adding to the locker, -1 if failed,
     * -2 if failed because adding contradicting types, 1 upon success adding,
     * but with using the longtermstorage to keep some of the given items.
     * LongTermStorage: 0 upon success, -1 otherwise.
     */
    public abstract int addItem(Item item, int n);

    /**
     * a method returning the item index according to the allLegalTypesArr Array.
     * @param item Item type item, to find his index
     * @return the item's index in the allLegalTypesArr Array.
     */
    protected int GetItemIndex(Item item){
        for (int i=0; i<allLegalTypesArr.length; i++){
            if (allLegalTypesArr[i].getType().equals(item.getType())){
                return i;
            }
        }
        return -1;
    }

    /**
     * a method returning a Map type inventory representing the items the
     * depository has inside, the Map is <String = item type, Integer = amount>
     * @return a Map type inventory according the items in the depository.
     */
    public Map<String, Integer> getInventory(){
        Map<String, Integer> inventory = new HashMap<String, Integer>();
        for (int i=0; i<allLegalTypesArr.length;i++){
            if (this.intArray[i] == 0){
                continue;
            }
            inventory.put(allLegalTypesArr[i].getType(), intArray[i]);
        }
        return inventory;
    }

    /**
     * a method that counts the amount of items from the type given
     * @param type a String, an item type.
     * @return the amount of times the item type is in the depository.
     */
    public int getItemCount(String type){
        int typeIndex = -1;
        for (int i=0; i<allLegalTypesArr.length; i++){
            if (allLegalTypesArr[i].getType().equals(type)){
                typeIndex = i;
            }
        }
        if (typeIndex == -1){
            return -1;
        }
        return this.intArray[typeIndex];
    }


}

