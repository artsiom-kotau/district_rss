package by.roodxx.district.instagram.data;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by roodxx on 3.8.16.
 */
public class HttpDataFetcher {

    public String fetchByGet(String request) {
        try (CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet get = new HttpGet(request);
            try (CloseableHttpResponse response = client.execute(get)) {
                HttpEntity httpEntity = response.getEntity();
                return IOUtils.toString(httpEntity.getContent());
            }
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
