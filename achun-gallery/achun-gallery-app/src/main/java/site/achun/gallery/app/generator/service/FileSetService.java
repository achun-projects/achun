package site.achun.gallery.app.generator.service;

import site.achun.gallery.app.generator.domain.FileSet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【file_set】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface FileSetService extends IService<FileSet> {

    default FileSet getByCode(String code){
        return lambdaQuery()
                .eq(FileSet::getCode,code)
                .one();
    }

    default FileSet getByCode(String code,String userCode){
        return lambdaQuery()
                .eq(FileSet::getCode,code)
                .eq(FileSet::getUserCode,userCode)
                .one();
    }

    default List<FileSet> getByCodes(Collection<String> codes, String userCode){
        return lambdaQuery()
                .in(FileSet::getCode,codes)
                .eq(FileSet::getUserCode,userCode)
                .list();
    }
}
