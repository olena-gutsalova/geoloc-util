import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.openweather.Location;
import org.openweather.OpenWeatherClient;
import org.testng.annotations.Test;

public class OpenWeatherClientTest {
    private final String API_KEY = System.getenv("OW_API_KEY");

    @Test
    public void testValidLocationName() {
        String validLocationName = "San Francisco, CA";
        Location location = new OpenWeatherClient(API_KEY).getCoordinatesByLocationName(validLocationName);

        Assert.assertEquals(location.getLat(), "37.7790262", "Expected latitude for " + validLocationName);
        Assert.assertEquals(location.getLon(), "-122.419906", "Expected longitude for " + validLocationName);
        Assert.assertEquals(location.getName(), "San Francisco", "Location name mismatch");
        Assert.assertEquals(location.getState(), "California", "Location state mismatch");
    }

    @DataProvider(name = "invalidLocationNames")
    public static Object[][] invalidLocationNames() {
        return new Object[][] { {"FakeCity, CA"}, {"Los Angeles, AA"},    {"      "} };
    }
    @Test(dataProvider = "invalidLocationNames")
    public void testInvalidLocationName(String invalidLocationName) throws IllegalArgumentException {
        Location location = new OpenWeatherClient(API_KEY).getCoordinatesByLocationName(invalidLocationName);

        Assert.assertNull(location.getName(), "City name should be empty");
        Assert.assertNull(location.getLat(), "Latitude should be empty");
        Assert.assertNull(location.getLon(), "Longitude should be empty");
        Assert.assertEquals(location.getCustomErrorMessage(), "Location '" + invalidLocationName + "' is not found",
                "Custom error message for invalid location name");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyLocationName() {
        new OpenWeatherClient(API_KEY).getCoordinatesByLocationName("");
    }

    @Test
    public void testValidZip() {
        String validZip = "94065";
        Location location = new OpenWeatherClient(API_KEY).getCoordinatesByZipCode(validZip);

        Assert.assertEquals(location.getZip(), validZip, "Location zip mismatch");
        Assert.assertEquals(location.getLat(), "37.5331", "Expected latitude for " + validZip);
        Assert.assertEquals(location.getLon(), "-122.2486", "Expected longitude for " + validZip);
        Assert.assertEquals(location.getName(), "Redwood City", "Location name mismatch for " + validZip);
    }

    @DataProvider(name = "invalidZips")
    public static Object[][] invalidZips() {
        return new Object[][] { {"94065-1916"}, {"99999"}, {"123456"}, {""} };
    }
    @Test(dataProvider = "invalidZips", expectedExceptions = IllegalArgumentException.class)
    public void testInvalidZipThrowsException(String invalidZip) {
        new OpenWeatherClient(API_KEY).getCoordinatesByZipCode(invalidZip);
    }
}
