package site.achun.gallery.client.module.board;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.board.request.BoardCreateRequest;
import site.achun.gallery.client.module.board.request.CreateOrUpdateBoard;
import site.achun.gallery.client.module.board.request.RemoveBoardRequest;
import site.achun.gallery.client.module.board.response.BoardResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "BoardUpdateClient")
public interface BoardUpdateClient {

    @Operation(summary = "创建画板")
    @PostMapping("/gallery/board/create")
    Rsp<BoardResponse> createBoard(@RequestBody CreateOrUpdateBoard createRequest);

    @Operation(summary = "创建或更新画板")
    @PostMapping("/gallery/board/create-or-update")
    Rsp<BoardResponse> createOrUpdateBoard(@RequestBody CreateOrUpdateBoard request);

    @Operation(summary = "删除画板，只有空的画板才可以删除")
    @PostMapping("/gallery/board/remove-when-empty")
    Rsp<Void> removeBoard(@RequestBody RemoveBoardRequest removeBoard);
}
