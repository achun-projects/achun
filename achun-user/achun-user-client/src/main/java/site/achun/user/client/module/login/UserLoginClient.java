package site.achun.user.client.module.login;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import site.achun.support.api.response.Rsp;
import site.achun.user.client.module.login.request.LoginRequest;
import site.achun.user.client.module.login.response.LoginResponse;

@FeignClient(name = "achun-user-app", contextId = "UserLoginClient")
public interface UserLoginClient {

    @Operation(summary = "登录")
    @PostMapping("/user/login/user-login")
    Rsp<LoginResponse> login(@RequestBody LoginRequest request);

    @Operation(summary = "校验token")
    @GetMapping("/user/login/check-token")
    Rsp<LoginResponse> checkToken(@RequestParam(name = "token") String token);

}
