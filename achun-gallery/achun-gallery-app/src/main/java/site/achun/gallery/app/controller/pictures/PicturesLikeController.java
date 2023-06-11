package site.achun.gallery.app.controller.pictures;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.generator.domain.PicturesLike;
import site.achun.gallery.app.generator.service.PicturesLikeService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.pictures.PicturesLikeClient;
import site.achun.gallery.client.module.pictures.request.PictureLikeRequest;
import site.achun.support.api.response.Rsp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "喜欢图片")
@RestController
@RequiredArgsConstructor
public class PicturesLikeController implements PicturesLikeClient {

    private final PicturesLikeService picturesLikeService;
    @Override
    public Rsp<Void> batchMarkLike(PictureLikeRequest request) {
        request.setUserCode(UserInfo.getCode(request::getUserCode));
        List<PicturesLike> picturesLikeList = request.getFileCodes().stream()
                .map(fileCode ->
                        PicturesLike.builder()
                                .ctime(LocalDateTime.now())
                                .userCode(request.getUserCode())
                                .listCode(request.getListCode())
                                .fileCode(fileCode)
                                .build()
                ).collect(Collectors.toList());
        for (PicturesLike picturesLike : picturesLikeList) {
            if(picturesLikeService.getByCode(picturesLike.getListCode(),picturesLike.getFileCode(), picturesLike.getUserCode())==null){
                picturesLikeService.save(picturesLike);
            }else{
                log.info("已存在：{}，{}，{}",picturesLike.getListCode(),picturesLike.getFileCode(), picturesLike.getUserCode());
            }
        }
        return Rsp.success(null,"保存成功");
    }

    @Override
    public Rsp<Void> batchRemoveLike(PictureLikeRequest request) {
        request.setUserCode(UserInfo.getCode(request::getUserCode));
        picturesLikeService.lambdaUpdate()
                .in(PicturesLike::getFileCode,request.getFileCodes())
                .eq(PicturesLike::getListCode,request.getListCode())
                .eq(PicturesLike::getUserCode,request.getUserCode())
                .remove();
        return Rsp.success(null,"操作成功");
    }
}
