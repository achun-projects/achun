package site.achun.file.app.controller.bucket;

import cn.virde.common.pojo.rsp.Rsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.api.modules.bucket.response.BucketResponse;
import site.achun.file.app.service.bucket.BucketQueryService;

import java.util.List;

@Tag(name = "Bucket查询")
@Order(11)
@Slf4j
@RestController
@RequiredArgsConstructor
public class BucketQueryController {

    private final BucketQueryService bucketQueryService;

    @Operation(summary = "查询所有bucket")
    @GetMapping("/file/bucket/query-buckets")
    public Rsp<List<BucketResponse>> queryBuckets(){
        return Rsp.success(bucketQueryService.queryBuckets());
    }

}
