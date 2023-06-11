package site.achun.gallery.client.module.pictures;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.pictures.request.PictureLikeRequest;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "PicturesLikeClient")
public interface PicturesLikeClient {

    @Operation(summary = "批量标记喜欢的图片")
    @PostMapping("/gallery/picture-like/batch-mark")
    Rsp<Void> batchMarkLike(@RequestBody PictureLikeRequest request);

    /**
     *
     * @param fileCodes
     * @return
     */
    @Operation(summary = "批量删除已标记的图片")
    @PostMapping("/gallery/picture-like/batch-remove")
    Rsp<Void> batchRemoveLike(@RequestBody PictureLikeRequest request);
}
