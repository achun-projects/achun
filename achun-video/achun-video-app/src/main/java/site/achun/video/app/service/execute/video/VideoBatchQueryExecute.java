package site.achun.video.app.service.execute.video;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileResponse;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.app.generator.domain.TagsMap;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.generator.domain.Channel;
import site.achun.video.app.generator.service.ChannelService;
import site.achun.video.app.generator.service.TagsMapService;
import site.achun.video.app.service.execute.record.VideoClickRecordQueryExecute;
import site.achun.video.client.module.channel.response.ChannelResponse;
import site.achun.video.client.module.video.response.VideoFileInfoResponse;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/17 16:24
 */
@Slf4j
@Service
@AllArgsConstructor
public class VideoBatchQueryExecute {

    private final ChannelService channelService;
    private final MediaFileQueryClient fileQueryClient;

    private final FileInfoQueryExecute fileInfoQueryExecute;

    private final VideoClickRecordQueryExecute videoClickRecordQueryExecute;

    private final TagsMapService tagsMapService;

    public List<VideoInfoResponse> queryBy(List<VideoInfo> videoInfoList){
        if(CollUtil.isEmpty(videoInfoList)) return new ArrayList<>();
        Set<String> videoCodes = videoInfoList.stream()
                .map(VideoInfo::getVideoCode)
                .collect(Collectors.toSet());
        // 补充频道
        Set<String> channelCodes = videoInfoList.stream()
                .map(VideoInfo::getChannelCode)
                .collect(Collectors.toSet());
        var channelMap = channelService.lambdaQuery()
                .in(Channel::getChannelCode, channelCodes)
                .list().stream()
                .map(v1 -> BeanUtil.toBean(v1, ChannelResponse.class))
                .collect(Collectors.toMap(ChannelResponse::getChannelCode,v1->v1, (v1, v2) -> v1));
        // 补充封面图片
        Set<String> coverFileCodes = videoInfoList.stream()
                .map(VideoInfo::getCoverFileCode)
                .collect(Collectors.toSet());
        Map<String, String> coverFileUrlMap = new HashMap<>();
        if(CollUtil.isNotEmpty(coverFileCodes)){
            Map<String, MediaFileResponse> coverFileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(coverFileCodes).build()).tryGetData();
            coverFileUrlMap = coverFileMap.values().stream()
                    .collect(Collectors.toMap(MediaFileResponse::getFileCode,MediaFileResponse::getUrl, (v1, v2) -> v1));
        }

        // 填充
        Map<String, String> finalCoverFileUrlMap = coverFileUrlMap;
        var videoInfoResponseList = videoInfoList.stream()
                .map(videoInfo->VideoConvert.toResponse(videoInfo,channelMap, finalCoverFileUrlMap))
                .collect(Collectors.toList());

        // 使用视频内文件补充封面
        Set<String> noCoverVideos = videoInfoResponseList.stream()
                .filter(video -> StrUtil.isEmpty(video.getCoverFileUrl()))
                .map(VideoInfoResponse::getVideoCode)
                .collect(Collectors.toSet());
        Map<String, List<VideoFileInfoResponse>> fileInfoMap = fileInfoQueryExecute.queryByVideoCodes(noCoverVideos).stream()
                .collect(Collectors.groupingBy(VideoFileInfoResponse::getVideoCode));
        videoInfoResponseList.stream()
                .filter(video -> StrUtil.isEmpty(video.getCoverFileUrl()))
                .filter(video -> fileInfoMap.containsKey(video.getVideoCode()))
                .forEach(video -> video.setCoverFileUrl(fileInfoMap.get(video.getVideoCode()).get(0).getCover()));
        // 补充点击量
        Map<String, Integer> clickNumMap = videoClickRecordQueryExecute.queryVideoClickNum(videoCodes);
        videoInfoResponseList.stream()
                .filter(video->clickNumMap.containsKey(video.getVideoCode()))
                .forEach(video->video.setClickNum(clickNumMap.get(video.getVideoCode())));
        // 补充标签
        Map<String, List<TagsMap>> tagsMap = tagsMapService.getTagsMapByObjectCodes(videoCodes).stream()
                .collect(Collectors.groupingBy(TagsMap::getObjectCode));
        videoInfoResponseList.stream()
                .filter(video->tagsMap.containsKey(video.getVideoCode()))
                .forEach(video->video.setTags(tagsMap.get(video.getVideoCode()).stream().map(TagsMap::getTagName).collect(Collectors.toSet())));
        return videoInfoResponseList;
    }
}
