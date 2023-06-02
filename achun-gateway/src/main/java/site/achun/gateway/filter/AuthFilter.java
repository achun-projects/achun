package site.achun.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthFilter implements GatewayFilter, Ordered {
    private static final String AUTH_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 在请求属性中设置header
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .header("user-code", "1")
                .build();
        ServerWebExchange build = exchange.mutate().request(newRequest).build();
        // 在这里进行权限校验逻辑
        // 如果校验不通过，可以返回一个错误响应，例如：
        // response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // return response.setComplete();
        log.info("pass auth filter");
        // 校验通过则继续向下传递
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

