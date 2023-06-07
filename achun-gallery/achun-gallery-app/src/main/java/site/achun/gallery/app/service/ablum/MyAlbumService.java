package site.achun.gallery.app.service.ablum;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Album;
import site.achun.gallery.app.generator.mapper.AlbumMapper;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.app.service.ablum.execute.AlbumQueryExecute;
import site.achun.gallery.app.service.pictures.MyPictureService;
import site.achun.gallery.client.module.album.request.QueryAlbumPage;
import site.achun.gallery.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.RspPage;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/9 16:50
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyAlbumService {

    private final AlbumService albumSerivce;

    private final AlbumMapper albumMapper;

    private final GalleryGroupRecordService groupRecordService;

    private final MyPictureService myPictureService;

    private final AlbumQueryExecute albumQueryExecute;

    public RspPage<AlbumResponse> page(QueryAlbumPage query){
        // 如果分组不为空，先查分组列表集合
        Set<String> listCodes = null;
        if(StrUtil.isNotEmpty(query.getGroupCode())){
            listCodes = groupRecordService.queryListCodes(query.getGroupCode());
            // 分组列表集合为空，说明此分组无数据，返回空
            if(CollUtil.isEmpty(listCodes)){
                return query.getReqPage().createPageRsp();
            }
        }
        RspPage<AlbumResponse> rspPage = query.getReqPage().createPageRsp();
        Page<Album> pageData = new LambdaQueryChainWrapper<>(albumMapper)
                .eq(Album::getUserCode, query.getUserCode())
                .like(StrUtil.isNotEmpty(query.getLikeName()),Album::getName,query.getLikeName())
                .in(CollUtil.isNotEmpty(listCodes), Album::getAlbumCode,listCodes)
                .orderByDesc(Album::getRecordUtime)
                .page(Page.of(query.getReqPage().getPage(), query.getReqPage().getSize()));
        if(pageData == null || CollectionUtil.isEmpty(pageData.getRecords())){
            return rspPage;
        }
        rspPage.setTotal(pageData.getTotal());
        rspPage.setRows(pageData.getRecords().stream()
                .map(albumQueryExecute::toAlbumResponse)
                .collect(Collectors.toList())
        );
        // 补充相册图片数量
        Set<String> albumCodes = pageData.getRecords().stream()
                .map(Album::getAlbumCode)
                .collect(Collectors.toSet());
        asyncFillFileCount(albumCodes);
        return rspPage;
    }

    @Async
    public void asyncFillFileCount(Set<String> albumCodes){
        Map<String, Integer> albumCountMap = myPictureService.queryAlbumFileCounts(albumCodes);
        for (String albumCode : albumCodes) {
            albumSerivce.lambdaUpdate()
                    .eq(Album::getAlbumCode,albumCode)
                    .set(Album::getFileCount,albumCountMap.get(albumCode))
                    .update();
        }
    }

    public AlbumResponse queryDetail(String albumCode,String userCode){
        Album album = albumSerivce.lambdaQuery()
                .eq(Album::getAlbumCode,albumCode)
                .eq(Album::getUserCode,userCode)
                .one();
        if(album == null){
            return null;
        }
        return albumQueryExecute.toAlbumResponse(album);
    }

}
