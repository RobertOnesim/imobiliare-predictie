package ro.uaic.info.data_mining.aggregation;

import com.google.common.collect.Multimap;
import com.sun.istack.internal.NotNull;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;
import ro.uaic.info.data_mining.aggregation.utils.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * TODO
 */
public class GeoLocationCoefficientCalculator implements ZoneCoefficientCalculator{
    private List<Location> locations;

    public GeoLocationCoefficientCalculator(@NotNull List<Location> locations) {
        this.locations = new ArrayList<>(locations);
    }

    @Override
    public double getZoneCoefficient(@NotNull GeoLocationCoefficientRequest request) {

        return getZoneCoefficientRecursive(request, request.getParameters()) / getAllZonesCoefficientRecursive(request.getParameters());
    }

    private double getAllZonesCoefficientRecursive(List<Construction.Parameter> parameters) {
        if (parameters.size() < 1) {
            return 1;
        }

        List<Double> zonesCoefficients = new ArrayList<>(100);
        throw new UnsupportedOperationException();
    }

    double getZoneCoefficientRecursive(@NotNull GeoLocationCoefficientRequest request, List<Construction.Parameter> parameters) {
        if (parameters.size() < 1) {
            return 1;
        }

        Construction.Parameter currentParameter = parameters.get(0);
        parameters.remove(0);

        switch (currentParameter) {
            case Price: {
                return getZonePriceCoefficient(request) / getZoneCoefficientRecursive(request, parameters);
            }
            default: {
                throw new UnsupportedOperationException("Default case unimplemented.");
            }
        }
    }

    double getZonePriceCoefficient(GeoLocationCoefficientRequest request) {
        throw new UnsupportedOperationException();
    }
}
