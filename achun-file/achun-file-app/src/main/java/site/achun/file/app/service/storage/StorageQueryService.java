package site.achun.file.app.service.storage;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.api.modules.storage.response.StorageResponse;
import site.achun.file.app.generator.domain.Storage;
import site.achun.file.app.generator.service.StorageService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageQueryService {

    private final StorageService storageService;

    public List<StorageResponse> queryStorages(){
        List<Storage> storages = storageService.lambdaQuery()
                .list();
        List<StorageResponse> list = BeanUtil.copyToList(storages, StorageResponse.class);
        return null;
    }
}
