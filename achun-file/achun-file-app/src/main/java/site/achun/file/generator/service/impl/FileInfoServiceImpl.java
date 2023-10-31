package site.achun.file.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.service.FileInfoService;
import site.achun.file.generator.mapper.FileInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【file_info】的数据库操作Service实现
* @createDate 2023-10-24 14:26:55
*/
@Service
@RequiredArgsConstructor
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo>
    implements FileInfoService{

    private final FileInfoMapper fileInfoMapper;
    @Override
    public int replaceInto(FileInfo fileInfo) {
        return fileInfoMapper.replaceInto(fileInfo);
    }
}




