package filesprocessing.Orders;

import java.io.File;
import java.util.Comparator;

/**
 * A class that is a Comparator for Files according to the way we want to compare them.
 * this class implements the Comparator method and also gets a sorting type, which is an enum
 * of the type we want to sort the class.
 */
public class OrderFileComparator implements Comparator<File> {
    /*magic numbers*/
    private static final String SEPARATOR = "\\.";
    private static final int POSITIVE_COMPARE = 1;
    private static final int NEGATIVE_COMPARE = -1;
    private static final int COMPARE_EQUAL = 0;
    /* a SortType sorttype, which is an enum representing the sortType.*/
    private Order.SortType sortType;
    /*a boolean whether the sorting is reversed or not*/
    private boolean isReversed;

    /**
     * The constructor of the class, getting a SortType from the user, and if we want it to
     * sort reversed or normally.
     *
     * @param newsortType an enum represents the SortType we wants.
     * @param Reversed    boolean whether it is reversed sorting or not.
     */
    OrderFileComparator(Order.SortType newsortType, boolean Reversed) {
        sortType = newsortType;
        isReversed = Reversed;
    }


    /**
     * An override of the compare method, this method compares 2 files,
     * and returns who is bigger according to the compare type, and if they are equal.
     * if two files are equal, it sorts them by ABS.
     *
     * @param file1 First file to compare, File type
     * @param file2 Second file to compare, File type
     * @return positive num if file1 is bigger, negative if file2 is bigger, 0 if equal.
     */
    @Override
    public int compare(File file1, File file2) {
        if ((compareHelper(file1, file2)) == COMPARE_EQUAL) {
            int AbsCompare = file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
            return isReversed ? NEGATIVE_COMPARE * AbsCompare : AbsCompare;
        }
        return isReversed ? (NEGATIVE_COMPARE * compareHelper(file1, file2)) : (compareHelper(file1, file2));
    }

    /**
     * The helper of the compare, this is doing the compare according to the type,
     * and defaultly return absCompare.
     *
     * @param file1 First file to compare, File type
     * @param file2 Second file to compare, File type
     * @return positive num if file1 is bigger, negative if file2 is bigger, 0 if equal.
     */
    private int compareHelper(File file1, File file2) {
        int AbsCompare = file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
        switch (sortType) {
            case ABS:
                return AbsCompare;
            case TYPE:
                return (getFileExtension(file1).compareTo(getFileExtension(file2)));
            case SIZE:
                if (file1.length() == file2.length()) {
                    return COMPARE_EQUAL;
                }
                return (file1.length() > file2.length()) ? POSITIVE_COMPARE : NEGATIVE_COMPARE;
        }
        return AbsCompare;
    }

    /**
     * This method returns the File Extension, etc, file1.txt will
     * return txt.
     *
     * @param file a file to find its extension
     * @return The file extension(His type)
     */
    private String getFileExtension(File file) {
        String name = file.getAbsolutePath();
        if (name.endsWith(".")) {
            return "";
        }
        String[] temp = name.split(SEPARATOR);
        return temp[temp.length - 1];
    }
}
