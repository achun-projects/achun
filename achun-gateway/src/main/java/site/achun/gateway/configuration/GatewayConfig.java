package site.achun.gateway.configuration;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.achun.gateway.filter.AuthFilter;

@Configuration
@AllArgsConstructor
public class GatewayConfig {

    private final AuthFilter authFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("gallery", r -> r.path("/gallery/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://achun-gallery-app"))
                .route("file", r -> r.path("/file/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://achun-file-app"))
                .route("user", r -> r.path("/user/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://achun-user-app"))
                .route("video", r -> r.path("/video/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://achun-video-app"))
                .route("theatre", r -> r.path("/theatre/**")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://achun-theatre-app"))
                .build();
    }
}


