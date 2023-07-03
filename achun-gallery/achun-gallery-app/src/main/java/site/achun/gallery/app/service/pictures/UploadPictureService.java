package site.achun.gallery.app.service.pictures;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.service.album_record.AlbumRecordUpdateExecute;
import site.achun.gallery.app.service.unit.PicUnitQueryExecute;
import site.achun.gallery.app.service.unit.PicUnitUpdateExecute;
import site.achun.gallery.client.module.pic_unit.request.CreatePicUnit;
import site.achun.gallery.client.module.pic_unit.response.PicUnitResponse;
import site.achun.gallery.client.module.pictures.request.UploadPicturesRequest;
import site.achun.support.api.response.Rsp;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadPictureService{

    private final AlbumService albumService;
    private final PicUnitUpdateExecute fileSetUpdateExecute;
    private final PicUnitQueryExecute fileSetQueryExecute;
    private final AlbumRecordUpdateExecute albumRecordUpdateExecute;

    public Rsp<PicUnitResponse> createFileSet(UploadPicturesRequest createFileSet) {
        // 新建分组
        fileSetUpdateExecute.createOrUpdate(
                CreatePicUnit.builder()
                        .setCode(createFileSet.getSetCode())
                        .userCode(createFileSet.getUserCode())
                        .name(createFileSet.getName())
                        .desc(createFileSet.getDesc())
                        .build()
        );
        // 创建相册记录
        albumRecordUpdateExecute.createAlbumRecord(createFileSet.getAlbumCode(),createFileSet.getSetCode());
        // 更新相册访问时间
        albumService.updateRecordUtime(createFileSet.getAlbumCode());
        return Rsp.success(fileSetQueryExecute.queryByUnitCode(createFileSet.getSetCode()));
    }
}
