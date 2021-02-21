package com.rawjute.rulesengine;

import com.rawjute.rulesengine.engine.Trigger;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Trigger("weather.change")
    public void changeWeather() {
        System.out.println("Hello");
    }

}
