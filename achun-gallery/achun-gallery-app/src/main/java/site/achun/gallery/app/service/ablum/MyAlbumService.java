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
import site.achun.gallery.app.service.ablum.execute.AsyncFillFileCountExecute;
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

    private final AlbumQueryExecute albumQueryExecute;

    private final AsyncFillFileCountExecute asyncFillFileCountExecute;

    public RspPage<AlbumResponse> page(QueryAlbumPage query){
        RspPage<AlbumResponse> rspPage = query.getReqPage().createPageRsp();
        Page<Album> pageData = albumMapper.queryPage(Page.of(query.getReqPage().getPage(), query.getReqPage().getSize()), query);
        if(pageData == null || CollectionUtil.isEmpty(pageData.getRecords())){
            return rspPage;
        }
        rspPage.setTotal(pageData.getTotal());
        rspPage.setRows(pageData.getRecords().stream()
                .map(album -> albumQueryExecute.toAlbumResponse(album,false))
                .collect(Collectors.toList())
        );
        // 补充相册图片数量
        Set<String> albumCodes = pageData.getRecords().stream()
                .map(Album::getAlbumCode)
                .collect(Collectors.toSet());
        asyncFillFileCountExecute.asyncFillFileCount(albumCodes);
        return rspPage;
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
