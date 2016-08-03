package by.roodxx.district.instagram.location;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.instagram.data.HttpDataFetcher;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by roodxx on 3.8.16.
 */
public class LocationServiceTest {

    @Test
    public void testCorrectResponse() throws IOException {
        try(InputStream data = LocationServiceTest.class.getClassLoader().getResourceAsStream("data/location/200_not_empty_response.json")) {
            String testData = IOUtils.toString(data);
            HttpDataFetcher dataFetcher = mock(HttpDataFetcher.class);
            when(dataFetcher.fetchByGet(anyString())).thenReturn(testData);
            LocationService locationService = new LocationService(dataFetcher);
            Collection<Place> places = locationService.getPlaces(new Location("52.374284","31.028692"));
            assertNotNull(places);
            assertEquals(4, places.size());
        }

    }
}
