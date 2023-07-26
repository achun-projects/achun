package site.achun.gateway.filter;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import site.achun.gateway.service.UserLoginService;
import site.achun.support.api.response.Rsp;
import site.achun.user.client.module.login.dto.UserCacheInfo;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter implements GatewayFilter, Ordered {
    private static final String AUTH_TOKEN = "Authorization";

    private final UserLoginService userLoginService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String satoken = request.getHeaders().getFirst("satoken");
        UserCacheInfo userCacheInfo = userLoginService.getByToken(satoken);
//        if(userCacheInfo==null){
//            byte[] bits = JSON.toJSONString(Rsp.error("无效token")).getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bits);
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            //指定编码，否则在浏览器中会中文乱码
//            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//            return response.writeWith(Mono.just(buffer));
//        }
        String userCode = userCacheInfo == null?"":userCacheInfo.getUserCode();
        // 在请求属性中设置header
        request = request.mutate()
                .header("user-code", userCode)
                .build();
        // 在这里进行权限校验逻辑
        // 如果校验不通过，可以返回一个错误响应，例如：
        // response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // return response.setComplete();
        // 校验通过则继续向下传递
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

