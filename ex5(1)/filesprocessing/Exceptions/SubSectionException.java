package filesprocessing.Exceptions;

/**
 * the subSectionException, this class represents the exceptions that are type 2,
 * and that are an invalid input by the user. dividing the command file not properly
 * to subSuctions.
 */
public class SubSectionException extends Exception {
    /* the default message*/
    private String message;

    /**
     * the constructor of the class.
     * @param msg the message for the exception to have.
     */
    public SubSectionException(String msg) {
        message = msg;
    }

    /**
     * getter of the message
     * @return the Exception's message.
     */
    public String getMessage() {
        return message;
    }
}
