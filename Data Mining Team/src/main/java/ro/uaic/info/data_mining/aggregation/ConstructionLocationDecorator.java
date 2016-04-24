package ro.uaic.info.data_mining.aggregation;

import com.sun.istack.internal.NotNull;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;
import ro.uaic.info.data_mining.aggregation.utils.Location;

/**
 * This class is used as a wrapper over a {@link Construction}, which adds its
 * corresponding {@link Location}, obtained via geo-location.
 */
public class ConstructionLocationDecorator {

    private Construction construction;
    private Location location;

    /**
     * Constructs a new decorator which adds geo-location to the supplied {@link Construction}
     *
     * @param construction The underlying construction for which to add geo-location ({@link Location}
     * @throws LocationGeocodeException
     */
    public ConstructionLocationDecorator(@NotNull Construction construction) throws LocationGeocodeException {
        this.construction = construction;

        if (construction.getParameter(Construction.Parameter.Zone) == null) {
            throw new IllegalArgumentException("Zone needed for geo-locating the construction.");
        }

        this.location = Location.fromString((String) construction.getParameter(Construction.Parameter.Zone));
    }

    public Construction getConstruction() {
        return construction;
    }

    public Location getLocation() {
        return location;
    }
}
