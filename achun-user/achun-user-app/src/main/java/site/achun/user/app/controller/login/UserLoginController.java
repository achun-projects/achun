package site.achun.user.app.controller.login;

import cn.hutool.core.lang.hash.Hash;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.user.app.generator.domain.UserAccount;
import site.achun.user.app.generator.service.UserAccountService;
import site.achun.user.app.service.UserCacheService;
import site.achun.user.app.utils.RsaUtil;
import site.achun.user.client.module.login.UserLoginClient;
import site.achun.user.client.module.login.dto.UserCacheInfo;
import site.achun.user.client.module.login.request.LoginRequest;
import site.achun.user.client.module.login.response.LoginResponse;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Tag(name = "用户登录")
@RequiredArgsConstructor
@RestController
public class UserLoginController implements UserLoginClient {

    private final UserAccountService userAccountService;

    private final UserCacheService userCacheService;

    private final static ConcurrentHashMap<String,Integer> errorLoginRecord = new ConcurrentHashMap<>();

    @Override
    public Rsp<LoginResponse> login(LoginRequest request) {
        if(errorLoginRecord.containsKey(request.getAccount()) && errorLoginRecord.get(request.getAccount()) > 5){
            return Rsp.error("账号锁定");
        }
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
        UserCacheInfo userCacheInfo = userCacheService.getByUserCode(userAccount.getUserCode());
        String token = UUID.randomUUID().toString().replace("-","");
        if(userCacheInfo != null){
            token = userCacheInfo.getToken();
        }
        LoginResponse response = LoginResponse.builder()
                .userCode(userAccount.getUserCode())
                .satoken(token)
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

    public static void errorRecord(String account){
        if(errorLoginRecord.containsKey(account)){
            errorLoginRecord.put(account,errorLoginRecord.get(account)+1);
        }else{
            errorLoginRecord.put(account,1);
        }
    }
}
