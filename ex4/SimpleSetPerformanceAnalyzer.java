/* imports*/
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.HashSet;

/**
 * the class analyzes the time each of the other classes of simple set are running,
 * checking the add, and contains methods.
 */
public class SimpleSetPerformanceAnalyzer {

    /* magic numbers*/
    private static final int MILLI_DEVISION = 1000000;
    private static final int ROUND_FOR_CONTAINS = 70000;
    private static final int ROUND_FOR_CONTAINS_LINKED_LST = 7000;
    private static final String SEARCH_VAL1 = "hi";
    private static final String SEARCH_VAL2 = "-13170890158";
    private static final String SEARCH_VAL3 = "23";
    public static final int NUMBER_OF_SETS = 5;

     /* the string represents the name of each set */
    private static final String OPEN_HASH_SET = "openHashSet";
    private static final String CLOSED_HASH_SET = "ClosedHashSet";
    private static final String FACADE_TREE_SET = "FacadeTreeSet";
    private static final String FACADE_LIST = "FacadeList";
    private static final String FACADE_HASH_SET = "FacadeHashSet";

    /* a static string array for the strings in the given files, data1 and data2, using the ex4Utils.*/
    private static String[] data1 = Ex4Utils.file2array("C:\\Program Files\\SHEL KOBI\\data1.txt");
    private static String[] data2 = Ex4Utils.file2array("C:\\Program Files\\SHEL KOBI\\data2.txt");

    /* the simpleset array that contains all the different kind of sets.*/
    private static SimpleSet[] setsArr;
    /* the string array for all the names of the sets, by their appearence in setsArr.*/
    private static String[] NamesArr;

    /* printing messages for the tests.*/
    private static final String INITIAL_MSG = "tests for: %s \n";
    private static final String ADD_TO_DADA1_MSG = "the time it takes (in ms) to insert data1 is: %s.\n";
    private static final String ADD_TO_DADA2_MSG = "the time it takes (in ms) to insert data2 is: %s.\n";
    private static final String CONTAINS_DADA1_MSG =
            "the time it takes (in ns) to check if %S is contained in data1 is: %s\n";
    private static final String CONTAINS_DADA2_MSG =
            "the time it takes (in ns) to check if %S is contained in data2 is: %s\n";

    /**
     * the constractor of the class, builds a set containing all kinds of hashsets we made,
     * and has a set of the sets names according to their appearence in the setsArr.
     */
    private SimpleSetPerformanceAnalyzer(){
        SimpleSet openHash = new OpenHashSet();
        SimpleSet closedHash = new ClosedHashSet();
        SimpleSet facadeTree = new CollectionFacadeSet(new TreeSet<String>());
        SimpleSet facadeList = new CollectionFacadeSet(new LinkedList<String>());
        SimpleSet facadeHashSet = new CollectionFacadeSet(new HashSet<String>());

        setsArr = new SimpleSet[]{openHash, closedHash, facadeTree, facadeList, facadeHashSet};
        NamesArr = new String[]{OPEN_HASH_SET, CLOSED_HASH_SET, FACADE_TREE_SET, FACADE_LIST, FACADE_HASH_SET};
    }

    /**
     * a method that calculates how much time does it take to add a given data, to a given set.
     * also printing the time it took, and adds the data to the set.
     * @param set a set given to add the data to.
     * @param data the data to add to the set, and calculate the time it takes.
     * @param msg a message to print after the adding is finished.
     */
    private static void addingDataTimeCalc(SimpleSet set, String[] data, String msg) {

        long timeBefore = System.nanoTime();
        for (String string: data) {
            set.add(string);
        }
        long Difference = System.nanoTime() - timeBefore;
        System.out.printf(msg, Difference/ MILLI_DEVISION);
    }

    /**
     * a method that calculates the time it take to find a given value in a given set.
     * the method checks if the set contains the value, and calculate the time it take to find it.
     * also this function works differently for linked lists according the the guidance.
     * @param set a given set to find if the value is in.
     * @param msg a message to print to the user when the search is finished
     * @param Value a value to find in the set.
     * @param IsLinked boolean for if the set is a linkedlist or not.
     */
    private static void contains(SimpleSet set, String msg, String Value, boolean IsLinked) {
        if (!IsLinked) {
            for (int i = 0; i < ROUND_FOR_CONTAINS; i++) {
                set.contains(Value);
            }
            long timeBefore = System.nanoTime();
            for (int i = 0; i < ROUND_FOR_CONTAINS; i++) {
                set.contains(Value);
            }
            long Difference = System.nanoTime() - timeBefore;
            System.out.printf(msg, Value, Difference / ROUND_FOR_CONTAINS, set);
        }
        else{
            long timeBefore = System.nanoTime();
            for (int i = 0; i < ROUND_FOR_CONTAINS_LINKED_LST; i++) {
                set.contains(Value);
            }
            long Difference = System.nanoTime() - timeBefore;
            System.out.printf(msg, Value, Difference / ROUND_FOR_CONTAINS_LINKED_LST, set);
        }
    }

    /**
     * the main method, running the tests for each 1 of the sets.
     * this method tests all the sets adding times, and contain times.
     * using both of the data text files
     */
    public static void main(String[] args){
        boolean IsLinked;
        SimpleSetPerformanceAnalyzer setAnalyzer = new SimpleSetPerformanceAnalyzer();
        for (int i = 0; i< NUMBER_OF_SETS; i++){
            /* checks if it is a linked list.*/
            IsLinked = NamesArr[i].equals(FACADE_LIST);
            System.out.printf(INITIAL_MSG, NamesArr[i]);
            addingDataTimeCalc(setsArr[i], data1, ADD_TO_DADA1_MSG);
            addingDataTimeCalc(setsArr[i], data2, ADD_TO_DADA2_MSG);
            contains(setsArr[i],CONTAINS_DADA1_MSG, SEARCH_VAL1, IsLinked);
            contains(setsArr[i],CONTAINS_DADA1_MSG, SEARCH_VAL2, IsLinked);
            contains(setsArr[i],CONTAINS_DADA2_MSG, SEARCH_VAL3, IsLinked);
            contains(setsArr[i],CONTAINS_DADA2_MSG, SEARCH_VAL1, IsLinked);
        }
    }

}
