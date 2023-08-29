package site.achun.gallery.client.module.pictures;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.pictures.request.RandomQueryPageOfPicturesRequest;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@FeignClient(name = "achun-gallery-app", contextId = "PictureQueryClient")
public interface PicturesQueryClient {

    @Operation(summary = "随机查询图片")
    @PostMapping("/gallery/picture-query/random-query-page-of-pictures")
    Rsp<RspPage<Photo>> randomQueryPageOfPictures(@RequestBody RandomQueryPageOfPicturesRequest request);

}
