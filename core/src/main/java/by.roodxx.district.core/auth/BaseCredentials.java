package by.roodxx.district.core.auth;

public class BaseCredentials {

    private final String login;

    private final String password;

    public BaseCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
