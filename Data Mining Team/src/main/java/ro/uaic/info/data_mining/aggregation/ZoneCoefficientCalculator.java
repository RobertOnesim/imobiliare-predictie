package ro.uaic.info.data_mining.aggregation;

import com.sun.istack.internal.NotNull;

/**
 * This interface should be implemented by any class that calculates coefficients
 * based on the zone in which the construction resides.
 */
public interface ZoneCoefficientCalculator {

    double getZoneCoefficient(@NotNull GeoLocationCoefficientRequest myRequest);
}
