package by.roodxx.district.instagram.location;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * Created by roodxx on 3.8.16.
 */
class MetaDeserializer extends JsonDeserializer<InstagramResponseMeta> {

    @Override
    public InstagramResponseMeta deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode metaNode = oc.readTree(jp).get("meta");
        int code = metaNode.get("code").asInt();
        InstagramResponseMeta meta = new InstagramResponseMeta(code);
        if (metaNode.has("error_message")) {
            meta.setContent(metaNode.get("error_message").asText());
        }
        return meta;
    }
}
