package by.roodxx.district.instagram.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roodxx on 3.8.16.
 */
public abstract class Request implements HeaderAware{

    private final String request;

    private Map<String, String> headers;

    public Request(String request) {
        this.request = request;
        this.headers = new HashMap<>();

    }

    public String getRequest() {
        return request;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }
}
