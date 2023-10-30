package site.achun.file.service.dir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.FileDir;
import site.achun.file.generator.service.FileDirService;
import site.achun.file.service.storage.StorageQueryService;
import site.achun.support.api.enums.Deleted;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDirScanService {

    private final StorageQueryService storageQueryService;
    private final FileDirService fileDirService;
    public void scanFromStorageCode(String storageCode){
        StorageResponse storage = storageQueryService.queryStorage(storageCode);
        // 检测storage是否存在于file_dir
        FileDir storageFileDir = fileDirService.queryByCode(storage.getStorageCode());
        if(storageFileDir == null){
            FileDir dir = FileDir.builder()
                    .dirCode(storage.getStorageCode())
                    .storageCode(storage.getStorageCode())
                    .parentDirCode(null)
                    .ctime(LocalDateTime.now())
                    .deleted(Deleted.NO.getStatus())
                    .path(null)
                    .name(storage.getName())
                    .build();
            fileDirService.save(dir);
            scan(dir);
        }else{
            scan(storageFileDir);
        }
    }

    public void scan(FileDir dir){

    }
}
