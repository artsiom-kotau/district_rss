package by.roodxx.district.core;

import by.roodxx.district.core.auth.AuthenticationService;
import by.roodxx.district.core.stream.ConnectionService;

public interface MediaSourceDescriptor<T> {

    Resource getMediaSourceDescriptor();

    AuthenticationService getAuthenticationService();

    ConnectionService<T> getConnectionService();
}
