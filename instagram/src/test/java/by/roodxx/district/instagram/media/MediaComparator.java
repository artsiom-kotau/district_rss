package by.roodxx.district.instagram.media;

import by.roodxx.district.core.data.Media;

import java.util.Comparator;

/**
 * Created by roodxx on 26.8.16.
 */
class MediaComparator implements Comparator<Media>{

    @Override
    public int compare(Media o1, Media o2) {
        int result = compareTwoObjects(o1.getId(), o2.getId());
        result+=compareTwoObjects(o1.getDescription(), o2.getDescription());
        result+=compareTwoObjects(o1.getTimestamp(), o2.getTimestamp());
        result+=compareTwoObjects(o1.getMediaSource(), o2.getMediaSource());
        result+=compareTwoObjects(o1.getMediaUrl(), o2.getMediaUrl());
        result+=compareTwoObjects(o1.getMediaType(), o2.getMediaType());
        result+=compareTwoObjects(o1.getOwner(), o2.getOwner());
        result+=compareTwoObjects(o1.getLocation(), o2.getLocation());
        return result;
    }

    private int compareTwoObjects(Object o1, Object o2) {
        if (o1 == o2) {
            return 0;
        }

        if (o1 == null && o2 != null || o1 != null && o2 == null) {
            return 1;
        }

        return o1.equals(o2) ? 0 : 1;
    }
}
