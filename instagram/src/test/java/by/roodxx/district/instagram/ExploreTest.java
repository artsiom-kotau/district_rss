package by.roodxx.district.instagram;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExploreTest {

    @Test
    public void testExplore() throws IOException {
        CookieStore httpCookieStore = new BasicCookieStore();
        HttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore).build();
        HttpGet httpGet  = new HttpGet("https://www.instagram.com/explore/locations/266878632/");
        HttpResponse getResponse = httpClient.execute(httpGet);
        String domain = httpCookieStore.getCookies().get(0).getDomain();
        String path = httpCookieStore.getCookies().get(0).getPath();
        Date expireDate = httpCookieStore.getCookies().get(0).getExpiryDate();

        BasicClientCookie igPr = new BasicClientCookie("ig_pr","1");
        igPr.setDomain(domain);
        igPr.setPath(path);
        igPr.setExpiryDate(expireDate);

        BasicClientCookie igVw = new BasicClientCookie("ig_vw","1366");
        igVw.setDomain(domain);
        igVw.setPath(path);
        igVw.setExpiryDate(expireDate);

        httpCookieStore.addCookie(igVw);
        httpCookieStore.addCookie(igPr);

        for(Cookie cookie : httpCookieStore.getCookies()) {
            System.out.println(cookie);
        }

        HttpPost httpPost = new HttpPost("https://www.instagram.com/query/");

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("q", "ig_location(266878632) { media.after(1273474044620660776, 12) {   count,   nodes {     caption,     code,     comments {       count     },     date,     dimensions {       height,       width     },     display_src,     id,     is_video,     likes {       count     },     owner {       id     },     thumbnail_src,     video_views   },   page_info }  }"));
        urlParameters.add(new BasicNameValuePair("ref", "locations::show"));


        httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
        httpPost.setHeader("x-csrftoken","EoUbt91TMqUKQCjvxuRyhSuxuX8Jzpoq");
        httpPost.setHeader("x-instagram-ajax","1");

        HttpResponse response = httpClient.execute(httpPost);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
    }
}
