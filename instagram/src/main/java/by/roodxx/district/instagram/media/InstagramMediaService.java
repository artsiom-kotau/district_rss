package by.roodxx.district.instagram.media;

import by.roodxx.district.core.data.Media;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.core.stream.DataStream;
import by.roodxx.district.core.stream.MediaCursor;
import by.roodxx.district.instagram.data.HttpDataFetcher;

import java.util.Collection;

/**
 * Created by roodxx on 4.8.16.
 */
public class InstagramMediaService implements DataStream<Media> {

    private final HttpDataFetcher dataFetcher;

    public InstagramMediaService(HttpDataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    @Override
    public MediaCursor<Media> load(Collection<Place> places) {

        return null;
    }
}
