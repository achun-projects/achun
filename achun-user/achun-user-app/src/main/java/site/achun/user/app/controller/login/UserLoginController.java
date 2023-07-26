package site.achun.user.app.controller.login;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.user.app.generator.domain.UserAccount;
import site.achun.user.app.generator.service.UserAccountService;
import site.achun.user.app.service.UserCacheService;
import site.achun.user.client.module.login.UserLoginClient;
import site.achun.user.client.module.login.request.LoginRequest;
import site.achun.user.client.module.login.response.LoginResponse;

import java.util.UUID;

@Slf4j
@Tag(name = "用户登录")
@RequiredArgsConstructor
@RestController
public class UserLoginController implements UserLoginClient {

    private final UserAccountService userAccountService;

    private final UserCacheService userCacheService;

    @Override
    public Rsp<LoginResponse> login(LoginRequest request) {
        UserAccount userAccount = userAccountService.lambdaQuery()
                .eq(UserAccount::getAccount, request.getAccount())
                .eq(UserAccount::getPassword, request.getPassword())
                .one();
        if(userAccount == null){
            return Rsp.error("用户不存在");
        }
        LoginResponse response = LoginResponse.builder()
                .userCode(userAccount.getUserCode())
                .satoken(UUID.randomUUID().toString().replace("-",""))
                .build();
        userCacheService.put(response.getSatoken(),response.getUserCode(),request.getTimeout());
        return Rsp.success(response);
    }

    @Override
    public Rsp<LoginResponse> checkToken(String token){
        String userCode = userCacheService.get(token);
        if(userCode == null){
            return Rsp.error("token无效");
        }
        LoginResponse response = LoginResponse.builder()
                .userCode(userCode)
                .satoken(token)
                .build();
        return Rsp.success(response);
    }

}
