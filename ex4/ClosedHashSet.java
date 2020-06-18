/**
 * this class represents a ClosedHashset.
 * this class extends the SimpleHashSet class,
 * using a inner class called stringObjWrapper,
 * the class has 3 constructors and the class extends SimpleHashSet.
 */
public class ClosedHashSet extends  SimpleHashSet {
    /* Magic Number*/
    public static final int INVALID_INDEX = -1;

    /**
     * an inner class that is a wrapper for string objects.
     * this class is used for the ClosedHashSet class
     */
    private class StringObjWrapper{
        /* String representing the value*/
        private String value;

        /**
         * a getter for the Value of the StringObjWrapper type.
         * @return the value of the object.
         */
        private String getValue(){
            return value;
        }

        /**
         * Setter for the value of the StringObjWrapper type.
         * @param newVal the new value to set the current value to.
         */
        private void setValue(String newVal){
            value = newVal;
        }

        /**
         * a method that deletes the value of the given StringWrapper,
         * replacing it with null.
         */
        private void deleteValue(){
            value = null;
        }

        /**
         * a boolean that checks if the value of the object is null,
         * of if it is not.
         * @return True if the value is null, false otherwise.
         */
        private Boolean isDeleted(){
            return value==null;
        }
    }
    /* an array of StringObjWrapper, will be the Wrapper of the class.*/
    private StringObjWrapper[] closedSet;

    /**
     * the default constructor of the class.
     * giving the class the default capacity and the default upper and lower factors.
     * and the constructor creates an array of StringObjWrapper type.
     */
    public ClosedHashSet(){
        resetCapacity();
        resetUpperAndLower();
        closedSet = new StringObjWrapper[INITIAL_CAPACITY];
    }

    /**
     * a constructor getting an array of strings, and creates the set, adding the given
     * strings to the set and giving the class the default capacity and the
     * default upper and lower factors.
     * @param data an array of strings to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data){
        resetCapacity();
        resetUpperAndLower();
        closedSet = new StringObjWrapper[INITIAL_CAPACITY];
        for (String item:data){
            add(item);
        }
    }

    /**
     * constructor of the class.
     *giving the class the default capacity and the default upper and lower factors from
     * the given args.
     *and the constructor creates an array of CollectionFacadeSet type.
     * @param upperLoadFactor a float represents the upperfactor of the set.
     * @param lowerLoadFactor a float represents the lowerfactor of the set.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        resetCapacity();
        upperFactor = upperLoadFactor;
        lowerFactor = lowerLoadFactor;
        closedSet = new StringObjWrapper[INITIAL_CAPACITY];
    }

    /**
     * this method adds a given string to the ClosedHashset,
     * this method first rehash and increase the size and then adds the string.
     * @param newValue New value to add to the set
     * @return True upon success, false otherwise.
     */
    public boolean add(java.lang.String newValue){
        if (contains(newValue)){
            return false;
        }
        reHashing(resizeForAdd(capacity()));
        hashSetSize++;
        int index = findValueIndex(newValue, true);
        closedSet[index] = new StringObjWrapper();
        closedSet[index].setValue(newValue);
        return true;
    }

    /**
     * this method checks of a given Value is in the ClosedHashset.
     * this method uses the findValueIndex method
     * @param searchVal Value to search for
     * @return True if contains the given value, false otherwise.
     */
    public boolean contains(java.lang.String searchVal){
        return findValueIndex(searchVal, false)>=0;
    }

    /**
     * this method deletes a given string from the ClosedHashset.
     * this method first deletes the string and then rehash and change
     * the set's size.
     * @param toDelete Value to delete
     * @return True upon success, false otherwise.
     */
    public boolean delete(java.lang.String toDelete){
        int index = findValueIndex(toDelete,false);
        if (index < 0) {
            return false;
        }
        closedSet[index].deleteValue();
        reHashing(resizeForDelete(capacity()));
        hashSetSize --;
        return true;
    }

    /**
     * this method main idea is to find the index of the value we want
     * to delete or we want to add, if we want to add a string the
     * toAdd should be true, if we want to delete we will use false
     * in the toAdd, this uses the clamp method.
     * if the value is not in the given index, it will return -1 if we delete,
     * otherwise it will return the index if needed to add, and if the string is
     * in the index.
     * @param value a value to find his index, for add it will find the place to add
     *              the value to, for delete it will find the index to delete.
     * @param toAdd boolean, true if we want to add a string, false if we want to
     *              delete a string.
     * @return -1 if we want to delete and the value is not in the given index,
     * an index if the value is there and we want to delete, and also the index if
     * we want to add.
     */
    private int findValueIndex(java.lang.String value, Boolean toAdd){
        int index;
        for (int i=0; i<capacity();i++){
            index = clamp(value.hashCode()+((i+i*i)/2));
            if (closedSet[index] == null){
                return toAdd ? index:INVALID_INDEX;
            }
            if ((((toAdd) && (closedSet[index].getValue() == null)))||
                    (!(closedSet[index].getValue() == null)&&(!toAdd) &&
                            (closedSet[index].getValue().equals(value)))){
                return index;
            }
        }
        return INVALID_INDEX;
    }

    /**
     * this method is doing the rehashing when needed(upon deleting or adding a new
     * object to the set), this method refreshes the capacity to the new capacity,
     * and rehash the way needed, according to the new capacity given.
     * @param newCapacity the new capacity of the set.
     */
    private void reHashing(int newCapacity){
        int oldCapacity = capacity();
        /* if the capacity did not change, or the oldcapacity is lower than the
        minimal capacity, returns.
         */
        if ((oldCapacity<= MINIMAL_CAPACITY)||(capacity() == newCapacity)){
            return;
        }
        capacity = newCapacity;
        StringObjWrapper[] oldHashSet = closedSet;
        closedSet = new StringObjWrapper[newCapacity];
        for (int i=0; i<oldCapacity;i++){
            if (oldHashSet[i] != null && !oldHashSet[i].isDeleted()){
                closedSet[findValueIndex(oldHashSet[i].getValue(), true)] = oldHashSet[i];
            }
        }
    }
}
