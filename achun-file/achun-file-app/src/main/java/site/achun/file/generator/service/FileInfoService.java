package site.achun.file.generator.service;

import site.achun.file.generator.domain.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import site.achun.support.api.enums.Deleted;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【file_info】的数据库操作Service
* @createDate 2023-05-16 14:35:30
*/
public interface FileInfoService extends IService<FileInfo> {

    default FileInfo getByCode(String fileCode){
        return this.lambdaQuery()
                .eq(FileInfo::getFileCode, fileCode)
                .eq(FileInfo::getDeleted, Deleted.NO.getStatus())
                .one();
    }

    default List<FileInfo> getByCodes(Collection<String> fileCodes){
        return this.lambdaQuery()
                .in(FileInfo::getFileCode,fileCodes)
                .eq(FileInfo::getDeleted, Deleted.NO.getStatus())
                .list();
    }
}
