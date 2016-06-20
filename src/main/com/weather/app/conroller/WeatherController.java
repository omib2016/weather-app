package main.com.weather.app.conroller;

import main.com.weather.app.entity.WeatherDetails;
import main.com.weather.app.entity.WeatherResponse;
import main.com.weather.app.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by omib on 19/06/2016.
 */
@RestController
public class WeatherController
{
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService)
    {
        this.weatherService = weatherService;
    }

    @RequestMapping("/getWeatherFor")
    public WeatherResponse getWeatherFor(@RequestParam(value = "city") String city)
    {
        WeatherDetails weatherdetails = weatherService.getWeatherFor(city);
        WeatherResponse.WeatherResponseBuilder builder = new WeatherResponse.WeatherResponseBuilder();
        //TODO: Add all weather attributes
        return builder.build();
    }
}
