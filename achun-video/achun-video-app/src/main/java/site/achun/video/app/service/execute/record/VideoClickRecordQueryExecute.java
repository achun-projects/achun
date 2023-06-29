package site.achun.video.app.service.execute.record;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.mapper.VideoClickRecordMapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/17 18:15
 */
@Slf4j
@Service
@AllArgsConstructor
public class VideoClickRecordQueryExecute {

    private final VideoClickRecordMapper videoClickRecordMapper;

    public Map<String,Integer> queryVideoClickNum(Collection<String> videoCodes){
        List<ObjectNum> resp = videoClickRecordMapper.selectClickNum(VideoClickRecordMapper.Request.builder().videoCodes(videoCodes).build());
        if(CollUtil.isEmpty(resp)) return new HashMap<>();
        return resp.stream()
                .collect(Collectors.toMap(ObjectNum::getCode,ObjectNum::getNum,(v1,v2)->v1));
    }

}
