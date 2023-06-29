package site.achun.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.achun.gateway.service.UserLoginService;

@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements GatewayFilter, Ordered {
    private static final String AUTH_TOKEN = "Authorization";

    private final StringRedisTemplate redisTemplate;
    private final static String KEY = "USER:LOGIN:%s";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String satoken = request.getHeaders().getFirst("satoken");
        String userCode = redisTemplate.opsForValue().get(String.format(KEY, satoken));
        // 在请求属性中设置header
        request = request.mutate()
                .header("user-code", userCode)
                .build();
        // 在这里进行权限校验逻辑
        // 如果校验不通过，可以返回一个错误响应，例如：
        // response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // return response.setComplete();
        log.info("pass auth filter");
        // 校验通过则继续向下传递
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

