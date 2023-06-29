package site.achun.video.app.service.business.playlist;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.utils.CodeGenUtil;
import site.achun.video.app.generator.domain.PlayList;
import site.achun.video.app.generator.domain.PlayListRecord;
import site.achun.video.app.generator.service.PlayListRecordService;
import site.achun.video.app.generator.service.PlayListService;
import site.achun.video.app.service.execute.playlist.PlayListConvertExecute;
import site.achun.video.client.module.playlist.request.CreatePlayList;
import site.achun.video.client.module.playlist.request.RemovePlaylist;
import site.achun.video.client.module.playlist.request.UpdatePlayList;
import site.achun.video.client.module.playlist.response.PlayListResponse;

import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:19
 */
@Slf4j
@Service
@AllArgsConstructor
public class PlayListUpdateService {

    private final PlayListService playListService;
    private final PlayListConvertExecute playListConvertExecute;
    private final PlayListRecordService playListRecordService;

    public PlayListResponse createPlayList(@RequestBody CreatePlayList createPlayList) {
        PlayList existPlayList = playListService.getByName(createPlayList.getName(), createPlayList.getObjectCode());
        if(existPlayList != null){
            throw new RuntimeException("已存在记录，无法创建");
        }
        var playList = PlayList.builder()
                .plistCode(CodeGenUtil.uuid())
                .name(createPlayList.getName())
                .description(createPlayList.getDescription())
                .objectCode(createPlayList.getObjectCode())
                .objectType(createPlayList.getObjectType())
                .ctime(LocalDateTime.now())
                .utime(LocalDateTime.now())
                .ruTime(LocalDateTime.now())
                .build();
        boolean result = playListService.save(playList);
        log.info("播放列表是否保存成功：{}",result);
        return playListConvertExecute.toResponse(playList);
    }

    public PlayListResponse updatePlayList(@RequestBody UpdatePlayList updatePlayList){
        PlayList playList = playListService.getByCode(updatePlayList.getPlistCode());
        if(playList == null){
            throw new RuntimeException("不存在记录，无法更新");
        }
        boolean result = playListService.lambdaUpdate()
                .eq(PlayList::getPlistCode, updatePlayList.getPlistCode())
                .set(updatePlayList.getName() != null, PlayList::getName, updatePlayList.getName())
                .set(updatePlayList.getDescription() != null, PlayList::getDescription, updatePlayList.getDescription())
                .set(PlayList::getUtime,LocalDateTime.now())
                .update();
        log.info("更新播放列表结果：{}",result);
        return playListConvertExecute.toResponse(playListService.getByCode(updatePlayList.getPlistCode()));
    }

    public boolean removePlayList(RemovePlaylist request){
        PlayList playList = playListService.getByCode(request.getPlistCode());
        if(playList == null){
            throw new RuntimeException("不存在记录，无法更新");
        }
        Long recordCount = 0L;
        if(!request.getForce()){
            recordCount = playListRecordService.lambdaQuery()
                    .eq(PlayListRecord::getPlistCode, request.getPlistCode())
                    .count();
            if(recordCount > 0){
                throw new RuntimeException("还存在记录，无法删除");
            }
        }
        if(recordCount > 0){
            playListRecordService.lambdaUpdate()
                    .eq(PlayListRecord::getPlistCode,request.getPlistCode())
                    .remove();
        }
        playListService.lambdaUpdate()
                .eq(PlayList::getPlistCode,request.getPlistCode())
                .remove();
        return true;
    }

}
