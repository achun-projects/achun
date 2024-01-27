package site.achun.gallery.client.module.album_record;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.album_record.request.QueryPageOfSetWithPics;
import site.achun.gallery.client.module.album_record.request.RandomQueryFromAlbum;
import site.achun.gallery.client.module.album_record.response.SetWithPicsResponse;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;

@FeignClient(name = "achun-gallery-app", contextId = "AlbumRecordQueryClient")
public interface AlbumRecordQueryClient {

    @Operation(summary = "分页查询相册记录")
    @PostMapping("/gallery/album-record/query-picture-page")
    Rsp<RspPage<Photo>> queryPageOfAlbumPictures(@RequestBody QueryRecord query);

    @Operation(summary = "随机查询一个分组")
    @PostMapping("/gallery/album-record/random-query-one-unit")
    Rsp<List<Photo>> randomQueryOneUnit(@RequestBody RandomQueryFromAlbum query);

    @Operation(summary = "随机查询一个图")
    @PostMapping("/gallery/album-record/random-query-one-photo")
    Rsp<Photo> randomQueryOnePhoto(@RequestBody RandomQueryFromAlbum query);

    @Operation(summary = "分页查询分组（带图）")
    @PostMapping("/gallery/album-record/query-page-of-set-with-pics")
    Rsp<RspPage<SetWithPicsResponse>> queryPageOfSet(@RequestBody QueryPageOfSetWithPics req);

}
