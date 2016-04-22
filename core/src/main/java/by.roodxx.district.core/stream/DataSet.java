package by.roodxx.district.core.stream;

import java.util.Collection;

public interface DataSet<T> {

    Collection<T> fetchNext();
}
