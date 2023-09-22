package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.VideoInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【video_info】的数据库操作Service
* @createDate 2023-09-22 13:42:19
*/
public interface VideoInfoService extends IService<VideoInfo> {
    default VideoInfo queryByCode(String videoCode){
        return this.lambdaQuery()
                .eq(VideoInfo::getVideoCode,videoCode)
                .one();
    }
}
