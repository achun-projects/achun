package site.achun.video.app.service.execute.channel;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.support.api.enums.Visibility;
import site.achun.support.api.request.ReqPage;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;
import site.achun.video.app.generator.domain.Channel;
import site.achun.video.app.generator.service.ChannelService;
import site.achun.video.app.utils.PageUtil;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.channel.response.ChannelResponse;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/2 11:37
 */
@Slf4j
@Service
@AllArgsConstructor
public class ChannelQueryExecute {
    private final ChannelService channelService;

    private final MediaFileQueryClient fileQueryClient;

    public Rsp<RspPage<ChannelResponse>> queryChannelPage(String userCode, ReqPage reqPage){
        Visibility visibility = UserInfo.calVisibility(userCode);
        Page<Channel> pageData = channelService.lambdaQuery()
                .le(Channel::getVisibility,visibility.getLevel())
                .or().eq(StrUtil.isNotBlank(userCode),Channel::getUserCode,userCode)
                .orderByDesc(Channel::getAtime)
                .page(Page.of(reqPage.getPage(), reqPage.getSize()));
        return Rsp.success(PageUtil.parse(pageData,reqPage,this::toResponse));
    }
    public ChannelResponse queryChannelDetail(String channelCode) {
        Channel channel = channelService.queryByChannelCode(channelCode);
        return toResponse(channel);
    }

    public ChannelResponse toResponse(Channel channel){
        ChannelResponse response = BeanUtil.toBean(channel, ChannelResponse.class);
        if(StrUtil.isNotBlank(channel.getCoverFileCode())){
            Rsp<MediaFileResponse> fileResponse = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(channel.getCoverFileCode()).build());
            response.setCoverFileUrl(fileResponse.hasData()?fileResponse.getData().getUrl():"");
        } else{
            response.setCoverFileUrl("");
        }
        if(StrUtil.isNotBlank(channel.getBannerFileCode())){
            Rsp<MediaFileResponse> fileResponse = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(channel.getBannerFileCode()).build());
            response.setBannerFileUrl(fileResponse.hasData()?fileResponse.getData().getUrl():"");
        }else{
            response.setBannerFileUrl("");
        }
        return response;
    }

}
