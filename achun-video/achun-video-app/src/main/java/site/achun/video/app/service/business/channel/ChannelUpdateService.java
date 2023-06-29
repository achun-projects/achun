package site.achun.video.app.service.business.channel;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.support.api.utils.CodeGenUtil;
import site.achun.video.app.generator.domain.Channel;
import site.achun.video.app.generator.service.ChannelService;
import site.achun.video.app.service.execute.channel.ChannelQueryExecute;
import site.achun.video.client.module.channel.request.CreateChannel;
import site.achun.video.client.module.channel.request.UpdateChannel;
import site.achun.video.client.module.channel.response.ChannelResponse;

import java.time.LocalDateTime;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/22 15:18
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChannelUpdateService {

    private final ChannelService channelService;
    private final ChannelQueryExecute channelQueryExecute;

    public ChannelResponse createChannel(CreateChannel createChannel){
        Channel existChannel = channelService.queryByUserCode(createChannel.getUserCode(), createChannel.getName());
        if(existChannel != null){
            throw new RuntimeException("该频道已存在");
        }
        Channel newChannel = Channel.builder()
                .channelCode(CodeGenUtil.uuid())
                .name(createChannel.getName())
                .userCode(createChannel.getUserCode())
                .bannerFileCode(createChannel.getBannerFileCode())
                .coverFileCode(createChannel.getCoverFileCode())
                .atime(LocalDateTime.now())
                .ctime(LocalDateTime.now())
                .utime(LocalDateTime.now())
                .build();
        channelService.save(newChannel);
        return channelQueryExecute.toResponse(newChannel);
    }

    public ChannelResponse updateChannel(UpdateChannel updateChannel){
        Channel existChannel = channelService.queryByChannelCode(updateChannel.getChannelCode());
        if(existChannel == null){
            throw new RuntimeException("频道不存在");
        }
        boolean result = channelService.lambdaUpdate()
                .eq(Channel::getChannelCode, updateChannel.getChannelCode())
                .set(StrUtil.isNotBlank(updateChannel.getName()), Channel::getName, updateChannel.getName())
                .set(StrUtil.isNotBlank(updateChannel.getBannerFileCode()), Channel::getBannerFileCode, updateChannel.getBannerFileCode())
                .set(StrUtil.isNotBlank(updateChannel.getCoverFileCode()), Channel::getCoverFileCode, updateChannel.getCoverFileCode())
                .update();
        log.info("channelCode:{},update result:{}",updateChannel.getChannelCode(),result);
        return channelQueryExecute.queryChannelDetail(updateChannel.getChannelCode());
    }
}
