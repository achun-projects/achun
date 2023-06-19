package site.achun.user.app.controller.data;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.user.app.utils.UserInfo;
import site.achun.user.client.module.data.UserDataQueryClient;
import site.achun.user.client.module.data.UserDataUpdateClient;
import site.achun.user.client.module.data.request.QueryUserData;
import site.achun.user.client.module.data.request.UpdateUserData;
import site.achun.user.client.module.data.response.UserDataResponse;

import java.util.concurrent.TimeUnit;

@Slf4j
@Tag(name = "用户数据")
@RequiredArgsConstructor
@RestController
public class UserDataController implements UserDataQueryClient, UserDataUpdateClient {

    private final StringRedisTemplate redisTemplate;

    @Override
    public Rsp<UserDataResponse> queryUserData(QueryUserData query) {
        query.setUserCode(UserInfo.getCode(query::getUserCode));
        String key = String.format("USER:DATA:%s:%s",query.getUserCode(),query.getKey());
        if(!redisTemplate.hasKey(key)){
            return Rsp.error("no data");
        }
        String value = redisTemplate.opsForValue().getAndExpire(key,100,TimeUnit.DAYS);
        UserDataResponse response = BeanUtil.toBean(query, UserDataResponse.class);
        response.setValue(JSONObject.parseObject(value));
        return Rsp.success(response);
    }

    @Override
    public Rsp<UserDataResponse> updateUserData(UpdateUserData update) {
        update.setUserCode(UserInfo.getCode(update::getUserCode));
        String key = String.format("USER:DATA:%s:%s",update.getUserCode(),update.getKey());

        redisTemplate.opsForValue().set(key, JSON.toJSONString(update.getValue()),100, TimeUnit.DAYS);
        UserDataResponse response = BeanUtil.toBean(update, UserDataResponse.class);
        return Rsp.success(response);
    }
}
