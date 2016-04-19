package by.roodxx.district.core.auth;

public interface Authentication {

    AuthInfo login(BaseCredentials credentials);

    void logout(AuthInfo authInfo);
}
