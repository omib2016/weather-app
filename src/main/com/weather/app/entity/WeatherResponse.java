package main.com.weather.app.entity;

/**
 * Created by omib on 19/06/2016.
 */
public class WeatherResponse
{
    private WeatherResponse(WeatherResponseBuilder responseBuilder)
    {

    }

    public static class WeatherResponseBuilder
    {
        public WeatherResponse build()
        {
            return new WeatherResponse(this);
        }

    }
}
