package by.roodxx.district.core.auth;

public interface AuthenticationService<K extends BaseCredentials, V extends AuthInfo> {

    V login(K credentials);

    void logout(V authInfo);
}
