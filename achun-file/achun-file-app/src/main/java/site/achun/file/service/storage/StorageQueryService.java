package site.achun.file.service.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.StorageService;

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
}
