package site.achun.gallery.app.controller.album;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.ablum.AlbumQueryService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.album.AlbumQueryClient;
import site.achun.gallery.client.module.album.request.QueryAlbumDetail;
import site.achun.gallery.client.module.album.request.QueryAlbumPage;
import site.achun.gallery.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@Slf4j
@Tag(name = "相册查询")
@RestController
@RequiredArgsConstructor
public class AlbumQueryController implements AlbumQueryClient {

    private final AlbumQueryService albumQueryService;
    @Override
    public Rsp<RspPage<AlbumResponse>> page(QueryAlbumPage query) {
        String userCode = StrUtil.isNotEmpty(query.getUserCode())?query.getUserCode(): UserInfo.getCode();
        query.setUserCode(userCode);
        if(StrUtil.isNotEmpty(query.getLikeName())){
            query.setLikeName("%"+query.getLikeName()+"%");
        }
        return albumQueryService.page(query);
    }

    @Override
    public Rsp<AlbumResponse> detail(QueryAlbumDetail query) {
        String userCode = StrUtil.isNotEmpty(query.getUserCode())?query.getUserCode(): UserInfo.getCode();
        return Rsp.success(albumQueryService.detail(query.getAlbumCode(),userCode));
    }
}
