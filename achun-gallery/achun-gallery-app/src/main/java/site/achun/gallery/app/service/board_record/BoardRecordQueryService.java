package site.achun.gallery.app.service.board_record;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
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

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardRecordQueryService {

    private final PicturesMapper picturesMapper;
    private final PictureConvertService pictureConvertService;

    public Rsp<RspPage<Photo>> queryPage(QueryRecord queryRecord) {
        Set<String> codes = queryRecord.getCodes();
        if(codes != null){
            HashSet<String> newSet = new HashSet<>();
            newSet.addAll(codes);
            queryRecord.setCodes(newSet);
        }
        if(queryRecord.getCode()!=null){
            if(queryRecord.getCodes()==null){
                HashSet<String> newSet = new HashSet<>();
                newSet.add(queryRecord.getCode());
                queryRecord.setCodes(newSet);
            }else{
                queryRecord.getCodes().add(queryRecord.getCode());
            }
        }
        IPage<Pictures> pageResp = picturesMapper.selectBoardFiles(PageUtil.parse(queryRecord.getReqPage()), queryRecord);
        if(CollUtil.isEmpty(pageResp.getRecords())){
            return Rsp.success(queryRecord.getReqPage().createPageRsp());
        }
        return Rsp.success(PageUtil.batchParse(pageResp,queryRecord.getReqPage(),pictureConvertService::toPhotos));
    }

}
