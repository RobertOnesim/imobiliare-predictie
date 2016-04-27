package ro.uaic.info.data_mining.aggregation.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by StefanSuhani on 27\04\16.
 */
public class CoordinatesTest {

    @Test
    public void testGetLatitude() throws Exception {
        Coordinates testPair = new Coordinates(10.57987, 15.56967);
        assertEquals(10.57987, testPair.getLatitude(), 7);
    }

    @Test
    public void testGetLongitude() throws Exception {
        Coordinates testPair = new Coordinates(10.57987, 15.56967);
        assertEquals(15.56967, testPair.getLongitude(), 7);
    }

    @Test
    public void testEquals() throws Exception {
        Coordinates firstPair = new Coordinates(14.5567, 23.4385);
        Coordinates secondPair = new Coordinates(14.5567, 23.4385);
        assertEquals(true, firstPair.equals(secondPair));
    }
}