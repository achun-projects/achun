package site.achun.gallery.client.module.album_record;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.album.request.MoveAlbumRecordRequest;
import site.achun.gallery.client.module.album_record.request.UploadPictures;
import site.achun.gallery.client.module.fileset.request.AnewFileSet;
import site.achun.gallery.client.module.fileset.response.FileSetResponse;
import site.achun.support.api.response.Rsp;

import java.util.Collection;

@FeignClient(name = "achun-gallery-app", contextId = "AlbumRecordUpdateClient")
public interface AlbumRecordUpdateClient {

    @Operation(summary = "创建相册记录,上传图片")
    @PostMapping("/gallery/album-record/upload-pictures")
    Rsp<FileSetResponse> uploadPictures(@RequestBody UploadPictures createInfo);

    @Operation(summary = "重新分组")
    @PostMapping("/gallery/album-record/anew-fileset")
    Rsp<Boolean> anewFileSet(@RequestBody @Valid AnewFileSet anewGroup);

    @Operation(summary = "移动一个相册内文件组，到另一个相册。")
    @PostMapping("/gallery/album-record/move")
    Rsp<Boolean> moveRecords(@RequestBody MoveAlbumRecordRequest req);

    @Operation(summary = "批量删除文件")
    @PostMapping("/gallery/album-record/batch-delete")
    Rsp<Boolean> deleteBatchRecords(@RequestBody Collection<String> fileCodes);
}
