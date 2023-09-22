package site.achun.video.app.generator.service;

import cn.hutool.core.bean.BeanUtil;
import site.achun.video.app.generator.domain.Channel;
import com.baomidou.mybatisplus.extension.service.IService;
import site.achun.video.client.module.channel.response.ChannelSimpleResponse;

/**
* @author Administrator
* @description 针对表【channel】的数据库操作Service
* @createDate 2023-09-22 13:41:56
*/
public interface ChannelService extends IService<Channel> {
    default Channel queryByChannelCode(String channelCode){
        return this.lambdaQuery()
                .eq(Channel::getChannelCode,channelCode)
                .one();
    }

    default Channel queryByUserCode(String userCode,String name){
        return this.lambdaQuery()
                .eq(Channel::getUserCode,userCode)
                .eq(Channel::getName,name)
                .last("limit 1")
                .one();
    }

    default ChannelSimpleResponse toChannelResponse(Channel channel){
        return BeanUtil.toBean(channel,ChannelSimpleResponse.class);
    }
}
