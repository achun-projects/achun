package site.achun.file.generator.service;

import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.generator.domain.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import site.achun.support.api.enums.Deleted;

import java.util.Collection;
import java.util.List;

/**
* @author Administrator
* @description 针对表【file_info】的数据库操作Service
* @createDate 2023-10-24 14:26:55
*/
public interface FileInfoService extends IService<FileInfo> {

    int replaceInto(FileInfo fileInfo);

    default List<FileInfo> getByUnitCode(String unitCode){
        return this.lambdaQuery()
                .eq(FileInfo::getUnitCode,unitCode)
                .list();
    }

    default FileInfo getBy(QueryByFileCode query){
        boolean containDeleted = query.getContainDeleted() != null ? query.getContainDeleted() : false;
        return this.lambdaQuery()
                .eq(FileInfo::getFileCode, query.getFileCode())
                .eq(!containDeleted,FileInfo::getDeleted, Deleted.NO.getStatus())
                .one();
    }
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
