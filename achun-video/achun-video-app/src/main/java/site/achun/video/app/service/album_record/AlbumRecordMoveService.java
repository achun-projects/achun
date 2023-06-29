package site.achun.video.app.service.album_record;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.achun.video.app.generator.service.AlbumRecordService;
import site.achun.video.app.generator.service.AlbumService;
import site.achun.video.app.generator.domain.AlbumRecord;
import site.achun.video.client.module.album.request.MoveAlbumRecordRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * 相册内转移记录服务
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/13
 */
@Slf4j
@Service
@AllArgsConstructor
public class AlbumRecordMoveService {

    private final AlbumRecordService albumRecordService;
    private final AlbumService albumService;

    @Transactional
    public void move(MoveAlbumRecordRequest request) {
        // 删除旧记录
        albumRecordService.lambdaUpdate()
                .in(AlbumRecord::getSetCode,request.getSetCodes())
                .remove();
        // 添加新纪录
        albumRecordService.saveBatch(
                request.getSetCodes().stream()
                        .map(setCode-> AlbumRecord.builder()
                                .setCode(setCode)
                                .albumCode(request.getAlbumCode())
                                .ctime(LocalDateTime.now())
                                .build()
                        ).collect(Collectors.toList())
        );
        // 更新相册记录时间
        albumService.updateRecordUtime(request.getAlbumCode());
    }

}
