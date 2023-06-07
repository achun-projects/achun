package site.achun.gallery.client.module.album;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import site.achun.gallery.client.module.album.request.QueryAlbumDetail;
import site.achun.gallery.client.module.album.request.QueryAlbumPage;
import site.achun.gallery.client.module.album.response.AlbumResponse;
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
