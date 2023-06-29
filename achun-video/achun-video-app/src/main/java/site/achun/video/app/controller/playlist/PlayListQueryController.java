package site.achun.video.app.controller.playlist;

import cn.hutool.core.util.StrUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;
import site.achun.video.app.service.business.playlist.PlayListQueryService;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.constant.PlaylistTypeEnum;
import site.achun.video.client.module.element.ElSelectView;
import site.achun.video.client.module.playlist.request.QueryPlayListPageRequest;
import site.achun.video.client.module.playlist.request.QueryPlaylistDetailInfo;
import site.achun.video.client.module.playlist.request.QueryVideoAddToPlayList;
import site.achun.video.client.module.playlist.response.PlayListPreviewResponse;
import site.achun.video.client.module.playlist.response.PlayListResponse;

import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:24
 */
@Tag(name = "播放列表-Query")
@RestController
@RequiredArgsConstructor
public class PlayListQueryController {

    private final PlayListQueryService playListQueryService;

//    @NeedLogin
    @Operation(summary = "查询播放列表分页")
    @PostMapping("/video/play-list/query-play-list-page")
    public Rsp<RspPage<PlayListResponse>> queryPlayListPage(@RequestBody QueryPlayListPageRequest request){
        if(StrUtil.isEmpty(request.getObjectCode())){
            request.setObjectCode(UserInfo.getCode());
            request.setObjectType(PlaylistTypeEnum.USER);
        }
        return Rsp.success(playListQueryService.queryPlayListPage(request));
    }

//    @NeedLogin
    @Operation(summary = "查询播放列表分页-携带视频预览数据")
    @PostMapping("/video/play-list/query-play-list-preview-page")
    public Rsp<RspPage<PlayListPreviewResponse>> queryPlayListPreviewPage(@RequestBody QueryPlayListPageRequest request){
        if(StrUtil.isEmpty(request.getObjectCode())){
            request.setObjectCode(UserInfo.getCode());
            request.setObjectType(PlaylistTypeEnum.USER);
        }
        return Rsp.success(playListQueryService.queryPlayListPreviewPage(request));
    }

//    @NeedLogin
    @Operation(summary = "查询视频可加入的播放列表")
    @PostMapping("/video/play-list/query-video-add-to-play-list")
    public Rsp<List<ElSelectView>> queryVideoAddToPlaylist(@RequestBody QueryVideoAddToPlayList query){
        if(StrUtil.isEmpty(query.getObjectCode()) && query.getObjectType()==null){
            query.setObjectCode(UserInfo.getCode(query::getObjectCode));
        }
        return Rsp.success(playListQueryService.queryVideoAddToPlaylist(query));
    }
//    @NeedLogin
    @Operation(summary = "查询播放列表详情信息")
    @PostMapping("/video/play-list/query-play-list-detail-info")
    public Rsp<PlayListResponse> queryPlaylistDetailInfo(@RequestBody @Valid QueryPlaylistDetailInfo query){
        query.setObjectCode(UserInfo.getCode(query::getObjectCode));
        return Rsp.success(playListQueryService.queryPlaylistDetailInfo(query));
    }

}
