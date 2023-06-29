package site.achun.video.app.service.business.playlist;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.PlayList;
import site.achun.video.app.generator.domain.PlayListRecord;
import site.achun.video.app.generator.service.PlayListRecordService;
import site.achun.video.app.generator.service.PlayListService;
import site.achun.video.client.module.playlist.request.CreatePlayListRecord;

import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:45
 */
@Slf4j
@Service
@AllArgsConstructor
public class PlayListRecordUpdateService {

    private final PlayListRecordService playListRecordService;

    private final PlayListService playListService;
    public boolean createPlayListRecord(CreatePlayListRecord createPlayListRecord){
        PlayListRecord existRecord = playListRecordService.getBy(createPlayListRecord.getPlistCode(), createPlayListRecord.getVideoCode());
        if(existRecord!=null){
            throw new RuntimeException("记录已存在");
        }
        PlayListRecord record = PlayListRecord.builder()
                .plistCode(createPlayListRecord.getPlistCode())
                .videoCode(createPlayListRecord.getVideoCode())
                .ctime(LocalDateTime.now())
                .build();
        return playListRecordService.save(record);
    }

    public Rsp<Integer> addOrRemoveRecord(CreatePlayListRecord req){
        PlayList pList = playListService.getByCode(req.getPlistCode());
        PlayListRecord existRecord = playListRecordService.getBy(req.getPlistCode(), req.getVideoCode());
        if(existRecord!=null){
            playListRecordService.lambdaUpdate()
                    .eq(PlayListRecord::getPlistCode,req.getPlistCode())
                    .eq(PlayListRecord::getVideoCode,req.getVideoCode())
                    .remove();
            return Rsp.success(-1,"从【"+pList.getName()+"】中删除了视频");
        }else{
            PlayListRecord record = PlayListRecord.builder()
                    .plistCode(req.getPlistCode())
                    .videoCode(req.getVideoCode())
                    .ctime(LocalDateTime.now())
                    .build();
            playListRecordService.save(record);
            return Rsp.success(1,"添加视频到【"+pList.getName()+"】");
        }
    }
}
