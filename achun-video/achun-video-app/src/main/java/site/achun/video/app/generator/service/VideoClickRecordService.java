package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.VideoClickRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【video_click_record】的数据库操作Service
* @createDate 2023-06-29 18:54:59
*/
public interface VideoClickRecordService extends IService<VideoClickRecord> {

    default List<VideoClickRecord> queryByVideoCode(String videoCode){
        return this.lambdaQuery()
                .eq(VideoClickRecord::getVideoCode,videoCode)
                .list();
    }
}
