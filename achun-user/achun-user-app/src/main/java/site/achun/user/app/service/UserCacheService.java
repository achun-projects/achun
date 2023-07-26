package site.achun.user.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCacheService {
    private final static String KEY = "USER:LOGIN:%s";
    private final StringRedisTemplate redisTemplate;

    /**
     * 设置缓存
     * @param token token
     * @param userCode 用户编码
     * @param timeout 失效时间，单位秒
     */
    public void put(String token,String userCode,Long timeout){
        String key = String.format(KEY,token);
        redisTemplate.opsForValue().set(key,userCode,timeout, TimeUnit.SECONDS);
    }

    public String get(String token){
        String key = String.format(KEY,token);
        if(!redisTemplate.hasKey(key)){
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }

}
