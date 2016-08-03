package by.roodxx.district.instagram.location;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by roodxx on 3.8.16.
 */
class PlaceDeserializer extends JsonDeserializer<List<Place>> {

    @Override
    public List<Place> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
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
