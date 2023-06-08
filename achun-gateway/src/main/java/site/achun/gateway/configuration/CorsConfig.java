package site.achun.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;

/**
 * Author: Heiffeng
 * Date: 2023/3/30
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedMethod("*");
        corsConfig.setAllowedOriginPatterns(Arrays.asList("*")); // 使用allowedOriginPatterns代替allowedOrigins
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);
        source.registerCorsConfiguration("/**", corsConfig);
        return new CorsWebFilter(source);
    }

}