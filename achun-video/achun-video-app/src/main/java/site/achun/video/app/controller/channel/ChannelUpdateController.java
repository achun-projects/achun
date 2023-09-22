package site.achun.video.app.controller.channel;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.service.business.channel.ChannelUpdateService;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.channel.request.CreateChannel;
import site.achun.video.client.module.channel.request.UpdateChannel;
import site.achun.video.client.module.channel.response.ChannelResponse;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/11 18:04
 */
@Tag(name = "频道更新")
@RestController
@RequiredArgsConstructor
public class ChannelUpdateController {

    private final ChannelUpdateService channelUpdateService;

    @Operation(summary = "创建频道")
    @PostMapping("/video/channel/create-channel")
    public Rsp<ChannelResponse> createChannel(@RequestBody CreateChannel createChannel){
        createChannel.setUserCode(UserInfo.getCode(createChannel::getUserCode));
        return Rsp.success(channelUpdateService.createChannel(createChannel));
    }

    @Operation(summary = "更新频道")
    @PostMapping("/video/channel/update-channel")
    public Rsp<ChannelResponse> updateChannel(@RequestBody @Valid UpdateChannel updateChannel){
        updateChannel.setUserCode(UserInfo.getCode(updateChannel::getUserCode));
        return Rsp.success(channelUpdateService.updateChannel(updateChannel));
    }
}
