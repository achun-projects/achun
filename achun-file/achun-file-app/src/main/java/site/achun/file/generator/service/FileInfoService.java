package site.achun.file.generator.service;

import site.achun.file.generator.domain.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【file_info】的数据库操作Service
* @createDate 2023-05-16 14:35:30
*/
public interface FileInfoService extends IService<FileInfo> {

    default FileInfo getByCode(String fileCode){
        return this.lambdaQuery().eq(FileInfo::getFileCode, fileCode).one();
    }
}
