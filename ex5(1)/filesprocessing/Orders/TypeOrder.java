package filesprocessing.Orders;

public class TypeOrder extends Order {
    /**
     * The constructor of the class.
     *
     * @param Reversed boolean, if to sort normally of reversed.
     */
    public TypeOrder(boolean Reversed) {
        isReversed = Reversed;
    }

    /**
     * a getter of the class's type.
     *
     * @return An enum of SortType. which represents the type of sort we want.
     */
    public SortType getType() {
        return SortType.TYPE;
    }

}
