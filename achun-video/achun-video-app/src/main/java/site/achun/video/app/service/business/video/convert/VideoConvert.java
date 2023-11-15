package site.achun.video.app.service.business.video.convert;

import cn.hutool.core.bean.BeanUtil;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.app.generator.domain.TagsMap;
import site.achun.video.app.generator.domain.VideoFileInfo;
import site.achun.video.client.module.video.response.Video;
import site.achun.video.client.module.video.response.VideoFileInfoResponse;

import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/1 0:06
 */
public class VideoConvert {

    public static VideoFileInfo toVideoFileInfo(FileInfoResponse fileResponse,String videoCode){
        VideoFileInfo videoFileInfo = BeanUtil.toBean(fileResponse, VideoFileInfo.class);
        videoFileInfo.setVideoCode(videoCode);
        videoFileInfo.setVideoName(fileResponse.getFileName());
        videoFileInfo.setCtime(LocalDateTime.now());
        videoFileInfo.setUtime(LocalDateTime.now());
        videoFileInfo.setAtime(LocalDateTime.now());
        return videoFileInfo;
    }

    public static Video toVideo(MediaFileResponse fileResponse){
        return Video.builder()
                .fileCode(fileResponse.getFileCode())
                .fileName(fileResponse.getFileName())
                .videoCode(fileResponse.getUnitCode())
                .cover(fileResponse.getCover())
                .url(fileResponse.getUrl())
                .type(fileResponse.getType())
                .build();
    }

    public static TagsMap toTagsMap(String objectCode, String tagName){
        return TagsMap.builder()
                .tagName(tagName)
                .objectType(2)
                .objectCode(objectCode)
                .ctime(LocalDateTime.now())
                .build();
    }

    public static VideoFileInfoResponse toVideoResponse(VideoFileInfo video){
        return BeanUtil.toBean(video, VideoFileInfoResponse.class);
    }

}
