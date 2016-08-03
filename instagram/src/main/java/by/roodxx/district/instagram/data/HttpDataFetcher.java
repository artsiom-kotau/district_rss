package by.roodxx.district.instagram.data;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Map;

/**
 * Created by roodxx on 3.8.16.
 */
public class HttpDataFetcher {

    public Response fetchByGet(GetRequest request) {
        try (CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet get = new HttpGet(request.getRequest());
            for(Map.Entry<String, String> header : request.getHeaders().entrySet()) {
                get.addHeader(header.getKey(), header.getValue());
            }
            try (CloseableHttpResponse responseStream = client.execute(get)) {
                HttpEntity httpEntity = responseStream.getEntity();
                Response response = new Response(IOUtils.toString(httpEntity.getContent()));
                response.setCode(responseStream.getStatusLine().getStatusCode());
                addHeaders(response, responseStream.getAllHeaders());
                return response;
            }
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    private void addHeaders(HeaderAware target, Header[] headers) {
        for(Header header : headers) {
            target.addHeader(header.getName(), header.getValue());
        }
    }
}
