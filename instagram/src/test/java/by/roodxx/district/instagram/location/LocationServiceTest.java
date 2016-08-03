package by.roodxx.district.instagram.location;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.instagram.data.GetRequest;
import by.roodxx.district.instagram.data.HttpDataFetcher;
import by.roodxx.district.instagram.data.Response;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by roodxx on 3.8.16.
 */
public class LocationServiceTest {

    @Test
    public void testCorrectRequest() throws IOException {
        try(InputStream data = LocationServiceTest.class.getClassLoader().getResourceAsStream("data/location/200_not_empty_response.json")) {
            String testData = IOUtils.toString(data);
            HttpDataFetcher dataFetcher = mock(HttpDataFetcher.class);
            when(dataFetcher.fetchByGet(any(GetRequest.class))).thenReturn(new Response(HttpStatus.SC_OK, testData));
            LocationService locationService = new LocationService(dataFetcher);
            Collection<Place> places = locationService.getPlaces(new Location("52.374284","31.028692"));
            assertNotNull(places);
            assertEquals(4, places.size());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInCorrectRequest() throws IOException {
        try(InputStream data = LocationServiceTest.class.getClassLoader().getResourceAsStream("data/location/400_error_response.json")) {
            String testData = IOUtils.toString(data);
            HttpDataFetcher dataFetcher = mock(HttpDataFetcher.class);
            when(dataFetcher.fetchByGet(any(GetRequest.class))).thenReturn(new Response(HttpStatus.SC_OK, testData));
            LocationService locationService = new LocationService(dataFetcher);
            locationService.getPlaces(new Location("52.374284","31.028692"));
        }
    }
}
