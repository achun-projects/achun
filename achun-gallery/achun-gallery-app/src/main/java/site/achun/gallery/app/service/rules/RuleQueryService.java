package site.achun.gallery.app.service.rules;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.generator.domain.QueryRule;
import site.achun.gallery.app.generator.service.QueryRuleService;
import site.achun.gallery.client.module.rules.beans.BaseRule;
import site.achun.gallery.client.module.rules.beans.RuleType;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.rules.requset.QueryRulesPage;
import site.achun.support.api.response.RspPage;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleQueryService {

    private final List<QueryHandler> queryHandlers;

    private final MediaFileQueryClient mediaFileQueryClient;

    private final StringRedisTemplate redisTemplate;

    private final QueryRuleService queryRuleService;
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

    public RspPage<QueryRule> queryRulesPage(QueryRulesPage req){
        Page<QueryRule> page = queryRuleService.lambdaQuery()
                .eq(QueryRule::getUserCode, req.getUserCode())
                .page(Page.of(req.getPage().getPage(), req.getPage().getSize()));
        return PageUtil.parse(page,req.getPage());
    }
}
