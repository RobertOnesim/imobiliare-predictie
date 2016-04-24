package ro.uaic.info.data_mining.aggregation;

import com.sun.istack.internal.NotNull;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;
import ro.uaic.info.data_mining.aggregation.utils.DistanceCalculator;
import ro.uaic.info.data_mining.aggregation.utils.DistanceUnit;
import ro.uaic.info.data_mining.aggregation.utils.Location;
import ro.uaic.info.data_mining.aggregation.utils.Radius;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to calculate geo-location coefficient. The geo-location
 * coefficient can be described as taking the average of the sum of the values for
 * a supplied parameter for all the buildings in a certain location and dividing
 * that by the average of the sum of the values for a supplied parameter for all
 * the locations supplied to this object's constructor.
 *
 */
public class GeoLocationCoefficientCalculator {

    private List<ConstructionLocationDecorator> constructions;
    private Construction.Parameter parameterOfInterest;

    public GeoLocationCoefficientCalculator(@NotNull List<Construction> constructions, Construction.Parameter parameter)
            throws LocationGeocodeException {

        this.parameterOfInterest = parameter;
        this.constructions = new ArrayList<>(constructions.size());

        for (Construction construction : constructions) {
            this.constructions.add(new ConstructionLocationDecorator(construction));
        }
    }

    /**
     * Calculates the double average coefficient for the given constructionsInRadius, over the
     * entire constructions list supplied within this object's constructor. The coefficient is
     * calculated based on the supplied {@link Construction.Parameter} (within the constructor).
     *
     * @param request The {@link GeoLocationCoefficientRequest} for which to calculate the coefficient
     * @return The calculated average coefficient of the zone, reported to all the constructions supplied.
     */
    public double getZoneCoefficient(@NotNull GeoLocationCoefficientRequest request) {

        List<ConstructionLocationDecorator> constructionsInRadius
                = getConstructionsInRadius(request.getLocation(), request.getRadius());

        double radiusAverageCoefficient;
        double allConstructionsAverageCoefficient;

        switch (parameterOfInterest) {
            case Price: {
                radiusAverageCoefficient = getAveragePrice(constructionsInRadius);
                allConstructionsAverageCoefficient = getAveragePrice(this.constructions);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Current parameter coefficient extraction not supported.");
            }
        }

        return radiusAverageCoefficient / allConstructionsAverageCoefficient;
    }

    double getAveragePrice(List<ConstructionLocationDecorator> constructionsInRadius) {
        double zoneAggregatedPrice = 0;

        for (ConstructionLocationDecorator constructionInRadius : constructionsInRadius) {
            zoneAggregatedPrice
                    += (double) constructionInRadius.getConstruction().getParameter(Construction.Parameter.Price);
        }

        return zoneAggregatedPrice / constructionsInRadius.size();
    }

    /**
     * Returns all constructions that are part of {@link GeoLocationCoefficientCalculator#constructions}
     * and are within the given radius around the given location.
     *
     * @param location The location which represents the center of the radius
     * @param radius   The radius for which to retrieve all Constructions within that radius
     * @return The list of the location decorated constructions withing the given radius of the given location
     */
    List<ConstructionLocationDecorator> getConstructionsInRadius(Location location, Radius radius) {
        // TODO improve for different kinds of distance measure units
        if (!radius.getUnit().equals(DistanceUnit.Meters)) {
            throw new UnsupportedOperationException("Only meters supported within distance radius.");
        }

        List<ConstructionLocationDecorator> constructionsWithinRadius = new ArrayList<>();

        for (ConstructionLocationDecorator construction : constructions) {
            double distanceBetweenLocationAndConstruction = DistanceCalculator.calculateLatLongDistance(
                    location.getCoordinates(),
                    construction.getLocation().getCoordinates(),
                    radius.getUnit());

            if (distanceBetweenLocationAndConstruction <= radius.getAmount()) {
                constructionsWithinRadius.add(construction);
            }
        }

        return constructionsWithinRadius;
    }
}
