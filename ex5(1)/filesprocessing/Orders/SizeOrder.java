package filesprocessing.Orders;

public class SizeOrder extends Order {
    /**
     * The constructor of the class.
     *
     * @param Reversed boolean, if to sort normally of reversed.
     */
    public SizeOrder(boolean Reversed) {
        isReversed = Reversed;
    }

    /**
     * a getter of the class's type.
     *
     * @return An enum of SortType. which represents the type of sort we want.
     */
    public SortType getType() {
        return SortType.SIZE;
    }
}
