package filesprocessing.Filters;

import filesprocessing.Exceptions.InvalidInputException;

import java.io.File;

/**
 * this class extends the filter, and it represents a Between filter,
 * which receives a file and checks if it is Hidden.
 */
public class HiddenFilter extends Filter {
    /* a boolean represents if it should be Hidden of not*/
    private boolean yesOrNo;
    /* and int represeting the number of args we need to receive from the user.*/
    private static final int ARGS_LENGTH = 2;

    /**
     * the constructor of the class, getting the args from the user and checks them for
     * bad input and gets the YesOrNo boolean.
     * @param args the args we got from the command file.
     * @param IsNot a boolean if it should not be hidden or it should.
     * @throws InvalidInputException if the args does not follow the args we got, or the
     * args have words that are bad inputs, it will throw the invalid input.
     */
    HiddenFilter(String[] args, boolean IsNot) throws InvalidInputException {
        CheckArgs(args, IsNot, ARGS_LENGTH);
        yesOrNo = checksIfYesOrNo(args[1]);
    }

    /**
     * a method checking if the file is passing the filter or not.
     * @param file a file to filter according to the filter.
     * @return True if the file fits the filter, false otherwise.
     */
    @Override
    public boolean accept(File file) {
        return (file.isHidden() == yesOrNo) != isNot;
    }
}
