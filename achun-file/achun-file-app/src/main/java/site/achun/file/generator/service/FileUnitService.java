package site.achun.file.generator.service;

import site.achun.file.generator.domain.FileUnit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【file_unit】的数据库操作Service
* @createDate 2023-05-16 14:35:30
*/
public interface FileUnitService extends IService<FileUnit> {

    int replaceInto(FileUnit fileUnit);

}
