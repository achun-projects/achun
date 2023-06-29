package site.achun.video.app.controller.video;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.achun.support.api.request.ReqPage;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;
import site.achun.video.app.generator.mapper.TagsMapMapper;
import site.achun.video.app.service.business.video.RecommendVideoQueryService;
import site.achun.video.app.service.business.video.VideoQueryService;
import site.achun.video.app.service.execute.video.VideoQueryExecute;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.tags.request.QueryByChannelRequest;
import site.achun.video.client.module.tags.request.QueryByPlistCodeRequest;
import site.achun.video.client.module.video.request.QueryRecommendVideos;
import site.achun.video.client.module.video.response.Video;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.util.List;

/**
 * 视频查询
 *
 * @Author: Heiffeng
 * @Date: 2022/3/17 13:44
 */
@Tag(name = "视频查询")
@RestController
@AllArgsConstructor
public class VideoQueryController {

    private final VideoQueryService videoQueryService;
    private final VideoQueryExecute videoQueryExecute;
    private final RecommendVideoQueryService recommendVideoQueryService;
    private final TagsMapMapper tagsMapMapper;
    @Operation(summary = "查询视频信息 - 用文件编码")
    @GetMapping("/video/query/by-file-code")
    public Rsp<Video> queryVideoByFileCode(@RequestParam("fileCode") String fileCode){
        return videoQueryService.queryVideo(fileCode);
    }
    @Operation(summary = "查询视频分页列表 - 用频道编码")
    @GetMapping("/video/query/by-channel-code")
//    @NeedLogin
    public Rsp<RspPage<VideoInfoResponse>> queryVideoPagesByChannelCode(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("channelCode") String channelCode)
    {
        ReqPage reqPage = ReqPage.of(page,size);
        String userCode = UserInfo.getCode();
        Rsp<RspPage<VideoInfoResponse>> rsp = Rsp.success(videoQueryService.queryVideoPagesByChannelCode(reqPage, channelCode, userCode));
        rsp.putValue("tags",tagsMapMapper.selectChannelTags(QueryByChannelRequest.builder().channelCode(channelCode).limit(12).build()));
        return rsp;
    }

//    @NeedLogin
    @Operation(summary = "查询视频分页列表 - 用播放列表编码")
    @GetMapping("/video/query/by-plist-code")
    public Rsp<RspPage<VideoInfoResponse>> queryVideoPagesByPlistCode(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("plistCode") String plistCode)
    {
        ReqPage reqPage = ReqPage.of(page,size);
        String userCode = UserInfo.getCode();
        Rsp<RspPage<VideoInfoResponse>> rsp = Rsp.success(videoQueryService.queryVideoPagesByPlistCode(reqPage, plistCode, userCode));
        rsp.putValue("tags",tagsMapMapper.selectPlaylistTags(QueryByPlistCodeRequest.builder().plistCode(plistCode).limit(12).build()));
        return rsp;
    }

    @Operation(summary = "查询视频信息 - 用视频编码")
    @GetMapping("/video/query/by-video-code")
    public Rsp<VideoInfoResponse> queryVideoByVideoCode(@RequestParam("videoCode") String videoCode){
        return Rsp.success(videoQueryExecute.queryBy(videoCode));
    }

    @Operation(summary = "分组内其他视频")
    @GetMapping("/video/query/other-videos-in-group")
    public Rsp<List<Video>> queryOtherVideosInGroup(@RequestParam("videoCode") String videoCode){
        return videoQueryService.querySameGroupVideos(videoCode);
    }

    @Operation(summary = "查询最近上传视频")
    @Deprecated
    @GetMapping("/video/query/recent-create")
    public Rsp<List<Video>> queryRecentCreate(@RequestParam("limit") Integer limit){
        return videoQueryService.queryRecentCreate(limit);
    }

    @Operation(summary = "查询推荐视频")
    @PostMapping("/vide/query/recommend-videos")
    public Rsp<List<VideoInfoResponse>> queryRecommendVideosByVideoCode(@RequestBody QueryRecommendVideos query){
        return recommendVideoQueryService.query(query);
    }

}
