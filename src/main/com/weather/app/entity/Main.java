package com.weather.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by omib on 20/06/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main
{
    private double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temperature=" + temp +
                '}';
    }
}
