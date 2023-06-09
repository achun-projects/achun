package site.achun.gallery.client.module.board;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.board.request.QueryBoardDetail;
import site.achun.gallery.client.module.board.request.QueryBoardPage;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.gallery.client.module.group.view.CascaderView;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;

@FeignClient(name = "achun-gallery-app", contextId = "BoardQueryClient")
public interface BoardQueryClient {

    @GetMapping("/gallery/group-board-cascader-view")
    Rsp<List<CascaderView>> queryGroupBoardToCascaderView();

    @PostMapping("/gallery/board/query-board-page")
    Rsp<RspPage<BoardResponse>> queryPage(@RequestBody QueryBoardPage query);

    @PostMapping("/gallery/board/detail")
    Rsp<BoardResponse> detail(@RequestBody QueryBoardDetail query);

}
