package com.tickets.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("traceability-sv", r -> r.path("/api/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("http://traceability-svc:8080"))
                .route("user-sv", r -> r.path("/api/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("http://user-svc:8080"))
                .route("requirement-sv", r -> r.path("/api/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("http://requirement-svc:8080"))
                .route("comment-sv", r -> r.path("/api/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("http://comment-svc:8080"))
                .route("type-sv", r -> r.path("/api/**")
                        .filters(GatewayFilterSpec::tokenRelay)
                        .uri("http://type-svc:8080"))
                .build();
    }
}
