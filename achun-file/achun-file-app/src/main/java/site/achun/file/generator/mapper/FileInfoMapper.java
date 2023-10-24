package site.achun.file.generator.mapper;

import site.achun.file.generator.domain.FileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【file_info】的数据库操作Mapper
* @createDate 2023-10-24 14:26:55
* @Entity site.achun.file.generator.domain.FileInfo
*/
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    int replaceInto(FileInfo fileInfo);

}




