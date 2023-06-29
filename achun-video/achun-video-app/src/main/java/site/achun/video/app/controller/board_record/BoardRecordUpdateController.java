package site.achun.video.app.controller.board_record;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.video.app.service.board_record.BoardRecordUpdateService;
import site.achun.video.client.module.board.request.BoardRecordCreateRequest;
import site.achun.video.client.module.board.response.BoardRecordResponse;
import site.achun.video.client.module.board_record.BoardRecordUpdateClient;
import site.achun.support.api.response.Rsp;

import java.util.List;

@Slf4j
@Tag(name = "画板记录更新")
@RestController
@RequiredArgsConstructor
public class BoardRecordUpdateController implements BoardRecordUpdateClient {

    private final BoardRecordUpdateService boardRecordUpdateService;

    @Override
    public Rsp<Void> saveBoardRecord(BoardRecordCreateRequest createRequest) {
        return boardRecordUpdateService.create(createRequest);
    }

    @Override
    public Rsp<Integer> deleteBoardRecord(BoardRecordCreateRequest createRequest) {
        return boardRecordUpdateService.remove(createRequest);
    }

    @Override
    public Rsp<List<BoardRecordResponse>> moveRecordsToOtherBoard(BoardRecordCreateRequest createRequest) {
        return Rsp.success(null,"暂未实现");
    }
}
