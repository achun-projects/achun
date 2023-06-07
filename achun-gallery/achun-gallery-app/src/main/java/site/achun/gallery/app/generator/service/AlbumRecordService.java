package site.achun.gallery.app.generator.service;

import site.achun.gallery.app.generator.domain.AlbumRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【album_record】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface AlbumRecordService extends IService<AlbumRecord> {

    default AlbumRecord getBy(String albumCode,String setCode) {
        return this.lambdaQuery()
                .eq(AlbumRecord::getAlbumCode,albumCode)
                .eq(AlbumRecord::getSetCode,setCode)
                .one();
    }
    default AlbumRecord getBySetCode(String setCode) {
        return this.lambdaQuery()
                .eq(AlbumRecord::getSetCode,setCode)
                .one();
    }

    default List<AlbumRecord> getBy(String albumCode){
        return this.lambdaQuery()
                .eq(AlbumRecord::getAlbumCode,albumCode)
                .list();
    }

    default boolean deleteBy(String setCode){
        return this.lambdaUpdate()
                .eq(AlbumRecord::getSetCode,setCode)
                .remove();
    }
}
