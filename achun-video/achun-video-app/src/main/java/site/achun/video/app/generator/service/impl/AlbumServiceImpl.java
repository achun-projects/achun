package site.achun.video.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.video.app.generator.domain.Album;
import site.achun.video.app.generator.service.AlbumService;
import site.achun.video.app.generator.mapper.AlbumMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【album(相册表)】的数据库操作Service实现
* @createDate 2023-05-29 17:17:29
*/
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album>
    implements AlbumService{

}




