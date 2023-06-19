package site.achun.user.client.module.data;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.Rsp;
import site.achun.user.client.module.data.request.UpdateUserData;
import site.achun.user.client.module.data.response.UserDataResponse;

@FeignClient(name = "achun-user-app", contextId = "UserDataUpdateClient")
public interface UserDataUpdateClient {

    @Operation(summary = "更新用户数据")
    @PostMapping("/user/data/update-user-data")
    Rsp<UserDataResponse> updateUserData(@RequestBody UpdateUserData update);
}
