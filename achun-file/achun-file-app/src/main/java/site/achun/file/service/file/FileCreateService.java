package site.achun.file.service.file;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.enums.Hidden;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.file.client.util.FileCodeGenerator;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.domain.FileUnit;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.mapper.FileInfoMapper;
import site.achun.file.generator.mapper.FileUnitMapper;
import site.achun.file.generator.service.FileUnitService;
import site.achun.file.generator.service.StorageService;
import site.achun.support.api.enums.Deleted;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileCreateService {


    private final FileInfoMapper fileInfoMapper;
    private final FileUnitMapper fileUnitMapper;
    private final StorageService storageService;
    private final FileConvert fileConvert;

    public InitFileInfoResponse initFileInfo(InitFileInfo initFileInfo) {
        FileInfo fileInfo = toFileInfo(initFileInfo);
        fileInfo.setFileCode(FileCodeGenerator.make().toString());
        Storage storage = storageService.getStorage(initFileInfo.getStorageCode());
        fileInfo.setInStoragePath(initFileInfo.getAbsolutePath().replace(storage.getPath(),""));
        fileInfo.setAtime(LocalDateTime.now());
        fileInfo.setCtime(LocalDateTime.now());
        fileInfo.setUtime(LocalDateTime.now());
        fileInfo.setLineTime(LocalDateTime.now());
        fileInfo.setDeleted(Deleted.NO.getStatus());
        fileInfo.setHidden(Hidden.NO.getStatus());
        if(StrUtil.isEmpty(fileInfo.getThirdId())){
            fileInfo.setThirdId("-1");
        }
        int line = fileInfoMapper.replaceInto(fileInfo);

        // 保存分组
        FileUnit fileUnit = new FileUnit();
        fileUnit.setUnitCode(initFileInfo.getUnitCode());
        fileUnit.setUnitName(initFileInfo.getUnitName());
        fileUnit.setCtime(LocalDateTime.now());
        fileUnit.setUtime(LocalDateTime.now());
        fileUnit.setDeleted(Deleted.NO.getStatus());
        fileUnitMapper.replaceInto(fileUnit);

        if(line>0){
            log.info("New:{},line:{}",fileInfo,line);
            return fileConvert.toInitFileInfoResponse(fileInfo,initFileInfo);
        }else{
            log.info("save file error,{},line:{}",fileInfo,line);
            return null;
        }
    }

    private FileInfo toFileInfo(InitFileInfo initFileInfo){
        FileInfo fileInfo = new FileInfo();
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
}
