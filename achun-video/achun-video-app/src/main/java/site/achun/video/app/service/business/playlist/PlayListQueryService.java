package site.achun.video.app.service.business.playlist;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.support.api.response.RspPage;
import site.achun.video.app.generator.domain.PlayList;
import site.achun.video.app.generator.domain.PlayListRecord;
import site.achun.video.app.generator.service.PlayListRecordService;
import site.achun.video.app.generator.service.PlayListService;
import site.achun.video.app.service.execute.playlist.PlayListConvertExecute;
import site.achun.video.app.utils.PageUtil;
import site.achun.video.client.module.element.ElSelectView;
import site.achun.video.client.module.playlist.request.QueryPlayListPageRequest;
import site.achun.video.client.module.playlist.request.QueryPlaylistDetailInfo;
import site.achun.video.client.module.playlist.request.QueryVideoAddToPlayList;
import site.achun.video.client.module.playlist.response.PlayListPreviewResponse;
import site.achun.video.client.module.playlist.response.PlayListResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/11/10 18:24
 */
@Slf4j
@Service
@AllArgsConstructor
public class PlayListQueryService {

    private final PlayListService playListService;
    private final PlayListRecordService playListRecordService;

    private final PlayListConvertExecute playListConvertExecute;

    public RspPage<PlayListResponse> queryPlayListPage(QueryPlayListPageRequest request){
        Page<PlayList> result = playListService.lambdaQuery()
                .eq(StrUtil.isNotBlank(request.getObjectCode()),PlayList::getObjectCode, request.getObjectCode())
                .eq(request.getObjectType() != null,PlayList::getObjectType, request.getObjectType())
                .page(Page.of(request.getPage(), request.getSize()));
        return PageUtil.parse(result,request, plist-> BeanUtil.toBean(plist,PlayListResponse.class));
    }

    public RspPage<PlayListPreviewResponse> queryPlayListPreviewPage(QueryPlayListPageRequest request){
        Page<PlayList> result = playListService.lambdaQuery()
                .eq(StrUtil.isNotBlank(request.getObjectCode()),PlayList::getObjectCode, request.getObjectCode())
                .eq(request.getObjectType() != null,PlayList::getObjectType, request.getObjectType())
                .page(Page.of(request.getPage(), request.getSize()));
        if(CollUtil.isEmpty(result.getRecords())){
            return request.createPageRsp();
        }
        return PageUtil.parse(result,request,row->playListConvertExecute.toPreviewResponse(row));
    }

    public List<ElSelectView> queryVideoAddToPlaylist(QueryVideoAddToPlayList query){
        List<PlayList> playlist = playListService.lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getObjectCode()),PlayList::getObjectCode, query.getObjectCode())
                .eq(query.getObjectType() != null,PlayList::getObjectType, query.getObjectType())
                .like(StrUtil.isNotBlank(query.getSearch()), PlayList::getName, "%" + query.getSearch() + "%")
                .orderByDesc(PlayList::getRuTime)
                .last("limit "+query.getLimit())
                .list();
        if(CollUtil.isEmpty(playlist)){
            return new ArrayList<>();
        }
        List<PlayListRecord> playlistRecord = playListRecordService.lambdaQuery()
                .eq(PlayListRecord::getVideoCode, query.getVideoCode())
                .list();
        List<String> existRecord = new ArrayList<>();
        if(CollUtil.isNotEmpty(playlistRecord)){
            existRecord = playlistRecord.stream()
                    .map(PlayListRecord::getPlistCode)
                    .collect(Collectors.toList());
        }
        List<String> finalExistRecord = existRecord;
        Function<PlayList,ElSelectView> map = plist->ElSelectView.builder()
                .label(plist.getName())
                .value(plist.getPlistCode())
                .disabled(finalExistRecord.contains(plist.getPlistCode()))
                .build();
        List<ElSelectView> existOptions = playlist.stream()
                .filter(p->finalExistRecord.contains(p.getPlistCode()))
                .map(map)
                .collect(Collectors.toList());
        List<ElSelectView> addToOptions = playlist.stream()
                .filter(p->!finalExistRecord.contains(p.getPlistCode()))
                .map(map)
                .collect(Collectors.toList());
        List<ElSelectView> respList = new ArrayList<>();
        ElSelectView addToView = ElSelectView.builder()
                .label("新增到..")
                .options(addToOptions)
                .build();
        ElSelectView existView = ElSelectView.builder()
                .label("点击从以下删除..")
                .options(existOptions)
                .build();
        respList.add(addToView);
        respList.add(existView);
        return respList;
    }

    public PlayListResponse queryPlaylistDetailInfo(@RequestBody @Valid QueryPlaylistDetailInfo query){
        PlayList playlist = playListService.getByCode(query.getPlistCode());
        return playListConvertExecute.toResponse(playlist);
    }

}
