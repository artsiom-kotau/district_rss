package by.roodxx.district.instagram.media;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.core.stream.MediaCursor;
import by.roodxx.district.instagram.data.GetRequest;
import by.roodxx.district.instagram.data.HttpDataFetcher;
import by.roodxx.district.instagram.data.Response;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by roodxx on 4.8.16.
 */
public class MediaServiceTest {

    @Test
    public void testSinglePlace() throws IOException {
        String data = IOUtils.toString(MediaServiceTest.class.getClassLoader().getResourceAsStream("data/media/location_266878632_response_part1.json"));
        HttpDataFetcher dataFetcher = mock(HttpDataFetcher.class);
        when(dataFetcher.fetchByGet(any(GetRequest.class))).thenReturn(new Response(HttpStatus.SC_OK, data));

        Place place = new Place("266878632", "Новобелица Гомель", new Location("52.390576346667", "31.029956036667"));

        InstagramMediaService mediaService = new InstagramMediaService(dataFetcher);
        MediaCursor mediaCursor = mediaService.load(Collections.singleton(place));
        assertNotNull(mediaCursor);
        assertTrue(mediaCursor.hasNext());
    }
}
