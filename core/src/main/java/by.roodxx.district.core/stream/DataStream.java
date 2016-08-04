package by.roodxx.district.core.stream;


import by.roodxx.district.core.data.location.Place;

import java.util.Collection;

public interface DataStream<T> {

    MediaCursor<T> load(Collection<Place> places);
}
