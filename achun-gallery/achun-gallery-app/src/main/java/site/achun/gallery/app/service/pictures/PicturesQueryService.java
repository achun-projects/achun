package site.achun.gallery.app.service.pictures;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.pictures.request.RandomQueryPageOfPicturesRequest;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.RspPage;

@Slf4j
@Service
@RequiredArgsConstructor
public class PicturesQueryService {

    private final PicturesService picturesService;
    private final PictureConvertService pictureConvertService;
    public RspPage<Photo> randomQueryPageOfPictures(RandomQueryPageOfPicturesRequest request) {
        Page<Pictures> rspPage = picturesService.lambdaQuery()
                .orderByDesc(Pictures::getFileCode)
                .page(Page.of(request.getReqPage().getPage(), request.getReqPage().getSize()));
        return PageUtil.batchParse(rspPage,request.getReqPage(),pictureConvertService::toPhotos);
    }
}
