package site.achun.video.app.service.execute.playrecord;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.PlayRecord;
import site.achun.video.app.generator.service.PlayRecordService;
import site.achun.video.client.module.record.response.PlayRecordResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/30 23:27
 */
@Slf4j
@Service
@AllArgsConstructor
public class PlayRecordQueryExecute {

    private final PlayRecordService playRecordService;

    public Rsp<List<PlayRecordResponse>> queryRecent(Integer limit) {
        List<PlayRecord> list = playRecordService.query()
                .select("max(id) as id", "video_file_code")
                .orderByDesc("id")
                .groupBy("video_file_code")
                .last("limit "+limit)
                .list();
        List<PlayRecordResponse> result = list.stream()
                .map(record -> BeanUtil.toBean(record, PlayRecordResponse.class))
                .collect(Collectors.toList());
        return Rsp.success(result);
    }
}
