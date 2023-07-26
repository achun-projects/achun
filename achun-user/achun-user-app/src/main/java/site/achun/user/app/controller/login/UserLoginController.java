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
import site.achun.user.app.service.dto.UserCacheInfo;
import site.achun.user.app.utils.RsaUtil;
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
        String password = RsaUtil.decrypt(request.getPassword(), RsaUtil.DEFAULT_RSA_PRIVATE_KEY);
        if(password==null){
            return Rsp.error("登录失败");
        }
        UserAccount userAccount = userAccountService.lambdaQuery()
                .eq(UserAccount::getAccount, request.getAccount())
                .eq(UserAccount::getPassword, password)
                .one();
        if(userAccount == null){
            return Rsp.error("登录失败");
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
        UserCacheInfo userCacheInfo = userCacheService.getByToken(token);
        if(userCacheInfo == null){
            return Rsp.error("token无效");
        }
        LoginResponse response = LoginResponse.builder()
                .userCode(userCacheInfo.getUserCode())
                .satoken(token)
                .build();
        return Rsp.success(response);
    }

}
