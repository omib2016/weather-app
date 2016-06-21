package com.weather.app.service;

import com.weather.app.entity.OpenWeatherResponse;
import com.weather.app.entity.WeatherDetails;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by omib on 20/06/2016.
 */
@Component
public class OpenMapService implements RemoteService
{
    @Autowired
    private final ConfigurationService configurationService;
    private final String openMapUrl;
    private final String openMapAppId;
    private final RestTemplate restTemplate = new RestTemplate();
    private final StringBuilder requestUrl;
    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OpenMapService(ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
        this.openMapUrl = configurationService.getUrl();
        this.openMapAppId = configurationService.getAppid();
        this.requestUrl = new StringBuilder(openMapUrl).append("?").append("appid=").append(openMapAppId);
        log.info("Mapped request url: "+requestUrl);

    }


    @Override
    public WeatherDetails getWeatherDetailsForCity(String city)
    {
        log.info("Looking up OpenMap weather for "+city);
        StringBuilder urlWithParamForCelcius = new StringBuilder(requestUrl);
        urlWithParamForCelcius.append("&q=").append(city).append("&units=metric");

        StringBuilder urlWithParamForFaranheit = new StringBuilder(requestUrl);
        urlWithParamForFaranheit.append("&q=").append(city).append("&units=imperial");


        OpenWeatherResponse weatherInCelcius = restTemplate.getForObject(urlWithParamForCelcius.toString(), OpenWeatherResponse.class);
        OpenWeatherResponse weatherInFaranheit = restTemplate.getForObject(urlWithParamForFaranheit.toString(), OpenWeatherResponse.class);

        WeatherDetails.WeatherDetailsBuilder responseBuilder = new WeatherDetails.WeatherDetailsBuilder();
        responseBuilder.withDate(new Date(weatherInCelcius.getDt()));
        responseBuilder.withCityName(weatherInCelcius.getName());
        responseBuilder.withDescription(weatherInCelcius.getWeather()[0].getDescription());
        responseBuilder.withTempInCelcius(weatherInCelcius.getMain().getTemp());
        responseBuilder.withTempInfaranheit(weatherInFaranheit.getMain().getTemp());
        responseBuilder.withSunrise(new Date(weatherInCelcius.getSys().getSunrise()));
        responseBuilder.withSunset(new Date(weatherInCelcius.getSys().getSunset()));

        return responseBuilder.build();
    }
}
