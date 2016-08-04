package by.roodxx.district.core.stream;

import java.util.Collection;
import java.util.Iterator;

public interface MediaCursor<T>  extends Iterator<Collection<T>>, AutoCloseable {

    int getFetchSize();
}