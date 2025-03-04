package org.openweather;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeoLocUtil {
    private static String API_KEY;
    public GeoLocUtil(String apiKey) {
        API_KEY = apiKey;
    }

    public List<Location> getCoordinates(String[] args) throws IllegalArgumentException {
        OpenWeatherClient owc = new OpenWeatherClient(API_KEY);
        List<Location> responses = new ArrayList<>();
        for (String arg : args) {
            Location location;
            if (arg.matches("\\d{5}")) {
                location = owc.getCoordinatesByZipCode(arg);
            } else {
                location = owc.getCoordinatesByLocationName(arg);
            }
            responses.add(location);
            printResults(arg, location);
        }
        return responses;
    }

    private void printResults(String input, Location location) {
        System.out.printf(":::: Location info for '%s' ::::%n", input);
        Optional.ofNullable(location.getName()).ifPresent(msg->System.out.println("Name: " + msg));
        Optional.ofNullable(location.getZip()).ifPresent(msg->System.out.println("Zip: " + msg));
        Optional.ofNullable(location.getState()).ifPresent(msg->System.out.println("State: " + msg));
        Optional.ofNullable(location.getLat()).ifPresent(msg->System.out.println("Latitude: " + msg));
        Optional.ofNullable(location.getLon()).ifPresent(msg->System.out.println("Longitude: " + msg));
        Optional.ofNullable(location.getCustomErrorMessage()).ifPresent(msg->System.out.println("Error Message: " + msg));
        System.out.println();
    }
}
