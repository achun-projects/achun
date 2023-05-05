package site.achun.gallery.app.dubbo.consumer;

import cn.virde.platform.api.module.auth.UserFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class LoginConsumer{

    @DubboReference
    private UserFacade userFacade;


    public UserFacade getUserFacade() {
        return userFacade;
    }
}
