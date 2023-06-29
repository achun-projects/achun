package site.achun.video.app.service.execute.playlist;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.PlayList;
import site.achun.video.app.generator.domain.PlayListRecord;
import site.achun.video.app.generator.service.PlayListRecordService;
import site.achun.video.app.service.execute.video.VideoQueryExecute;
import site.achun.video.client.module.playlist.response.PlayListPreviewResponse;
import site.achun.video.client.module.playlist.response.PlayListResponse;
import site.achun.video.client.module.video.response.VideoInfoResponse;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/11 14:40
 */
@Slf4j
@Service
@AllArgsConstructor
public class PlayListConvertExecute {

    private final PlayListRecordService playListRecordService;
    private final VideoQueryExecute videoQueryExecute;
    public PlayListResponse toResponse(PlayList playList){
        PlayListResponse response = BeanUtil.toBean(playList, PlayListResponse.class);
        // 获取记录数量
        Long count = playListRecordService.lambdaQuery()
                .select(PlayListRecord::getVideoCode)
                .eq(PlayListRecord::getPlistCode, playList.getPlistCode())
                .count();
        response.setRecordCount(count==null?0L:count);
        return response;
    }

    public PlayListPreviewResponse toPreviewResponse(PlayList plist){
        PlayListPreviewResponse response = BeanUtil.toBean(plist, PlayListPreviewResponse.class);
        List<PlayListRecord> records = playListRecordService.lambdaQuery()
                .eq(PlayListRecord::getPlistCode, plist.getPlistCode())
                .orderByDesc(PlayListRecord::getCtime)
                .last("limit 5")
                .list();
        if(CollUtil.isEmpty(records)){
            return response;
        }
        List<VideoInfoResponse> videos = records.stream()
                .map(record -> videoQueryExecute.queryBy(record.getVideoCode()))
                .collect(Collectors.toList());
        response.setPreviewVideos(videos);
        return response;
    }

    public Collection<PlayListResponse> toResponse(Collection<PlayList> playLists){
        return null;
    }
}
