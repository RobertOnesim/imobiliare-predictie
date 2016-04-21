package ro.uaic.info.data_mining.aggregation;

import org.junit.Before;
import org.junit.Test;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;
import ro.uaic.info.data_mining.aggregation.utils.DistanceUnit;
import ro.uaic.info.data_mining.aggregation.utils.Location;
import ro.uaic.info.data_mining.aggregation.utils.Radius;

import java.util.ArrayList;
import java.util.List;

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
                .setPrice(100)
                .setZone("Copou Iasi"));
        constructions.add(new Construction()
                .setPrice(100)
                .setZone("Galata Iasi"));
    }

    @Test
    public void testGeoLocation_CalculatingZoneCoefficient_BasedOnPrice() throws Exception {
        ZoneCoefficientCalculator zoneCoefficientCalculator
                = new GeoLocationCoefficientCalculator(constructions, Construction.Parameter.Price);

        Location myLocation = Location.fromString("Copou Iasi");
        Location myArea = Location.fromString("Iasi");
        Radius myRadius = new Radius(2000, DistanceUnit.Meters);

        GeoLocationCoefficientRequest myRequest = new GeoLocationCoefficientRequest()
                .withLocation(myLocation)
                .withArea(myArea)
                .withRadius(myRadius)
                .withParameters(Construction.Parameter.Price);

        double myCoefficient = zoneCoefficientCalculator.getZoneCoefficient(myRequest);
        System.out.println(myLocation + " in " + myArea + " has a price-geolocation coefficient of " + myCoefficient);
    }
}