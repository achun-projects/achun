package site.achun.file.service.dir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.dir.response.DirResponse;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.FileDir;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.FileDirService;
import site.achun.file.service.storage.StorageQueryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDirQueryService {

    private final StorageQueryService storageQueryService;
    private final FileDirService fileDirService;

    public DirResponse query(ByDirCode dirCode){
        FileDir fileDir = fileDirService.queryByCode(dirCode.getDirCode());
        DirResponse response = toResponse(fileDir);
        response.setStorage(storageQueryService.queryStorage(response.getStorageCode()));
        return response;
    }

    private DirResponse toResponse(FileDir fileDir){
        return DirResponse.builder()
                .dirCode(fileDir.getDirCode())
                .name(fileDir.getName())
                .parentCode(fileDir.getParentDirCode())
                .path(fileDir.getPath())
                .storageCode(fileDir.getStorageCode())
                .build();
    }


}