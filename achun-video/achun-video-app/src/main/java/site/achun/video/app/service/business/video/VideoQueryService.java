package site.achun.video.app.service.business.video;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.request.QueryByUnitCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.request.ReqPage;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;
import site.achun.video.app.generator.domain.PlayListRecord;
import site.achun.video.app.generator.domain.VideoFileInfo;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.generator.service.PlayListRecordService;
import site.achun.video.app.generator.service.VideoFileInfoService;
import site.achun.video.app.generator.service.VideoInfoService;
import site.achun.video.app.service.business.video.convert.VideoConvert;
import site.achun.video.app.service.execute.video.VideoBatchQueryExecute;
import site.achun.video.app.service.execute.video.VideoQueryExecute;
import site.achun.video.app.utils.PageUtil;
import site.achun.video.client.module.video.response.Video;
import site.achun.video.client.module.video.response.VideoFileInfoResponse;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import javax.print.attribute.standard.Media;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/17 13:53
 */
@Slf4j
@Service
@AllArgsConstructor
public class VideoQueryService {

    private final MediaFileQueryClient fileQueryClient;
    private final VideoFileInfoService videoFileInfoService;
    private final VideoQueryExecute videoQueryExecute;
    private final VideoBatchQueryExecute videoBatchQueryExecute;
//    private final PlatformMapConsumer platformMapConsumer;
    private final VideoInfoService videoInfoService;

    private final PlayListRecordService playListRecordService;
    public Rsp<Video> queryVideo(String fileCode){
        MediaFileResponse fileResponse = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build()).tryGetData();
        Video video = VideoConvert.toVideo(fileResponse);
        video.setVideoResponse(queryVideoDetail(fileCode).tryGetData());
        return Rsp.success(video);
    }
    public RspPage<VideoInfoResponse> queryVideoPagesByPlistCode(ReqPage reqPage, String plistCode, String userCode) {
        List<PlayListRecord> plistRecords = playListRecordService.lambdaQuery()
                .eq(PlayListRecord::getPlistCode, plistCode)
                .list();
        if(CollUtil.isEmpty(plistRecords)){
            return reqPage.createPageRsp();
        }
        Set<String> videoCodes = plistRecords.stream()
                .map(PlayListRecord::getVideoCode)
                .collect(Collectors.toSet());
//        boolean showNsfw = platformMapConsumer.showNsfwContent();
        boolean showNsfw = true;
        List<Integer> viewLevels = showNsfw ? null : Arrays.asList(1, 2);
        Page<VideoInfo> pageResult = videoInfoService.lambdaQuery()
                .in(VideoInfo::getVideoCode, videoCodes)
                .eq(VideoInfo::getUserCode, userCode)
                .in(CollUtil.isNotEmpty(viewLevels),VideoInfo::getViewLevel,viewLevels)
                .orderByDesc(VideoInfo::getUtime)
                .page(Page.of(reqPage.getPage(), reqPage.getSize()));
        if(CollUtil.isEmpty(pageResult.getRecords())){
            return reqPage.createPageRsp();
        }
        return PageUtil.batchParse(pageResult,reqPage,videoBatchQueryExecute::queryBy);
    }
    public RspPage<VideoInfoResponse> queryVideoPagesByChannelCode(ReqPage reqPage, String channelCode, String userCode) {
//        boolean showNsfw = platformMapConsumer.showNsfwContent();
        boolean showNsfw = true;
        List<Integer> viewLevels = showNsfw ? null : Arrays.asList(1, 2);
        Page<VideoInfo> pageResult = videoInfoService.lambdaQuery()
                .eq(VideoInfo::getChannelCode, channelCode)
                .eq(VideoInfo::getUserCode, userCode)
                .in(CollUtil.isNotEmpty(viewLevels),VideoInfo::getViewLevel,viewLevels)
                .orderByDesc(VideoInfo::getUtime)
                .page(Page.of(reqPage.getPage(), reqPage.getSize()));
        if(CollUtil.isEmpty(pageResult.getRecords())){
            return reqPage.createPageRsp();
        }
        return PageUtil.batchParse(pageResult,reqPage,videoBatchQueryExecute::queryBy);
    }

    public Rsp<VideoInfoResponse> queryVideoDetail(String videoFileCode) {
        VideoFileInfo video = videoFileInfoService.getByFileCode(videoFileCode);
        return Rsp.success(videoQueryExecute.queryBy(video.getVideoCode()));
    }

    public Rsp<List<Video>> querySameGroupVideos(String videoCode){
        var list = fileQueryClient.queryFileList(QueryByUnitCode.builder().unitCode(videoCode).build()).tryGetData();
        List<Video> respList = list.stream()
                .map(VideoConvert::toVideo)
                .collect(Collectors.toList());
        return Rsp.success(respList);
    }

    public Rsp<List<Video>> queryRecentCreate(Integer limit) {
        List<VideoFileInfo> list = videoFileInfoService.lambdaQuery()
                .orderByDesc(VideoFileInfo::getCtime)
                .last("limit " + limit)
                .list();
        List<VideoFileInfoResponse> videoResponseList = list.stream()
                        .map(VideoConvert::toVideoResponse)
                        .collect(Collectors.toList());
        // 通过fileCodes查询file系统，得到访问链接等信息
        List<String> videoFileCodes = videoResponseList.stream()
                .map(VideoFileInfoResponse::getFileCode)
                .collect(Collectors.toList());

        Map<String, MediaFileResponse> videoFileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(videoFileCodes).build()).tryGetData();
        // 转换
        List<Video> respList = videoResponseList.stream()
                .map(rsp -> VideoConvert.toVideo(videoFileMap.get(rsp.getFileCode())))
                .collect(Collectors.toList());
        // 转换输出
        return Rsp.success(respList);
    }


}
