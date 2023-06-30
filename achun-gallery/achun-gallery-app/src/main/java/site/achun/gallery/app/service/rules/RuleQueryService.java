package site.achun.gallery.app.service.rules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.service.rules.beans.BaseRule;
import site.achun.gallery.app.service.rules.beans.RuleType;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleQueryService {

    private final List<QueryHandler> queryHandlers;

    private final MediaFileQueryClient mediaFileQueryClient;

    private final StringRedisTemplate redisTemplate;
    private final static String RULES_KEY = "GALLERY:RULES:%s";


    public List<String> queryFileCodesByRuleCode(String ruleCode){
        String key = String.format(RULES_KEY,ruleCode);
        if(!redisTemplate.hasKey(key)){
            throw new RuntimeException("不存在该规则");
        }
        String rule = redisTemplate.opsForValue().get(key);
        List<BaseRule> baseRule = JSON.parseArray(rule, BaseRule.class);
        List<WeightRandom.WeightObj<BaseRule>> weights = baseRule.stream()
                .map(r -> new WeightRandom.WeightObj<>(r, r.getWeight()))
                .collect(Collectors.toList());
        BaseRule randomBaseRule = RandomUtil.weightRandom(weights).next();

        RuleType ruleType = RuleType.parse(randomBaseRule.getType());
        QueryHandler queryHandler = queryHandlers.stream().filter(handler -> handler.match(ruleType)).findFirst().get();
        return queryHandler.query(randomBaseRule.getData());
    }

    public List<MediaFileResponse> queryFilesByRuleCode(String ruleCode){
        List<String> fileCodes = queryFileCodesByRuleCode(ruleCode);
        if(CollUtil.isEmpty(fileCodes)){
            return null;
        }
        Map<String, MediaFileResponse> fileMap = mediaFileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(fileCodes).build()).getData();
        return fileCodes.stream()
                .map(fileCode -> fileMap.get(fileCode))
                .collect(Collectors.toList());

    }
}
