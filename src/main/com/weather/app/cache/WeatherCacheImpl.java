package com.weather.app.cache;

import com.weather.app.entity.WeatherDetails;
import com.weather.app.service.RemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by omib on 20/06/2016.
 */
@Component
public class WeatherCacheImpl implements WeatherCache
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //weather cache keyed on city
    private final ConcurrentMap<String,WeatherDetails> weatherCache = new ConcurrentHashMap<>();
    //subscription set used to subscribe for weather updates
    private final Set<String> subscriptionSet = Collections.synchronizedSet(new HashSet<String>());
    //Openweather service interface
    private final RemoteService remoteService;
    //scheduler
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    //configurable polling interval
    private final int intervalInSeconds = 3000;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    public WeatherCacheImpl(RemoteService remoteService)
    {
        this.remoteService = remoteService;
    }


    @Override
    public WeatherDetails getWeatherFor(String city)
    {
        if (weatherCache.containsKey(city))
        {
            log.info("Looking up cache for "+city);
            return weatherCache.get(city);
        }
        else
        {
            log.info("Subscription made for "+city);
            subscriptionSet.add(city);
            WeatherDetails weatherDetailsForCity = remoteService.getWeatherDetailsForCity(city);
            WeatherDetails previousValue = weatherCache.replace(city, weatherDetailsForCity);
            if (previousValue == null)
            {
                weatherCache.putIfAbsent(city,weatherDetailsForCity);
            }
            log.info("Updated cache for "+city);
            return weatherDetailsForCity;
        }
    }

    @Override
    public boolean populate()
    {
        //update each city weather every 5 minutes
        scheduledExecutorService.scheduleAtFixedRate(new WeatherRunnable(),0,intervalInSeconds, TimeUnit.SECONDS);

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
            log.info("Starting Updater threads..");
            if (subscriptionSet.size() == 0)
            {
                log.info("No cities have been subscribed for weather updates yet..");
            }

            for (String city : subscriptionSet)
            {
               executorService.submit(new WeatherUpdater(city));
            }

            return true;
        }
    }

    private class WeatherUpdater implements Runnable
    {
        private final String city;

        private WeatherUpdater(String city)
        {
            this.city = city;
        }

        @Override
        public void run()
        {
            log.info("Updating weather for "+city);
            WeatherDetails latestWeather = remoteService.getWeatherDetailsForCity(city);
            WeatherDetails previousValue = weatherCache.replace(city, latestWeather);
            if (previousValue == null)
            {
                weatherCache.putIfAbsent(city,latestWeather);
            }
            log.info("Updated cache for "+city);
        }
    }
}
