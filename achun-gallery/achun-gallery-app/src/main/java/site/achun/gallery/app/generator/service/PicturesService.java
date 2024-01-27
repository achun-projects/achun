package site.achun.gallery.app.generator.service;

import site.achun.gallery.app.generator.domain.Pictures;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【pictures】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface PicturesService extends IService<Pictures> {

    default Pictures getByFileCode(String fileCode){
        return this.lambdaQuery()
                .eq(Pictures::getFileCode,fileCode)
                .one();
    }

    default List<Pictures> getByFileCodes(Collection<String> fileCodes){
        return this.lambdaQuery()
                .in(Pictures::getFileCode,fileCodes)
                .list();
    }

    default List<Pictures> getByFileSetCode(String setCode){
        return this.lambdaQuery()
                .eq(Pictures::getSetCode,setCode)
                .list();
    }

    default List<Pictures> getByFileSetCodes(Collection<String> setCodes){
        return this.lambdaQuery()
                .in(Pictures::getSetCode,setCodes)
                .list();
    }
}
