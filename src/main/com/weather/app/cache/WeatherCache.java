package main.com.weather.app.cache;

import main.com.weather.app.entity.WeatherDetails;

/**
 * Created by omib on 20/06/2016.
 */
public interface WeatherCache
{
    public WeatherDetails getWeatherFor(String city);

    public boolean populate();

    public boolean tearDown();

}
