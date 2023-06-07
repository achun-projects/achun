package site.achun.gallery.app.generator.service;

import site.achun.gallery.app.generator.domain.BoardRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【board_record】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface BoardRecordService extends IService<BoardRecord> {


    default List<BoardRecord> select(String boardCode, Collection<String> fileCodes){
        return this.lambdaQuery()
                .eq(BoardRecord::getBoardCode,boardCode)
                .in(BoardRecord::getFileCode,fileCodes)
                .list();
    }


    default BoardRecord select(String boardCode, String fileCode){
        return this.lambdaQuery()
                .eq(BoardRecord::getBoardCode,boardCode)
                .eq(BoardRecord::getFileCode,fileCode)
                .one();
    }
}
