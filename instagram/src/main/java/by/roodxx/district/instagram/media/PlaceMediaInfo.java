package by.roodxx.district.instagram.media;

import by.roodxx.district.core.data.Media;

import java.util.Collection;

/**
 * Created by roodxx on 10.8.16.
 */
class PlaceMediaInfo {

    private final Collection<Media> medias;

    private final boolean hasNext;

    public PlaceMediaInfo(Collection<Media> medias, boolean hasNext) {
        this.medias = medias;
        this.hasNext = hasNext;
    }

    public Collection<Media> getMedias() {
        return medias;
    }

    public boolean hasNext() {
        return hasNext;
    }
}
