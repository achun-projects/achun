package site.achun.gallery.app.service.album_record;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.mapper.AlbumRecordMapper;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import site.achun.gallery.app.generator.service.AlbumRecordService;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.gallery.app.service.list.ListRandomQueryService;
import site.achun.gallery.app.service.pictures.PictureConvertService;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.album_record.request.QueryPageOfSetWithPics;
import site.achun.gallery.client.module.album_record.request.RandomQueryFromAlbum;
import site.achun.gallery.client.module.album_record.response.SetWithPicsResponse;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.gallery.client.module.pictures.response.PictureResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumRecordQueryService {

    private final PicturesMapper picturesMapper;
    private final PicturesService picturesService;
    private final PictureConvertService pictureConvertService;
    private final AlbumRecordMapper albumRecordMapper;

    public Rsp<RspPage<Photo>> queryPage(QueryRecord queryRecord){
        IPage<Pictures> pageResp = picturesMapper.selectAlbumFiles(PageUtil.parse(queryRecord.getReqPage()), queryRecord);
        if(CollUtil.isEmpty(pageResp.getRecords())){
            return Rsp.success(queryRecord.getReqPage().createPageRsp());
        }
        return Rsp.success(PageUtil.batchParse(pageResp,queryRecord.getReqPage(),pictureConvertService::toPhotos));
    }

    public RspPage<SetWithPicsResponse> queryPageOfSet(QueryPageOfSetWithPics req) {
        IPage<FileSet> pageResp = albumRecordMapper.selectPageOfSet(PageUtil.parse(req.getReqPage()), req);
        if(CollUtil.isEmpty(pageResp.getRecords())){
            return req.getReqPage().createPageRsp();
        }
        RspPage<SetWithPicsResponse> respPage = PageUtil.batchParse(pageResp, req.getReqPage(), fs -> BeanUtil.copyToList(fs, SetWithPicsResponse.class));

        // 补充文件
        List<String> fileSetCodes = respPage.getRows().stream()
                .map(SetWithPicsResponse::getCode)
                .distinct()
                .collect(Collectors.toList());
        List<Pictures> pictures = picturesService.getByFileSetCodes(fileSetCodes);
        List<Photo> photos = pictureConvertService.toPhotos(pictures);
        Map<String, List<Photo>> map = photos.stream()
                .collect(Collectors.groupingBy(Photo::getUnitCode));

        respPage.getRows().stream()
                .forEach(r -> {
                    if(map.containsKey(r.getCode())){
                        List<Photo> list = map.get(r.getCode());
                        r.setPhotosCount(list.size());
                        if(list.size() > 9) list = list.subList(0,9);
                        r.setPhotos(list);
                    }
                });
        return respPage;
    }

}
