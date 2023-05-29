package site.achun.file.generator.mapper;

import org.apache.ibatis.annotations.Param;
import site.achun.file.generator.domain.FileUnit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【file_unit】的数据库操作Mapper
* @createDate 2023-05-16 14:35:30
* @Entity site.achun.file.generator.domain.FileUnit
*/
public interface FileUnitMapper extends BaseMapper<FileUnit> {


    int replaceInto(@Param("fu") FileUnit fileUnit);
}




