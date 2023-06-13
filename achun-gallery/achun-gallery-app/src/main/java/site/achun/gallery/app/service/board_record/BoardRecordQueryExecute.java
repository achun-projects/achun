package site.achun.gallery.app.service.board_record;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.BoardRecord;
import site.achun.gallery.app.generator.service.BoardRecordService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardRecordQueryExecute {

    private final BoardRecordService boardRecordService;

    public BoardRecord randomQueryOne(String boardCode){
        List<BoardRecord> result = randomQuery(boardCode, 1);
        if(result == null||CollUtil.isEmpty(result)) return null;
        return result.get(0);
    }

    public List<BoardRecord> randomQuery(String boardCode, long size){
        if(StrUtil.isBlank(boardCode) || size <= 0){
            log.info("随机查询画板记录，参数错误，boardCode:{},size:{}",boardCode,size);
            return new ArrayList<>();
        }
        Long count = boardRecordService.lambdaQuery()
                .eq(BoardRecord::getBoardCode, boardCode)
                .count();
        if(count == null || count == 0){
            return new ArrayList<>();
        }
        log.info("size:{},count:{}",size,count);
        long random = count <= size ? 0 : RandomUtil.randomLong(count - size);
        return boardRecordService.lambdaQuery()
                .eq(BoardRecord::getBoardCode,boardCode)
                .page(Page.of(random,size))
                .getRecords();
    }
}
