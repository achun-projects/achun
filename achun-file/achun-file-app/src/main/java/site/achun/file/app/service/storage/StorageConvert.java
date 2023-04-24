package site.achun.file.app.service.storage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.api.modules.storage.response.StorageResponse;
import site.achun.file.app.generator.domain.Bucket;
import site.achun.file.app.generator.domain.Storage;
import site.achun.file.app.generator.service.BucketService;
import site.achun.file.app.service.bucket.BucketConvert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageConvert {

    private final BucketService bucketService;
    private final BucketConvert bucketConvert;
    public List<StorageResponse> toResponseList(Collection<Storage> storages){
        if(CollUtil.isEmpty(storages)) return new ArrayList<>();
        List<String> bucketCodes = storages.stream()
                .map(Storage::getBucketCode)
                .collect(Collectors.toList());
        Map<String, Bucket> bucketMap = bucketService.lambdaQuery()
                .in(Bucket::getBucketCode, bucketCodes)
                .list().stream()
                .collect(Collectors.toMap(Bucket::getBucketCode, Function.identity(), (k1, k2) -> k1));
        List<StorageResponse> resultList = BeanUtil.copyToList(storages, StorageResponse.class);
        resultList.forEach(storageResponse -> {
            Bucket bucket = bucketMap.get(storageResponse.getBucketCode());
            storageResponse.setBucket(bucketConvert.toResponse(bucket));
        });
        return resultList;
    }
}
