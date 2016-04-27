package ro.uaic.info.data_mining.aggregation;

import org.junit.Test;
import ro.uaic.info.data_mining.aggregation.utils.Location;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Suhani on 27\04\16.
 */
public class ConstructionLocationDecoratorTest {

    @Test
    public void testGetConstruction() throws Exception {
        Construction bufferConstruction = new Construction();
        bufferConstruction.setPrice(10000);
        bufferConstruction.setZone("Copou");
        ConstructionLocationDecorator testDecorator = new ConstructionLocationDecorator(bufferConstruction);
        assertThat(bufferConstruction, equalTo(testDecorator.getConstruction()));
    }

    @Test
    public void testGetLocation() throws Exception {
        Construction bufferConstruction = new Construction();
        Location testLocation = Location.fromString("Copou Iasi");
        bufferConstruction.setPrice(4758);
        bufferConstruction.setZone("Copou Iasi");
        ConstructionLocationDecorator testDecorator = new ConstructionLocationDecorator(bufferConstruction);
        assertThat(testDecorator.getLocation(), equalTo(testLocation));
    }
}