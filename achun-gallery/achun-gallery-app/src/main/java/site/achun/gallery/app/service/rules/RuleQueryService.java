package site.achun.gallery.app.service.rules;

import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public List<MediaFileResponse> queryFilesByRuleCodeV2(String ruleCode){
        String rule = ruleMap.get(ruleCode);
        List<BaseRule> baseRule = JSON.parseArray(rule, BaseRule.class);
        List<WeightRandom.WeightObj<BaseRule>> weights = baseRule.stream()
                .map(r -> new WeightRandom.WeightObj<>(r, r.getWeight()))
                .collect(Collectors.toList());
        BaseRule randomBaseRule = RandomUtil.weightRandom(weights).next();

        RuleType ruleType = RuleType.parse(randomBaseRule.getType());
        QueryHandler queryHandler = queryHandlers.stream().filter(handler -> handler.match(ruleType)).findFirst().get();
        List<String> fileCodes = queryHandler.query(randomBaseRule.getData());
        Map<String, MediaFileResponse> fileMap = mediaFileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(fileCodes).build()).getData();
        return fileCodes.stream()
                .map(fileCode -> fileMap.get(fileCode))
                .collect(Collectors.toList());

    }

    private final static Map<String,String> ruleMap = new HashMap<>();
    static{
        ruleMap.put("10001",
                """
                [{"weight": 100,"type": "ALBUM_UNIT_RANDOM","data": {"albumCodes":["1779654914"],"maxCount":9   }},{"weight": 100,"type": "FROM_LISTS","data": [{"name": "正文","values": ["ada5c7965ddd4b0da14beb83b057af5c"]},{"name": "辅文","values": ["f142ad4998c4478080416ffb44cc4923"]}]}]
                """
        );

        ruleMap.put("10011",
                """
                [{"weight":100,"type":"FROM_LISTS","data":[{"name":"正文","values":["ada5c7965ddd4b0da14beb83b057af5c"]},{"name":"辅文","values":["f142ad4998c4478080416ffb44cc4923"]}]}]    
                """
                );

        ruleMap.put("10002",
                """
                [{"weight":100,"type":"FROM_LISTS","data":[{"name":"正文","values":["417e4b72f0284fa8ae533ad38e00ce76"]},{"name":"辅文","values":["f142ad4998c4478080416ffb44cc4923"]}]}]  
                """
        );
    }
}
