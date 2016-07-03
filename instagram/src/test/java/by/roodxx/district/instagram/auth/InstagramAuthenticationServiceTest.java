package by.roodxx.district.instagram.auth;

import org.junit.Test;

public class InstagramAuthenticationServiceTest {

    @Test
    public void testLogin() {
        InstagramAuthenticationService authenticationService = new InstagramAuthenticationService();
        authenticationService.login(new InstagramCredentials("03fd617fdee647daad83d78300899dda",
                "12f3d06ce64d4140a308963969806f1c", "http://localhost"));
    }
}
