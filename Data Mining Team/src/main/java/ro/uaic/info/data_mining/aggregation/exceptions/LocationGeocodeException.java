package ro.uaic.info.data_mining.aggregation.exceptions;

/**
 * Exception used to indicate that an error occurred when trying to
 * geo-locate a place via some geo-location API.
 */
public class LocationGeocodeException extends Exception {
    public static final String GEOCODE_ERROR = "Geocoding error while fetching data.";
    public static final String NOT_FOUND = "Geocoding could not find requested location.";

    public LocationGeocodeException(String message, StackTraceElement[] stackTrace) {
        super(message);
        this.setStackTrace(stackTrace);
    }

    public LocationGeocodeException(String message) {
        super(message);
    }
}
