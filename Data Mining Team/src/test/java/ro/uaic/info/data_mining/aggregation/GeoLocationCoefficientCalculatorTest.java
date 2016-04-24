package ro.uaic.info.data_mining.aggregation;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;
import ro.uaic.info.data_mining.aggregation.utils.DistanceUnit;
import ro.uaic.info.data_mining.aggregation.utils.Location;
import ro.uaic.info.data_mining.aggregation.utils.Radius;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeoLocationCoefficientCalculatorTest {

    private List<Construction> constructions;

    @Before
    public void initializeLocations() throws LocationGeocodeException {
        constructions = new ArrayList<>();
        constructions.add(new Construction()
                .setPrice(100)
                .setZone("Alexandru cel Bun / Dacia Iasi"));
        constructions.add(new Construction()
                .setPrice(100)
                .setZone("Alexandru cel Bun / Dacia Iasi"));
        constructions.add(new Construction()
                .setPrice(100)
                .setZone("Centru Iasi"));
        constructions.add(new Construction()
                .setPrice(200)
                .setZone("Copou Iasi"));
        constructions.add(new Construction()
                .setPrice(300)
                .setZone("Copou Iasi"));
        constructions.add(new Construction()
                .setPrice(100)
                .setZone("Galata Iasi"));
    }

    @Test
    public void testGeoLocation_ForAboveAverageZone_CalculatingZoneCoefficient_BasedOnPrice() throws Exception {
        GeoLocationCoefficientCalculator zoneCoefficientCalculator
                = new GeoLocationCoefficientCalculator(constructions, Construction.Parameter.Price);

        Location myLocation = Location.fromString("Copou Iasi");
        Radius myRadius = new Radius(1000, DistanceUnit.Meters);

        GeoLocationCoefficientRequest myRequest = new GeoLocationCoefficientRequest()
                .withLocation(myLocation)
                .withRadius(myRadius)
                .withParameters(Construction.Parameter.Price);

        double myCoefficient = zoneCoefficientCalculator.getZoneCoefficient(myRequest);
        System.out.println(myLocation + " has a price-geolocation coefficient of " + myCoefficient);

        assertThat(myCoefficient, is(greaterThan(1.0)));
    }

    @Test
    public void testGeoLocation_ForBelowAverageZone_CalculatingZoneCoefficient_BasedOnPrice() throws Exception {
        GeoLocationCoefficientCalculator zoneCoefficientCalculator
                = new GeoLocationCoefficientCalculator(constructions, Construction.Parameter.Price);

        Location myLocation = Location.fromString("Alexandru cel Bun / Dacia Iasi");
        Radius myRadius = new Radius(1000, DistanceUnit.Meters);

        GeoLocationCoefficientRequest myRequest = new GeoLocationCoefficientRequest()
                .withLocation(myLocation)
                .withRadius(myRadius)
                .withParameters(Construction.Parameter.Price);

        double myCoefficient = zoneCoefficientCalculator.getZoneCoefficient(myRequest);
        System.out.println(myLocation + " has a price-geolocation coefficient of " + myCoefficient);

        assertThat(myCoefficient, is(Matchers.lessThan(1.0)));
    }
}