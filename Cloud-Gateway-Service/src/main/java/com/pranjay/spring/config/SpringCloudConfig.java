package com.pranjay.spring.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/**")
                        .uri("lb://order-service/"))
                        //.id("first-service"))

                .route(r -> r.path("/api/v2/**")
                        .uri("lb://payment-service/"))
                        //.id("second-service"))
                .build();
    }


}