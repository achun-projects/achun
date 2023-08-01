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
    private final static Integer ERROR_LIMIT_COUNT = 5;

    @Override
    public Rsp<LoginResponse> login(LoginRequest request) {
        if(errorLoginRecord.containsKey(request.getAccount()) && errorLoginRecord.get(request.getAccount()) > ERROR_LIMIT_COUNT){
            log.info("登录失败，账号锁定:{}",request.getAccount());
            errorRecord(request.getAccount());
            return Rsp.error("账号锁定");
        }
        String password = RsaUtil.decrypt(request.getPassword(), RsaUtil.DEFAULT_RSA_PRIVATE_KEY);
        if(password==null){
            log.info("登录失败，密码错误:{},password:{}",request.getAccount(),request.getPassword());
            errorRecord(request.getAccount());
            return Rsp.error("登录失败");
        }
        UserAccount userAccount = userAccountService.lambdaQuery()
                .eq(UserAccount::getAccount, request.getAccount())
                .eq(UserAccount::getPassword, password)
                .one();
        if(userAccount == null){
            log.info("登录失败，账号不存在:{},password:{}",request.getAccount(),password);
            errorRecord(request.getAccount());
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
        log.info("登录成功：{}",request.getAccount());
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
