package ro.uaic.info.data_mining.aggregation.utils;

import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.sun.istack.internal.NotNull;
import ro.uaic.info.data_mining.aggregation.exceptions.LocationGeocodeException;

import java.util.Objects;

/**
 * TODO
 */
public class Location {
    private Coordinates coordinates;

    public Location(@NotNull Coordinates coordinates) {
        this.coordinates = coordinates;
    }

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
