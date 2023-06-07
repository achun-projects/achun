package site.achun.gallery.app.service.ablum;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.client.constant.GalleryRC;
import site.achun.gallery.client.module.album.request.QueryAlbumPage;
import site.achun.gallery.client.module.album.response.AlbumResponse;
import site.achun.support.api.exception.RspException;
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
    private MediaFileQueryClient fileQueryClient;

    public Rsp<RspPage<AlbumResponse>> page(QueryAlbumPage query){
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
            throw new RspException(GalleryRC.DB_QUERY_RESULT_EMPTY,"查询不到相册:%s",albumCode);
        }
        String coverFileCode = detail.getCoverFileCode();
        QueryByFileCode queryFile = QueryByFileCode.builder().fileCode(coverFileCode).build();
        MediaFileResponse coverFile = fileQueryClient.queryFile(queryFile).tryGetData();
        if(coverFile != null){
            detail.setCover(coverFile.getMediumUrl());
        }
        return detail;
    }

}
