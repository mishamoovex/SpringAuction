package com.lead.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class DateTimeConfig {

    @Bean
    public Clock systemClock() {
        return Clock.systemUTC();
    }
}
