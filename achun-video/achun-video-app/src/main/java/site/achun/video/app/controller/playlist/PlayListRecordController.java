package site.achun.video.app.controller.playlist;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.service.business.playlist.PlayListRecordUpdateService;
import site.achun.video.client.module.playlist.request.CreatePlayListRecord;


/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:28
 */
@Tag(name = "播放列表记录Controller")
@RestController
@RequiredArgsConstructor
public class PlayListRecordController {

    private final PlayListRecordUpdateService playListRecordUpdateService;
    // 新增记录，查询分页记录
//    @NeedLogin
    @Operation(summary = "创建播放列表记录")
    @PostMapping("/video/play-list-record/create-play-list-record")
    public Rsp<Boolean> createPlayListRecord(@RequestBody CreatePlayListRecord createPlayListRecord){
        return Rsp.success(playListRecordUpdateService.createPlayListRecord(createPlayListRecord));
    }

//    @NeedLogin
    @Operation(summary = "新增或删除记录")
    @PostMapping("/video/play-list-record/add-or-remove-record")
    public Rsp<Integer> addOrRemoveRecord(@RequestBody @Valid CreatePlayListRecord createPlayListRecord){
        return playListRecordUpdateService.addOrRemoveRecord(createPlayListRecord);
    }

}
