package by.roodxx.district.instagram;

import by.roodxx.district.core.data.location.Location;
import by.roodxx.district.core.data.location.Place;
import by.roodxx.district.instagram.data.HttpDataFetcher;
import by.roodxx.district.instagram.location.LocationService;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ExploreTest {

    @Test
    public void testRealLocationRequest() {

        LocationService locationService = new LocationService(new HttpDataFetcher());
        Collection<Place> places = locationService.getPlaces(new Location("52.373057", "29.031638"));
        assertNotNull(places);
        assertEquals(20, places.size());
    }

}
