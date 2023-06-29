package site.achun.video.app.service.execute.video;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.utils.DateTimeUtil;
import site.achun.video.client.module.channel.response.ChannelResponse;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.util.Map;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/17 16:29
 */
public class VideoConvert {

    public static VideoInfoResponse toResponse(VideoInfo videoInfo){
        VideoInfoResponse response = BeanUtil.toBean(videoInfo, VideoInfoResponse.class);
        response.setCtimeFormat(DateTimeUtil.parse(videoInfo.getCtime()));
        return response;
    }

    public static VideoInfoResponse toResponse(
            VideoInfo videoInfo,
            Map<String, ChannelResponse> channelMap,
            Map<String,String> fileUrlMap){
        VideoInfoResponse response = BeanUtil.toBean(videoInfo, VideoInfoResponse.class);
        response.setCtimeFormat(DateTimeUtil.parse(videoInfo.getCtime()));
        response.setChannelResponse(channelMap.get(videoInfo.getChannelCode()));
        if(StrUtil.isNotEmpty(videoInfo.getCoverFileCode())){
            response.setCoverFileUrl(fileUrlMap.get(videoInfo.getCoverFileCode()));
        }
        return response;
    }
}
