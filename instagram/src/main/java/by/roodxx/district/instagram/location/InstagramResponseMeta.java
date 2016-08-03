package by.roodxx.district.instagram.location;

/**
 * Created by roodxx on 3.8.16.
 */
class InstagramResponseMeta {

    private final int code;

    private String content;

    public InstagramResponseMeta(int code) {
        this.code = code;
    }

    public InstagramResponseMeta(int code, String content) {
        this(code);
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
