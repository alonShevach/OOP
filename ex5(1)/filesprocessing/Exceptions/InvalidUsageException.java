package filesprocessing.Exceptions;

/**
 * this method is InvalidUsageException it represents an invalid usage of the
 * files given by the user.
 */
public class InvalidUsageException extends Exception {

    /* the default message*/
    private static final String message = "ERROR: Invalid usage";

    /**
     * the default constructor
     */
    public InvalidUsageException() {
    }

    /**
     * getter of the message
     * @return the Exception's message.
     */
    public String getMessage() {
        return message;
    }
}
