package site.achun.gallery.app.service.rules;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import site.achun.gallery.client.module.rules.beans.BaseRule;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleCacheService {

    private final static String RULES_KEY = "GALLERY:RULES:%s";

    private final StringRedisTemplate redisTemplate;

    public List<BaseRule> get(String ruleCode){
        String key = String.format(RULES_KEY,ruleCode);
        if(!redisTemplate.hasKey(key)){
            throw new RuntimeException("不存在该规则");
        }
        String rule = redisTemplate.opsForValue().get(key);
        List<BaseRule> baseRule = JSON.parseArray(rule, BaseRule.class);
        return baseRule;
    }

    public void set(String ruleCode,List<BaseRule> rules){
        String key = String.format(RULES_KEY,ruleCode);
        redisTemplate.opsForValue().set(key,JSON.toJSONString(rules));
    }

}
