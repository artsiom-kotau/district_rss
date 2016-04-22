package by.roodxx.district.core.stream;

public interface ConnectionService<T> extends AutoCloseable{

    DataStream<T> openStream();
}
