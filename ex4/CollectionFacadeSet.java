/* imports*/
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collection;

/**
 * a class represent a set,implements SimpleSet this class uses the facade we learned in class,
 * the class gets any type of collection and implements the methods:
 * contains, add, delete , size.
 */
public class CollectionFacadeSet extends java.lang.Object implements SimpleSet {

    /* a collection of string types.*/
    protected Collection<String> strCollection;

    /**
     * the constructor of the class, getting anytype of collection, remove its
     * duplicates and puts it back to the same kind of collection.
     * @param collection a collection of strings.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        /* removing duplicates from the collection*/
        LinkedList<String> lstToRemoveDuplicates = new LinkedList<String>(collection);
        collection.clear();
        strCollection = collection;
        strCollection.addAll(lstToRemoveDuplicates);
    }

    /**
     * the method adding a given value to the collectionFacadeSet.
     * this method is using the contains method, and also uses the
     * add method from the collection type.
     * @param newValue New value to add to the set
     * @return True upon success adding, false otherwise.
     */
    public boolean	add(java.lang.String newValue){
        if (contains(newValue)){
            return false;
        }
        strCollection.add(newValue);
        return true;
    }

    /**
     * a method checks if the collection contains a given value,
     * this method uses the method from the type of the collection given.
     * @param searchVal Value to search for
     * @return True if the value is in the collection, false otherwise.
     */
    public boolean	contains(java.lang.String searchVal){
        return strCollection.contains(searchVal);
    }

    /**
     * a method deleting an string from the collection
     * this method is using the remove() method from the given collection type.
     * @param toDelete Value to delete
     * @return True upon success, false otherwise.
     */
    public boolean	delete(java.lang.String toDelete){
        if (!contains(toDelete)){
            return false;
        }
        strCollection.remove(toDelete);
        return true;
    }

    /**
     * a method returns the size of the collection. using the size() method
     * of the collection class.
     * @return the size of the collection
     */
    public int size(){
        return strCollection.size();
    }

    /**
     * an iterator for the class, the iterator uses the iterator() method of
     * the given collection class.
     * @return an iterator of the collection.
     */
    protected Iterator<String> iterator(){
        return strCollection.iterator();
    }
}
