package by.roodxx.district.instagram.media;

import by.roodxx.district.core.data.Media;
import by.roodxx.district.core.data.MediaType;
import by.roodxx.district.core.data.Owner;
import by.roodxx.district.core.data.location.Location;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by roodxx on 9.8.16.
 */
class PlaceMediaDeserializer extends JsonDeserializer<PlaceMediaInfo> {

    private static final String INSTAGRAM_MEDIA_URL_PREFIX = "instagram.com/p/";

    @Override
    public PlaceMediaInfo deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        Collection<Media> medias = new ArrayList<>();
        ObjectCodec oc = jp.getCodec();
        JsonNode mainNode = oc.readTree(jp).get("location");

        Location location = getLocation(mainNode);

        JsonNode mediaNode = mainNode.get("media");

        boolean hasNext = isHasNext(mediaNode);

        JsonNode dataNode = mediaNode.get("nodes");
        Iterator<JsonNode> mediaIterator = dataNode.getElements();
        while (mediaIterator.hasNext()) {
            JsonNode jsonMedia = mediaIterator.next();
            Media media = new Media(jsonMedia.get("thumbnail_src").asText(), location);
            setId(jsonMedia, media);
            setMediaUrl(jsonMedia, media);
            setDescription(jsonMedia, media);
            setMediaType(jsonMedia, media);
            setOwner(jsonMedia, media);
            media.setTimestamp(jsonMedia.get("date").asLong());
            medias.add(media);
        }
        return new PlaceMediaInfo(medias, hasNext);
    }

    private void setOwner(JsonNode jsonMedia, Media media) {
        JsonNode ownerNode = jsonMedia.get("owner");
        Owner owner = new Owner(ownerNode.get("id").asText(), "instagram");
        media.setOwner(owner);
    }

    private void setMediaType(JsonNode jsonMedia, Media media) {
        if (jsonMedia.get("is_video").asBoolean()) {
            media.setMediaType(MediaType.VIDEO);
        } else {
            media.setMediaType(MediaType.PICTURE);
        }
    }

    private void setDescription(JsonNode jsonMedia, Media media) {
        if (jsonMedia.has("caption")) {
            String caption = jsonMedia.get("caption").asText();
            media.setDescription(caption);
        }
    }

    private void setId(JsonNode jsonMedia, Media media) {
        media.setId(jsonMedia.get("id").asText());
    }

    private void setMediaUrl(JsonNode jsonMedia, Media media) {
        media.setMediaUrl(INSTAGRAM_MEDIA_URL_PREFIX+jsonMedia.get("code").asText());
    }

    private boolean isHasNext(JsonNode node) {
        JsonNode pageInfoNode = node.get("page_info");
        return pageInfoNode.get("has_next_page").asBoolean();
    }

    private Location getLocation(JsonNode node) {
        return new Location(
                node.get("lat").getNumberValue().toString(),
                node.get("lng").getNumberValue().toString());
    }
}
