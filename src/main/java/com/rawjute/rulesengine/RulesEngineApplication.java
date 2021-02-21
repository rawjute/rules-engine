package com.rawjute.rulesengine;

import com.rawjute.rulesengine.engine.RulesEngineManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RulesEngineApplication {

    private final RulesEngineManager rulesEngineManager;
    private final WeatherService weatherService;

    public RulesEngineApplication(RulesEngineManager rulesEngineManager, WeatherService weatherService) {
        this.rulesEngineManager = rulesEngineManager;
        this.weatherService = weatherService;

        rulesEngineManager
                .when("weather.change")
                .and(() -> true)
                .then(() -> System.out.println("Weather changed"))
                .when("weather.change")
                .then(() -> System.out.println("Yuhuuu"));

        this.weatherService.changeWeather();
    }

    public static void main(String[] args) {
        SpringApplication.run(RulesEngineApplication.class, args);
    }

}
