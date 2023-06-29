package site.achun.gateway.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import site.achun.support.api.response.Rsp;
import site.achun.user.client.module.login.UserLoginClient;
import site.achun.user.client.module.login.response.LoginResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@AllArgsConstructor
public class UserLoginService {

    private final ApplicationContext applicationContext;

    public String getUserCode(String satoken){
        UserLoginClient userLoginClient = applicationContext.getBean(UserLoginClient.class);
        CompletableFuture<Rsp<LoginResponse>> f = CompletableFuture.supplyAsync(()->{
               return userLoginClient.checkToken(satoken);
        });
        Rsp<LoginResponse> loginResponse = null;
        try {
            loginResponse = f.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        if(loginResponse.hasData()){
            return loginResponse.getData().getUserCode();
        }else{
            return "1";
        }
    }
}
