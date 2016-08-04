package by.roodxx.district.instagram.location;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.instagram.data.HttpDataFetcher;
import by.roodxx.district.instagram.data.InstagramRequestFactory;
import by.roodxx.district.instagram.data.Response;
import org.apache.http.HttpStatus;
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

    private final HttpDataFetcher dataFetcher;

    private final ObjectMapper mapper;

    public LocationService(HttpDataFetcher dataFetcher) {

        this.dataFetcher = dataFetcher;

        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("place", new org.codehaus.jackson.Version(0,0,1,""));
        module.addDeserializer(List.class, new PlaceDeserializer());
        module.addDeserializer(InstagramResponseMeta.class, new MetaDeserializer());
        mapper.registerModule(module);
    }

    public Collection<Place> getPlaces(Location location) {
        try {
            Response response = dataFetcher.fetchByGet(InstagramRequestFactory.requestForLocation(location));
            if (response.getCode() != HttpStatus.SC_OK) {
                throw new IllegalArgumentException(response.getContent());
            }

            InstagramResponseMeta meta = mapper.readValue(response.getContent(), InstagramResponseMeta.class);
            if (meta.getCode() != 200) {
                throw new IllegalArgumentException(meta.getContent());
            }
            return mapper.readValue(response.getContent(), new TypeReference<List<Place>>() {});
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

}
