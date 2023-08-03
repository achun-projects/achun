package site.achun.video.app.controller.video;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.service.business.video.VideoRemoveService;
import site.achun.video.app.service.business.video.VideoUpdateService;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.video.request.CreateOrUpdateVideoRequest;
import site.achun.video.client.module.video.request.RemoveVideoRequest;
import site.achun.video.client.module.video.response.VideoInfoResponse;


/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/17 15:03
 */
@Tag(name = "视频增删改")
@Slf4j
@RestController
@AllArgsConstructor
public class VideoUpdateController {

    private final VideoUpdateService videoUpdateService;
    private final VideoRemoveService videoRemoveService;

    @Operation(summary = "创建视频信息")
    @PostMapping("/video/create/create-or-update-video")
    public Rsp<VideoInfoResponse> createOrUpdateVideo(@RequestBody @Valid CreateOrUpdateVideoRequest createVideo){
        String userCode = UserInfo.getCode();
        createVideo.setUserCode(userCode);
        return videoUpdateService.createOrUpdateVideo(createVideo);
    }

    @Operation(summary = "删除视频")
    @PostMapping("/video/update/remove-video-unit")
    public Rsp<Void> removeVideo(@RequestBody @Valid RemoveVideoRequest request){
        videoRemoveService.removeVideo(request);
        return Rsp.success(null);
    }
}
