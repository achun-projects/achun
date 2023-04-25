package site.achun.file.app.service.bucket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.api.modules.bucket.response.BucketResponse;
import site.achun.file.app.generator.domain.Bucket;
import site.achun.file.app.generator.service.BucketService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BucketQueryService {

    private final BucketService bucketService;
    private final BucketConvert bucketConvert;

    public List<BucketResponse> queryBuckets(){
        List<Bucket> buckets = bucketService.lambdaQuery()
                .list();
        return bucketConvert.toResponseList(buckets);
    }


}
