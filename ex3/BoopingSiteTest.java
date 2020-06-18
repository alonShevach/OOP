/* Imports*/
import static org.junit.Assert.*;
import org.junit.*;
import oop.ex3.searchengine.*;

/**
 * A Test class for the BoopingSite Class.
 */
public class BoopingSiteTest {

    /** the Booping site we will use for testing*/
    private static BoopingSite booptest = new BoopingSite("hotels_tst1.txt");

    /**
     * Tests for the getHotelsInCityByRatingTest() method.
     */
    @Test
    public void getHotelsInCityByRatingTest() {
        /* should not find any hotel in ness tsiona, because there are none.*/
        Hotel[] emptyArr = booptest.getHotelsInCityByRating("ness tsiona");
        assertEquals("No hotels in ness tsiona, of course", 0, emptyArr.length);
        /* checks for hotels in delhi and should sort them.*/
        Hotel[] toTest = booptest.getHotelsInCityByRating("delhi");
        int prevInd = -1;
        for (Hotel h : toTest) {
            /* checks if the hotels are in delhi*/
            assertEquals("Wrong city in Array.", "delhi", h.getCity());
            if (prevInd == -1){
                prevInd++;
                continue;
            }
            /* checks if list is StarRating Sorted*/
            assertFalse (toTest[prevInd].getStarRating()<h.getStarRating());
            if (toTest[prevInd].getStarRating()==h.getStarRating()){
                int comp = h.getPropertyName().compareTo(toTest[prevInd].getPropertyName());
                System.out.println(toTest[prevInd].getPropertyName().compareTo(h.getPropertyName()));
                /* if 2 hotels has the same Rating, checks if it sorts them by their Prop Name.*/
                assertTrue(comp>=0);
            }
            prevInd++;
        }
    }

    /**
     * Tests for the getHotelsByProximityTest() method.
     */
    @Test
    public void getHotelsByProximityTest() {
        /* checks for bad input*/
        Hotel[] emptyArr = booptest.getHotelsByProximity(100, 30);
        Hotel[] emptyArr2 = booptest.getHotelsByProximity(20, 190);
        assertEquals("Empty array for bad input", 0, emptyArr.length);
        assertEquals("Empty array for bad input", 0, emptyArr2.length);
        Hotel[] toTest2 = booptest.getHotelsByProximity(20, 30);
        int prevInd = -1;
        for (Hotel h: toTest2){
            if (prevInd == -1){
                prevInd++;
                continue;
            }
            double dist1 = calculateDistance(toTest2[prevInd],20,30);
            double dist2 = calculateDistance(h,20,30);
            /* checks if the Array is Distance sorted*/
            assertFalse(dist1>dist2);
            if (dist1 == dist2){
                /* if two hotels has the same distance, checks if sorted by number of POI.*/
                assertTrue(toTest2[prevInd].getNumPOI()>= h.getNumPOI());
            }
        }
    }

    /**
     * Tests for the getHotelsInCityByProximityTest() method.
     */
    @Test
    public void getHotelsInCityByProximityTest() {
        /* checks for bad input*/
        Hotel[] emptyArr = booptest.getHotelsByProximity(100, 30);
        Hotel[] emptyArr2 = booptest.getHotelsByProximity(20, 190);
        assertEquals("Empty array for bad input", 0, emptyArr.length);
        assertEquals("Empty array for bad input", 0, emptyArr2.length);
        Hotel[] toTest3 = booptest.getHotelsInCityByProximity("manali", 30, 75);
        int prevInd = -1;
        for (Hotel h : toTest3) {
            /* checks if the hotels are in manali*/
            assertEquals("Wrong city in Array.", "manali", h.getCity());
            if (prevInd == -1){
                prevInd++;
                continue;
            }
            double dist1 = calculateDistance(toTest3[prevInd],30,75);
            double dist2 = calculateDistance(h,30,75);
            /* checks if the Array is Distance sorted*/
            assertFalse(dist1>dist2);
            if (dist1 == dist2){
                /* if two hotels has the same distance, checks if sorted by number of POI.*/
                assertTrue(toTest3[prevInd].getNumPOI()>= h.getNumPOI());
            }
        }
    }

    /**
     * this method calculated the distance between a given hotel to the point got
     * in the constructor, using pythagoras rule.
     * @param h Hotel type hotel, the hotel to calculate his distance from the
     *          point.
     * @param latitude double type, the latitude of the point
     * @param longitude double type, the longitude of the point
     * @return the distance from the hotel to the point.
     */
    private double calculateDistance(Hotel h,double latitude,double longitude){
        double hotelLatitude = h.getLatitude()-latitude;
        double hotelLongitude = h.getLongitude()-longitude;
        return Math.sqrt((Math.pow(hotelLongitude, 2))+(Math.pow(hotelLatitude, 2)));
    }

}