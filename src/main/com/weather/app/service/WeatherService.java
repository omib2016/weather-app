package com.weather.app.service;

import com.weather.app.entity.WeatherDetails;

/**
 * Created by omib on 19/06/2016.
 */
public interface WeatherService
{
    public WeatherDetails getWeatherFor(String city);
    public boolean start();
    public boolean shutdown();

}
