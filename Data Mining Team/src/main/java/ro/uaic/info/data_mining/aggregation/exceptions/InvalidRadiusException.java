package ro.uaic.info.data_mining.aggregation.exceptions;

/**
 * Exception used for indicating if a radius ({@link ro.uaic.info.data_mining.aggregation.utils.Radius Radius})
 * was invalidly constructed.
 */
public class InvalidRadiusException extends Exception {
    public static final String AMOUNT_NOT_VALID = "Error: Invalid distance supplied.";

    public InvalidRadiusException(String message) {
        super(message);
    }
}
