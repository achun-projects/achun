package site.achun.gallery.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.gallery.app.generator.domain.Board;
import site.achun.gallery.app.generator.service.BoardService;
import site.achun.gallery.app.generator.mapper.BoardMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【board(画板表)】的数据库操作Service实现
* @createDate 2023-05-29 17:17:29
*/
@Service
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board>
    implements BoardService{

}




