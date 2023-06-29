package site.achun.video.app.generator.service;

import site.achun.video.app.generator.domain.PicturesLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【pictures_like】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface PicturesLikeService extends IService<PicturesLike> {

    default PicturesLike getByCode(String listCode,String fileCode,String userCode){
        return this.lambdaQuery()
                .eq(PicturesLike::getListCode,listCode)
                .eq(PicturesLike::getFileCode,fileCode)
                .eq(PicturesLike::getUserCode,userCode)
                .one();
    }
}
