package ro.uaic.info.data_mining.aggregation.utils;

import com.google.maps.GeoApiContext;

/**
 * This class is used to set the context of the google maps api.
 * It is just a convenience class, to avoid creating a
 * <code>{@link GeoApiContext}</code> each time we need to use it.
 */
public class GeoLocationContext {
    private static GeoApiContext context;

    static {
        context = new GeoApiContext().setApiKey("AIzaSyB_WGfQ7zRQcbAdHgoBGEmZH7VbUVR6P5I");
    }

    public static GeoApiContext getContext() {
        return context;
    }
}
