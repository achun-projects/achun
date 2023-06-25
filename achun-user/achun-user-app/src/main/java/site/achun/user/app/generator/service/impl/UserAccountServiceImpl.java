package site.achun.user.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.user.app.generator.domain.UserAccount;
import site.achun.user.app.generator.service.UserAccountService;
import site.achun.user.app.generator.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user_account】的数据库操作Service实现
* @createDate 2023-06-25 18:19:36
*/
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount>
    implements UserAccountService{

}




