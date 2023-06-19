package site.achun.user.client.module.data;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.Rsp;
import site.achun.user.client.module.data.request.QueryUserData;
import site.achun.user.client.module.data.response.UserDataResponse;

@FeignClient(name = "achun-user-app", contextId = "UserDataQueryClient")
public interface UserDataQueryClient {

    @Operation(summary = "查询用户数据")
    @PostMapping("/user/data/query-user-data")
    Rsp<UserDataResponse> queryUserData(@RequestBody QueryUserData query);

}
