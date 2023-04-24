package site.achun.updown.app.generator.service;

import site.achun.updown.app.generator.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save(){
        String name = "小李"+System.currentTimeMillis();
        User user = new User();
        user.setName(name);
        user.setPhone(System.currentTimeMillis()+"");
        user.setCtime(LocalDateTime.now());
        user.setUtime(LocalDateTime.now());
        userService.save(user);
        log.info("New user,id:{},name:{}",user.getId(),user.getName());
    }
}
