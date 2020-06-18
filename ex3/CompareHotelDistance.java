/* imports */
import java.util.Comparator;
import oop.ex3.searchengine.*;

/**
 * the CompareHotelStarRate which implies the Comparator implement.
 * this class main idea is to compare between Hotel type hotels by their
 * distance from a given point, presented by latitude and longitude.
 */
public class CompareHotelDistance implements Comparator<Hotel> {

    /** the double represents the latitude of the point we calculate the
     * distances from
     */
    private final double latitude;

    /** the double represents the longitude of the point we calculate the
     * distances from
     */
    private final double longitude;

    /**
     * the constructor of the class, getting a latitude and longitude of a
     * point to take distance from.
     * @param latitude double type, the latitude of the point
     * @param longitude double type, the longitude of the point
     */
    CompareHotelDistance(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
    /**
     * this method is the Compare method, overriding the normal compare
     * method, this method compares two hotels by their distance from a given point
     * (latitude and longitude)
     * and if they have the same distance it compares by their name's
     * String.
     * @param o1 a Hotel type hotel to sort.
     * @param o2 a Hotel type hotel to sort.
     * @return 0 if equal by their star rating and same name,
     * 1 if o1's distance is lower than o2's, or equal and String name is before his.
     * -1 if o2's distance is lower than o1's, or equal and String name is before his.
     */
    @Override
    public int compare(Hotel o1, Hotel o2) {
        double distanceH1 = calculateDistance(o1);
        double distanceH2 = calculateDistance(o2);
        if (distanceH1<distanceH2){
            return -1;
        }
        if (distanceH1>distanceH2){
            return 1;
        }
        if (o1.getNumPOI()> o2.getNumPOI()){
            return -1;
        }
        if (o1.getNumPOI()< o2.getNumPOI()){
            return 1;
        }
        return 0;
    }

    /**
     * this method calculated the distance between a given hotel to the point got
     * in the constructor, using pythagoras rule.
     * @param h Hotel type hotel, the hotel to calculate his distance from the
     *          point.
     * @return the distance from the hotel to the point.
     */
    private double calculateDistance(Hotel h){
        double hotelLatitude = h.getLatitude()-this.latitude;
        double hotelLongitude = h.getLongitude()-this.longitude;
        return Math.sqrt((Math.pow(hotelLongitude, 2))+(Math.pow(hotelLatitude, 2)));
    }
}
