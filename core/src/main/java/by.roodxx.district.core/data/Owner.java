package by.roodxx.district.core.data;

public class Owner {

    private final String userId;

    //social net where user has registered
    private final String source;

    private String urlToProfile;

    public Owner(String userId, String source) {
        this.userId = userId;
        this.source = source;
    }

    public String getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public String getUrlToProfile() {
        return urlToProfile;
    }

    public void setUrlToProfile(String urlToProfile) {
        this.urlToProfile = urlToProfile;
    }
}
