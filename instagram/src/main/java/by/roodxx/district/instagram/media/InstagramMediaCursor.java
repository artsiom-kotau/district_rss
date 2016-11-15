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

    private Collection<Media> cache;

    public InstagramMediaCursor(HttpDataFetcher dataFetcher, Collection<Place> places) {
        this.dataFetcher = dataFetcher;
        this.lastFetchedIdForPlace = new HashMap<>();
        this.placesWithNextPage = new HashSet<>(places);
        this.cache = new ArrayList<>(0);

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
        if (cache.isEmpty()) {
            cache = fetchMedias();
        }
        return !cache.isEmpty();
    }

    @Override
    public Collection<Media> next() {
        if (cache.isEmpty()) {
            throw new NoSuchElementException();
        }
        Collection<Media> media = new ArrayList<>(cache);
        cache.clear();
        return media;
    }

    private Map<Place, Iterator<Media>> getMediaByPlace() {
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
        placesWithNextPage.removeAll(finishedPlaces);
        return fetchedMedia;
    }

    private Collection<Media> fetchMedias() {

        //todo replace with Stack
        Map<Place, Iterator<Media>> fetchedMedia = getMediaByPlace();

        int count = getFetchSize();
        Collection<Media> mediaFeed = new ArrayList<>(count);
        Map<Place, Media> topMedias = new HashMap<>();
        while (count > 0) {
            Iterator<Map.Entry<Place, Iterator<Media>>> mediaByPlaceEntry = fetchedMedia.entrySet().iterator();

            while (mediaByPlaceEntry.hasNext()) {
                Map.Entry<Place, Iterator<Media>> mediaEntry = mediaByPlaceEntry.next();
                Place place = mediaEntry.getKey();
                if (!topMedias.containsKey(place)) {
                    Iterator<Media> mediaIterator = mediaEntry.getValue();
                    //todo remove if doesn't have next
                    if (mediaIterator.hasNext()) {
                        topMedias.put(place, mediaIterator.next());
                    } else {
                        mediaByPlaceEntry.remove();
                    }
                }
            }

            Place placeForMaxMedia = null;
            Media maxMedia = null;
            for(Map.Entry<Place, Media> mediaEntry : topMedias.entrySet()) {
                Place place = mediaEntry.getKey();
                Media media = mediaEntry.getValue();
                if ( placeForMaxMedia == null || maxMedia.getTimestamp() < media.getTimestamp()){
                    placeForMaxMedia = place;
                    maxMedia = mediaEntry.getValue();
                }
            }
            mediaFeed.add(maxMedia);
            topMedias.remove(placeForMaxMedia);
            count--;
        }
        return mediaFeed;
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
