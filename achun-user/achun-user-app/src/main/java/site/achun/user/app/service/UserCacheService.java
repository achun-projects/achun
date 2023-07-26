package site.achun.user.app.service;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import site.achun.user.app.service.dto.UserCacheInfo;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCacheService {
    private final static String KEY = "USER:LOGIN:%s:%s";
    private final StringRedisTemplate redisTemplate;

    private final static Long DEFAULT_TIMEOUT = 60 * 60 * 24 * 2L; // 2天

    /**
     * 设置缓存
     * @param token token
     * @param userCode 用户编码
     * @param timeout 失效时间，单位秒
     */
    public void put(String token,String userCode,Long timeout){
        if(timeout==null || timeout <= 0){
            timeout = DEFAULT_TIMEOUT;
        }
        String key = String.format(KEY,token,userCode);
        UserCacheInfo info = UserCacheInfo.builder()
                .timeout(timeout)
                .token(token)
                .userCode(userCode)
                .build();
        redisTemplate.opsForValue().set(key, JSON.toJSONString(info),timeout, TimeUnit.SECONDS);
    }

    public UserCacheInfo getByToken(String token){
        String key = String.format(KEY,token,"*");
        if(!redisTemplate.hasKey(key)){
            return null;
        }
        String json = redisTemplate.opsForValue().get(key);
        return JSON.parseObject(json,UserCacheInfo.class);
    }

    public UserCacheInfo getByUserCode(String userCode){
        String key = String.format(KEY,"*",userCode);
        if(!redisTemplate.hasKey(key)){
            return null;
        }
        String json = redisTemplate.opsForValue().get(key);
        return JSON.parseObject(json,UserCacheInfo.class);
    }

}
