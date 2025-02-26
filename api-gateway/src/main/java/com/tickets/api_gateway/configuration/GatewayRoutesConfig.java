package com.tickets.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("traceability-sv", r -> r.path("/api/**")
                        .uri("lb://traceability-sv"))
                .route("user-sv", r -> r.path("/api/**")
                        .uri("lb://user-sv"))
                .route("requirement-sv", r -> r.path("/api/**")
                        .uri("lb://requirement-sv"))
                .route("comment-sv", r -> r.path("/api/**")
                        .uri("lb://comment-sv"))
                .route("type-sv", r -> r.path("/api/**")
                        .uri("lb://type-sv"))
                .build();
    }
}
