package site.achun.gallery.client.module.album;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.album.request.CreateOrUpdateAlbum;
import site.achun.gallery.client.module.album.request.RemoveAlbumRequest;
import site.achun.gallery.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "AlbumUpdateClient")
public interface AlbumUpdateClient {

    @Operation(summary = "创建或更新相册")
    @PostMapping("/gallery/album/create-or-update")
    Rsp<AlbumResponse> createOrUpdate(@RequestBody CreateOrUpdateAlbum request);


    @Operation(summary = "删除相册（为空时）")
    @PostMapping("/gallery/album/delete-when-empty")
    Rsp<Boolean> removeWhenEmpty(@RequestBody RemoveAlbumRequest request);

}
