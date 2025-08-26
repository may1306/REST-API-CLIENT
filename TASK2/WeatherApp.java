import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherApp {

    public static void main(String[] args) {
        parseAndDisplayWeatherFromFile("weather_sample.json");
    }

    private static void parseAndDisplayWeatherFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            parseAndDisplayWeather(json.toString());
        } catch (IOException e) {
            System.out.println("Error reading local JSON file: " + e.getMessage());
        }
    }

    private static void parseAndDisplayWeather(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        String cityName = jsonObject.get("name").getAsString();
        JsonObject main = jsonObject.getAsJsonObject("main");
        JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();

        double temperature = main.get("temp").getAsDouble();
        int humidity = main.get("humidity").getAsInt();
        String description = weather.get("description").getAsString();

        System.out.println("------ Weather Information ------");
        System.out.println("City: " + cityName);
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Condition: " + description);
        System.out.println("---------------------------------");
    }
}


