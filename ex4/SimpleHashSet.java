/**
 * This class is the abstract class uses to represent a simpleHashSet,
 * this class implements the simpleset class.
 * this class keeps the protected methods for all its extends.
 */
public abstract class SimpleHashSet extends java.lang.Object implements SimpleSet {

    /*magic numbers*/
    protected static final float DEFAULT_HIGHER_FACTOR =0.75f;
    protected static final float DEFAULT_LOWER_FACTOR = 0.25f;
    protected static final int INITIAL_CAPACITY= 16;
    protected static final int MINIMAL_CAPACITY = 1;
    protected static final int RESIZE_UP_DEFAULT_NUM = 2;
    protected static final float RESIZE_DOWN_DEFAULT_NUM = 0.5f;

    /* the upper factor for the set*/
    protected float upperFactor;
    /* the lower factor for the set*/
    protected float lowerFactor;
    /* the capacity of the set*/
    protected int capacity;
    /*the size of the Hashset*/
    protected int hashSetSize;

    /**
     * the default constructor of the class.
     * giving the class the default capacity and the default upper and lower factors
     */
    protected SimpleHashSet(){
        resetCapacity();
        resetUpperAndLower();
    }

    /**
     * constructor of the class.
     * giving the class the default capacity and the default upper and lower factors from
     * the given args.
     * @param upperLoadFactor a float represents the upperfactor of the set.
     * @param lowerLoadFactor a float represents the lowerfactor of the set.
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        resetCapacity();
        upperFactor = upperLoadFactor;
        lowerFactor = lowerLoadFactor;
    }

    /**
     * a method resets the capacity to the Default initial capacity
     */
    protected void resetCapacity(){
        capacity = INITIAL_CAPACITY;
    }

    /**
     * a method resets the factors to the Default higher and lower factors.
     */
    protected void resetUpperAndLower(){
        upperFactor = DEFAULT_HIGHER_FACTOR;
        lowerFactor = DEFAULT_LOWER_FACTOR;
    }

    /**
     * a method returns the capacity of the set.
     * @return the capacity of the set.
     */
    public int capacity(){return capacity;}

    /**
     * a method returns the size of the hashset.
     * @return the size of the hashset.
     */
    public int size(){return hashSetSize;}

    /**
     * a method that returns the right index to place a given value by its
     * hashcode according to the instructions.
     * @param index an int that is the calculation to the right index.
     * @return the int represents the right index for the string.
     */
    protected int clamp(int index){
        return index&(capacity-1);
    }

    /**
     * a getter returning the lowerFactor of the hashset.
     * @return the lowerFactor of the hashset
     */
    protected float getLowerLoadFactor(){
        return lowerFactor;
    }
    /**
     * a getter returning the upperFactor of the hashset.
     * @return the upperFactor of the hashset
     */
    protected float getUpperLoadFactor(){
        return upperFactor;
    }

    /**
     * this method resizes the set upon adding a string.
     * this method checks if the capacity is beyond the upper factor,
     * if so if resizes, if not it returns the given capacity
     * @param hashsetCapacity the current capacity of the set.
     * @return the given capacity, or if beyond the upper factor
     *         returns the new capacity.
     */
    protected int resizeForAdd(int hashsetCapacity){
        /* checks if under the upper factor*/
        if (((float)size()+1)/capacity() <= getUpperLoadFactor()) {
            return hashsetCapacity;
        }
        hashsetCapacity *= RESIZE_UP_DEFAULT_NUM;
        return hashsetCapacity;
    }

    /**
     * this method resizes the set upon deleting a string.
     * this method checks if the capacity is under the lower factor,
     * if so if resizes, if not it returns the given capacity
     * @param hashsetCapacity the current capacity of the set.
     * @return the given capacity, or if beyond the upper factor
     *         returns the new capacity.
     */
    protected int resizeForDelete(int hashsetCapacity){
        /* checks if beyond the lower factor*/
        if (((float)size()-1)/capacity() >= getLowerLoadFactor()) {
            return hashsetCapacity;
        }
        hashsetCapacity *= RESIZE_DOWN_DEFAULT_NUM;
        return hashsetCapacity;
    }
}

