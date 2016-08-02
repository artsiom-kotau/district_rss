package by.roodxx.district.core.stream;


import by.roodxx.district.core.data.location.Location;

public interface DataStream<T> extends AutoCloseable{

    DataSet<T> load(Location location);
}
