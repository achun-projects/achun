package site.achun.updown.app.dubbo.provider;

import site.achun.updown.api.modules.module1.UserFacade;
import site.achun.updown.api.modules.module1.response.UserResponse;
import site.achun.updown.app.generator.domain.User;
import site.achun.updown.app.generator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
@RequiredArgsConstructor
public class UserDubboService implements UserFacade {

    private final UserService userService;
    @Override
    public UserResponse queryUser(String likeName) {
        User user = userService.lambdaQuery()
                .like(User::getName, "%" + likeName + "%")
                .last("limit 1")
                .one();
        return UserResponse.builder()
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }
}
