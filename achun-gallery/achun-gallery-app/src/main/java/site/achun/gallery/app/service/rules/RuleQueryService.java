package site.achun.gallery.app.service.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.client.module.pictures.response.PicturesBasicInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RuleQueryService {



    public List<PicturesBasicInfo> queryFilesByRuleCode(String ruleCode){
        return null;
    }


    private final static Map<String,FromList> map = new HashMap<>();
    static{
        map.put("10001",new FromList("",""));
        map.put("10002",new FromList("",""));
        map.put("10003",new FromList("",""));
    }


    @Data
    @AllArgsConstructor
    public static class FromList{
        private String aList;
        private String bList;
    }
}
