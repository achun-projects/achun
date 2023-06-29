package site.achun.video.app.service.ablum.execute;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.Album;
import site.achun.video.app.generator.domain.GalleryGroupRecord;
import site.achun.video.app.generator.service.AlbumService;
import site.achun.video.app.generator.service.GalleryGroupRecordService;
import site.achun.video.client.constant.GalleryRC;
import site.achun.video.client.module.album.request.CreateOrUpdateAlbum;
import site.achun.support.api.exception.RspException;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumCreateExecute {

    private final AlbumService albumService;
    private final GalleryGroupRecordService groupRecordService;

    public String createAlbum(CreateOrUpdateAlbum request){
        log.info("创建相册请求参数：{}",request);
        // 检查相册是否存在
        if(StrUtil.isNotEmpty(request.getAlbumCode()) && albumService.getByCode(request.getAlbumCode())!=null){
            log.info("该相册编码已存在，albumCode:{}", request.getAlbumCode());
            throw new RspException(GalleryRC.EXISTS,"该相册编码已存在，请确认后重新提交创建");
        }
        // 实际插入
        Album album = toAlbum(request);
        albumService.save(album);
        // 保存分组信息
        GalleryGroupRecord groupRecord = GalleryGroupRecord.builder()
                .groupCode(request.getGroupCode())
                .listCode(album.getAlbumCode())
                .ctime(LocalDateTime.now())
                .build();
        groupRecordService.save(groupRecord);
        return album.getAlbumCode();
    }

    private Album toAlbum(CreateOrUpdateAlbum createRequest){
        String albumCode = StrUtil.isNotEmpty(createRequest.getAlbumCode()) ?
                createRequest.getAlbumCode() : UUID.randomUUID().toString().replace("-","");
        return Album.builder()
                .albumCode(albumCode)
                .description(createRequest.getDescription())
                .source(createRequest.getSource())
                .name(createRequest.getName())
                .userCode(createRequest.getUserCode())
                .coverFileCode(createRequest.getCoverFileCode())
                .ctime(LocalDateTime.now()).utime(LocalDateTime.now())
                .recordUtime(LocalDateTime.now())
                .build();
    }

}
