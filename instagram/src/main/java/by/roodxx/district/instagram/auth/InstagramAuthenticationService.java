package by.roodxx.district.instagram.auth;

import by.roodxx.district.core.auth.AuthenticationService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

public class InstagramAuthenticationService implements AuthenticationService<InstagramCredentials, InstagramAuthInfo> {

    @Override
    public InstagramAuthInfo login(InstagramCredentials credentials) {
        try {
            String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";
            String url = "https://www.instagram.com/oauth/authorize/" +
                    "?client_id=03fd617fdee647daad83d78300899dda&redirect_uri=http://localhost&response_type=code";
            Map<String,String> cookies = new HashMap<>();
            Connection.Response loginFormFirstStep = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .userAgent(userAgent)
                    .execute();
            cookies.putAll(loginFormFirstStep.cookies());

            Document loginFormSecondStep = Jsoup.connect(loginFormFirstStep.url().toString())
                    .method(Connection.Method.GET)
                    .cookies(loginFormFirstStep.cookies())
                    .userAgent(userAgent)
                    .get();
            Element hiddenElement = loginFormSecondStep.body().getElementById("login-form").getElementsByAttributeValue("type","hidden").get(0);
            Connection.Response loginFormFirstStepConn = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .userAgent(userAgent)
                    .execute();
            cookies.putAll(loginFormFirstStepConn.cookies());

            Connection.Response loginResponse = Jsoup.connect(loginFormFirstStep.url().toString())
                    .data("username", "akotau")
                    .data("password", "RB6UVJbCku84rKSv")
                    .data("csrfmiddlewaretoken", hiddenElement.val())
                    .cookies(cookies)
                    .userAgent(userAgent)
                    .method(Connection.Method.POST).execute();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void logout(InstagramAuthInfo authInfo) {

    }
}
