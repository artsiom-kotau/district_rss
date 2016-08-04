package by.roodxx.district.instagram.data;

import by.roodxx.district.core.data.location.Location;

/**
 * Created by roodxx on 3.8.16.
 */
public class InstagramRequestFactory {

    private static final String TOCKEN="1601705272.03fd617.8f4bbaf98917433fa88fbc81d9a1ecd4";

    private final static String GET_PLACES_TEMPLATE = "https://api.instagram.com/v1/locations/search?lat=%s&lng=%s&access_token="+TOCKEN;

    public static GetRequest requestForLocation(Location location) {
        String request = String.format(GET_PLACES_TEMPLATE, location.getLatitude(), location.getLongitude());
        return new GetRequest(request);
    }
}
