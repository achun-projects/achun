package site.achun.gallery.app.service.ablum.execute;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Album;
import site.achun.gallery.app.generator.domain.GalleryGroupRecord;
import site.achun.gallery.app.generator.response.AlbumExtra;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.app.service.ablum.AlbumUpdateService;
import site.achun.gallery.client.constant.GalleryRC;
import site.achun.gallery.client.module.album.request.CreateOrUpdateAlbum;
import site.achun.support.api.exception.RspException;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumUpdateExecute {

    private final AlbumService albumService;
    private final GalleryGroupRecordService groupRecordService;

    public void updateAlbum(CreateOrUpdateAlbum request){
        Album album = albumService.getByCode(request.getAlbumCode(),request.getUserCode());
        if(album == null){
            throw new RspException(GalleryRC.ALBUM_NOT_EXIST);
        }
        // 更新相册
        albumService.lambdaUpdate()
                .eq(Album::getAlbumCode,request.getAlbumCode())
                .set(StrUtil.isNotEmpty(request.getCoverFileCode()),
                        Album::getCoverFileCode,request.getCoverFileCode())
                .set(StrUtil.isNotEmpty(request.getName()),
                        Album::getName,request.getName())
                .set(StrUtil.isNotEmpty(request.getDescription()),
                        Album::getDescription,request.getDescription())
                .set(StrUtil.isNotEmpty(request.getSource()),
                        Album::getSource,request.getSource())
                .set(Album::getUtime, LocalDateTime.now())
                .set(Album::getRecordUtime,LocalDateTime.now())
                .update();

        if(StrUtil.isNotEmpty(request.getDirCode())){
            AlbumExtra extra = album.getExtra();
            if(extra == null) extra = new AlbumExtra();
            extra.setDirCode(request.getDirCode());
            albumService.updateById(album);
        }
        if(StrUtil.isNotEmpty(request.getGroupCode())){
            groupRecordService.lambdaUpdate()
                    .eq(GalleryGroupRecord::getListCode,album.getAlbumCode())
                    .remove();
            GalleryGroupRecord record = GalleryGroupRecord.builder()
                    .groupCode(request.getGroupCode())
                    .listCode(album.getAlbumCode())
                    .ctime(LocalDateTime.now())
                    .build();
            groupRecordService.save(record);
        }
    }
}
