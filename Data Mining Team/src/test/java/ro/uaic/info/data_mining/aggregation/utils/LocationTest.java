package ro.uaic.info.data_mining.aggregation.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LocationTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGeolocation_Invalid_FromString_ThrowingException() throws Exception {
        exception.expect(LocationGeocodeException.class);
        exception.expectMessage(LocationGeocodeException.NOT_FOUND);

        Location.fromString("Ias2937gjas1u7jnsad2kbk232i");
    }

    @Test
    public void testGeolocation_FromString_ReturningCorrectValues() throws Exception {
        Location location = Location.fromString("Iasi");
        System.out.println("location = " + location);

        assertThat(location.getCoordinates().getLatitude(), is(equalTo(47.1584549)));
        assertThat(location.getCoordinates().getLongitude(), is(equalTo(27.6014418)));
    }

}