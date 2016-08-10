package by.roodxx.district.instagram.media;

import by.roodxx.district.core.data.Media;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.core.stream.MediaCursor;
import by.roodxx.district.instagram.data.GetRequest;
import by.roodxx.district.instagram.data.HttpDataFetcher;
import by.roodxx.district.instagram.data.InstagramRequestFactory;
import by.roodxx.district.instagram.data.Response;
import org.apache.http.HttpStatus;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import java.io.IOException;
import java.util.*;

/**
 * Created by roodxx on 9.8.16.
 */
public class InstagramMediaCursor implements MediaCursor<Media> {

    static final int DEFAULT_FETCH_SIZE = 12;

    private final HttpDataFetcher dataFetcher;

    private Map<Place,String> lastFetchedIdForPlace;

    private Set<Place> placesWithNextPage;

    private final ObjectMapper mapper;

    public InstagramMediaCursor(HttpDataFetcher dataFetcher, Collection<Place> places) {
        this.dataFetcher = dataFetcher;
        this.lastFetchedIdForPlace = new HashMap<>();
        this.placesWithNextPage = new HashSet<>(places);

        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("media", new org.codehaus.jackson.Version(0,0,1,""));
        module.addDeserializer(PlaceMediaInfo.class, new PlaceMediaDeserializer());
        mapper.registerModule(module);
    }

    @Override
    public int getFetchSize() {
        return DEFAULT_FETCH_SIZE;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public boolean hasNext() {
        return !placesWithNextPage.isEmpty();
    }

    @Override
    public Collection<Media> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Map<Place, Iterator<Media>> fetchedMedia = new HashMap<>();
        Set<Place> finishedPlaces = new HashSet<>();
        for(Place place : placesWithNextPage) {
            GetRequest mediaRequest = createGetRequestForMedia(place);
            Response response = dataFetcher.fetchByGet(mediaRequest);
            if (response.getCode() != HttpStatus.SC_OK) {
                throw new IllegalArgumentException(response.getContent());
            }

            try {
                PlaceMediaInfo mediaInfo = mapper.readValue(response.getContent(), PlaceMediaInfo.class);
                if (!mediaInfo.hasNext()) {
                    finishedPlaces.add(place);
                }
                fetchedMedia.put(place, mediaInfo.getMedias().iterator());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private GetRequest createGetRequestForMedia(Place place) {
        GetRequest mediaRequest;
        if (lastFetchedIdForPlace.containsKey(place)) {
            mediaRequest = InstagramRequestFactory.requestMediaForPlace(place, lastFetchedIdForPlace.get(place));
        } else {
            mediaRequest = InstagramRequestFactory.requestMediaForPlace(place, lastFetchedIdForPlace.get(place));
        }
        return mediaRequest;
    }
}
