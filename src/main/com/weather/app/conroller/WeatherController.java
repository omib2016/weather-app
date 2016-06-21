package com.weather.app.conroller;

import com.weather.app.entity.Request;
import com.weather.app.entity.WeatherDetails;
import com.weather.app.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by omib on 19/06/2016.
 */
@Controller
public class WeatherController
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService)
    {
        this.weatherService = weatherService;
    }

    @RequestMapping(value="/input", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("request", new Request());
        return "input";
    }

    @RequestMapping(value = "/getWeatherFor", method=RequestMethod.GET)
    public String getWeatherFor(@ModelAttribute Request request, Model model)
    {
        WeatherDetails weatherDetails = weatherService.getWeatherFor(request.getCity());
        log.info("Response received: " + weatherDetails);
        model.addAttribute("date", weatherDetails.getDate());
        model.addAttribute("cityName", weatherDetails.getCityName());
        model.addAttribute("description",weatherDetails.getDescription());
        model.addAttribute("tempInCelcius",weatherDetails.getTempInCelcius());
        model.addAttribute("tempInfaranheit",weatherDetails.getTempInfaranheit());
        model.addAttribute("sunrise",weatherDetails.getSunrise());
        model.addAttribute("sunset",weatherDetails.getSunset());
        return "output";
    }

}
