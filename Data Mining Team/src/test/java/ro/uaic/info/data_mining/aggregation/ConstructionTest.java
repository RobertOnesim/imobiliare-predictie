package ro.uaic.info.data_mining.aggregation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Suhani on 27\04\16.
 */
public class ConstructionTest {

    @Test
    public void testGetParameter_Zone() throws Exception {
        Construction testConstruction = new Construction();
        testConstruction.setPrice(10000);
        testConstruction.setZone("Copou");
        assertEquals("Copou", testConstruction.getParameter(Construction.Parameter.Zone));
    }

    @Test
    public void testGetParameter_Price() throws Exception {
        Construction testConstruction = new Construction();
        testConstruction.setPrice(10000);
        testConstruction.setZone("Copou");
        assertEquals(10000.0, testConstruction.getParameter(Construction.Parameter.Price));
    }

    @Test
    public void testSetZone() throws Exception {
        Construction testConstruction = new Construction();
        testConstruction.setZone("Copou");
        assertEquals("Copou", testConstruction.getParameter(Construction.Parameter.Zone));
    }

    @Test
    public void testSetPrice() throws Exception {
        Construction testConstruction = new Construction();
        testConstruction.setPrice(10000);
        assertEquals(10000.0, testConstruction.getParameter(Construction.Parameter.Price));
    }
}