package site.achun.gallery.app.controller.pictures;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.pictures.PicturesQueryService;
import site.achun.gallery.client.module.pictures.PicturesQueryClient;
import site.achun.gallery.client.module.pictures.request.RandomQueryPageOfPicturesRequest;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@Slf4j
@Tag(name = "查询图片")
@RestController
@RequiredArgsConstructor
public class PicturesQueryController implements PicturesQueryClient {

    private final PicturesQueryService picturesQueryService;
    @Override
    public Rsp<RspPage<Photo>> randomQueryPageOfPictures(RandomQueryPageOfPicturesRequest request) {
        return Rsp.success(picturesQueryService.randomQueryPageOfPictures(request));
    }
}
