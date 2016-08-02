package by.roodxx.district.instagram.location;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by roodxx on 2.8.16.
 */
public class LocationService {

    private final static String GET_PLACES_TEMPLATE = "https://api.instagram.com/v1/locations/search?lat=%s&lng=%s&access_token=1601705272.03fd617.8f4bbaf98917433fa88fbc81d9a1ecd4&distance=50";

    private ObjectMapper mapper = new ObjectMapper();

    public Collection<Place> getPlaces(Location location) {
        Collection<Place> places = new ArrayList<>();
        try (CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet get = new HttpGet(String.format(GET_PLACES_TEMPLATE, location.getLatitude(), location.getLongitude()));
            try (CloseableHttpResponse response = client.execute(get)) {
                HttpEntity httpEntity = response.getEntity();
                Map responseMap = mapper.readValue(httpEntity.getContent(), Map.class);
            }

        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        return places;
    }
}
