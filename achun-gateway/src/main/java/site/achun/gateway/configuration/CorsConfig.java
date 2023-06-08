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
        // 允许所有请求方法
        corsConfig.addAllowedMethod("*");
        // 允许指定的域，可以设置多个来源
//        corsConfig.addAllowedOrigin("http://example1.com");
//        corsConfig.addAllowedOrigin("http://example2.com");
        corsConfig.setAllowedOriginPatterns(Arrays.asList("http://*.*.achun.site"));
        // 允许全部请求头
        corsConfig.addAllowedHeader("*");
        // 允许携带 Cookie 等用户凭证
        corsConfig.setAllowCredentials(true);
        // 允许全部请求路径
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }


}