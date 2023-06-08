package site.achun.gallery.app.service.album_record;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import site.achun.gallery.app.service.pictures.PictureConvertService;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.gallery.client.module.pictures.response.PictureResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumRecordQueryService {

    private final PicturesMapper picturesMapper;
    private final PictureConvertService pictureConvertService;

    public Rsp<RspPage<Photo>> queryPage(QueryRecord queryRecord){

        IPage<Pictures> pageResp = picturesMapper.selectAlbumFiles(PageUtil.parse(queryRecord.getReqPage()), queryRecord);
        RspPage<PictureResponse> rspPage = PageUtil.parse(pageResp,pictureConvertService::toResponse,queryRecord.getReqPage());

        if(CollUtil.isEmpty(rspPage.getRows())){
            RspPage result = rspPage;
            return Rsp.success(result);
        }
        return pictureConvertService.toPhotoPage(rspPage);
    }
}