package site.achun.file.generator.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.achun.file.generator.domain.FileDir;
import site.achun.file.generator.service.FileDirService;
import site.achun.file.generator.mapper.FileDirMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【file_dir】的数据库操作Service实现
* @createDate 2023-10-30 20:38:41
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class FileDirServiceImpl extends ServiceImpl<FileDirMapper, FileDir>
    implements FileDirService{

    private final FileDirMapper fileDirMapper;
    @Override
    public void batchReplaceInto(List<FileDir> fileDirList) {
        if(CollUtil.isEmpty(fileDirList)){
            return;
        }
        for (FileDir fileDir : fileDirList) {
            fileDirMapper.replaceInto(fileDir);
        }
    }
}




