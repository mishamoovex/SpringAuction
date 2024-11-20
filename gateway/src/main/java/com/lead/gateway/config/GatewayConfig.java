package com.lead.gateway.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", eurekaRoute("/v1/auth/**", "auth-service"))
                .route("user-service", eurekaRoute("/v1/user/**", "user-service"))
                .route("auction-service", eurekaRoute("/v1/auction/**", "auction-service"))
                .route("lot-service", eurekaRoute("/v1/lot/**", "lot-service"))
                .build();
    }

    private Function<PredicateSpec, Buildable<Route>> eurekaRoute(String path, String serverName) {
        return r -> r.path(path).uri("lb://" + serverName);
    }
}
