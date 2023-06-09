package site.achun.gallery.app.controller.board;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.achun.gallery.app.service.board.BoardQueryService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.board.BoardQueryClient;
import site.achun.gallery.client.module.board.request.QueryBoardDetail;
import site.achun.gallery.client.module.board.request.QueryBoardPage;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.gallery.client.module.group.view.CascaderView;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;

@Slf4j
@Tag(name = "画板查询")
@RestController
@RequiredArgsConstructor
public class BoardQueryController implements BoardQueryClient {

    private final BoardQueryService boardQueryService;

    @Override
    public Rsp<List<CascaderView>> queryGroupBoardToCascaderView() {
        return boardQueryService.groupBoardCascaderView(UserInfo.getCode());
    }

    @Override
    public Rsp<RspPage<BoardResponse>> queryPage(QueryBoardPage query) {
        query.setUserCode(UserInfo.getCode(query::getUserCode));
        return boardQueryService.queryPage(query);
    }

    @Override
    public Rsp<BoardResponse> detail(QueryBoardDetail query) {
        return Rsp.success(boardQueryService.detail(query.getBoardCode()));
    }

}
