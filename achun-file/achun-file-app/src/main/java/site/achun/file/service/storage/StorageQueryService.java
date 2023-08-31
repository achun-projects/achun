package site.achun.file.service.storage;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.storage.request.QueryStoragePage;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.StorageService;
import site.achun.file.util.PageUtil;
import site.achun.support.api.response.RspPage;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageQueryService {

    private final StorageService storageService;
    private final StorageConvert storageConvert;
    public StorageResponse queryStorage(String bucketCode,String storageName){
        Storage existStorage = storageService.lambdaQuery()
                .eq(Storage::getBucketCode, bucketCode)
                .eq(Storage::getName, storageName)
                .last("LIMIT 1")
                .one();
        if(existStorage != null){
            return storageConvert.toResponse(existStorage);
        }else{
            return null;
        }
    }

    public StorageResponse queryStorage(String storageCode){
        return storageConvert.toResponse(storageService.getStorage(storageCode));
    }

    public RspPage<StorageResponse> queryStoragePage(QueryStoragePage query){
        Page<Storage> pageResult = storageService.lambdaQuery()
                .orderByDesc(Storage::getUtime)
                .page(Page.of(query.getPage(), query.getSize()));
        if(CollUtil.isEmpty(pageResult.getRecords())){
            return query.createPageRsp();
        }
        return PageUtil.batchParse(pageResult,query,storageConvert::toResponse);
    }
}
