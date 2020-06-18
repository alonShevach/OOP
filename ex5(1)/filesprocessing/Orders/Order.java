package filesprocessing.Orders;

import java.io.File;

/**
 * The class representing an order, this class gets a type of order
 * and if the order should be reversed. and creates the order accordingly.
 */
public abstract class Order {
    /* A boolean represents if the sorting is reversed or normal.*/
    protected boolean isReversed;

    /*creating an SortType enum*/
    public enum SortType {
        ABS, TYPE, SIZE
    }


    /**
     * default constructor
     */
    protected Order() {
    }

    /**
     * an abstarct method which is a getter of the class's type.
     *
     * @return An enum of SortType. which represents the type of sort we want.
     */
    public abstract SortType getType();

    /**
     * This method used to implement Quicksort, this method is getting a pivot
     * and sort all objects that are lower than the pivot before it and
     * all the greater object after the pivot.
     *
     * @param arr  a File array, the array which we want to sort.
     * @param low  the low index to start from
     * @param high the high index to sort to.
     * @return the new pivot.
     */
    private int partition(File[] arr, int low, int high) {
        OrderFileComparator fileCompare = new OrderFileComparator(this.getType(), isReversed);
        File pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            /* checks if the current element is smaller than the pivot*/
            if (fileCompare.compare(arr[j], pivot) <= 0) {
                i++;
                /* swapping arr[i] and arr[j]*/
                File temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        /* swap arr[i+1] and arr[high] (or pivot)*/
        File temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    /**
     * The main function that implements QuickSort()
     * Sorting the given arr from the low index to the high index.
     *
     * @param arr  a File array, the array which we want to sort.
     * @param low  the low index to start from
     * @param high the high index to sort to.
     */
    public void quickSort(File[] arr, int low, int high) {
        if (high == 0) {
            return;
        }
        if (low < high) {
            /* pi is the partitioning pivot*/
            int pi = partition(arr, low, high);

            /* the recursive calls*/
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
