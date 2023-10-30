package site.achun.file.generator.service;

import site.achun.file.generator.domain.FileDir;
import com.baomidou.mybatisplus.extension.service.IService;
import site.achun.support.api.enums.Deleted;

/**
* @author Administrator
* @description 针对表【file_dir】的数据库操作Service
* @createDate 2023-10-30 20:38:41
*/
public interface FileDirService extends IService<FileDir> {
    default FileDir queryByCode(String dirCode){
        return this.lambdaQuery()
                .eq(FileDir::getDirCode,dirCode)
                .eq(FileDir::getDeleted, Deleted.NO.getStatus())
                .one();
    }
}
