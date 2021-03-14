package com.rawjute.rulesengine.config;

import com.rawjute.rulesengine.engine.RulesDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RulesDataSourceConfig {

    @Bean
    public RulesDataSource getRulesDataSource() {
        return new MapRulesDataSource();
    }

}
