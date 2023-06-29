package site.achun.video.app.service.execute.video;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.app.generator.domain.VideoFileInfo;
import site.achun.video.app.generator.service.VideoFileInfoService;
import site.achun.video.client.module.video.response.VideoFileInfoResponse;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/29 11:03
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileInfoQueryExecute {

    private final VideoFileInfoService videoFileInfoService;

    private final MediaFileQueryClient fileQueryClient;

    public List<VideoFileInfoResponse> queryByVideoCode(String videoCode){
        List<VideoFileInfo> videoFileInfoList = videoFileInfoService.getByVideoCode(videoCode);
        return toResponse(videoFileInfoList);
    }


    public List<VideoFileInfoResponse> queryByVideoCodes(Collection<String> videoCodes){
        List<VideoFileInfo> videoFileInfoList = videoFileInfoService.getByVideoCodes(videoCodes);
        return toResponse(videoFileInfoList);
    }


    public List<VideoFileInfoResponse> toResponse(List<VideoFileInfo> videoFileInfoList){
        if(CollUtil.isEmpty(videoFileInfoList)) return new ArrayList<>();
        Set<String> fileCodes = videoFileInfoList.stream()
                .map(VideoFileInfo::getFileCode)
                .collect(Collectors.toSet());
        Map<String, MediaFileResponse> fileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(fileCodes).build()).tryGetData();
        List<VideoFileInfoResponse> videoFileInfoResponseList = videoFileInfoList.stream()
                .map(info->{
                    VideoFileInfoResponse response = BeanUtil.toBean(info, VideoFileInfoResponse.class);
                    MediaFileResponse fileResponse = fileMap.get(response.getFileCode());
                    // 补充url
                    response.setUrl(fileResponse.getUrl());
                    // 补充封面url
                    response.setCover(fileResponse.getCover());
                    return response;
                })
                .collect(Collectors.toList());
        return videoFileInfoResponseList;
    }

}
