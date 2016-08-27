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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;

        Owner owner = (Owner) o;

        if (userId != null ? !userId.equals(owner.userId) : owner.userId != null) return false;
        return source != null ? source.equals(owner.source) : owner.source == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"Owner\":{"
                + "\"userId\":\"" + userId + "\""
                + ", \"source\":\"" + source + "\""
                + ", \"urlToProfile\":\"" + urlToProfile + "\""
                + "}}";
    }
}
