package site.achun.gallery.app.service.board_record;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Board;
import site.achun.gallery.app.generator.domain.BoardRecord;
import site.achun.gallery.app.generator.service.BoardRecordService;
import site.achun.gallery.app.generator.service.BoardService;
import site.achun.gallery.client.module.board.request.BoardRecordCreateRequest;
import site.achun.support.api.response.Rsp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardRecordUpdateService {

    private final BoardRecordService boardRecordService;
    private final BoardService boardService;

    public Rsp<Void> create(BoardRecordCreateRequest createRequest){
        List<BoardRecord> existRecords = boardRecordService.select(createRequest.getBoardCode(), createRequest.getFileCodes());
        Set<String> existFileCodes = new HashSet<>();
        if(CollUtil.isNotEmpty(existRecords)){
            existFileCodes = existRecords.stream()
                    .map(BoardRecord::getFileCode)
                    .collect(Collectors.toSet());
        }
        Set<String> finalExistFileCodes = existFileCodes;
        List<BoardRecord> boardRecords = createRequest.getFileCodes().stream()
                .filter(fileCode-> !finalExistFileCodes.contains(fileCode))
                .map(fileCode -> BoardRecord.builder()
                        .boardCode(createRequest.getBoardCode())
                        .fileCode(fileCode)
                        .ctime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
        boardRecordService.saveBatch(boardRecords);
        updateRecordUTime(createRequest.getBoardCode());
        return Rsp.success(null,"保存成功");
    }

    public Rsp<Integer> remove(BoardRecordCreateRequest createRequest){
        updateRecordUTime(createRequest.getBoardCode());
        boardRecordService.lambdaUpdate()
                .eq(BoardRecord::getBoardCode,createRequest.getBoardCode())
                .in(BoardRecord::getFileCode,createRequest.getFileCodes())
                .remove();
        return Rsp.success(-1);
    }

    public void updateRecordUTime(String boardCode){
        boardService.lambdaUpdate()
                .eq(Board::getBoardCode,boardCode)
                .set(Board::getRecordUtime,LocalDateTime.now())
                .update();
    }

}
