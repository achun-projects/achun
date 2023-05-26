package site.achun.updown.app.service.module.detected;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.achun.file.client.enums.Type;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
import site.achun.updown.app.service.module.transfer.FileTransferService;
import site.achun.updown.client.module.detected.request.RequestLoopAndInitFiles;

import java.io.File;
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
    @Async
    public void detected(RequestLoopAndInitFiles request) {
        // 获取到所有包含文件的目录
        LoopGetHaveFilePaths loopGet = new LoopGetHaveFilePaths();
        Path path = Path.of(request.getLocalPath());
        List<Path> haveFilePaths = loopGet.apply(path);

        log.info("detected dirs count:{}", haveFilePaths.size());
        for (Path haveFilePath : haveFilePaths) {
            String unitCode = MD5.create().digestHex(request.getStorageCode()+"::"+haveFilePath.toAbsolutePath().toString());
            dealOneUnitFiles(request.getThirdId(),request.getStorageCode(),unitCode,haveFilePath);
        }
    }

    private void dealOneUnitFiles(String thirdCode,String storageCode,String unitCode,Path path){
        List<Path> subFilePathList = null;
        try {
            subFilePathList = Files.list(path)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("dealOneUnitFiles error,unitCodes:{},path:{}",unitCode,path,e);
        }

        for (Path subFilePath : subFilePathList) {
            File subFile = subFilePath.toFile();
            log.info("UnitFiles,[{}]({})",unitCode,subFile.getAbsolutePath());
            String suffix = FileUtil.getSuffix(subFile);
            InitFileInfo init = InitFileInfo.builder()
                    .thirdCode(thirdCode)
                    .absolutePath(subFile.getAbsolutePath())
                    .fileName(subFile.getName())
                    .size(subFile.length() / 1024)
                    .md5(MD5.create().digestHex(subFile))
                    .unitCode(unitCode)
                    .storageCode(storageCode)
                    .type(Type.parse(suffix).getCode())
                    .suffix(suffix)
                    .build();
            FileTransferInfo transfer = requestInitFileInfo(init);
            if(transfer == null){
                fileTransferService.transfer(transfer);
            }
        }
    }

    private FileTransferInfo requestInitFileInfo(InitFileInfo initFileInfo){
        InitFileInfoResponse response = null;
        try {
            Rsp<InitFileInfoResponse> rsp = fileUpdateClient.initFileInfo(initFileInfo);
            response = rsp.getData();
        }catch (Exception ex){
            log.error("requestInitFileInfo error,initFileInfo:{}",initFileInfo,ex);
            return null;
        }
        FileTransferInfo transfer = new FileTransferInfo();
        transfer.setFileCode(response.getFileCode());
        transfer.setInStoragePath(response.getInStoragePath());
        transfer.setStorage(response.getStorage());
        transfer.setFile(Path.of(response.getStorage().getPath(),response.getInStoragePath()).toFile());
        return transfer;
    }

    /**
     * 递归获取所有包含文件的目录
     */
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
                    // 如果路径下有文件，则加入数组
                    haveFilePaths.add(path);
                }
                // 递归
                Files.list(path).filter(Files::isDirectory).forEach(this::loopDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
