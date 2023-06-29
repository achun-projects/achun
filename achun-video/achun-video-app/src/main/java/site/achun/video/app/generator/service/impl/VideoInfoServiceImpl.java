package site.achun.video.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.video.app.generator.domain.VideoInfo;
import site.achun.video.app.generator.service.VideoInfoService;
import site.achun.video.app.generator.mapper.VideoInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【video_info】的数据库操作Service实现
* @createDate 2023-06-29 18:54:59
*/
@Service
public class VideoInfoServiceImpl extends ServiceImpl<VideoInfoMapper, VideoInfo>
    implements VideoInfoService{

}




