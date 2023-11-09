package site.achun.gallery.app.generator.service;

import site.achun.gallery.app.generator.domain.Album;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.Collection;

/**
* @author Administrator
* @description 针对表【album(相册表)】的数据库操作Service
* @createDate 2023-11-09 15:30:56
*/
public interface AlbumService extends IService<Album> {

    default Album getByCode(String albumCode){
        return lambdaQuery()
                .eq(Album::getAlbumCode,albumCode)
                .one();
    }


    default Album getByCode(String albumCode,String userCode){
        return lambdaQuery()
                .eq(Album::getAlbumCode,albumCode)
                .eq(Album::getUserCode,userCode)
                .one();
    }


    default Album getByName(String name,String userCode){
        return lambdaQuery()
                .eq(Album::getName,name)
                .eq(Album::getUserCode,userCode)
                .one();
    }


    default void updateRecordUtime(String albumCode){
        lambdaUpdate()
                .eq(Album::getAlbumCode,albumCode)
                .set(Album::getRecordUtime, LocalDateTime.now())
                .update();
    }
    default void updateRecordUtime(Collection<String> albumCodes){
        this.lambdaUpdate()
                .in(Album::getAlbumCode,albumCodes)
                .set(Album::getRecordUtime,LocalDateTime.now())
                .update();
    }
}
