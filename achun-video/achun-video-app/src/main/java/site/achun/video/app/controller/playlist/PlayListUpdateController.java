package site.achun.video.app.controller.playlist;

import cn.hutool.core.util.StrUtil;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.service.business.playlist.PlayListUpdateService;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.constant.PlaylistTypeEnum;
import site.achun.video.client.module.playlist.request.CreatePlayList;
import site.achun.video.client.module.playlist.request.RemovePlaylist;
import site.achun.video.client.module.playlist.request.UpdatePlayList;
import site.achun.video.client.module.playlist.response.PlayListResponse;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:03
 */
@Tag(name = "播放列表-Update")
@RestController
@RequiredArgsConstructor
public class PlayListUpdateController {

    private final PlayListUpdateService playListUpdateService;

//    @NeedLogin
    @Operation(summary = "创建播放列表")
    @PostMapping("/video/play-list/create-play-list")
    public Rsp<PlayListResponse> createPlayList(@RequestBody CreatePlayList createPlayList){
        if(StrUtil.isEmpty(createPlayList.getObjectCode())){
            createPlayList.setObjectCode(UserInfo.getCode(createPlayList::getObjectCode));
            createPlayList.setObjectType(PlaylistTypeEnum.USER);
        }else{
            if(createPlayList.getObjectType()==null){
                throw new RuntimeException("类型必传");
            }
        }
        return Rsp.success(playListUpdateService.createPlayList(createPlayList));
    }

//    @NeedLogin
    @Operation(summary = "更新播放列表")
    @PostMapping("/video/play-list/update-play-list")
    public Rsp<PlayListResponse> updatePlayList(@RequestBody UpdatePlayList updatePlayList){
        updatePlayList.setUserCode(UserInfo.getCode(updatePlayList::getUserCode));
        return Rsp.success(playListUpdateService.updatePlayList(updatePlayList));
    }

//    @NeedLogin
    @Operation(summary = "删除播放列表")
    @PostMapping("/video/play-list/remove-play-list")
    public Rsp<Boolean> removePlayList(@RequestBody RemovePlaylist request){
        request.setUserCode(UserInfo.getCode(request::getUserCode));
        return Rsp.success(playListUpdateService.removePlayList(request));
    }

}
