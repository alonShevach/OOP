package filesprocessing.Exceptions;

/**
 * this class represents the InvalidInputException according to our
 * expectations to the receiving arguments.
 */
public class InvalidInputException extends Exception {

    /* the default message*/
    private static final String message = "Warning in line ";

    /**
     * the default constructor
     */
    public InvalidInputException() {}

    /**
     * getter of the message
     * @return the Exception's message.
     */
    public String getMessage() {
        return message;
    }
}
