package ro.uaic.info.data_mining.aggregation.exceptions;

/**
 * TODO
 */
public class InvalidRadiusException extends Exception {
    public static final String AMOUNT_NOT_VALID = "Error: Invalid distance supplied.";

    public InvalidRadiusException(String message) {
        super(message);
    }
}
