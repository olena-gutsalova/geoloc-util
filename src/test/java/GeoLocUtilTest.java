import org.openweather.GeoLocUtil;
import org.openweather.Location;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class GeoLocUtilTest {
    private final String API_KEY = System.getenv("OW_API_KEY");

    @Test
    public void testMultipleValidLocations() throws IllegalArgumentException {
        List<Location> locations = new GeoLocUtil(API_KEY).getCoordinates(new String[]{"Los Angeles, CA", "90210", "Palo Alto, CA"});
        for (Location location : locations) {
            Assert.assertTrue(!location.getLat().isBlank() && !location.getLat().isEmpty(), "Expected latitude");
            Assert.assertTrue(!location.getLon().isBlank() && !location.getLon().isEmpty(), "Expected longitude");
            Assert.assertTrue(!location.getName().isBlank() && !location.getName().isEmpty(), "Expected name");
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidAPIKey() throws IllegalArgumentException {
        String invalidApiKey = "invalid_key"; // Simulating API connection failure
        new GeoLocUtil(invalidApiKey).getCoordinates(new String[]{"Chicago, IL"});
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMixedValidAndInvalidLocations() throws IllegalArgumentException {
        new GeoLocUtil(API_KEY).getCoordinates(new String[]{"Palo Alto, CA", "", "Miami, FL"});
    }
}
