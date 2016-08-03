package by.roodxx.district.instagram.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roodxx on 3.8.16.
 */
public class Response implements HeaderAware {

    private int code;

    private final String content;

    private Map<String, String> headers;

    public Response(String content) {
        this(0, content);
    }

    public Response(int code, String content) {
        this.code = code;
        this.content = content;
        this.headers = new HashMap<>();
    }


    public String getContent() {
        return content;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
