package site.achun.file.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.file.generator.domain.Bucket;
import site.achun.file.generator.service.BucketService;
import site.achun.file.generator.mapper.BucketMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【bucket】的数据库操作Service实现
* @createDate 2023-05-16 14:35:30
*/
@Service
public class BucketServiceImpl extends ServiceImpl<BucketMapper, Bucket>
    implements BucketService{

}




