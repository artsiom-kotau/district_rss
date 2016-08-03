package by.roodxx.district.instagram;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.instagram.location.LocationService;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static junit.framework.TestCase.assertNotNull;

public class ExploreTest {

    @Test
    public void testExplore() throws IOException {

    }

    @Test
    public void testGetPlaces() {
        LocationService locationService = new LocationService();
        Collection<Place> places = locationService.getPlaces(new Location("52.374284","31.028692"));
        assertNotNull(places);
    }
}
