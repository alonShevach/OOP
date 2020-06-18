/* imports*/
import oop.ex3.searchengine.*;
import java.util.*;
import static oop.ex3.searchengine.HotelDataset.*;

/**
 * A class that represents a site for searching hotels,
 * the class is getting a file with hotels, and it searches for
 * the right hotel by distance from point, and by Star Rating.
 * this Class uses the oop.ex3.searchengine.
 */
public class BoopingSite {

    /* Magic numbers.*/
    private static final int LATITUDE_MAX = 90;
    private static final int LATITUDE_MIN = -90;
    private static final int LONGITUDE_MAX = 180;
    private static final int LONGITUDE_MIN = -180;

    /** the dataSet for the Hotels Array.*/
    private String dataSet;

    /**
     * The constructor of the Class, getting a path to a file with
     * hotel discriptions.
     * @param name a String represents the Path or the Name of the file.
     */
    public BoopingSite(String name){
        this.dataSet = name;
    }

    /**
     * This method sorts the Hotels in a given city by their StarRating.
     * If two hotels has the same star rating, this method will sort
     * by their PropertyName.
     * @param city a String, represents the name of the city.
     * @return an Hotel Array, sorted by the StarRating.
     */
    public Hotel[] getHotelsInCityByRating(String city){
        List<Hotel> cityHotelLst = getHotelListForCity(city);
        Collections.sort(cityHotelLst,new CompareHotelStarRate());
        Hotel[] HotelArr = new Hotel[cityHotelLst.size()];
        cityHotelLst.toArray(HotelArr);
        return HotelArr;
    }

    /**
     * This method creates a list of all the hotels in a given city, using the
     * dataset given in the constructor.
     * @param city a String, the city to find the hotels in.
     * @return A list of Hotel type hotels, that are in the given city
     */
    private List<Hotel> getHotelListForCity(String city) {
        Hotel[] Hotelarr = getHotels(this.dataSet);
        List<Hotel> newLst = new ArrayList<Hotel>();
        for (Hotel h : Hotelarr) {
            if (h.getCity().equals(city)) {
                newLst.add(h);
            }
        }
        return newLst;
    }

    /**
     * this method returns an sorted array of the closest hotels to a given point
     * (from the closest to the most far)the point represented with two coordinates, latitude, longitude.
     * if two hotels has the same distance to the given point, it
     * sorts them by their number of points of interest.
     * @param latitude a Double, the latitude coordinate of the point.
     * @param longitude a Double, the longitude coordinate of the point.
     * @return A sorted array by the distance to the point.
     */
    public Hotel[] getHotelsByProximity(double latitude, double longitude){
        return getHotelIsByProximityWithOrWIthoutCity(latitude,longitude,null);
    }

    /**
     * this method returns an sorted array of the closest hotels to a given point
     * (from the closest to the most far)the point represented with two coordinates, latitude, longitude.
     * if two hotels has the same distance to the given point, it
     * sorts them by their number of points of interest.
     * this method returns only hotels that are in the given city.
     * @param latitude a Double, the latitude coordinate of the point.
     * @param longitude a Double, the longitude coordinate of the point.
     * @return A sorted array by the distance to the point, and by the city given.
     */
    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){
        return getHotelIsByProximityWithOrWIthoutCity(latitude,longitude,city);
    }

    /**
     * this method returns an sorted array of the closest hotels to a given point
     * (from the closest to the most far)the point represented with two coordinates, latitude, longitude.
     * if two hotels has the same distance to the given point, it
     * sorts them by their number of points of interest.
     * this method can get a city to look in, if not given a city,
     * it will get all hotels, if given a city, it will find the hotels only in the given city.
     * @param latitude a Double, the latitude coordinate of the point.
     * @param longitude a Double, the longitude coordinate of the point.
     * @param city a city to give hotels in, or null if not to look in city.
     * @return A sorted array by the distance to the point, and by the city given.
     */
    private Hotel[] getHotelIsByProximityWithOrWIthoutCity(double latitude, double longitude, String city){
        if ((latitude< LATITUDE_MIN) || (latitude> LATITUDE_MAX) ||(longitude> LONGITUDE_MAX) || (longitude< LONGITUDE_MIN)){
            return new Hotel[0];
        }
        List<Hotel> HotelLst;
        if (city == null){
            Hotel[] Hotelarr = getHotels(this.dataSet);
            HotelLst = Arrays.asList(Hotelarr);
        }
        else{
            HotelLst = getHotelListForCity(city);
        }
        Collections.sort(HotelLst,new CompareHotelDistance(latitude, longitude));
        Hotel[] HotelArr = new Hotel[HotelLst.size()];
        HotelLst.toArray(HotelArr);
        return HotelArr;
    }

}
