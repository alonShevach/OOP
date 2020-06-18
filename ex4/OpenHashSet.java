/* imports*/
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * This class represents the OpenHashTable, this class extends the SimpleHashSet.
 * The class uses the CollectionFacadeSet Class, and builts to each set a LinkedList
 * Subset.
 */
public class OpenHashSet extends SimpleHashSet {

    /* an openSet, represented using the CollectionFacadeSet class.*/
    private CollectionFacadeSet[] openSet;

    /**
     * the default constructor of the class.
     * giving the class the default capacity and the default upper and lower factors.
     * and the constructor creates an array of CollectionFacadeSet type.
     */
    public OpenHashSet(){
        resetCapacity();
        resetUpperAndLower();
        openSet = new CollectionFacadeSet[INITIAL_CAPACITY];
    }

    /**
     * constructor of the class.
     *giving the class the default capacity and the default upper and lower factors from
     * the given args.
     *and the constructor creates an array of CollectionFacadeSet type.
     * @param upperLoadFactor a float represents the upperfactor of the set.
     * @param lowerLoadFactor a float represents the lowerfactor of the set.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        resetCapacity();
        upperFactor = upperLoadFactor;
        lowerFactor = lowerLoadFactor;
        openSet = new CollectionFacadeSet[INITIAL_CAPACITY];
    }

    /**
     * a constructor getting an array of strings, and creates the set, adding the given
     * strings to the set and giving the class the default capacity and the
     * default upper and lower factors.
     * @param data an array of strings to add to the set.
     */
    public OpenHashSet(java.lang.String[] data){
        resetCapacity();
        resetUpperAndLower();
        openSet = new CollectionFacadeSet[INITIAL_CAPACITY];
        for (String item:data){
            add(item);
        }
    }

    /**
     * a method adding new string to the openset, this
     * method first reHash the set, and then increasing it size by 1.
     * this method is using the method addHelper.
     * @param newValue New value to add to the set.
     * @return True upon success, false otherwise.
     */
    public boolean add(java.lang.String newValue){
        reHashing(resizeForAdd(capacity()));
        if (!addHelper(newValue)){
            return false;
        }
        hashSetSize++;
        return true;
    }

    /**
     * a method that is used to add a new value to the openSet.
     * this method finds the right index using the Clamp() method,
     * then if it is null it adds a new treeset to the set in the
     * index location, and adds the new value.
     * @param newValue New value to add to the set
     * @return True upon success adding, false otherwise.
     */
    private boolean addHelper(java.lang.String newValue){
        int index = clamp(newValue.hashCode());
        if (openSet[index] == null) {
            openSet[index] = new CollectionFacadeSet(new LinkedList<String> ());
        }
        return openSet[index].add(newValue);
    }

    /**
     * a method checks if the openSet contains a given value.
     * using the clamp() method.
     * @param searchVal Value to search for
     * @return True if the value is in the set, false otherwise.
     */
    public boolean	contains(java.lang.String searchVal){
        if (openSet[clamp(searchVal.hashCode())] == null){
            return false;
        }
        return openSet[clamp(searchVal.hashCode())].contains(searchVal);
    }

    /**
     * a method deleting a given string from the set.
     * this method removes the string then rehashes the set.
     * @param toDelete Value to delete
     * @return True upon success deleting, false otherwise.
     */
    public boolean delete(java.lang.String toDelete){
        if (!contains(toDelete)){
            return false;
        }
        openSet[clamp(toDelete.hashCode())].delete(toDelete);
        hashSetSize --;
        reHashing(resizeForDelete(capacity()));
        return true;
    }

    /**
     * this method is doing the rehashing when needed(upon deleting or adding a new
     * object to the set), this method refreshes the capacity to the new capacity,
     * and rehash the way needed, according to the new capacity given.
     * @param newCapacity the new capacity of the set.
     */
    private void reHashing(int newCapacity) {
        int oldCapacity = capacity();
        /* if the capacity did not change, or the capacity is lower than the
        minimal capacity, returns.
         */
        if ((capacity() <= MINIMAL_CAPACITY)||(oldCapacity == newCapacity)){
            return;
        }
        capacity = newCapacity;
        CollectionFacadeSet[] oldOpenSet = openSet;
        openSet = new CollectionFacadeSet[newCapacity];
        /* adding the strings to the new set.*/
        for (int i=0; i<oldCapacity; i++) {
            if (oldOpenSet[i] != null) {
                Iterator<String> iter = oldOpenSet[i].iterator();
                while (iter.hasNext()) {
                    addHelper(iter.next());
                }
            }
        }
    }
}
