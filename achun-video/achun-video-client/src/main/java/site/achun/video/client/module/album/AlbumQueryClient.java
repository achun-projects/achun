package site.achun.video.client.module.album;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.video.client.module.album.request.QueryAlbumDetail;
import site.achun.video.client.module.album.request.QueryAlbumPage;
import site.achun.video.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@FeignClient(name = "achun-gallery-app", contextId = "AlbumQueryClient")
public interface AlbumQueryClient {
    @Operation(summary = "分页查询相册")
    @PostMapping("/gallery/album/page")
    Rsp<RspPage<AlbumResponse>> page(@RequestBody QueryAlbumPage query);

    @Operation(summary = "查询相册详情")
    @PostMapping("/gallery/album/detail")
    Rsp<AlbumResponse> detail(@RequestBody QueryAlbumDetail query);
}
