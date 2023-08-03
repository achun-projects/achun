package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.PlayListRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【play_list_record】的数据库操作Service
* @createDate 2023-06-29 18:54:59
*/
public interface PlayListRecordService extends IService<PlayListRecord> {
    default PlayListRecord getBy(String plistCode,String videoCode){
        return this.lambdaQuery()
                .eq(PlayListRecord::getPlistCode,plistCode)
                .eq(PlayListRecord::getVideoCode,videoCode)
                .one();
    }
    default List<PlayListRecord> getBy(String videoCode){
        return this.lambdaQuery()
                .eq(PlayListRecord::getVideoCode,videoCode)
                .list();
    }
}
