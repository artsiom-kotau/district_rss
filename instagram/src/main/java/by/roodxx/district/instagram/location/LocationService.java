package by.roodxx.district.instagram.location;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.instagram.data.HttpDataFetcher;
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

    private final HttpDataFetcher dataFetcher;

    private final ObjectMapper mapper;

    public LocationService(HttpDataFetcher dataFetcher) {

        this.dataFetcher = dataFetcher;

        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("place", new org.codehaus.jackson.Version(0,0,1,""));
        module.addDeserializer(List.class, new PlaceDeserializer());
        mapper.registerModule(module);
    }

    public Collection<Place> getPlaces(Location location) {
        try {
            String data = dataFetcher.fetchByGet(String.format(GET_PLACES_TEMPLATE, location.getLatitude(), location.getLongitude()));
            return mapper.readValue(data, new TypeReference<List<Place>>() {});
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

}
