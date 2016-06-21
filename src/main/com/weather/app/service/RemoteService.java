package com.weather.app.service;

import com.weather.app.entity.WeatherDetails;

/**
 * Created by omib on 20/06/2016.
 */
public interface RemoteService
{
    public WeatherDetails getWeatherDetailsForCity(String city);
}
