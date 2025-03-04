package org.openweather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestInstance;

import java.lang.reflect.Type;
import java.util.List;

public class OpenWeatherClient {
    private final UnirestInstance unirest = Unirest.spawnInstance();
    private final String apiKey;

    public OpenWeatherClient(String apiKey) {
        this.apiKey = apiKey;
        this.unirest.config()
                .defaultBaseUrl("http://api.openweathermap.org/geo/1.0")
                .setDefaultHeader("Content-Type", "application/json");
    }

    public Location getCoordinatesByLocationName(String locationName) {
        HttpResponse<JsonNode> response = unirest.get("/direct")
                .queryString("limit", "1")
                .queryString("appid", apiKey)
                .queryString("q", "%s,US".formatted(locationName))
                .asJson();
        if (response.getStatus() != 200) {
            throw new IllegalArgumentException(response.getBody().toString());
        }
        if (response.getBody().getArray().isEmpty()) {
            return new Location("Location '%s' is not found".formatted(locationName));
        } else {
            Type listType = new TypeToken<List<Location>>() {}.getType();
            List<Location> coordinateResponse = new Gson().fromJson(response.getBody().toString(), listType);
            return coordinateResponse.getFirst();
        }
    }

    public Location getCoordinatesByZipCode(String zipCode) {
        HttpResponse<JsonNode> response = unirest.get("/zip")
                .queryString("appid", apiKey)
                .queryString("zip", "%s,US".formatted(zipCode)).asJson();
        if (response.getStatus() != 200) {
            throw new IllegalArgumentException(response.getBody().toString());
        }
        if (response.getBody().getArray().isEmpty()) {
            return new Location("Location '%s' is not found".formatted(zipCode));
        } else {
            return new Gson().fromJson(response.getBody().toString(), Location.class);
        }
    }
}
