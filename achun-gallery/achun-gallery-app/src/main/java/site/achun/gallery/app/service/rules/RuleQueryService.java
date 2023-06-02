package site.achun.gallery.app.service.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.service.list.ListRandomQueryService;
import site.achun.gallery.app.service.pictures.PicturesQueryService;
import site.achun.gallery.client.module.pictures.response.PicturesBasicInfo;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleQueryService {

    private final ListRandomQueryService listRandomQueryService;
    private final PicturesQueryService picturesQueryService;

    public List<PicturesBasicInfo> queryFilesByRuleCode(String ruleCode){
        FromList fromList = map.get(ruleCode);
        String aFileCode = listRandomQueryService.randomQuery(fromList.getALists());
        String bFileCode = listRandomQueryService.randomQuery(fromList.getBLists());

        List<PicturesBasicInfo> respList = new ArrayList<>();
        respList.add(picturesQueryService.queryBasicInfo(aFileCode));
        respList.add(picturesQueryService.queryBasicInfo(bFileCode));

        return respList;
    }


    private final static Map<String,FromList> map = new HashMap<>();
    static{
        map.put("10001",new FromList(Arrays.asList("3694407292"),Arrays.asList("3694407292")));
        map.put("10002",new FromList(Arrays.asList(""),Arrays.asList("")));
        map.put("10003",new FromList(Arrays.asList(""),Arrays.asList("")));
    }


    @Data
    @AllArgsConstructor
    public static class FromList{
        private Collection<String> aLists;
        private Collection<String> bLists;
    }
}
