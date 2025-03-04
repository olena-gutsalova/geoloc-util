package org.openweather;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException {
        String apiKey = System.getenv("OW_API_KEY");
        new GeoLocUtil(apiKey).getCoordinates(args);
    }
}
