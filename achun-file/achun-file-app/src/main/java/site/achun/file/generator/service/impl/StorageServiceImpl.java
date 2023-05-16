package site.achun.file.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.StorageService;
import site.achun.file.generator.mapper.StorageMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【storage】的数据库操作Service实现
* @createDate 2023-05-16 14:35:30
*/
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
    implements StorageService{

}




