package site.achun.gallery.app.service.board;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Board;
import site.achun.gallery.app.generator.domain.BoardRecord;
import site.achun.gallery.app.generator.service.BoardService;
import site.achun.gallery.app.service.board_record.BoardRecordQueryExecute;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardUpdateExecute {
    private final BoardService boardService;

    private final BoardRecordQueryExecute boardRecordQueryExecute;
    // 为画板设置默认封面
    public String setDefaultCover(String boardCode){
        if(StrUtil.isBlank(boardCode) || boardService.getByCode(boardCode)==null){
            return  null;
        }
        BoardRecord record = boardRecordQueryExecute.randomQueryOne(boardCode);
        if(record == null){
            return null;
        }
        // 更新封面
        boardService.lambdaUpdate()
                .eq(Board::getBoardCode,boardCode)
                .set(Board::getCoverFileCodes,record.getFileCode())
                .update();
        return record.getFileCode();
    }
}
