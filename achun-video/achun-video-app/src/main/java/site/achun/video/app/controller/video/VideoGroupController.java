package site.achun.video.app.controller.video;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import site.achun.file.client.module.file.FileResponse;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.service.business.video.VideoInfoQueryService;
import site.achun.video.app.service.business.video.convert.VideoConvert;
import site.achun.video.client.module.video.response.Video;
import site.achun.video.client.module.video.response.VideoInfoResponse;
import site.achun.video.client.module.video.response.VideoFileInfoResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/4 8:52
 */
@Tag(name = "视频分组")
@RestController
@AllArgsConstructor
public class VideoGroupController {

    private final MediaFileQueryClient fileQueryClient;
    private final VideoInfoQueryService videoGroupQueryFacade;

    @Operation(summary = "搜索分组信息")
    @PostMapping("/video/group/search-by-name-or-code")
    public Rsp<List<VideoInfoResponse>> searchVideoGroupByNameOrCode(@RequestBody SearchRequest searchRequest){
        return videoGroupQueryFacade.searchByNameOrCode(searchRequest.getNameOrCode());
    }

    @Operation(summary = "查询最近上传视频")
    @GetMapping("/video/group/query-recent-create")
    public Rsp<List<Video>> queryRecentCreate(@RequestParam("limit") Integer limit){
        // 查询video库
        List<VideoInfoResponse> videoGroupResponseList = videoGroupQueryFacade.queryRecentCreate(limit,null).tryGetData();
        // 通过fileCodes查询file系统，得到访问链接等信息
        Set<String> fileCodes = videoGroupResponseList.stream()
                .map(VideoInfoResponse::getDefaultVideoFileInfo)
                .map(VideoFileInfoResponse::getFileCode)
                .collect(Collectors.toSet());
        Map<String, MediaFileResponse> fileResponseMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(fileCodes).build()).tryGetData();
        // 转换输出
        List<Video> respList = videoGroupResponseList.stream()
                .map(group -> VideoConvert.toVideo(fileResponseMap.get(group.getDefaultVideoFileInfo().getFileCode())))
                .collect(Collectors.toList());
        return Rsp.success(respList);
    }

    @Data
    public static class SearchRequest implements Serializable {

        private static final long serialVersionUID = -4022794735648007400L;

        @Parameter(description = "分组编码或分组名")
        private String nameOrCode;
    }
}
