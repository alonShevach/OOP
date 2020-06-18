package filesprocessing.Filters;

import filesprocessing.Exceptions.InvalidInputException;
import java.io.File;

/**
 * this class extends the filter, and it represents a Between filter,
 * which receives a number and checks if the file length is smaller than
 * that number.
 */
public class SmallerThanFilter extends Filter {
    /* a non negative number, represents the upper bound.*/
    private double upperBound;
    /* magic numbers*/
    /* and int represeting the number of args we need to receive from the user.*/
    private static final int ARGS_LENGTH = 2;
    /* an int that is the conversion of Kbytes to bytes.*/
    private static final int K_BYTES_TO_BYTES = 1024;

    /**
     * the constructor of the class, getting the args from the user and checks them for
     * bad input and gets the YesOrNo boolean.
     * checks if the upperbound is negative, if so throws an exception
     * @param args the args we got from the command file.
     * @param IsNot a boolean if the file length should be bigger of smaller than the upperbound.
     * @throws InvalidInputException if the args does not follow the args we got, or the
     * args have words that are bad inputs or if the upperbound is negative, if so throws an exception.
     */
    SmallerThanFilter(String[] args, boolean IsNot) throws InvalidInputException {
        CheckArgs(args, IsNot, ARGS_LENGTH);
        upperBound = Double.parseDouble(args[1]);
        if (upperBound < 0) {
            throw new InvalidInputException();
        }
    }

    /**
     * a method checking if the file is passing the filter or not.
     * @param file a file to filter according to the filter.
     * @return True if the file fits the filter, false otherwise.
     */
    @Override
    public boolean accept(File file) {
        return isNot != ((double) file.length() < (upperBound * K_BYTES_TO_BYTES));
    }
}
