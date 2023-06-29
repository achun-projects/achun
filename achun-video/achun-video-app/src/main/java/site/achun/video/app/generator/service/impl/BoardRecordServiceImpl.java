package site.achun.video.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.video.app.generator.domain.BoardRecord;
import site.achun.video.app.generator.service.BoardRecordService;
import site.achun.video.app.generator.mapper.BoardRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【board_record】的数据库操作Service实现
* @createDate 2023-05-29 17:17:29
*/
@Service
public class BoardRecordServiceImpl extends ServiceImpl<BoardRecordMapper, BoardRecord>
    implements BoardRecordService {

}




