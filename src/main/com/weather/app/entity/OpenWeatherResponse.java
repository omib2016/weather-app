package com.weather.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by omib on 19/06/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse
{
    public  OpenWeatherResponse()
    {

    }

    private long dt;
    private String name;
    private Weather[] weather;
    private Main main;
    private Sys sys;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setTemperature(Main temperature) {
        this.main = temperature;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @Override
    public String toString() {
        return "OpenWeatherResponse{" +
                "dt=" + dt +
                ", name='" + name + '\'' +
                ", weather=" + weather +
                ", main=" + main +
                ", sys=" + sys +
                '}';
    }
}
