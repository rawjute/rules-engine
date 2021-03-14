package com.rawjute.rulesengine;

import com.rawjute.rulesengine.engine.RulesEngine;
import com.rawjute.rulesengine.engine.RulesEngineBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RulesEngineTest {

    @Autowired
    private RulesEngine rulesEngine;
    @Autowired
    private WeatherService weatherService;

    @Test
    public void rulesEngineTest() {

        rulesEngine
                .saveRule(RulesEngineBuilder
                        .newRule("test")
                        .when("weather.change")
                        .and(() -> true)
                        .then(() -> System.out.println("Weather changed"))
                        .build());

        this.weatherService.weatherChanged();
    }

}
