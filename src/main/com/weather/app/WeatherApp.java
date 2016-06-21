package com.weather.app;

import com.weather.app.service.WeatherServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by omib on 20/06/2016.
 */
@SpringBootApplication
public class WeatherApp
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext context= SpringApplication.run(WeatherApp.class, args);
        WeatherServiceImpl weatherService = (WeatherServiceImpl) context.getBean("weatherServiceImpl");
        weatherService.start();
    }

}
