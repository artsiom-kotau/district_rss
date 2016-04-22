package by.roodxx.district.core.auth;

public interface AuthenticationService {

    AuthInfo login(BaseCredentials credentials);

    void logout(AuthInfo authInfo);
}
