package site.achun.video.app.service.ablum;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.client.module.album.request.QueryAlbumPage;
import site.achun.video.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumQueryService {

    private final MyAlbumService myAlbumService;
    private final MediaFileQueryClient fileQueryClient;

    public RspPage<AlbumResponse> page(QueryAlbumPage query){
        // 获取相册分页数据
        RspPage<AlbumResponse> rspPage = myAlbumService.page(query);
        if(CollectionUtil.isEmpty(rspPage.getRows())){
            return rspPage;
        }
        // 获取相册封面
        Set<String> coverFileCodes = rspPage.getRows().stream()
                .map(AlbumResponse::getCoverFileCode)
                .collect(Collectors.toSet());
        if(CollectionUtil.isEmpty(coverFileCodes)){
            return rspPage;
        }
        QueryByFileCodes queryFileMap = QueryByFileCodes.builder().fileCodes(coverFileCodes).build();
        Map<String, MediaFileResponse> coverFileMap = fileQueryClient.queryFileMap(queryFileMap).tryGetData();
        rspPage.getRows().stream().forEach(a->{
            if(coverFileMap != null && coverFileMap.containsKey(a.getCoverFileCode())){
                a.setCover(coverFileMap.get(a.getCoverFileCode()).getMediumUrl());
            }
        });
        return rspPage;
    }

    public AlbumResponse detail(String albumCode,String userCode){
        AlbumResponse detail = myAlbumService.queryDetail(albumCode, userCode);
        if(detail == null){
            return null;
        }
        String coverFileCode = detail.getCoverFileCode();
        if(StrUtil.isNotBlank(coverFileCode)){
            QueryByFileCode queryFile = QueryByFileCode.builder().fileCode(coverFileCode).build();
            Rsp<MediaFileResponse> coverFileRsp = fileQueryClient.queryFile(queryFile);
            if(coverFileRsp != null && coverFileRsp.hasData()){
                detail.setCover(coverFileRsp.tryGetData().getCover());
            }else{
                log.info("相册{}的封面文件{}查询为空",albumCode,coverFileCode);
            }
        }
        return detail;
    }

}
