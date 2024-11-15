package com.lead.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class DateTimeConfig {

    @Bean
    public Clock systemClock() {
        return Clock.systemUTC();
    }
}
