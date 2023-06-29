package site.achun.video.app.service.album_record;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.Pictures;
import site.achun.video.app.generator.mapper.PicturesMapper;
import site.achun.video.app.generator.service.AlbumRecordService;
import site.achun.video.app.service.pictures.PictureConvertService;
import site.achun.video.app.utils.PageUtil;
import site.achun.video.client.module.pictures.request.QueryRecord;
import site.achun.video.client.module.pictures.response.Photo;
import site.achun.video.client.module.pictures.response.PictureResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumRecordQueryService {

    private final PicturesMapper picturesMapper;
    private final PictureConvertService pictureConvertService;

    private final AlbumRecordService albumRecordService;

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
