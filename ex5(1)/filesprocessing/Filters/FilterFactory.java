package filesprocessing.Filters;

import filesprocessing.Exceptions.InvalidInputException;
import java.util.Arrays;

/**
 * A class That is the Factory for filters, creating filters according
 * to the command given to the class.
 */
public class FilterFactory {
    // The names of the filters: according to the commands./*
    private static final String GREATER_THAN = "greater_than";
    private static final String BETWEEN = "between";
    private static final String SMALLER_THAN = "smaller_than";
    private static final String FILE = "file";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";
    private static final String WRITABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String ALL = "all";
    private static final String SEPARATOR = "#";
    private static final String DECORATOR = "NOT";

    /**
     * The main function of the class, the filter creator, it creates the filters
     * according to the command given.
     * @param command a String represents the command received by the file.
     * @return The Filter that is right according to the command.
     * @throws InvalidInputException Throws the InvalidinputException when
     * the args length is below 1, or if the args length is one but contains
     * only decorator.
     */
    public static Filter filterCreator(String command) throws InvalidInputException {
        String[] args = command.split(SEPARATOR);
        boolean isNot = false;
        if (args[args.length - 1].equals(DECORATOR)) {
            isNot = true;
            /* removes the decorator.*/
            args = Arrays.copyOfRange(args, 0, args.length - 1);
        }
        if (args.length < 1) {
            throw new InvalidInputException();
        }
        switch (args[0]) {
            case GREATER_THAN:
                return new GreaterThanFilter(args, isNot);
            case BETWEEN:
                return new BetweenFilter(args, isNot);
            case SMALLER_THAN:
                return new SmallerThanFilter(args, isNot);
            case FILE:
                return new FileNameFilter(args, isNot);
            case CONTAINS:
                return new ContainsFilter(args, isNot);
            case PREFIX:
                return new PrefixFilter(args, isNot);
            case SUFFIX:
                return new SuffixFilter(args, isNot);
            case WRITABLE:
                return new WritableFilter(args, isNot);
            case EXECUTABLE:
                return new ExecutableFilter(args, isNot);
            case HIDDEN:
                return new HiddenFilter(args, isNot);
            case ALL:
                return new AllFilter(args, isNot);
            default:
                throw new InvalidInputException();
        }
    }
}
