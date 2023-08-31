package site.achun.file.service.storage;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.Storage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageConvert {

    public StorageResponse toResponse(Storage storage){
        return StorageResponse.builder()
                .accessPrefix(storage.getAccessPrefix())
                .bucketCode(storage.getBucketCode())
                .bucket(null) // TODO 后续补充
                .storageCode(storage.getStorageCode())
                .status(storage.getStatus())
                .extra(storage.getExtra())
                .utime(storage.getUtime())
                .name(storage.getName())
                .path(storage.getPath())
                .ctime(storage.getCtime())
                .build();
    }

    public List<StorageResponse> toResponse(List<Storage> storage){
        return storage.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
