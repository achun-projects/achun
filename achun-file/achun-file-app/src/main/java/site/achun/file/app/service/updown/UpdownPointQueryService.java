package site.achun.file.app.service.updown;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.api.modules.storage.enums.StorageStatus;
import site.achun.file.api.modules.updown.response.UpdownPointInfo;
import site.achun.file.app.generator.domain.Storage;
import site.achun.file.app.generator.service.StorageService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdownPointQueryService {

    private final StorageService storageService;

    public UpdownPointInfo queryOneAvailablePoint(String bucketCode){
        // 查询出所有可用storage，用storageCode查询可用节点。
        List<Storage> availableStorages = storageService.lambdaQuery()
                .eq(Storage::getBucketCode, bucketCode)
                .eq(Storage::getStatus, StorageStatus.AVAILABLE)
                .list();

        // 询问updown，看是否有可用节点
        return null;

    }
}
