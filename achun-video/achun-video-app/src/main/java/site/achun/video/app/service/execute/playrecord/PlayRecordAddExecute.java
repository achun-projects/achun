package site.achun.video.app.service.execute.playrecord;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.PlayRecord;
import site.achun.video.app.generator.service.PlayRecordService;
import site.achun.video.client.module.record.request.AddPlayRecord;
import site.achun.video.client.module.record.response.PlayRecordResponse;

import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/30 23:13
 */
@Slf4j
@Service
@AllArgsConstructor
public class PlayRecordAddExecute {

    private final PlayRecordService playRecordService;

    public Rsp<PlayRecordResponse> newRecord(AddPlayRecord addPlayRecord) {
        PlayRecord playRecord = BeanUtil.toBean(addPlayRecord, PlayRecord.class);
        playRecord.setCtime(LocalDateTime.now());
        playRecordService.save(playRecord);
        return Rsp.success(BeanUtil.toBean(playRecord,PlayRecordResponse.class));
    }

}
