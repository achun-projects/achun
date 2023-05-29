package site.achun.gallery.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【pictures】的数据库操作Service实现
* @createDate 2023-05-29 17:17:29
*/
@Service
public class PicturesServiceImpl extends ServiceImpl<PicturesMapper, Pictures>
    implements PicturesService{

}




