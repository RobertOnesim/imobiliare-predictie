package ro.uaic.info.data_mining.aggregation.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Suhani on 27\04\16.
 */
public class RadiusTest {

    @Test
    public void testGetAmount() throws Exception {
        Radius testRadius = new Radius(100, DistanceUnit.Meters);
        assertEquals(100, testRadius.getAmount());
    }

    @Test
    public void testGetUnit() throws Exception {
        Radius testRadius = new Radius(100, DistanceUnit.Meters);
        assertEquals(DistanceUnit.Meters, testRadius.getUnit());
    }
}