package site.achun.updown.app.service.module.detected;

import cn.hutool.core.io.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.transfer.FileTransferService;
import site.achun.updown.client.module.detected.request.RequestLoopAndInitFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalFileDetectedService {

    private final FileUpdateClient fileUpdateClient;
    private final FileTransferService fileTransferService;

    public Boolean existFile(String pathString){
        return FileUtil.exist(pathString);
    }
    public void detected(RequestLoopAndInitFiles request) {
        LoopGetHaveFilePaths loopGet = new LoopGetHaveFilePaths();
        Path path = Path.of(request.getLocalPath());
        List<Path> haveFilePaths = loopGet.apply(path);

        haveFilePaths.stream().forEach(p -> System.out.println(p.toAbsolutePath()));
        log.info("LocalFileDetectedService detected");

        List<InitFileInfoResponse> result = haveFilePaths.stream()
                .map(haveFilePath -> {
                    InitFileInfo init = InitFileInfo.builder()
                            .absolutePath(haveFilePath.toAbsolutePath().toString())
                            .fileName(haveFilePath.getFileName().toString())
                            .build();
                    Rsp<InitFileInfoResponse> rsp = fileUpdateClient.initFileInfo(init);
                    return rsp.getData();
                }).collect(Collectors.toList());
        log.info("LocalFileDetectedService detected result: {}", result);
        // 进一步处理File
        // 各种文件类型的handler
        result.stream().forEach(fileInfo -> fileTransferService.transfer(fileInfo));
    }

    private static class LoopGetHaveFilePaths implements Function<Path,List<Path>>{
        private List<Path> haveFilePaths = null;

        @Override
        public List<Path> apply(Path path) {
            haveFilePaths = new ArrayList<>();
            loopDirectory(path);
            return haveFilePaths;
        }

        private void loopDirectory(Path path){
            if(!Files.isDirectory(path)) return;
            try {
                if(Files.list(path).anyMatch(file->!Files.isDirectory(file))){
                    haveFilePaths.add(path);
                }
                Files.list(path).filter(Files::isDirectory).forEach(this::loopDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
