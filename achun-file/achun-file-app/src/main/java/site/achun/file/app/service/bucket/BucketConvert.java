package site.achun.file.app.service.bucket;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import site.achun.file.api.modules.bucket.response.BucketResponse;
import site.achun.file.app.generator.domain.Bucket;

import java.util.List;

@Service
public class BucketConvert {

    public BucketResponse toResponse(Bucket bucket){
        return BeanUtil.toBean(bucket, BucketResponse.class);
    }

    public List<BucketResponse> toResponseList(List<Bucket> buckets){
        return BeanUtil.copyToList(buckets, BucketResponse.class);
    }
}
