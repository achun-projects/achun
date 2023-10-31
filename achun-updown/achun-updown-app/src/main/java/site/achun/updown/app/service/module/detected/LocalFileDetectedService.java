package site.achun.updown.app.service.module.detected;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.achun.file.client.enums.Type;
import site.achun.file.client.module.dir.FileDirQueryClient;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.dir.response.DirResponse;
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
    private final FileDirQueryClient fileDirQueryClient;
    private final FileTransferService fileTransferService;

    public Boolean existFile(String pathString){
        return FileUtil.exist(pathString);
    }

    @Async
    public void scanFilesByDirCode(ByDirCode byDirCode){
        DirResponse dirResponse = fileDirQueryClient.queryBy(byDirCode).getData();
        Path dirPath = Path.of(dirResponse.getStorage().getPath(), dirResponse.getPath());
        List<Path> subFilePathList = null;
        try {
            subFilePathList = Files.list(dirPath)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("scanFilesByDirCode error,dirCode:{},path:{}",byDirCode.getDirCode(),dirPath.toString(),e);
        }

        for (Path subFilePath : subFilePathList) {
            File subFile = subFilePath.toFile();
            log.info("dirCode,[{}]({})",dirResponse.getDirCode(),subFile.getAbsolutePath());
            String suffix = FileUtil.getSuffix(subFile);
            String md5 = MD5.create().digestHex(subFile);
            InitFileInfo init = InitFileInfo.builder()
                    .fileCode(MD5.create().digestHex(md5+dirResponse.getDirCode()))
                    .thirdId(null)
                    .absolutePath(subFile.getAbsolutePath())
                    .fileName(subFile.getName())
                    .size(subFile.length() / 1024)
                    .md5(md5)
                    .unitCode(dirResponse.getDirCode())
                    .unitName(dirResponse.getName())
                    .storageCode(dirResponse.getStorageCode())
                    .type(Type.parse(suffix).getCode())
                    .suffix(suffix)
                    .build();
            FileTransferInfo transfer = requestInitFileInfo(init);
            if(transfer != null){
                fileTransferService.transfer(transfer);
            }
        }
    }

    @Async
    public void detected(RequestLoopAndInitFiles request) {
        // 获取到所有包含文件的目录
        LoopGetHaveFilePaths loopGet = new LoopGetHaveFilePaths();
        Path path = Path.of(request.getLocalPath());
        List<Path> haveFilePaths = loopGet.apply(path);
        log.info("detected dirs count:{}，path:{}", haveFilePaths.size(),request.getLocalPath());
        for (Path haveFilePath : haveFilePaths) {
            String unitCode = MD5.create().digestHex(request.getStorageCode()+"::"+haveFilePath.toAbsolutePath().toString());
            dealOneUnitFiles(request.getThirdId(),request.getStorageCode(),unitCode,haveFilePath);
        }
    }

    private void dealOneUnitFiles(String thirdId,String storageCode,String unitCode,Path path){
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
            String md5 = MD5.create().digestHex(subFile);
            InitFileInfo init = InitFileInfo.builder()
                    .thirdId(thirdId)
                    .absolutePath(subFile.getAbsolutePath())
                    .fileName(subFile.getName())
                    .size(subFile.length() / 1024)
                    .md5(md5)
                    .unitCode(unitCode)
                    .unitName(path.getFileName().toString())
                    .storageCode(storageCode)
                    .type(Type.parse(suffix).getCode())
                    .suffix(suffix)
                    .build();
            FileTransferInfo transfer = requestInitFileInfo(init);
            if(transfer != null){
                fileTransferService.transfer(transfer);
            }
        }
    }

    private FileTransferInfo requestInitFileInfo(InitFileInfo initFileInfo){
        InitFileInfoResponse response = null;
        try {
            Rsp<InitFileInfoResponse> rsp = fileUpdateClient.initFileInfoV2(initFileInfo);
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
            if(!Files.isDirectory(path)) {
                return;
            }
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
