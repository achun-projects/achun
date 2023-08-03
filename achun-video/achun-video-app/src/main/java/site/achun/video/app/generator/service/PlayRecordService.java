package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.PlayRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【play_record】的数据库操作Service
* @createDate 2023-06-29 18:54:59
*/
public interface PlayRecordService extends IService<PlayRecord> {


    default List<PlayRecord> queryByFileCodes(List<String> fileCodes){
        return this.lambdaQuery()
                .in(PlayRecord::getVideoFileCode,fileCodes)
                .list();
    }
}
