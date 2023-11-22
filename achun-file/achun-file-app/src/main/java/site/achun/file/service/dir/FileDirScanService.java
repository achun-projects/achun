package site.achun.file.service.dir;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.alist.response.FSListResponse;
import site.achun.file.client.alist.response.FSResponse;
import site.achun.file.client.module.dir.beans.DirInfo;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.dir.request.RequestScanDir;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.generator.domain.FileDir;
import site.achun.file.generator.service.FileDirService;
import site.achun.file.service.alist.AListService;
import site.achun.file.service.storage.StorageQueryService;
import site.achun.support.api.enums.Deleted;
import site.achun.updown.client.module.detected.UpdownDetectedClient;
import site.achun.updown.client.module.file.GetSubDirsReq;
import site.achun.updown.client.module.file.LocalFileInfoClient;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDirScanService {

    private final StorageQueryService storageQueryService;
    private final FileDirService fileDirService;
    private final LocalFileInfoClient localFileInfoClient;
    private final UpdownDetectedClient updownDetectedClient;
    private final AListService aListService;

    public void scan(RequestScanDir req){
        FileDir fileDir = fileDirService.queryByCode(req.getDirCode());
        StorageResponse storage = null;
        if(fileDir == null){
            storage = storageQueryService.queryStorage(req.getDirCode());
            if(storage == null){
                log.info("dirCode:{},not found fileDir and storage",req.getDirCode());
                throw new RuntimeException("dir not found");
            }
            fileDir = FileDir.builder()
                    .dirCode(storage.getStorageCode())
                    .storageCode(storage.getStorageCode())
                    .parentDirCode(null)
                    .ctime(LocalDateTime.now())
                    .deleted(Deleted.NO.getStatus())
                    .path("")
                    .name(storage.getName())
                    .build();
            fileDirService.save(fileDir);
            log.info("dirCode:{}，not found fileDir but storage",req.getDirCode());
        }else{
            storage = storageQueryService.queryStorage(fileDir.getStorageCode());
            log.info("dirCode:{} found FileDir",req.getDirCode());
        }
        if(storage.getBucketCode().equals("10115")){
            scanAList(storage,fileDir,req.getOnlyDir());
        }else{
            scan(storage,fileDir,req.getOnlyDir());
        }
    }

    public void scanAList(StorageResponse storage,FileDir dir,boolean onlyDir){
        new Thread(()->{
            LoopGetDirs loop = new LoopGetDirs(storage,dir);
            loop.setDealDirFunction(this::saveFileDir);
            loop.setFunction(path->{
                FSListResponse resp = aListService.list(path);
                if(resp!=null && CollUtil.isNotEmpty(resp.getContent())){
                    return resp.getContent().stream()
                            .filter(fs -> fs.getIs_dir())
                            .map(fs->Path.of(path,fs.getName()).toString())
                            .collect(Collectors.toList());
                }else{
                    return new ArrayList<>();
                }
            });
            if(!onlyDir){
                // 保存路径中的文件
                Consumer<List<FileDir>> scanDirFilesConsumer = (dirInfoList)->{
                    dirInfoList.stream().forEach(d->{
                        log.info("get files from dirCode:{}",d.getDirCode());
                        String dirPath = Path.of(storage.getPath(), d.getPath()).toString();
                        FSListResponse resp = aListService.list(dirPath);
                        List<FSResponse> list = new ArrayList<>();
                        if(resp!=null && CollUtil.isNotEmpty(resp.getContent())) {
                            list = resp.getContent().stream()
                                    .filter(fs -> !fs.getIs_dir())
                                    .collect(Collectors.toList());
                        }
                        save(list,d);
                    });
                };
                loop.setScanDirFilesConsumer(scanDirFilesConsumer);
            }
            loop.startLoop();
        }).start();
    }

    private void save(List<FSResponse> list,FileDir dir){
        for (FSResponse fsResponse : list) {
            String path = Path.of(dir.getPath(), fsResponse.getName()).toString();
            log.info("save file:{},",path);
        }
    }

    public void scan(StorageResponse storage,FileDir dir,boolean onlyDir){
        new Thread(()->{
            LoopGetDirs loop = new LoopGetDirs(storage,dir);
            loop.setDealDirFunction(this::saveFileDir);
            loop.setFunction(path->localFileInfoClient.getSubDirectoryList(GetSubDirsReq.builder().path(path).build()).getData());
            if(!onlyDir){
                // 保存路径中的文件
                Consumer<List<FileDir>> scanDirFilesConsumer = (dirInfoList)->{
                    dirInfoList.stream().forEach(d->{
                        log.info("get files from dirCode:{}",d.getDirCode());
                        ByDirCode req = ByDirCode.builder().dirCode(d.getDirCode()).build();
                        updownDetectedClient.scanFilesByDirCode(req);
                    });
                };
                loop.setScanDirFilesConsumer(scanDirFilesConsumer);
            }
            loop.startLoop();
        }).start();
    }

    private List<FileDir> saveFileDir(List<DirInfo> dirInfo){
        if(CollUtil.isEmpty(dirInfo)){
            return new ArrayList<>();
        }

        List<FileDir> fileDirList = dirInfo.stream()
                .map(dir -> FileDir.builder()
                        .name(dir.getName())
                        .parentDirCode(dir.getParentCode())
                        .dirCode(dir.getDirCode())
                        .storageCode(dir.getStorageCode())
                        .path(dir.getPath())
                        .ctime(LocalDateTime.now())
                        .deleted(Deleted.NO.getStatus())
                        .build())
                .collect(Collectors.toList());

        String parentDirCode = dirInfo.get(0).getParentCode();
        List<FileDir> existDirList = fileDirService.queryByParentCode(parentDirCode);
        if(CollUtil.isEmpty(existDirList)){
            fileDirService.saveBatch(fileDirList);
        }else{
            Map<String, FileDir> dirMap = fileDirList.stream()
                    .collect(Collectors.toMap(FileDir::getDirCode, v -> v, (v1, v2) -> v1));
            Map<String, FileDir> existDirMap = existDirList.stream()
                    .collect(Collectors.toMap(FileDir::getDirCode, v -> v, (v1, v2) -> v1));
            List<FileDir> needDeleted = existDirList.stream()
                    .filter(d -> !dirMap.containsKey(d.getDirCode()))
                    .collect(Collectors.toList());
            List<FileDir> needInsert = fileDirList.stream()
                    .filter(d -> !existDirMap.containsKey(d.getDirCode()))
                    .collect(Collectors.toList());
            if(CollUtil.isNotEmpty(needDeleted)){
                fileDirService.lambdaUpdate()
                        .set(FileDir::getDeleted,Deleted.YES.getStatus())
                        .in(FileDir::getDirCode,needDeleted.stream().map(FileDir::getDirCode).collect(Collectors.toList()))
                        .update();
            }
            if(CollUtil.isNotEmpty(needInsert)){
                fileDirService.saveBatch(needInsert);
            }
        }
        return fileDirList;
    }

    public static class LoopGetDirs{
        private StorageResponse storage;
        private FileDir root;
        private Function<List<DirInfo>,List<FileDir>> dealDirFunction;
        private Consumer<List<FileDir>> scanDirFilesConsumer;
        private Function<String,List<String>> getDirsFunction;
        public LoopGetDirs(StorageResponse storage,FileDir root){
            this.storage = storage;
            this.root = root;
        }

        public void setDealDirFunction(Function<List<DirInfo>, List<FileDir>> dealDirFunction) {
            this.dealDirFunction = dealDirFunction;
        }

        public void setScanDirFilesConsumer(Consumer<List<FileDir>> scanDirFilesConsumer) {
            this.scanDirFilesConsumer = scanDirFilesConsumer;
        }

        public void setFunction(Function<String, List<String>> function) {
            this.getDirsFunction = function;
        }

        public void startLoop(){
            loop(root);
        }

        private void loop(FileDir fileDir){
            Path path = Path.of(storage.getPath(), fileDir.getPath());
            List<String> dirs = getDirsFunction.apply(path.toString());
            if(CollUtil.isNotEmpty(dirs)){
                List<DirInfo> dirInfoList = dirs.stream()
                        .map(dir -> {
                            Path dirPath = Path.of(dir);
                            String dirPathString = dirPath.toString().replace(storage.getPath(), "");
                            String halfPathMd5 = MD5.create().digestHex(dirPathString).substring(0, 16);
                            String halfDirCode = fileDir.getDirCode().length() > 16 ?
                                    fileDir.getDirCode().substring(fileDir.getDirCode().length() - 16)
                                    : fileDir.getDirCode();
                            String dirCode = halfDirCode + halfPathMd5;
                            return DirInfo.builder()
                                    .dirCode(dirCode)
                                    .parentCode(fileDir.getDirCode())
                                    .path(dirPathString)
                                    .name(dirPath.getFileName().toString())
                                    .storageCode(storage.getStorageCode())
                                    .build();
                        })
                        .collect(Collectors.toList());
                List<FileDir> dirInfos = dealDirFunction.apply(dirInfoList);
                if(CollUtil.isNotEmpty(dirInfos) && scanDirFilesConsumer!=null){
                    scanDirFilesConsumer.accept(dirInfos);
                }
                dirInfos.stream().forEach(this::loop);
            }
        }
    }
}
