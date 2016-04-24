package ro.uaic.info.data_mining.aggregation;

import com.sun.istack.internal.NotNull;
import ro.uaic.info.data_mining.aggregation.utils.Location;
import ro.uaic.info.data_mining.aggregation.utils.Radius;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */

public class GeoLocationCoefficientRequest {
    private Location location;
    private Radius radius;

    private List<Construction.Parameter> parameters;

    public GeoLocationCoefficientRequest withLocation(@NotNull Location location) {
        if (this.location != null) {
            throw new UnsupportedOperationException("GeoLocationCoefficientRequest is immutable.");
        }

        this.location = location;
        return this;
    }

    public GeoLocationCoefficientRequest withRadius(@NotNull Radius radius) {
        if (this.radius != null) {
            throw new UnsupportedOperationException("GeoLocationCoefficientRequest is immutable.");
        }

        this.radius = radius;
        return this;
    }

    public GeoLocationCoefficientRequest withParameters(Construction.Parameter... parameters) {
        if (this.parameters != null) {
            throw new UnsupportedOperationException("GeoLocationCoefficientRequest is immutable.");
        }

        this.parameters = new ArrayList<>();

        for (Construction.Parameter parameter : parameters) {
            this.parameters.add(parameter);
        }

        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Radius getRadius() {
        return radius;
    }

    public List<Construction.Parameter> getParameters() {
        return new ArrayList<>(this.parameters);
    }
}
