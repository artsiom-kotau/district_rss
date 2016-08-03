package by.roodxx.district.instagram.location;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by roodxx on 2.8.16.
 */
public class LocationService {

    private final static String GET_PLACES_TEMPLATE = "https://api.instagram.com/v1/locations/search?lat=%s&lng=%s&access_token=1601705272.03fd617.8f4bbaf98917433fa88fbc81d9a1ecd4&distance=50";

    private ObjectMapper mapper;

    public LocationService() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("place", new org.codehaus.jackson.Version(0,0,1,""));
        module.addDeserializer(List.class, new PlaceDeserializer());
        mapper.registerModule(module);
    }

    public Collection<Place> getPlaces(Location location) {
        try {
            String data = getDataStream(String.format(GET_PLACES_TEMPLATE, location.getLatitude(), location.getLongitude()));
            return mapper.readValue(data, new TypeReference<List<Place>>() {});
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    String getDataStream(String request) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet get = new HttpGet(request);
            try (CloseableHttpResponse response = client.execute(get)) {
                HttpEntity httpEntity = response.getEntity();
                return IOUtils.toString(httpEntity.getContent());
            }
        }
    }

}
