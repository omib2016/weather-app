package com.weather.app;


import com.weather.app.entity.OpenWeatherResponse;
import org.junit.Test;

import org.springframework.web.client.RestTemplate;

/**
 * Created by omib on 20/06/2016.
 */

public class RestTemplateTest
{
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testRestCall()
    {
       String urlWithParamForCelcius = "http://api.openweathermap.org/data/2.5/weather?appid=09c1b9401d7aaf5479e0fe7c31cee169&q=London&units=metric";
       OpenWeatherResponse weatherInCelcius = restTemplate.getForObject(urlWithParamForCelcius, OpenWeatherResponse.class);
       System.out.println(weatherInCelcius);

       String urlWithParamForFarhn = "http://api.openweathermap.org/data/2.5/weather?appid=09c1b9401d7aaf5479e0fe7c31cee169&q=London&units=imperial";
       OpenWeatherResponse weatherInFarhn = restTemplate.getForObject(urlWithParamForFarhn, OpenWeatherResponse.class);
       System.out.println(weatherInFarhn);
    }

}
