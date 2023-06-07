package site.achun.gallery.client.module.board;


import jakarta.validation.constraints.NotNull;
import site.achun.gallery.client.module.board.request.BoardCreateRequest;
import site.achun.gallery.client.module.board.request.BoardUpdateRequest;
import site.achun.gallery.client.module.board.request.QueryBoardPage;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/7 16:36
 */
public interface BoardFacade {

    /**
     * 创建一个画板
     * @param createRequest
     * @return
     */
    Rsp<BoardResponse> create(BoardCreateRequest createRequest);


    /**
     * 分页查询画板
     * @param userCode
     * @return
     */
    Rsp<RspPage<BoardResponse>> queryPage(QueryBoardPage query);

    /**
     * 查询相册详情
     * @param boardCode
     * @return
     */
    Rsp<BoardResponse> detail(@NotNull String boardCode);


    /**
     * 删除空的画板
     * @param boardCode
     * @return
     */
    Rsp<Void> removeBoard(@NotNull String boardCode);

    /**
     * 修改相册信息
     * @param updateRequest
     * @return
     */
    Rsp<BoardResponse> updateBoard(BoardUpdateRequest updateRequest);

    Rsp<Void> updateRecordUTime(@NotNull String boardCode);
}
