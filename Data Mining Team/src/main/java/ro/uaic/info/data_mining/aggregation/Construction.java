package ro.uaic.info.data_mining.aggregation;

/**
 * A construction represents a building or parcel from the agency's database.
 */
public class Construction {
    private Double price;
    private String zone;

    public enum Parameter {
        Price,
        Zone
    }

    /**
     * Returns the parameter which the caller asked for.
     * Parameter return type must be casted to its data type by the caller.
     * <br/>
     * Please use the following associative table to figure what to cast the return type to:
     * <ul>
     *     <li>{@link Parameter#Price} - {@link Double}</li>
     *     <li>{@link Parameter#Zone} - {@link String}</li>
     * </ul>
     * @param parameter
     * @return
     */
    public Object getParameter(Parameter parameter) {
        switch (parameter) {
            case Price:
                return price;
            case Zone:
                return zone;
            default:
                throw new UnsupportedOperationException("Parameter not implemented.");
        }
    }

    public Construction setZone(String zone) {
        this.zone = zone;
        return this;
    }

    public Construction setPrice(double price) {
        this.price = price;
        return this;
    }
}
