package site.achun.video.app.controller.channel;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.achun.support.api.request.ReqPage;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;
import site.achun.video.app.service.execute.channel.ChannelQueryExecute;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.channel.request.QueryChannelPage;
import site.achun.video.client.module.channel.response.ChannelResponse;

import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/2 11:39
 */
@Tag(name = "频道查询")
@RestController
@RequiredArgsConstructor
public class ChannelQueryController {

    private final ChannelQueryExecute channelQueryExecute;

    @Operation(summary = "查询频道列表")
//    @NeedLogin
    @GetMapping("/video/channel/query-channel-list")
    public Rsp<List<ChannelResponse>> queryChannels(@RequestParam("limit") Integer limit){
        String userCode = UserInfo.getCode();
        ReqPage reqPage = ReqPage.builder()
                .page(1)
                .size(limit)
                .build();
        return Rsp.success(channelQueryExecute.queryChannelPage(userCode,reqPage).getData().getRows());
    }

    @Operation(summary = "查询频道分页")
//    @NeedLogin
    @PostMapping("/video/channel/query-channel-page")
    public Rsp<RspPage<ChannelResponse>> queryChannelPage(@RequestBody QueryChannelPage query){
        String userCode = UserInfo.getCode();
        ReqPage reqPage = ReqPage.builder()
                .page(query.getPage())
                .size(query.getSize())
                .build();
        return channelQueryExecute.queryChannelPage(userCode,reqPage);
    }

    @Operation(summary = "查询频道详情")
    @GetMapping("/video/channel/query-channel-detail")
    public Rsp<ChannelResponse> queryChannelDetail(@RequestParam("channelCode") String channelCode){
        return Rsp.success(channelQueryExecute.queryChannelDetail(channelCode));
    }


}

