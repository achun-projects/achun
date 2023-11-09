package site.achun.video.client.module.video;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.Rsp;
import site.achun.video.client.module.video.request.CreateOrUpdateVideoRequest;
import site.achun.video.client.module.video.request.RemoveVideoRequest;
import site.achun.video.client.module.video.response.VideoInfoResponse;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/12 10:43
 */
@FeignClient(name = "achun-video-app", contextId = "VideoUpdateFacade")
public interface VideoUpdateClient {

    @Operation(summary = "创建视频信息")
    @PostMapping("/video/create/create-or-update-video")
    Rsp<VideoInfoResponse> createOrUpdateVideo(@RequestBody @Valid CreateOrUpdateVideoRequest createVideo);


    @Operation(summary = "删除视频")
    @PostMapping("/video/update/remove-video-unit")
    Rsp<Void> removeVideo(@RequestBody @Valid RemoveVideoRequest request);

}
