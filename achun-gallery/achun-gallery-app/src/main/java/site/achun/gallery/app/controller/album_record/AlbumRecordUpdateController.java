package site.achun.gallery.app.controller.album_record;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.DeleteFileRequest;
import site.achun.gallery.app.service.album_record.AlbumRecordMoveService;
import site.achun.gallery.app.service.pictures.PictureUpdateExecute;
import site.achun.gallery.app.service.pictures.PictureUpdateService;
import site.achun.gallery.app.service.pictures.UploadPictureService;
import site.achun.gallery.app.service.unit.AnewFileSetService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.album.request.MoveAlbumRecordRequest;
import site.achun.gallery.client.module.album_record.AlbumRecordUpdateClient;
import site.achun.gallery.client.module.album_record.request.UploadPictures;
import site.achun.gallery.client.module.fileset.request.AnewFileSet;
import site.achun.gallery.client.module.fileset.response.FileSetResponse;
import site.achun.gallery.client.module.pictures.request.UploadPicturesRequest;
import site.achun.support.api.response.Rsp;

import java.util.Collection;

@Slf4j
@Tag(name = "相册记录更新")
@RestController
@RequiredArgsConstructor
public class AlbumRecordUpdateController implements AlbumRecordUpdateClient {

    private final UploadPictureService uploadPictureService;
    private final AnewFileSetService anewFileSetService;
    private final AlbumRecordMoveService albumRecordMoveService;
    private final PictureUpdateService pictureUpdateService;
    private final FileUpdateClient fileUpdateClient;

    @Override
    public Rsp<FileSetResponse> uploadPictures(UploadPictures createInfo) {
        createInfo.setUserCode(UserInfo.getCode(createInfo::getUserCode));
        UploadPicturesRequest createFileSet = UploadPicturesRequest.builder()
                .setCode(createInfo.getSetCode())
                .albumCode(createInfo.getAlbumCode())
                .tags(createInfo.getTags())
                .name(createInfo.getName())
                .desc(createInfo.getDesc())
                .userCode(createInfo.getUserCode())
                .build();
        return uploadPictureService.createFileSet(createFileSet);
    }

    @Override
    public Rsp<Boolean> anewFileSet(AnewFileSet anewGroup) {
        anewFileSetService.anewFileset(anewGroup);
        return Rsp.success(true);
    }

    @Override
    public Rsp<Boolean> moveRecords(MoveAlbumRecordRequest req) {
        albumRecordMoveService.move(req);
        return Rsp.success(true);
    }

    @Override
    public Rsp<Boolean> deleteBatchRecords(Collection<String> fileCodes) {
        pictureUpdateService.remove(fileCodes);
        return fileUpdateClient.deleteFileInfos(DeleteFileRequest.builder().fileCodes(fileCodes).build());
    }
}
