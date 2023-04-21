package site.achun.file.api.modules.module1;

import site.achun.file.api.modules.module1.response.UserResponse;

public interface UserFacade {

    UserResponse queryUser(String likeName);
}
