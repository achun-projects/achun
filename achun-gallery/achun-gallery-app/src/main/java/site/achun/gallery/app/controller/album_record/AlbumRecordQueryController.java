package site.achun.gallery.app.controller.album_record;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.achun.gallery.app.service.album_record.AlbumRecordQueryService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.album_record.AlbumRecordQueryClient;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@Slf4j
@Tag(name = "相册记录查询")
@RestController
@RequiredArgsConstructor
public class AlbumRecordQueryController implements AlbumRecordQueryClient {

    private final AlbumRecordQueryService recordQueryService;
    @Override
    public Rsp<RspPage<Photo>> queryPageOfAlbumPictures(QueryRecord query) {
        if(CollUtil.isNotEmpty(query.getExcludeBoardCodes())){
            query.getExcludeBoardCodes().remove(null);
        }
        if(StrUtil.isNotBlank(query.getSearch())){
            query.setSearch("%"+query.getSearch()+"%");
        }

        query.setUserCode(UserInfo.getCode(query::getUserCode));
        return recordQueryService.queryPage(query);
    }
}
