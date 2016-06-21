package com.weather.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by omib on 20/06/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys
{
    private long sunrise;
    private long sunset;

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
