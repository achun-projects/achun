package site.achun.video.app.service.ablum.execute;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.Album;
import site.achun.video.app.generator.domain.AlbumRecord;
import site.achun.video.app.generator.domain.GalleryGroupRecord;
import site.achun.video.app.generator.domain.Pictures;
import site.achun.video.app.generator.service.AlbumRecordService;
import site.achun.video.app.generator.service.AlbumService;
import site.achun.video.app.generator.service.GalleryGroupRecordService;
import site.achun.video.app.service.list.ListFilesQueryExecute;
import site.achun.video.client.constant.GalleryRC;
import site.achun.video.client.module.album.request.RemoveAlbumRequest;
import site.achun.support.api.response.Rsp;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumRemoveExecute {

    private final AlbumService albumService;
    private final AlbumRecordService albumRecordService;
    private final GalleryGroupRecordService groupRecordService;

    private final ListFilesQueryExecute listFilesQueryExecute;

    public Rsp<Boolean> removeWhenEmpty(RemoveAlbumRequest request) {
        // 检测相册是否存在
        Album album = albumService.lambdaQuery()
                .eq(Album::getAlbumCode, request.getAlbumCode())
                .eq(Album::getUserCode, request.getUserCode())
                .one();
        if(album==null){
            return Rsp.error(GalleryRC.ALBUM_NOT_EXIST);
        }
        // 检测相册是否为空
        IPage<Pictures> pageResp = listFilesQueryExecute.queryPages(request.getAlbumCode(),1,10);

        if(pageResp.getTotal() > 0){
            return Rsp.error(GalleryRC.ALBUM_RECORD_NOT_EMPTY,"相册内容不为空，无法删除");
        }
        albumService.removeById(album);
        albumRecordService.lambdaUpdate()
                .eq(AlbumRecord::getAlbumCode,request.getAlbumCode())
                .remove();
        // 清除相册分组记录
        groupRecordService.lambdaUpdate()
                .eq(GalleryGroupRecord::getListCode,request.getAlbumCode())
                .remove();
        return Rsp.success(null,"成功删除");
    }

}
