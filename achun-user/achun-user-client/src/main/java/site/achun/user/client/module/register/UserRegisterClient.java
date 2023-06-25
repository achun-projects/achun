package site.achun.user.client.module.register;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "achun-user-app", contextId = "UserRegisterClient")
public interface UserRegisterClient {
}
