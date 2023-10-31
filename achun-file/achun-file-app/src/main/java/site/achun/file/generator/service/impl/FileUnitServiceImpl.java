package site.achun.file.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import site.achun.file.generator.domain.FileUnit;
import site.achun.file.generator.service.FileUnitService;
import site.achun.file.generator.mapper.FileUnitMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【file_unit】的数据库操作Service实现
* @createDate 2023-05-16 14:35:30
*/
@Service
@RequiredArgsConstructor
public class FileUnitServiceImpl extends ServiceImpl<FileUnitMapper, FileUnit>
    implements FileUnitService{

    private final FileUnitMapper fileUnitMapper;

    @Override
    public int replaceInto(FileUnit fileUnit) {
        return fileUnitMapper.replaceInto(fileUnit);
    }
}




