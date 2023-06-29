package site.achun.video.client.module.board_record;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.video.client.module.board.request.BoardRecordCreateRequest;
import site.achun.video.client.module.board.response.BoardRecordResponse;
import site.achun.support.api.response.Rsp;

import java.util.List;

@FeignClient(name = "achun-gallery-app", contextId = "BoardRecordUpdateClient")
public interface BoardRecordUpdateClient {

    @Operation(summary = "创建画板记录")
    @PostMapping("/gallery/board/records/create")
    Rsp<Void> saveBoardRecord(@RequestBody BoardRecordCreateRequest createRequest);

    @Operation(summary = "删除画板记录")
    @PostMapping("/gallery/remove-board-records")
    Rsp<Integer> deleteBoardRecord(@RequestBody BoardRecordCreateRequest createRequest);

    @Operation(summary = "转移记录到别的画板")
    @PostMapping("/gallery/board-record/move-records-to-other-board")
    Rsp<List<BoardRecordResponse>> moveRecordsToOtherBoard(@RequestBody BoardRecordCreateRequest createRequest);
}
