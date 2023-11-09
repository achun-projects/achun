package site.achun.gallery.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.gallery.app.generator.domain.Album;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.generator.mapper.AlbumMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【album(相册表)】的数据库操作Service实现
* @createDate 2023-11-09 15:30:56
*/
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album>
    implements AlbumService{

}




