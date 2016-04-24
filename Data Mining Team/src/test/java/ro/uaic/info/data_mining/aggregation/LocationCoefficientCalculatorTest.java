package ro.uaic.info.data_mining.aggregation;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Heatlerio on 22.04.2016.
 */
public class LocationCoefficientCalculatorTest {

    List<Construction> constructions;

    @Before
    public void initializeLocations() {
        constructions = new ArrayList<>();
        constructions.add(new Construction()
                .setPrice(25000)
                .setZone("Pacurari Iasi"));
        constructions.add(new Construction()
                .setPrice(20000)
                .setZone("Pacurari Iasi"));
        constructions.add(new Construction()
                .setPrice(15000)
                .setZone("Centru Iasi"));
        constructions.add(new Construction()
                .setPrice(34000)
                .setZone("Copou Iasi"));
        constructions.add(new Construction()
                .setPrice(11000)
                .setZone("Galata Iasi"));
    }

    @Test
    public void testLocation_CalculatingZoneCoefficient_BasedOnPrice() throws Exception {
        LocationCoefficientCalculator locationCoefficientCalculator
                = new LocationCoefficientCalculator(constructions, Construction.Parameter.Price);

        String zone = "Pacurari Iasi";
        double expectedCoefficient = ((25000 + 20000) / 2.0) / ((25000 + 20000 + 15000 + 34000 + 11000) / 5.0);

        LocationCoefficientRequest myRequest = new LocationCoefficientRequest()
                .withLocation(zone)
                .withParameters(Construction.Parameter.Price);

        double myCoefficient = locationCoefficientCalculator.getZoneCoefficient(myRequest);
        assertThat(myCoefficient, equalTo(expectedCoefficient));
        System.out.println(zone + " has a price-location coefficient of " + myCoefficient);
    }

}