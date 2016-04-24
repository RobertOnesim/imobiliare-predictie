package ro.uaic.info.data_mining.aggregation.utils;

import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.sun.istack.internal.NotNull;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;

import java.util.Objects;

/**
 * This class is used to build and store, based on a real (string)
 * address, the coordinates on the Earth's surface corresponding to that
 * address, obtained via a geo-location API.
 */
public class Location {
    private Coordinates coordinates;

    public Location(@NotNull Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Static factory method used to build a {@link Location} via it's corresponding,
     * plain-text given address
     *
     * @param locationString The address of the location for which to find the corresponding {@link Coordinates}
     *
     * @return The {@link Location} corresponding to the given, plain-text, address, by applying geo-location algorithms
     * via some geo-location API.
     *
     * @throws LocationGeocodeException In case an error occurs while trying to geo-locate the given locationString
     */
    public static Location fromString(String locationString) throws LocationGeocodeException {
        GeocodingApiRequest geocodeRequest = GeocodingApi.geocode(GeoLocationContext.getContext(), locationString);
        GeocodingResult[] geocodingResults;

        try {
            geocodingResults = geocodeRequest.await();
        } catch (Exception e) {
            throw new LocationGeocodeException(LocationGeocodeException.GEOCODE_ERROR, e.getStackTrace());
        }

        if (geocodingResults.length < 1) {
            throw new LocationGeocodeException(LocationGeocodeException.NOT_FOUND);
        }

        Coordinates coordinates = new Coordinates(
                geocodingResults[0].geometry.location.lat, geocodingResults[0].geometry.location.lng);

        return new Location(coordinates);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(coordinates, location.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return "Location{" +
                "coordinates=" + coordinates +
                '}';
    }
}
