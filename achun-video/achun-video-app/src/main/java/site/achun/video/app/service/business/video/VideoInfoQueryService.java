package site.achun.video.app.service.business.video;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.generator.domain.VideoFileInfo;
import site.achun.video.app.generator.service.VideoFileInfoService;
import site.achun.video.app.generator.service.VideoInfoService;
import site.achun.video.app.service.business.video.convert.VideoConvert;
import site.achun.video.client.module.video.response.VideoFileInfoResponse;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/28 18:11
 */
@Slf4j
@Service
@AllArgsConstructor
public class VideoInfoQueryService {

    private final VideoInfoService videoInfoService;

    private final VideoFileInfoService videoFileInfoService;

    public Rsp<List<VideoInfoResponse>> searchByNameOrCode(String nameOrCode) {
        List<VideoInfo> list = videoInfoService.lambdaQuery()
                .like(VideoInfo::getVideoCode, "%" + nameOrCode + "%")
                .or()
                .like(VideoInfo::getTitle, "%" + nameOrCode + "%")
                .list();
        if(CollUtil.isEmpty(list)){
            return Rsp.success(Arrays.asList());
        }
        List<VideoInfoResponse> rspList = list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return Rsp.success(rspList);
    }

    public Rsp<List<VideoInfoResponse>> queryRecentCreate(Integer limit, Collection<Integer> viewLevels) {
        List<VideoInfo> list = videoInfoService.lambdaQuery()
                .in(CollUtil.isNotEmpty(viewLevels),VideoInfo::getViewLevel,viewLevels)
                .orderByDesc(VideoInfo::getCtime)
                .last("limit " + limit)
                .list();
        if(CollUtil.isEmpty(list)){
            return Rsp.success(null);
        }
        List<VideoInfoResponse> rspList = list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        // 设置defaultVideo
        setDefaultVideo(rspList);
        return Rsp.success(rspList);
    }

    private VideoInfoResponse toResponse(VideoInfo videoGroup){
        return BeanUtil.toBean(videoGroup, VideoInfoResponse.class);
    }

    private void setDefaultVideo(Collection<VideoInfoResponse> list){
        // 设置defaultVideo
        Set<String> videoCodes = list.stream()
                .map(VideoInfoResponse::getVideoCode)
                .collect(Collectors.toSet());
        Map<String, VideoFileInfoResponse> map = videoFileInfoService.lambdaQuery()
                .in(VideoFileInfo::getVideoCode, videoCodes)
                .list().stream()
                .map(VideoConvert::toVideoResponse)
                .collect(Collectors.toMap(VideoFileInfoResponse::getVideoCode, v -> v, (v1, v2) -> v1));
        list.stream().forEach(videoInfo->{
            if(map.containsKey(videoInfo.getVideoCode())){
                videoInfo.setDefaultVideoFileInfo(map.get(videoInfo.getVideoCode()));
            }
        });
    }
}
