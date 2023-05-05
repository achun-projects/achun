package site.achun.gallery.app.dubbo.consumer;


import cn.virde.common.pojo.rsp.Rsp;
import cn.virde.platform.api.module.auth.response.LoginUserResponse;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserConsumerTest {

    @Autowired
    private LoginConsumer loginConsumer;

    @Test
    public void execute(){
        Rsp<LoginUserResponse> rsp = loginConsumer.getUserFacade().login("virde", "yunbiddn", 11);
        log.info("rsp:{}", JSON.toJSONString(rsp));
    }
}
