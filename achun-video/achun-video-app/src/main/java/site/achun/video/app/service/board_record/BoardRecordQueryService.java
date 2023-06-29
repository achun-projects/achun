package site.achun.video.app.service.board_record;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.Pictures;
import site.achun.video.app.generator.mapper.PicturesMapper;
import site.achun.video.app.service.pictures.PictureConvertService;
import site.achun.video.app.utils.PageUtil;
import site.achun.video.client.module.pictures.request.QueryRecord;
import site.achun.video.client.module.pictures.response.Photo;
import site.achun.video.client.module.pictures.response.PictureResponse;
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
        RspPage<PictureResponse> rspPage = PageUtil.parse(pageResp,pictureConvertService::toResponse,queryRecord.getReqPage());

        if(CollUtil.isEmpty(rspPage.getRows())){
            RspPage result = rspPage;
            return Rsp.success(result);
        }
        return pictureConvertService.toPhotoPage(rspPage);
    }

}
