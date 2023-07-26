package site.achun.gateway.filter;

import com.alibaba.fastjson2.JSON;
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
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter implements GatewayFilter, Ordered {
    private static final String AUTH_TOKEN = "Authorization";

    private final UserLoginService userLoginService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 检测白名单
        String path = request.getPath().toString();
        boolean inWhiteList = list.stream().anyMatch(p -> path.startsWith(p));

        // 用token从redis获取用户信息
        ServerHttpResponse response = exchange.getResponse();
        String satoken = request.getHeaders().getFirst("satoken");
        UserCacheInfo userCacheInfo = userLoginService.getByToken(satoken);

        // 用户信息为空且不在白名单，则拦截
        if(userCacheInfo==null && !inWhiteList){
            byte[] bits = JSON.toJSONString(Rsp.error("无效token")).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }

        // 设置userCode Header
        String userCode = userCacheInfo == null?"":userCacheInfo.getUserCode();
        request = request.mutate()
                .header("user-code", userCode)
                .build();
        // 校验通过
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public final static List<String> list = List.of(
            "/user/login/user-login",
            "/user/login/check-token",
            "/gallery/random-get/"
    );
}

