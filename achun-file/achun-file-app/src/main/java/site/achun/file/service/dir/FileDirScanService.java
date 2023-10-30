package site.achun.file.service.dir;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.FileDir;
import site.achun.file.generator.service.FileDirService;
import site.achun.file.service.storage.StorageQueryService;
import site.achun.support.api.enums.Deleted;
import site.achun.updown.client.module.file.GetSubDirsReq;
import site.achun.updown.client.module.file.LocalFileInfoClient;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDirScanService {

    private final StorageQueryService storageQueryService;
    private final FileDirService fileDirService;
    private final LocalFileInfoClient localFileInfoClient;

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
                    .path("")
                    .name(storage.getName())
                    .build();
            fileDirService.save(dir);
            scan(storage,dir);
        }else{
            scan(storage,storageFileDir);
        }
    }

    public void scan(StorageResponse storage,FileDir dir){
        LoopGetDirs loop = new LoopGetDirs(storage,dir);
        loop.setConsumer(d->log.info("dir:{}",d));
        loop.setFunction(path->localFileInfoClient.getSubDirectoryList(GetSubDirsReq.builder().path(path).build()).getData());
        loop.startLoop();
    }

    public static class LoopGetDirs{
        private StorageResponse storage;
        private FileDir root;
        private Consumer<String> dealDirconsumer;
        private Function<String,List<String>> getDirsFunction;
        public LoopGetDirs(StorageResponse storage,FileDir root){
            this.storage = storage;
            this.root = root;
        }

        public void setConsumer(Consumer<String> consumer) {
            this.dealDirconsumer = consumer;
        }

        public void setFunction(Function<String, List<String>> function) {
            this.getDirsFunction = function;
        }

        public void startLoop(){
            Path path = Path.of(storage.getPath(), root.getPath());
            loop(path.toAbsolutePath().toString());

        }

        private void loop(String path){
            List<String> dirs = getDirsFunction.apply(path);
            if(CollUtil.isNotEmpty(dirs)){
                dirs.stream().forEach(dealDirconsumer);
                dirs.stream().forEach(this::loop);
            }
        }
    }
}
