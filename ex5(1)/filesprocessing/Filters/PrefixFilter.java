package filesprocessing.Filters;

import filesprocessing.Exceptions.InvalidInputException;
import java.io.File;

/**
 * this class extends the filter, and it represents a Between filter,
 * which receives a prefix, and checks if the filename contains that prefix.
 */
public class PrefixFilter extends Filter {
    /*the prefix to check if the file has in it name.*/
    private String prefix;
    /* and int represeting the number of args we need to receive from the user.*/
    private static final int ARGS_LENGTH = 2;

    /**
     * the constructor of the class, getting the args from the user and checks them for
     * bad input and gets the YesOrNo boolean.
     * @param args the args we got from the command file.
     * @param IsNot a boolean if the prefix should not be in the name or it should.
     * @throws InvalidInputException if the args does not follow the args we got, or the
     * args have words that are bad inputs, it will throw the invalid input.
     */
    PrefixFilter(String[] args, boolean IsNot) throws InvalidInputException {
        CheckArgs(args, IsNot, ARGS_LENGTH);
        prefix = args[1];
    }

    /**
     * a method checking if the file is passing the filter or not.
     * @param file a file to filter according to the filter.
     * @return True if the file fits the filter, false otherwise.
     */
    @Override
    public boolean accept(File file) {
        return isNot != file.getName().startsWith(prefix);
    }
}
