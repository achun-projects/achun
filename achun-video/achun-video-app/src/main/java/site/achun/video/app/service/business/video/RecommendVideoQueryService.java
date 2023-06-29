package site.achun.video.app.service.business.video;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.support.api.response.Rsp;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.generator.domain.TagsMap;
import site.achun.video.app.generator.service.TagsMapService;
import site.achun.video.app.generator.service.VideoInfoService;
import site.achun.video.app.service.execute.video.VideoBatchQueryExecute;
import site.achun.video.client.module.video.request.QueryRecommendVideos;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/18 16:24
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecommendVideoQueryService {

    private final TagsMapService tagsMapService;
    private final VideoInfoService videoInfoService;
    private final VideoBatchQueryExecute videoBatchQueryExecute;

    public Rsp<List<VideoInfoResponse>> query(QueryRecommendVideos query){
        // 根据videoCode的标签查询其他相同标签的内容。
        Set<String> sameTagVideoCodes = null;
        if(StrUtil.isNotEmpty(query.getVideoCode())){
            sameTagVideoCodes = tagsMapService.getSameTagsByObjectCode(query.getVideoCode()).stream()
                    .map(TagsMap::getObjectCode)
                    .collect(Collectors.toSet());
            sameTagVideoCodes.remove(query.getVideoCode());
        }
        List<VideoInfo> recommendVideos = videoInfoService.lambdaQuery()
                .in(CollUtil.isNotEmpty(sameTagVideoCodes), VideoInfo::getVideoCode, sameTagVideoCodes)
                .eq(StrUtil.isNotEmpty(query.getChannelCode()), VideoInfo::getChannelCode, query.getChannelCode())
                .last("limit " + query.getLimit())
                .list();
        return Rsp.success(videoBatchQueryExecute.queryBy(recommendVideos));
    }

}
