package site.achun.file.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.file.generator.domain.FileDir;
import site.achun.file.generator.service.FileDirService;
import site.achun.file.generator.mapper.FileDirMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【file_dir】的数据库操作Service实现
* @createDate 2023-10-30 20:38:41
*/
@Service
public class FileDirServiceImpl extends ServiceImpl<FileDirMapper, FileDir>
    implements FileDirService{

}




