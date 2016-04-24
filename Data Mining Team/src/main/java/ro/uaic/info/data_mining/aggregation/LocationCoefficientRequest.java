package ro.uaic.info.data_mining.aggregation;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LocationCoefficientRequest {

    private String zone;
    private List<Construction.Parameter> parameters;

    public LocationCoefficientRequest withLocation(@NotNull String zone) {
        if (this.zone != null) {
            throw new UnsupportedOperationException("LocationCoefficientRequest is immutable.");
        }

        this.zone = zone;
        return this;
    }

    public LocationCoefficientRequest withParameters(Construction.Parameter... parameters) {
        if (this.parameters != null) {
            throw new UnsupportedOperationException("LocationCoefficientRequest is immutable.");
        }

        this.parameters = new ArrayList<>();

        for (Construction.Parameter parameter : parameters) {
            this.parameters.add(parameter);
        }

        return this;
    }

    public String getZone() {
        return zone;
    }

    public List<Construction.Parameter> getParameters() {
        return parameters;
    }
}
