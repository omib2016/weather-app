package main.com.weather.app.service;

import main.com.weather.app.cache.WeatherCache;
import main.com.weather.app.entity.WeatherDetails;
import org.springframework.stereotype.Service;

/**
 * Created by omib on 19/06/2016.
 */
@Service
public class WeatherServiceImpl implements WeatherService
{
    private final WeatherCache weatherCache;

    public WeatherServiceImpl(WeatherCache weatherCache)
    {
        this.weatherCache = weatherCache;
    }

    @Override
    public WeatherDetails getWeatherFor(String city)
    {
        return null;
    }

    @Override
    public boolean start()
    {
        return weatherCache.populate();
    }

    @Override
    public boolean shutdown()
    {
        return weatherCache.tearDown();
    }
}
