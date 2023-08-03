package site.achun.video.app.service.business.video;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.DeleteFileRequest;
import site.achun.support.api.exception.RspException;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.*;
import site.achun.video.app.generator.service.*;
import site.achun.video.client.module.video.request.RemoveVideoRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class VideoRemoveService {

    private final VideoInfoService videoInfoService;
    private final VideoFileInfoService videoFileInfoService;
    private final VideoClickRecordService videoClickRecordService;
    private final TagsMapService tagsMapService;
    private final PlayRecordService playRecordService;
    private final PlayListRecordService playListRecordService;

    private final FileUpdateClient fileUpdateClient;

    public void removeVideo(RemoveVideoRequest request) {
        // 删除videoInfo
        VideoInfo videoInfo = videoInfoService.queryByCode(request.getVideoCode());
        if(videoInfo == null){
            throw new RspException("视频不存在");
        }
        videoInfoService.removeById(videoInfo);
        log.info("删除videoInfo,videoCode:{}",request.getVideoCode());

        // 删除videoFileInfo
        List<VideoFileInfo> fileInfoList = videoFileInfoService.getByVideoCode(request.getVideoCode());
        List<String> videoFileCodes = new ArrayList<>();
        if(CollUtil.isNotEmpty(fileInfoList)){
            videoFileCodes = fileInfoList.stream()
                    .map(VideoFileInfo::getFileCode)
                    .collect(Collectors.toList());
            videoFileInfoService.removeBatchByIds(fileInfoList);
            log.info("删除videoFileInfo,videoFileCodes:{}",videoFileCodes);
        }

        // 删除videoClickRecord
        List<VideoClickRecord> videoClickRecords = videoClickRecordService.queryByVideoCode(request.getVideoCode());
        if(CollUtil.isNotEmpty(videoClickRecords)){
            videoClickRecordService.removeBatchByIds(videoClickRecords);
            log.info("删除VideoClickRecord，videoCode:{}",request.getVideoCode());
        }

        // 删除tagsMap
        List<TagsMap> tagsMapList = tagsMapService.queryByObjectCode(request.getVideoCode());
        if(CollUtil.isNotEmpty(tagsMapList)){
            tagsMapService.removeBatchByIds(tagsMapList);
            log.info("删除TagsMap,videoCode:{}",request.getVideoCode());
        }

        // 删除playRecord
        if(CollUtil.isNotEmpty(videoFileCodes)){
            List<PlayRecord> playRecords = playRecordService.queryByFileCodes(videoFileCodes);
            if(CollUtil.isNotEmpty(playRecords)){
                playRecordService.removeBatchByIds(playRecords);
                log.info("删除PlayRecords,videoFileCodes：{}",videoFileCodes);
            }
        }

        // 删除playListRecord
        List<PlayListRecord> plistRecords = playListRecordService.getBy(request.getVideoCode());
        if(CollUtil.isNotEmpty(plistRecords)){
            playListRecordService.removeBatchByIds(plistRecords);
            log.info("删除plistRecords,videoCode:{}",request.getVideoCode());
        }

        // 删除文件
        if(CollUtil.isNotEmpty(videoFileCodes)){
            Rsp<Boolean> deleteResponse = fileUpdateClient.deleteFileInfos(DeleteFileRequest.builder().fileCodes(videoFileCodes).build());
            log.info("调用achun-file,删除文件：{}，结果：{}",videoFileCodes,deleteResponse.getData());
        }

    }
}
