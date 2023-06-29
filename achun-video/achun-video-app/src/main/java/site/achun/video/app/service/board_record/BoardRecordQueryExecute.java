package site.achun.video.app.service.board_record;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.BoardRecord;
import site.achun.video.app.generator.service.BoardRecordService;

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
        long random = count <= size ? 0 : RandomUtil.randomLong(count - size);
        List<BoardRecord> result = boardRecordService.lambdaQuery()
                .select(BoardRecord::getBoardCode, BoardRecord::getFileCode)
                .eq(BoardRecord::getBoardCode, boardCode)
                .last("limit " + random + "," + size)
                .list();
        log.info("randomQuery-boardCode:{},files:{},size:{},count:{},random:{}",boardCode, JSON.toJSONString(result),size,count,random);
        return result;
    }
}
