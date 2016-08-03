package by.roodxx.district.instagram.data;

/**
 * Created by roodxx on 3.8.16.
 */
public class PostRequest extends Request{

    private String body;

    public PostRequest(String request) {
        super(request);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
