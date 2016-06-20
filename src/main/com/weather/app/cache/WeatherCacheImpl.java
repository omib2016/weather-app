package main.com.weather.app.cache;

import main.com.weather.app.entity.WeatherDetails;
import main.com.weather.app.service.RemoteService;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by omib on 20/06/2016.
 */
public class WeatherCacheImpl implements WeatherCache
{
    //weather cache keyed on city
    private final ConcurrentMap<String,WeatherDetails> weatherCache = new ConcurrentHashMap<>();
    //subscription set used to subscribe for weather updates
    private final Set<String> subscriptionSet = Collections.synchronizedSet(new HashSet<String>());
    //Openweather service interface
    private final RemoteService remoteService;
    //scheduler
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    //configurable polling interval
    private final int intervalInSeconds;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public WeatherCacheImpl(RemoteService remoteService, int intervalInSeconds)
    {
        this.remoteService = remoteService;
        this.intervalInSeconds = intervalInSeconds;
    }


    @Override
    public WeatherDetails getWeatherFor(String city)
    {
        if (weatherCache.containsKey(city))
        {
            return weatherCache.get(city);
        }
        else {
            subscriptionSet.add(city);
            WeatherDetails weatherDetailsForCity = remoteService.getWeatherDetailsForCity(city);
            weatherCache.replace(city,weatherDetailsForCity);
            return weatherDetailsForCity;
        }
    }

    @Override
    public boolean populate()
    {
        //update each city weather every 5 minutes
        scheduledExecutorService.schedule(new WeatherRunnable(), intervalInSeconds, TimeUnit.SECONDS);

        return true;
    }

    @Override
    public boolean tearDown()
    {
        scheduledExecutorService.shutdownNow();
        weatherCache.clear();

        return true;
    }

    private class WeatherRunnable implements Runnable
    {
        @Override
        public void run()
        {
           updateAll();
        }

        private boolean updateAll()
        {
            for (String city : subscriptionSet)
            {
               executorService.submit(new WeatherUpdater(remoteService,city));
            }

            return true;
        }
    }

    private class WeatherUpdater implements Runnable
    {
        private final RemoteService remoteService;
        private final String city;

        private WeatherUpdater(RemoteService remoteService, String city)
        {

            this.remoteService = remoteService;
            this.city = city;
        }

        @Override
        public void run()
        {
            WeatherDetails latestWeather = remoteService.getWeatherDetailsForCity(city);
            weatherCache.replace(city,latestWeather);
        }
    }
}
