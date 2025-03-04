# Geolocation Utility (Java)

A command-line utility that retrieves latitude, longitude, and place information based on a city, state, or ZIP code using the OpenWeather Geocoding API.

## API Reference
- Geocoding API Docs: [OpenWeather Geocoding API](https://openweathermap.org/api/geocoding-api)
- Endpoints Used:
  - `/geo/1.0/direct?q={city,state}&appid=API_KEY`
  - `/geo/1.0/zip?zip={zipcode}&appid=API_KEY`

## Prerequisites
- Java 11+ installed ([Download Java](https://adoptium.net/))
- Maven installed ([Download Maven](https://maven.apache.org/download.cgi))
- OpenWeather API Key (set as an environment variable `OW_API_KEY`)

## Running the Utility
#### Option 1:
Run the pre-compiled and packaged `geoloc-util-1.0.jar` utility, which is located in the project's root folder. You can run it using the following command: 

`OW_API_KEY=YOUR_API_KEY java -jar geoloc-util-1.0.jar "Miami, FL" "94561"`

#### Option 2:
To compile and package the utility from scratch, you need a Java JDK 21+ and Apache Maven.

`$ OW_API_KEY=YOUR_API_KEY mvn clean package`

The utility will be packaged and located in the `target` folder as `geoloc-util-1.0.jar`. To run it, you can use the same command as in Option 1:

`OW_API_KEY=YOUR_API_KEY java -jar target/geoloc-util-1.0.jar "New York, NY" "10001" "Los Angeles, CA"`

## Running the Tests:
`$ OW_API_KEY=YOUR_API_KEY mvn clean test`

