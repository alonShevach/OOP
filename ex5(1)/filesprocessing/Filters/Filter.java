package filesprocessing.Filters;

import filesprocessing.Exceptions.InvalidInputException;
import java.io.File;
import java.io.FileFilter;

/**
 * The class represents a filter to the directory,
 * this class is an abstract class.
 */
public abstract class Filter implements FileFilter {
    /*magic numbers*/
    private static final String YES = "YES";
    private static final String NO = "NO";
    /* a boolean represents if we want the specific command to happend or no.*/
    protected boolean isNot;

    /**
     * a method checking if the file is passing the filter or not.
     * @param file a file to filter according to the filter.
     * @return True if the file fits the filter, false otherwise.
     */
    @Override
    public abstract boolean accept(File file);

    /**
     * A method checking if we got a YES as a command or No.
     * @param yesOrNo the String to check if equal Yes or No.
     * @return True if YES, False if No.
     * @throws InvalidInputException Throws the InvalidInputException if the
     * received String if not yes and not no.
     */
    protected boolean checksIfYesOrNo(String yesOrNo) throws InvalidInputException {
        if (yesOrNo.equals(YES)) {
            return true;
        } else if (yesOrNo.equals(NO)) {
            return false;
        } else {
            throw new InvalidInputException();
        }
    }

    /**
     * A method that checks the given args String array according to the
     * rest of the args.
     * @param args A string array to check if fits the args.
     * @param not A boolean represents a NOT in the args.
     * @param myNumOfArgs The number of args we expect to have in the Args Array.
     * @throws InvalidInputException Throws this Exception if the args array does not fit the
     * rest of the args.
     */
    protected void CheckArgs(String[] args, boolean not, int myNumOfArgs) throws InvalidInputException {
        isNot = not;
        if (args.length != myNumOfArgs) {
            throw new InvalidInputException();
        }
    }

}
