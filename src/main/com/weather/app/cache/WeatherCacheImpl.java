package main.com.weather.app.cache;

import main.com.weather.app.entity.WeatherDetails;
import main.com.weather.app.service.RemoteService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by omib on 20/06/2016.
 */
public class WeatherCacheImpl implements WeatherCache
{
    private final ConcurrentMap<String,WeatherDetails> weatherCache = new ConcurrentHashMap<>();
    private final List<String> subscriptionList = Collections.synchronizedList(new ArrayList<String>());
    private final RemoteService remoteService;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public WeatherCacheImpl(RemoteService remoteService)
    {
        this.remoteService = remoteService;
    }


    @Override
    public WeatherDetails getWeatherFor(String city)
    {
        if (weatherCache.containsKey(city))
        {
            return weatherCache.get(city);
        }
        else {
            subscriptionList.add(city);
            WeatherDetails weatherDetailsForCity = remoteService.getWeatherDetailsForCity(city);
            weatherCache.replace(city,weatherDetailsForCity);
            return weatherDetailsForCity;
        }
    }

    @Override
    public boolean populate()
    {
        //update each city weather every 5 minutes
        scheduledExecutorService.schedule(new WeatherRunnable(), 300, TimeUnit.SECONDS);

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
           populate();
        }

        public boolean populate()
        {
            for (String city : subscriptionList)
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
