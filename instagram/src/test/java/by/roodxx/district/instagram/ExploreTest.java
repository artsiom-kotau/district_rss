package by.roodxx.district.instagram;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.instagram.location.LocationService;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

public class ExploreTest {

    @Test
    public void testExplore() throws IOException {

    }

    @Test
    public void testGetPlaces() {
        LocationService locationService = new LocationService();
        locationService.getPlaces(new Location("52.374284","31.028692"));
    }

    @Test
    public void testObjectMapper() throws IOException {
        String inputSource = "{\"meta\": {\"code\": 200}, \"data\": [{\"latitude\": 52.390576346667, \"id\": \"266878632\", \"longitude\": 31.029956036667, \"name\": \"name1\"}, {\"latitude\": 52.41344, \"id\": \"944653585625206\", \"longitude\": 31.04575, \"name\": \"name2\"}]}";
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("place", new org.codehaus.jackson.Version(0,0,1,""));
        module.addDeserializer(List.class, new PlaceDeserializer());
        mapper.registerModule(module);
        Map responseMap = mapper.readValue(inputSource, new TypeReference<List<Place>>() {});
        assertNotNull(responseMap);
    }

    private static class PlaceDeserializer extends JsonDeserializer<List<Place>> {

        @Override
        public List<Place> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            List<Place> places = new ArrayList<>();
            ObjectCodec oc = jp.getCodec();
            JsonNode node = oc.readTree(jp);
            ArrayNode dataNode = (ArrayNode)node.get("data");
            Iterator<JsonNode> placeIterator = dataNode.getElements();
            while (placeIterator.hasNext()) {
                JsonNode jsonPlace = placeIterator.next();
                places.add(new Place(jsonPlace.get("id").getTextValue(), jsonPlace.get("name").getTextValue(), new Location(jsonPlace.get("latitude").getNumberValue().toString(), jsonPlace.get("longitude").getNumberValue().toString())));
            }
            return places;
        }
    }
}
