package ro.uaic.info.data_mining.aggregation.utils;

/**
 * This class is used as a static container for methods that calculate
 * various distances between two points on the Earth's surface.
 */
public class DistanceCalculator {

    /**
     * This uses the ‘haversine’ formula to calculate the great-circle distance between two points – that is,
     * the shortest distance over the earth’s surface – giving an ‘as-the-crow-flies’ distance between the
     * points (ignoring any hills they fly over, of course!).
     *
     * @param point1 The first point on Earth
     * @param point2 The second point on Earth
     * @param distanceUnit The units in which to return the distance
     *
     * @return The great-circle distance between two points – that is, the shortest distance over the earth’s
     * surface – giving an ‘as-the-crow-flies’ distance between the points (ignoring any hills they fly over,
     * of course!).
     */
    public static double calculateLatLongDistance(Coordinates point1, Coordinates point2, DistanceUnit distanceUnit) {
        final double EARTH_RADIUS = 6371000; // metres

        final double theta1 = Math.toRadians(point1.getLatitude());
        final double theta2 = Math.toRadians(point2.getLatitude());

        final double deltaTheta = Math.toRadians(point2.getLatitude() - point1.getLatitude());
        final double deltaLambda = Math.toRadians(point2.getLongitude() - point1.getLongitude());

        final double a
                = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2)
                + Math.cos(theta1) * Math.cos(theta2)
                * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);

        final double c
                = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        final double distance = EARTH_RADIUS * c;

        return convertDistance(distance, distanceUnit);
    }

    static double convertDistance(double distance, DistanceUnit distanceUnit) {
        switch (distanceUnit) {
            case Meters:
                return distance;
            //TODO Use UnitConverter to add useful distance conversions
            default:
                throw new UnsupportedOperationException("Invalid default case caught.");
        }
    }
}
