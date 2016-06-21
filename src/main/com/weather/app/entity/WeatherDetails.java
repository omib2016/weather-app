package com.weather.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by omib on 19/06/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetails
{
    private final Date date;
    private final String cityName;
    private final String description;
    private final double tempInCelcius;
    private final double tempInfaranheit;
    private final Date sunrise;
    private final Date sunset;

    private WeatherDetails(WeatherDetailsBuilder responseBuilder)
    {
        this.date = responseBuilder.date;
        this.cityName = responseBuilder.cityName;
        this.description = responseBuilder.description;
        this.tempInCelcius = responseBuilder.tempInCelcius;
        this.tempInfaranheit = responseBuilder.tempInfaranheit;
        this.sunrise = responseBuilder.sunrise;
        this.sunset = responseBuilder.sunset;
    }

    public static class WeatherDetailsBuilder
    {
        private Date date;
        private String cityName;
        private String description;
        private double tempInCelcius;
        private double tempInfaranheit;
        private Date sunrise;
        private Date sunset;


        public void withDate(Date date) {
            this.date = date;
        }

        public void withCityName(String cityName) {
            this.cityName = cityName;
        }

        public void withDescription(String description) {
            this.description = description;
        }

        public void withTempInCelcius(double tempInCelcius) {
            this.tempInCelcius = tempInCelcius;
        }

        public void withTempInfaranheit(double tempInfaranheit) {
            this.tempInfaranheit = tempInfaranheit;
        }

        public void withSunrise(Date sunrise) {
            this.sunrise = sunrise;
        }

        public void withSunset(Date sunset) {
            this.sunset = sunset;
        }

        public WeatherDetails build()
        {
            return new WeatherDetails(this);
        }
    }

    public Date getDate() {
        return date;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public double getTempInCelcius() {
        return tempInCelcius;
    }

    public double getTempInfaranheit() {
        return tempInfaranheit;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    @Override
    public String toString() {
        return "WeatherDetails{" +
                "date=" + date +
                ", cityName='" + cityName + '\'' +
                ", description='" + description + '\'' +
                ", tempInCelcius=" + tempInCelcius +
                ", tempInfaranheit=" + tempInfaranheit +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
