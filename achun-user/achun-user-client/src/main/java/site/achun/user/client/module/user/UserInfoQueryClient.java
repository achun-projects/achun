package site.achun.user.client.module.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import site.achun.support.api.response.Rsp;
import site.achun.user.client.module.user.response.UserInfoResponse;

@FeignClient(name = "achun-user-app", contextId = "UserInfoQueryClient")
public interface UserInfoQueryClient {

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/user/user-info/query-user-by-satoken")
    Rsp<UserInfoResponse> queryUser(@RequestHeader("satoken") String satoken);
}
