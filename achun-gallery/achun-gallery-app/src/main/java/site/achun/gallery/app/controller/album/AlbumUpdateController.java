package site.achun.gallery.app.controller.album;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.client.module.album.AlbumUpdateClient;
import site.achun.gallery.client.module.album.request.CreateOrUpdateAlbum;
import site.achun.gallery.client.module.album.request.RemoveAlbumRequest;
import site.achun.gallery.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "相册更新")
@RestController
@RequiredArgsConstructor
public class AlbumUpdateController implements AlbumUpdateClient {

    @Override
    public Rsp<AlbumResponse> createOrUpdate(CreateOrUpdateAlbum request) {
        return null;
    }

    @Override
    public Rsp<Boolean> removeWhenEmpty(RemoveAlbumRequest request) {
        return null;
    }
}
