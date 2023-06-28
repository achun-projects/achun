package site.achun.user.app.controller.login;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.user.app.generator.domain.UserAccount;
import site.achun.user.app.generator.service.UserAccountService;
import site.achun.user.client.module.login.UserLoginClient;
import site.achun.user.client.module.login.request.LoginRequest;
import site.achun.user.client.module.login.response.LoginResponse;

@Slf4j
@Tag(name = "用户登录")
@RequiredArgsConstructor
@RestController
public class UserLoginController implements UserLoginClient {

    private final UserAccountService userAccountService;

    @Override
    public Rsp<LoginResponse> login(LoginRequest request) {
        UserAccount userAccount = userAccountService.lambdaQuery()
                .eq(UserAccount::getAccount, request.getAccount())
                .eq(UserAccount::getPassword, request.getPassword())
                .one();
        if(userAccount == null){
            return Rsp.error("用户不存在");
        }
        StpUtil.login(userAccount.getUserCode(),request.getTimeout());
        LoginResponse response = LoginResponse.builder()
                .userCode(userAccount.getUserCode())
                .satoken(StpUtil.getTokenValue())
                .build();
        return Rsp.success(response);
    }

    @Override
    public Rsp<LoginResponse> checkToken(String token) {
        var userCode = StpUtil.getLoginIdByToken(token);
        if(userCode == null){
            return Rsp.error("token无效");
        }
        LoginResponse response = LoginResponse.builder()
                .userCode((String) userCode)
                .satoken(token)
                .build();
        return Rsp.success(response);
    }

}
