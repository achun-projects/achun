package site.achun.gallery.app.service.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.service.list.ListRandomQueryService;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleQueryService {

    private final ListRandomQueryService listRandomQueryService;
    private final MediaFileQueryClient mediaFileQueryClient;

    public List<MediaFileResponse> queryFilesByRuleCode(String ruleCode){
        FromList fromList = map.get(ruleCode);
        String aFileCode = listRandomQueryService.randomQuery(fromList.getALists());
        String bFileCode = listRandomQueryService.randomQuery(fromList.getBLists());

        List<MediaFileResponse> respList = new ArrayList<>();
        respList.add(mediaFileQueryClient.queryFile(QueryByFileCode.builder().fileCode(aFileCode).build()).getData());
        respList.add(mediaFileQueryClient.queryFile(QueryByFileCode.builder().fileCode(bFileCode).build()).getData());

        return respList;
    }


    private final static Map<String,FromList> map = new HashMap<>();
    static{
        map.put("10001",new FromList(Arrays.asList("c6e537af1c5c4a2ca416da6ad7064493","7d6217e8da7445d89d2c3259afc1bd7a"),Arrays.asList("f142ad4998c4478080416ffb44cc4923")));
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
