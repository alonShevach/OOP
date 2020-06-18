package filesprocessing.Orders;

import filesprocessing.Exceptions.InvalidInputException;

/**
 * A class that is creating the orders according to the user's command.
 */
public class OrderFactory {
    /*magic numbers*/
    private static final String SEPARATOR = "#";
    private static final String DECORATOR = "REVERSE";
    private static final int ARGS_LENGTH = 2;

    /**
     * The creator of orders. This method gets a String command and returns the order,
     * for how to sort the files according to the given command.
     * @param orderCommand A string of command to create the order by.
     * @return An order type order, returns the order which will sort the way we
     * want according to the command.
     * @throws InvalidInputException Throws the error if we got a file type which
     * is not one of TYPE, SIZE OR ABS.
     */
    public static Order OrderCreator(String orderCommand)throws InvalidInputException {
        String[] ordersArr = orderCommand.split(SEPARATOR);
        boolean isReversed;
        isReversed = (ordersArr.length == ARGS_LENGTH) && (ordersArr[1].equals(DECORATOR));
        switch (ordersArr[0]) {
            case "type":
                return new TypeOrder(isReversed);
            case "size":
                return new SizeOrder(isReversed);
            case "abs":
                return new AbsOrder(isReversed);
            default:
                throw new InvalidInputException();
        }
    }
}
