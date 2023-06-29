package site.achun.video.app.service.board;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.app.generator.domain.GalleryGroup;
import site.achun.video.app.service.gallery_group.GroupConvertExecute;
import site.achun.video.app.service.gallery_group.MyGalleryGroupService;
import site.achun.video.client.module.board.request.QueryBoardPage;
import site.achun.video.client.module.board.response.BoardResponse;
import site.achun.video.client.module.group.enums.GroupType;
import site.achun.video.client.module.group.response.GroupResponse;
import site.achun.video.client.module.group.view.CascaderView;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardQueryService {
    private final MyBoardService myBoardService;
    private final MediaFileQueryClient fileQueryClient;
    private final MyGalleryGroupService myGalleryGroupService;
    private final GroupConvertExecute groupConvertExecute;

    private final BoardUpdateExecute boardUpdateExecute;

    public BoardResponse detail(String boardCode){
        return myBoardService.selectByBoardCode(boardCode);
    }

    public RspPage<BoardResponse> queryPage(QueryBoardPage query){
        // 获取相册分页数据
        RspPage<BoardResponse> rspPage = myBoardService.queryPage(query);
        if(CollectionUtil.isEmpty(rspPage.getRows())){
            return rspPage;
        }

        // 如果封面为空，随机设置默认封面
        rspPage.getRows().stream().forEach(board->{
            if(StrUtil.isBlank(board.getCover())){
                String coverFileCode = boardUpdateExecute.setDefaultCover(board.getBoardCode());
                if(coverFileCode!=null){
                    MediaFileResponse file = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(coverFileCode).build()).getData();
                    if(file!=null){
                        board.setCover(file.getCover());
                    }else{
                        log.info("file not found,fileCode:{}",coverFileCode);
                    }
                }
            }
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
