package com.weather.app.service;

import com.weather.app.cache.WeatherCache;
import com.weather.app.entity.WeatherDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by omib on 19/06/2016.
 */
@Service
public class WeatherServiceImpl implements WeatherService
{
    private final WeatherCache weatherCache;

    @Autowired
    public WeatherServiceImpl(WeatherCache weatherCache)
    {
        this.weatherCache = weatherCache;
    }

    @Override
    public WeatherDetails getWeatherFor(String city)
    {
        return weatherCache.getWeatherFor(city);
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
