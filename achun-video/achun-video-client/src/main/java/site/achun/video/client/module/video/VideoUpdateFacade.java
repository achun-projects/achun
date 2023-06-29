package site.achun.video.client.module.video;


import site.achun.support.api.response.Rsp;
import site.achun.video.client.module.video.request.CreateOrUpdateVideoRequest;
import site.achun.video.client.module.video.response.VideoInfoResponse;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/12 10:43
 */
public interface VideoUpdateFacade {

    /**
     *
     * @param request
     * @return
     */
    Rsp<VideoInfoResponse> createOrUpdateVideo(CreateOrUpdateVideoRequest request);
}
