package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.VideoFileInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【video_file_info】的数据库操作Service
* @createDate 2023-06-29 18:54:59
*/
public interface VideoFileInfoService extends IService<VideoFileInfo> {
    default VideoFileInfo getByFileCode(String fileCode){
        return this.lambdaQuery()
                .eq(VideoFileInfo::getFileCode,fileCode)
                .one();
    }

    default List<VideoFileInfo> getByVideoCode(String videoCode){
        return this.lambdaQuery()
                .eq(VideoFileInfo::getVideoCode,videoCode)
                .list();
    }

    default List<VideoFileInfo> getByVideoCodes(Collection<String> videoCodes){
        return this.lambdaQuery()
                .in(VideoFileInfo::getVideoCode,videoCodes)
                .list();
    }
}
