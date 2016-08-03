package by.roodxx.district.instagram.data;

import java.util.Map;

/**
 * Created by roodxx on 3.8.16.
 */
public interface HeaderAware {

    Map<String, String> getHeaders();

    void addHeader(String name, String value);
}
