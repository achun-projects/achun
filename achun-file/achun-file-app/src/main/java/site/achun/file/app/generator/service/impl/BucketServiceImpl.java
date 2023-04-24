package site.achun.file.app.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import site.achun.file.app.generator.domain.Bucket;
import site.achun.file.app.generator.service.BucketService;
import site.achun.file.app.generator.mapper.BucketMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【bucket】的数据库操作Service实现
* @createDate 2023-04-24 16:11:07
*/
@Service
public class BucketServiceImpl extends ServiceImpl<BucketMapper, Bucket>
    implements BucketService{

}




