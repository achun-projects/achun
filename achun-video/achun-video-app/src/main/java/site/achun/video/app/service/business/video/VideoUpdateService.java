package site.achun.video.app.service.business.video;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByUnitCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.enums.Visibility;
import site.achun.support.api.response.RC;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.PlayListRecord;
import site.achun.video.app.generator.domain.TagsMap;
import site.achun.video.app.generator.domain.VideoFileInfo;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.generator.service.PlayListRecordService;
import site.achun.video.app.generator.service.TagsMapService;
import site.achun.video.app.generator.service.VideoFileInfoService;
import site.achun.video.app.generator.service.VideoInfoService;
import site.achun.video.app.service.business.video.convert.VideoConvert;
import site.achun.video.app.service.execute.video.VideoQueryExecute;
import site.achun.video.client.constant.ViewLevelEnum;
import site.achun.video.client.module.video.request.CreateOrUpdateVideoRequest;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/31 22:00
 */
@Slf4j
@Service
@AllArgsConstructor
public class VideoUpdateService {

    private final VideoInfoService videoInfoService;
    private final VideoFileInfoService videoFileInfoService;
    private final TagsMapService tagsMapService;

    private final VideoQueryExecute videoQueryExecute;

    private final FileQueryClient fileQueryClient;

    private final PlayListRecordService playListRecordService;


    public Rsp<VideoInfoResponse> createOrUpdateVideo(CreateOrUpdateVideoRequest createVideo) {
        // 通过视频编码查视频文件信息
        List<FileInfoResponse> videoFiles = fileQueryClient.queryFileList(QueryByUnitCode.builder().unitCode(createVideo.getVideoCode()).build()).getData();
        if(CollUtil.isEmpty(videoFiles)){
            return Rsp.error(RC.PARAMS_IS_NULL,"视频文件信息不能为空");
        }
        createVideo.setVideoFiles(videoFiles);

        // 更新视频基本信息
        createOrUpdateVideoInfo(createVideo);

        // 更新视频标签
        if(CollUtil.isNotEmpty(createVideo.getTags())){
            tagsMapService.lambdaUpdate()
                    .eq(TagsMap::getObjectCode,createVideo.getVideoCode())
                    .remove();
            List<TagsMap> tagList = createVideo.getTags().stream()
                    .filter(tag -> StrUtil.isNotEmpty(tag))
                    .map(tag -> VideoConvert.toTagsMap(createVideo.getVideoCode(),tag))
                    .collect(Collectors.toList());
            // 如果传入的tag只有一个空字符串，就会导致这个tagList为空。从而实现删除所有标签的目的
            if(CollUtil.isNotEmpty(tagList)){
                tagsMapService.saveBatch(tagList);
            }
        }

        // 保存视频文件信息
        videoFileInfoService.lambdaUpdate()
                .eq(VideoFileInfo::getVideoCode,createVideo.getVideoCode())
                .remove();
        List<VideoFileInfo> videoFileInfos = createVideo.getVideoFiles().stream()
                .map(VideoConvert::toVideoFileInfo)
                .collect(Collectors.toList());
        videoFileInfoService.saveBatch(videoFileInfos);

        // 保存播放列表
        if(StrUtil.isNotBlank(createVideo.getPlistCode())){
            PlayListRecord record = playListRecordService.lambdaQuery()
                    .eq(PlayListRecord::getPlistCode, createVideo.getPlistCode())
                    .eq(PlayListRecord::getVideoCode, createVideo.getVideoCode())
                    .one();
            if(record==null){
                record = PlayListRecord.builder()
                        .ctime(LocalDateTime.now())
                        .videoCode(createVideo.getVideoCode())
                        .plistCode(createVideo.getPlistCode())
                        .build();
                playListRecordService.save(record);
            }
        }

        return Rsp.success(videoQueryExecute.queryBy(createVideo.getVideoCode()));

    }


    private void createOrUpdateVideoInfo(CreateOrUpdateVideoRequest createVideo){
        VideoInfo videoInfo = videoInfoService.queryByCode(createVideo.getVideoCode());
        if(videoInfo == null){
            videoInfoService.save(toVideoInfo(createVideo));
        }else{
            videoInfoService.updateById(toVideoInfo(videoInfo,createVideo));
        }
    }

    private VideoInfo toVideoInfo(CreateOrUpdateVideoRequest createVideo){
        return VideoInfo.builder()
                .videoCode(createVideo.getVideoCode())
                .channelCode(createVideo.getChannelCode())
                .userCode(createVideo.getUserCode())
                .coverFileCode(createVideo.getCoverFileCode())
                .title(createVideo.getTitle())
                .description(createVideo.getDescription())
                .viewLevel(createVideo.getViewLevel()==null? ViewLevelEnum.NORMAL : createVideo.getViewLevel())
                .sourceType(createVideo.getSourceType())
                .sourceUrl(createVideo.getSourceUrl())
                .visibility(Visibility.ALL.getLevel())
                .ctime(LocalDateTime.now())
                .utime(LocalDateTime.now())
                .deleted(Deleted.NO.getStatus())
                .build();
    }
    private VideoInfo toVideoInfo(VideoInfo videoInfo,CreateOrUpdateVideoRequest createVideo){
        videoInfo.setTitle(createVideo.getTitle());
        videoInfo.setDescription(createVideo.getDescription());
        if(StrUtil.isNotBlank(createVideo.getCoverFileCode())){
            videoInfo.setCoverFileCode(createVideo.getCoverFileCode());
        }
        if(createVideo.getViewLevel() != null){
            videoInfo.setViewLevel(createVideo.getViewLevel());
        }
        if(StrUtil.isNotBlank(createVideo.getChannelCode())){
            videoInfo.setChannelCode(createVideo.getChannelCode());
        }
        videoInfo.setUtime(LocalDateTime.now());
        videoInfo.setSourceType(createVideo.getSourceType());
        videoInfo.setSourceUrl(createVideo.getSourceUrl());
        return videoInfo;
    }
}
