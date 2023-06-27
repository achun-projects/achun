package site.achun.gallery.app.service.rules;

import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.service.list.ListRandomQueryService;
import site.achun.gallery.app.service.rules.beans.BaseRule;
import site.achun.gallery.app.service.rules.beans.RuleType;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleQueryService {

    private final ListRandomQueryService listRandomQueryService;
    private final MediaFileQueryClient mediaFileQueryClient;

    private final List<QueryHandler> queryHandlers;

    public List<MediaFileResponse> queryFilesByRuleCode(String ruleCode){
        FromList fromList = map.get(ruleCode);
        String aFileCode = listRandomQueryService.randomQuery(fromList.getALists());
        String bFileCode = listRandomQueryService.randomQuery(fromList.getBLists());

        List<MediaFileResponse> respList = new ArrayList<>();
        respList.add(mediaFileQueryClient.queryFile(QueryByFileCode.builder().fileCode(aFileCode).build()).getData());
        respList.add(mediaFileQueryClient.queryFile(QueryByFileCode.builder().fileCode(bFileCode).build()).getData());

        return respList;
    }

    public List<MediaFileResponse> queryFilesByRuleCodeV2(String ruleCode){
        String rule = ruleMap.get(ruleCode);
        List<BaseRule> baseRule = JSON.parseArray(rule, BaseRule.class);
        List<WeightRandom.WeightObj<BaseRule>> weights = baseRule.stream()
                .map(r -> new WeightRandom.WeightObj<>(r, r.getWeight()))
                .collect(Collectors.toList());
        BaseRule randomBaseRule = RandomUtil.weightRandom(weights).next();

        RuleType ruleType = RuleType.parse(randomBaseRule.getType());
        QueryHandler queryHandler = queryHandlers.stream().filter(handler -> handler.match(ruleType)).findFirst().get();
        return queryHandler.query(randomBaseRule.getData());
    }


    private final static Map<String,FromList> map = new HashMap<>();
    static{
        map.put("10001",new FromList(Arrays.asList("ada5c7965ddd4b0da14beb83b057af5c"),Arrays.asList("f142ad4998c4478080416ffb44cc4923")));
        map.put("10002",new FromList(Arrays.asList("417e4b72f0284fa8ae533ad38e00ce76"),Arrays.asList("f142ad4998c4478080416ffb44cc4923")));
        map.put("10003",new FromList(Arrays.asList(""),Arrays.asList("")));
    }


    @Data
    @AllArgsConstructor
    public static class FromList{
        private Collection<String> aLists;
        private Collection<String> bLists;
    }


    private final static Map<String,String> ruleMap = new HashMap<>();
    static{
        ruleMap.put("10001",
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
