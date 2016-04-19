package by.roodxx.district.core.auth;

public class AuthInfo {

    private final String token;

    public AuthInfo(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
