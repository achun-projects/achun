package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.Board;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【board(画板表)】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface BoardService extends IService<Board> {

    default Board getByCode(String boardCode){
        return lambdaQuery()
                .eq(Board::getBoardCode,boardCode)
                .one();
    }
    default Board getByCode(String boardCode,String userCode){
        return lambdaQuery()
                .eq(Board::getBoardCode,boardCode)
                .eq(Board::getUserCode,userCode)
                .one();
    }
}
