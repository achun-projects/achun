package site.achun.file.service.file;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.constant.FileRC;
import site.achun.file.client.enums.Hidden;
import site.achun.file.client.module.file.request.CreateFileInfo;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.domain.FileUnit;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.mapper.FileInfoMapper;
import site.achun.file.generator.mapper.FileUnitMapper;
import site.achun.file.generator.service.FileInfoService;
import site.achun.file.generator.service.FileUnitService;
import site.achun.file.generator.service.StorageService;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.response.Rsp;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileCreateService {

    private final FileInfoService fileInfoService;
    private final FileUnitService fileUnitService;
    private final StorageService storageService;
    private final FileConvert fileConvert;

    public InitFileInfoResponse initFileInfoV2(InitFileInfo initFileInfo) {
        Storage storage = storageService.getByCode(initFileInfo.getStorageCode());
        String inStoragePath = initFileInfo.getAbsolutePath().replace(storage.getPath(), "");
        FileInfo existFileInfo = fileInfoService.lambdaQuery()
                .eq(FileInfo::getStorageCode, initFileInfo.getStorageCode())
                .eq(FileInfo::getInStoragePath, inStoragePath)
                .last("limit 1")
                .one();
        if(existFileInfo != null){
            return fileConvert.toInitFileInfoResponse(existFileInfo,initFileInfo,true);
        }else{
            String code = MD5.create().digestHex16(initFileInfo.getAbsolutePath());
            String fileCode = storage.getStorageCode().substring(0,4) + initFileInfo.getDirCode().substring(16,20) + code.substring(0,24);
            FileInfo fileInfo = toFileInfo(initFileInfo);
            fileInfo.setFileCode(fileCode);
            fileInfo.setInStoragePath(inStoragePath);
            fileInfo.setAtime(LocalDateTime.now());
            fileInfo.setCtime(LocalDateTime.now());
            fileInfo.setUtime(LocalDateTime.now());
            fileInfo.setLineTime(LocalDateTime.now());
            fileInfo.setDeleted(Deleted.NO.getStatus());
            fileInfo.setHidden(Hidden.NO.getStatus());
            if(StrUtil.isEmpty(fileInfo.getThirdId())){
                fileInfo.setThirdId("-1");
            }
            int line = fileInfoService.replaceInto(fileInfo);
            // 保存分组
            FileUnit fileUnit = new FileUnit();
            fileUnit.setUnitCode(initFileInfo.getUnitCode());
            fileUnit.setUnitName(initFileInfo.getUnitName());
            fileUnit.setCtime(LocalDateTime.now());
            fileUnit.setUtime(LocalDateTime.now());
            fileUnit.setDeleted(Deleted.NO.getStatus());
            fileUnitService.replaceInto(fileUnit);
            if(line>0){
                log.info("New:{},line:{}",fileInfo,line);
                return fileConvert.toInitFileInfoResponse(fileInfo,initFileInfo,false);
            }else{
                log.info("save file error,{},line:{}",fileInfo,line);
                return null;
            }
        }
    }

//    public InitFileInfoResponse initFileInfo(InitFileInfo initFileInfo) {
//        FileInfo fileInfo = toFileInfo(initFileInfo);
//        Storage storage = storageService.getStorage(initFileInfo.getStorageCode());
//        fileInfo.setInStoragePath(initFileInfo.getAbsolutePath().replace(storage.getPath(),""));
//        fileInfo.setAtime(LocalDateTime.now());
//        fileInfo.setCtime(LocalDateTime.now());
//        fileInfo.setUtime(LocalDateTime.now());
//        fileInfo.setLineTime(LocalDateTime.now());
//        fileInfo.setDeleted(Deleted.NO.getStatus());
//        fileInfo.setHidden(Hidden.NO.getStatus());
//        if(StrUtil.isEmpty(fileInfo.getThirdId())){
//            fileInfo.setThirdId("-1");
//        }
//        int line = fileInfoService.replaceInto(fileInfo);
//
//        // 保存分组
//        FileUnit fileUnit = new FileUnit();
//        fileUnit.setUnitCode(initFileInfo.getUnitCode());
//        fileUnit.setUnitName(initFileInfo.getUnitName());
//        fileUnit.setCtime(LocalDateTime.now());
//        fileUnit.setUtime(LocalDateTime.now());
//        fileUnit.setDeleted(Deleted.NO.getStatus());
//        fileUnitService.replaceInto(fileUnit);
//
//        if(line>0){
//            log.info("New:{},line:{}",fileInfo,line);
//            return fileConvert.toInitFileInfoResponse(fileInfo,initFileInfo,null);
//        }else{
//            log.info("save file error,{},line:{}",fileInfo,line);
//            return null;
//        }
//    }

    private FileInfo toFileInfo(InitFileInfo initFileInfo){
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileCode(initFileInfo.getFileCode());
        fileInfo.setUnitCode(initFileInfo.getUnitCode());
        fileInfo.setThirdId(initFileInfo.getThirdId());
        fileInfo.setStorageCode(initFileInfo.getStorageCode());
        fileInfo.setFileName(initFileInfo.getFileName());
        fileInfo.setMd5(initFileInfo.getMd5());
        fileInfo.setSize(initFileInfo.getSize());
        fileInfo.setSuffix(initFileInfo.getSuffix());
        fileInfo.setType(initFileInfo.getType());
        return fileInfo;
    }

    public Rsp<FileLocalInfoResponse> createFileInfo(CreateFileInfo createFileInfo) {
        if(createFileInfo==null){
            return Rsp.error(FileRC.PARAMS_IS_NULL);
        }
        FileInfo fileInfo = BeanUtil.toBean(createFileInfo, FileInfo.class);
        fileInfo.setStorageCode(createFileInfo.getStorageCode());
        fileInfo.setInStoragePath(createFileInfo.getInStoragePath());
        fileInfo.setUnitCode(createFileInfo.getUnitCode());
        fileInfo.setAtime(LocalDateTime.now());
        fileInfo.setCtime(LocalDateTime.now());
        fileInfo.setUtime(LocalDateTime.now());
        fileInfo.setLineTime(LocalDateTime.now());
        fileInfo.setDeleted(Deleted.NO.getStatus());
        fileInfo.setHidden(Hidden.NO.getStatus());
        if(StrUtil.isEmpty(fileInfo.getThirdId())){
            fileInfo.setThirdId("-1");
        }
        int line = fileInfoService.replaceInto(fileInfo);
        if(line>0){
            log.info("New:{},line:{}",fileInfo,line);
        }else{
            log.info("save file error,{},line:{}",fileInfo,line);
        }
        return Rsp.success(fileConvert.toFileLocalInfoResponse(fileInfo));
    }
}
