package ro.uaic.info.data_mining.aggregation;

import com.google.common.collect.Multimap;
import org.junit.Before;
import org.junit.Test;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;
import ro.uaic.info.data_mining.aggregation.utils.DistanceUnit;
import ro.uaic.info.data_mining.aggregation.utils.Location;
import ro.uaic.info.data_mining.aggregation.utils.Radius;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeoLocationCoefficientCalculatorTest {

    private List<Location> locations;
    private Multimap<String, Construction> constructions;

    @Before
    public void initializeLocations() throws LocationGeocodeException {
        locations = new ArrayList<>();
        locations.add(Location.fromString("Alexandru cel Bun / Dacia Iasi"));
        locations.add(Location.fromString("Centru Iasi"));
        locations.add(Location.fromString("Copou Iasi"));
        locations.add(Location.fromString("Galata Iasi"));
        locations.add(Location.fromString("Gara Iasi"));
        locations.add(Location.fromString("Mircea cel Batran Iasi"));
        locations.add(Location.fromString("Nicolina / Frumoasa / CUG Iasi"));
        locations.add(Location.fromString("Pacurari / Canta Iasi"));
        locations.add(Location.fromString("Podu Ros Iasi"));
        locations.add(Location.fromString("Podul de Fier Iasi"));
        locations.add(Location.fromString("Tatarasi / Oancea / Metalurgie Iasi"));
        locations.add(Location.fromString("Bucium Iasi"));

        constructions = Constructions.aggregateViaZone();
    }

    @Test
    public void testGeoLocation_CalculatingZoneCoefficient_BasedOnPrice() throws Exception {
        ZoneCoefficientCalculator zoneCoefficientCalculator = new GeoLocationCoefficientCalculator(locations, Construction.Parameter.Price);

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