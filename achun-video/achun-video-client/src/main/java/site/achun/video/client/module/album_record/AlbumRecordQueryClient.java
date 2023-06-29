package site.achun.video.client.module.album_record;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.video.client.module.pictures.request.QueryRecord;
import site.achun.video.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@FeignClient(name = "achun-gallery-app", contextId = "AlbumRecordQueryClient")
public interface AlbumRecordQueryClient {

    @Operation(summary = "分页查询相册记录")
    @PostMapping("/gallery/album-record/query-picture-page")
    Rsp<RspPage<Photo>> queryPageOfAlbumPictures(@RequestBody QueryRecord query);

}
