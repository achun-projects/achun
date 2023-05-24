package site.achun.file.service.storage;

import cn.hutool.crypto.digest.MD5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.storage.beans.StorageExtra;
import site.achun.file.client.module.storage.enums.StorageStatus;
import site.achun.file.client.module.storage.request.CreateStorageWithDetected;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.StorageService;
import site.achun.support.api.exception.LogicException;
import site.achun.updown.client.module.detected.StorageDetectedClient;
import site.achun.updown.client.module.detected.request.RequestLoopAndInitFiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageUpdateService {

    private final StorageQueryService storageQueryService;
    private final StorageService storageService;
    private final StorageConvert storageConvert;
    private final StorageDetectedClient storageDetectedClient;

    public StorageResponse createStorageWithDetected(CreateStorageWithDetected create) {
        log.info("createStorageWithDetected: {}", create);
        // 校验storage是否已存在
        StorageResponse exitStorage = storageQueryService.queryStorage(create.getBucketCode(), create.getStorageName());
        if(exitStorage != null){
            throw new LogicException("存储单位存在");
        }
        // 存储storage
        Storage storage = Storage.builder()
                .bucketCode(create.getBucketCode())
                .name(create.getStorageName())
                .storageCode(generatorStorageCode(create.getBucketCode(),create.getStorageName()))
                .status(StorageStatus.READONLY.getCode())
                .extra(defaultStorageExtra(create.getStorageRootPath()))
                .accessPrefix("http://disk3.buckets.achun.site")
                .path(create.getStorageRootPath())
                .utime(LocalDateTime.now())
                .ctime(LocalDateTime.now())
                .build();
        storageService.save(storage);
        // 触发文件探测服务
        RequestLoopAndInitFiles requestLoopAndInitFiles = RequestLoopAndInitFiles.builder()
                .storageCode(storage.getStorageCode())
                .localPath(storage.getPath())
                .build();
        storageDetectedClient.asyncLoopAndInitFiles(requestLoopAndInitFiles);
        return storageConvert.toResponse(storage);
    }

    public static String generatorStorageCode(String bucketCode,String storageName){
        return bucketCode + "_" + MD5.create().digestHex(bucketCode+"_"+storageName).substring(0,5);
    }

    private static StorageExtra defaultStorageExtra(String path){
        StorageExtra.Env defaultEnv = new StorageExtra.Env();
        defaultEnv.setPath("/path/");
        defaultEnv.setCode("LAN");
        defaultEnv.setHost("http://disk3.buckets.achun.site");

        List<StorageExtra.Env> envs = new ArrayList<>();

        StorageExtra.Env local = new StorageExtra.Env();
        local.setPath("/path/");
        local.setCode("LOCAL");
        local.setHost("http://disk3.buckets.achun.site");
        envs.add(local);

        StorageExtra.Env lan = new StorageExtra.Env();
        lan.setPath("/path/");
        lan.setCode("LAN");
        lan.setHost("http://disk3.buckets.achun.site");
        envs.add(lan);

        StorageExtra.Env net = new StorageExtra.Env();
        net.setPath("/path/");
        net.setCode("NET");
        net.setHost("http://disk3.buckets.sun-ao.top:9034");
        envs.add(net);

        return StorageExtra.builder()
                .defaultEnv(defaultEnv)
                .envs(envs)
                .build();
    }
}
