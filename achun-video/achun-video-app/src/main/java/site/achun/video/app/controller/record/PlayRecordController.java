package site.achun.video.app.controller.record;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.VideoClickRecord;
import site.achun.video.app.generator.service.VideoClickRecordService;
import site.achun.video.app.service.business.playrecord.PlayRecordService;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.record.request.AddPlayRecord;
import site.achun.video.client.module.video.response.Video;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/30 10:50
 */
@Tag(name = "播放记录")
@RestController
@AllArgsConstructor
public class PlayRecordController {

    private final PlayRecordService playRecordService;
    private final VideoClickRecordService videoClickRecordService;

    @Operation(summary = "查询播放记录")
    @GetMapping("/video/play-record/recent")
    public Rsp<List<Video>> queryRecentPlayRecord(@RequestParam("limit") Integer limit){
        return playRecordService.queryRecent(limit);
    }

    @Operation(summary = "新增播放记录")
    @PostMapping("/video/play-record/new-record")
    public Rsp<Boolean> addNewRecord(@RequestBody @Valid AddPlayRecord addPlayRecord){
        playRecordService.newRecord(addPlayRecord);
        return Rsp.success(Boolean.TRUE);
    }

//    @NeedLogin
    @Operation(summary = "新增视频点击记录")
    @PostMapping("/video/click/new-record")
    public Rsp<Boolean> addNewVideoClickRecord(@RequestBody @Valid VideoClickRecord record){
        record.setCtime(LocalDateTime.now());
        record.setLoginUserCode(UserInfo.getCode(record::getLoginUserCode));
        videoClickRecordService.save(record);
        return Rsp.success(Boolean.TRUE);
    }

}
