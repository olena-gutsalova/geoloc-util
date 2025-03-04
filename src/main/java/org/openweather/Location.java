package org.openweather;

public class Location {
    private String lat;
    private String lon;
    private String name;
    private String zip;
    private String state;
    private final String customErrorMessage;

    public Location(String customErrorMessage) {
        this.customErrorMessage = customErrorMessage;
    }

    public String getCustomErrorMessage() {
        return customErrorMessage;
    }


    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public String getZip(){
        return zip;
    }

    public String getState() {
        return state;
    }

}
