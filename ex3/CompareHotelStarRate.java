/* imports */
import oop.ex3.searchengine.*;
import java.util.Comparator;

/**
 * the CompareHotelStarRate which implies the Comparator implement.
 * this class main idea is to compare between Hotel type hotels by their
 * star ratings.
 */
public class CompareHotelStarRate implements Comparator<Hotel> {

    /**
     * this method is the Compare method, overriding the normal compare
     * method, this method compares two hotels by their star ratings,
     * and if they have the same star rating it compares by their name's
     * String.
     * @param o1 a Hotel type hotel to sort.
     * @param o2 a Hotel type hotel to sort.
     * @return 0 if equal by their star rating and same name,
     * 1 if o1's star rating is higher than o2's, or equal and String name is before his.
     * -1 if o2's star rating is higher than o1's, or equal and String name is before his.
     */
    @Override
    public int compare(Hotel o1, Hotel o2) {
        if (o1.getStarRating()== o2.getStarRating()){
            return o1.getPropertyName().compareTo(o2.getPropertyName());
        }
        if (o1.getStarRating()>o2.getStarRating()){
            return -1;
        }
        return 1;
    }
}
