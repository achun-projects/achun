package site.achun.gallery.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.gallery.app.generator.domain.User;
import site.achun.gallery.app.generator.service.UserService;
import site.achun.gallery.app.generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-03 14:34:08
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




