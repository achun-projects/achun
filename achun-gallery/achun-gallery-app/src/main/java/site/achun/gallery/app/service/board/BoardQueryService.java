package site.achun.gallery.app.service.board;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.generator.domain.GalleryGroup;
import site.achun.gallery.app.service.gallery_group.GroupConvertExecute;
import site.achun.gallery.app.service.gallery_group.MyGalleryGroupService;
import site.achun.gallery.client.module.board.request.QueryBoardPage;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.gallery.client.module.group.enums.GroupType;
import site.achun.gallery.client.module.group.response.GroupResponse;
import site.achun.gallery.client.module.group.view.CascaderView;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardQueryService {
    private final MyBoardService myBoardService;
    private final MediaFileQueryClient fileQueryClient;
    private final MyGalleryGroupService myGalleryGroupService;
    private final GroupConvertExecute groupConvertExecute;

    public BoardResponse detail(String boardCode){
        return myBoardService.selectByBoardCode(boardCode);
    }

    public RspPage<BoardResponse> queryPage(QueryBoardPage query){
        // 获取相册分页数据
       RspPage<BoardResponse> rspPage = myBoardService.queryPage(query);
        if(CollectionUtil.isEmpty(rspPage.getRows())){
            return rspPage;
        }
        // 获取相册封面
        Set<String> coverFileCodes = rspPage.getRows().stream()
                .map(BoardResponse::getCoverFileCodes)
                .collect(Collectors.toSet());
        if(CollectionUtil.isEmpty(coverFileCodes)){
            return rspPage;
        }


        Map<String, MediaFileResponse> coverFileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(coverFileCodes).build()).getData();
        rspPage.getRows().stream().forEach(a->{
            if(coverFileMap != null && coverFileMap.containsKey(a.getCoverFileCodes())){
                a.setCover(coverFileMap.get(a.getCoverFileCodes()).getMediumUrl());
            }
        });
        // 获取预览图片
        List<String> previewFileCodes = rspPage.getRows().stream()
                .flatMap(board -> board.getPreviews().stream())
                .collect(Collectors.toList());
        Map<String, MediaFileResponse> previewFileMap = fileQueryClient.queryFileMap(QueryByFileCodes.builder().fileCodes(previewFileCodes).build()).getData();
        rspPage.getRows().stream().forEach(board -> {
            List<String> previewUrls = board.getPreviews().stream()
                    .map(fileCode -> previewFileMap.get(fileCode).getMediumUrl())
                    .collect(Collectors.toList());
            board.setPreviews(previewUrls);
        });
        return rspPage;
    }

    public Rsp<List<CascaderView>> groupBoardCascaderView(String userCode){
        // 获取分组信息
        List<GalleryGroup> groups = myGalleryGroupService.select(GroupType.BOARD, userCode);
        List<GroupResponse> groupList = groups.stream()
                .map(groupConvertExecute::toResponse)
                .collect(Collectors.toList());

        // 获取画板
        QueryBoardPage queryBoardPage = new QueryBoardPage();
        queryBoardPage.setGroup(null);
        queryBoardPage.setPage(1);
        queryBoardPage.setSize(1000);
        queryBoardPage.setUserCode(userCode);
        List<BoardResponse> boardList = myBoardService.queryPage(queryBoardPage).getRows();
        // 组装顶层菜单
        List<CascaderView> topView = groupList.stream()
                .map(group -> CascaderView.builder()
                        .value(group.getGroupCode())
                        .label(group.getName())
                        .build())
                .collect(Collectors.toList());
        // 组装子菜单
        topView.stream().forEach(view->{
            List<CascaderView> children = boardList.stream()
                    .filter(board -> board.getGroupCode().equals(view.getValue()))
                    .map(board -> CascaderView.builder()
                            .value(board.getBoardCode())
                            .label(board.getName())
                            .build())
                    .collect(Collectors.toList());
            view.setChildren(children);
        });
        return Rsp.success(topView);
    }

}
