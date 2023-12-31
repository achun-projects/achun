package site.achun.file.generator.mapper;

import org.apache.ibatis.annotations.Param;
import site.achun.file.generator.domain.FileDir;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【file_dir】的数据库操作Mapper
* @createDate 2023-10-30 20:38:41
* @Entity site.achun.file.generator.domain.FileDir
*/
public interface FileDirMapper extends BaseMapper<FileDir> {

    int replaceInto(@Param("req") FileDir fileDir);

}




