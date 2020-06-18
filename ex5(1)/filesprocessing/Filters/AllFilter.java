package filesprocessing.Filters;

import filesprocessing.Exceptions.InvalidInputException;

import java.io.File;

public class AllFilter extends Filter {

    /* and int represeting the number of args we need to receive from the user.*/
    private static final int ARGS_LENGTH = 1;

    public AllFilter() {
    }

    /**
     * the constructor of the class, getting the args from the user and checks them for
     * bad input and gets the YesOrNo boolean.
     * checks if the lowerBound is negative, if so throws an exception
     * @param args the args we got from the command file.
     * @param IsNot a boolean if the file length should filter all or not.
     * @throws InvalidInputException if the args does not follow the args we got, or the
     * args have words that are bad inputs, if so throws an exception.
     */
    AllFilter(String[] args, boolean IsNot) throws InvalidInputException {
        CheckArgs(args, IsNot, ARGS_LENGTH);
    }

    /**
     * a method checking if the file is passing the filter or not.
     * @param file a file to filter according to the filter.
     * @return True if the file fits the filter, false otherwise.
     */
    @Override
    public boolean accept(File file) {
        return !isNot;
    }
}
