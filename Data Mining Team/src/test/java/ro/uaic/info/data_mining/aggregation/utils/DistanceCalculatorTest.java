package ro.uaic.info.data_mining.aggregation.utils;



import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DistanceCalculatorTest {
    @Test
    public void testDistanceCorrectlyCalculated_BetweenTwoCoordinates() throws Exception {
        Coordinates point1 = new Coordinates(50.0359, 5.1489);
        Coordinates point2 = new Coordinates(49.0359, 6.1489);

        double distance1 = DistanceCalculator.calculateLatLongDistance(point1, point2, DistanceUnit.Meters);
        assertThat(distance1, is(equalTo(132555.499972398)));

        Coordinates point3 = new Coordinates(50.0359, 5.1489);
        Coordinates point4 = new Coordinates(49.0359, 4.1489);

        double distance2 = DistanceCalculator.calculateLatLongDistance(point3, point4, DistanceUnit.Meters);
        assertThat(distance2, is(equalTo(132555.499972398)));

        Coordinates point5 = new Coordinates(50.0359, 5.1489);
        Coordinates point6 = new Coordinates(51.0359, 4.1489);

        double distance3 = DistanceCalculator.calculateLatLongDistance(point5, point6, DistanceUnit.Meters);
        assertThat(distance3, is(equalTo(131751.61915185925)));

        Coordinates point7 = new Coordinates(50.0359, 5.1489);
        Coordinates point8 = new Coordinates(51.0359, 6.1489);

        double distance4 = DistanceCalculator.calculateLatLongDistance(point7, point8, DistanceUnit.Meters);
        assertThat(distance4, is(equalTo(131751.61915185925)));
    }
}