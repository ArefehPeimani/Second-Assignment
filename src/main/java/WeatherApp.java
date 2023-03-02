import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {

    public final static String apiKey = "01a0596afb9a4560941163947232602";

    //main function
    public static void main(String[] args) {
        // prompt the user for input
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the city name : ");
        String city = in.nextLine();

        // get weather data from the API and find specific information
        String weatherData = getWeatherData(city);
        double temperature = getTemperature(weatherData);
        int humidity = getHumidity(weatherData);
        double windSpeed = getWindSpeed(weatherData);
        String windDirection = getWindDirection(weatherData);

        // output
        System.out.println("Current temperature in " + city + " is " + temperature + " °C.");
        System.out.println("Humidity is " + humidity + "%.");
        System.out.println("Wind speed is " + windSpeed + " kilometers per hour.");
        System.out.println("Wind direction is " + windDirection + ".");
    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds the temperature in celsius from the weather data.
     *
     * @param weatherJson the complete weather information of the city in JSON string format
     * @return the temperature in celsius
     */
    public static double getTemperature(String weatherJson){
        double answer = 0.0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getDouble("temp_c");
        return answer;
    }

    /**
     * Finds the humidity percentage from the weather data.
     *
     * @param weatherJson the complete weather information of the city in JSON string format
     * @return the humidity percentage
     */
    public static int getHumidity(String weatherJson){
        int answer = 0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getInt("humidity");
        return answer;
    }

    /**
     * Finds the wind speed from the weather data.
     *
     * @param weatherJson the complete weather information of the city in JSON string format
     * @return the wind speed in kilometers per hour
     */
    public static double getWindSpeed(String weatherJson){
        double answer = 0;
        JSONObject data = new JSONObject(weatherJson);
        answer = data.getJSONObject("current").getDouble("wind_kph");
        return answer;
    }

    /**
     * Finds the wind direction from the weather data.
     *
     * @param weatherJson the complete weather information of the city in JSON string format
     * @return the wind direction
     */
    public static String getWindDirection(String weatherJson){
        String answer = "";
        JSONObject data = new JSONObject(weatherJson);
        String direction = data.getJSONObject("current").getString("wind_dir");

        // convert short-form directions to long ones.
        if (direction.length() == 1) {
            if (direction.equals("N")) {
                answer = "north";
            }
            else if (direction.equals("S")) {
                answer = "south";
            }
            else if (direction.equals("W")) {
                answer = "west";
            }
            else if (direction.equals("E")) {
                answer = "east";
            }
        }
        else if (direction.length() == 2) {
            if (direction.equals("NE")) {
                answer = "northeast";
            }
            if (direction.equals("NW")) {
                answer = "northwest";
            }
            if (direction.equals("SE")) {
                answer = "southeast";
            }
            if (direction.equals("SW")) {
                answer = "southwest";
            }
        }
        else {
            if (direction.equals("NNE")) {
                answer = "north-northeast";
            }
            if (direction.equals("NNE")) {
                answer = "north-northeast";
            }
            if (direction.equals("ENE")) {
                answer = "east-northeast";
            }
            if (direction.equals("ESE")) {
                answer = "east-southeast";
            }
            if (direction.equals("SSE")) {
                answer = "south-southeast";
            }
            if (direction.equals("SSW")) {
                answer = "south-southwest";
            }
            if (direction.equals("WSW")) {
                answer = "west-southwest";
            }
            if (direction.equals("WNW")) {
                answer = "west-northwest";
            }
        }
        return answer;
    }
}
