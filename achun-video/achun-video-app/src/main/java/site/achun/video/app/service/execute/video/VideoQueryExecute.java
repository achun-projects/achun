package site.achun.video.app.service.execute.video;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.app.generator.domain.TagsMap;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.generator.service.ChannelService;
import site.achun.video.app.generator.service.TagsMapService;
import site.achun.video.app.generator.service.VideoInfoService;
import site.achun.video.app.service.execute.channel.ChannelQueryExecute;
import site.achun.video.client.module.video.response.VideoFileInfoResponse;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/28 17:35
 */
@Slf4j
@Service
@AllArgsConstructor
public class VideoQueryExecute {

    private final VideoInfoService videoInfoService;
    private final FileInfoQueryExecute videoFileInfoQueryExecute;
    private final ChannelService channelService;

    private final ChannelQueryExecute channelQueryExecute;
    private final MediaFileQueryClient fileQueryClient;
    private final TagsMapService tagsMapService;
    public VideoInfoResponse queryBy(String videoCode) {
        VideoInfo videoInfo = videoInfoService.queryByCode(videoCode);
        if (videoInfo == null) {
            throw new RuntimeException("不存在视频：" + videoCode);
        }
        VideoInfoResponse videoInfoResponse = VideoConvert.toResponse(videoInfo);
        // 补充频道
        if (StrUtil.isNotBlank(videoInfo.getChannelCode())) {
            videoInfoResponse.setChannelResponse(channelQueryExecute.queryChannelDetail(videoInfo.getChannelCode()));
        }
        // 补充封面图片
        if (StrUtil.isNotBlank(videoInfo.getCoverFileCode())) {
            MediaFileResponse coverFileResponse = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(videoInfo.getCoverFileCode()).build()).tryGetData();
            videoInfoResponse.setCoverFileUrl(coverFileResponse.getUrl());
        }
        // 补充视频信息
        List<VideoFileInfoResponse> videoFileInfoResponseList = videoFileInfoQueryExecute.queryByVideoCode(videoInfo.getVideoCode());
        if(CollUtil.isNotEmpty(videoFileInfoResponseList)){
            videoInfoResponse.setVideoFileInfoList(videoFileInfoResponseList);
            videoInfoResponse.setDefaultVideoFileInfo(videoFileInfoResponseList.get(0));
            // 再次补充封面信息
            if(StrUtil.isEmpty(videoInfoResponse.getCoverFileUrl())){
                videoInfoResponse.setCoverFileUrl(videoInfoResponse.getDefaultVideoFileInfo().getCover());
            }
        }
        // 补充标签
        List<TagsMap> tagsMapList = tagsMapService.getTagsMapByObjectCode(videoInfo.getVideoCode());
        if(CollUtil.isNotEmpty(tagsMapList)){
            Set<String> tags = tagsMapList.stream()
                    .map(TagsMap::getTagName)
                    .collect(Collectors.toSet());
            videoInfoResponse.setTags(tags);
        }
        return videoInfoResponse;
    }

    public Map<String,VideoInfoResponse> queryMapBy(Set<String> videoCodes) {
        return videoCodes.stream()
                .map(this::queryBy)
                .collect(Collectors.toMap(VideoInfoResponse::getVideoCode,v->v,(v1,v2)->v1));
    }
}
