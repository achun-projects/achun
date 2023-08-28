package site.achun.updown.app.service.module.upload;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.enums.Type;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.CreateFileInfo;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.module.storage.StorageQueryClient;
import site.achun.file.client.module.storage.request.QueryStorageByCode;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
import site.achun.updown.app.service.module.transfer.FileTransferService;

import java.io.File;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/8 16:21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UploadService {

    private final StorageQueryClient storageQueryClient;

    private final FileUpdateClient fileUpdateClient;

    private final FileTransferService fileTransferService;


    public Rsp<FileLocalInfoResponse> uploadBigFile(UploadBigFileParams params){
        String suffix = FileUtil.getSuffix(params.getFile());
        CreateFileInfo createFileInfo = CreateFileInfo.builder()
                .fileCode(params.fileCode)
                .md5(params.getHash())
                .storageCode(params.getStorageResponse().getStorageCode())
                .fileName(params.getFile().getName())
                .inStoragePath(params.getInStoragePath())
                .unitCode(params.getUnitCode())
                .origin(JSON.toJSONString(params))
                .size((int) params.getFile().length()/1024)
                .suffix(suffix)
                .type(Type.parse(suffix).getCode())
                .build();
        FileLocalInfoResponse fileResponse = fileUpdateClient.createFile(createFileInfo).tryGetData();
        log.info("upload response:{}", fileResponse);
        // 执行后续的文件转换过程
        transferService(params.getFile(), fileResponse);
        return Rsp.success(fileResponse);
    }

    public Rsp<FileLocalInfoResponse> uploadLocalFile(Upload upload,String fileCode) {
        log.info("upload:{}", upload.toString());
        try {
            File localFile = new File(upload.getPath());
            if (!localFile.exists()) {
                return Rsp.error("NoSuchFile localFile:" + upload.getPath());
            }
            String md5 = MD5.create().digestHex(localFile);
            StorageResponse storage = storageQueryClient.queryStorage(QueryStorageByCode.builder().code(upload.getStorageCode()).build()).getData();
            File targetFile = new File(storage.getPath()+upload.getKey());

            Boolean override = upload.getOverride() == null ? false : upload.getOverride();
            try{
                FileUtil.mkParentDirs(targetFile);
                FileUtil.move(localFile, targetFile.getParentFile(), override);
            }catch (Exception ex){
                ex.printStackTrace();
                log.error("移动文件时发生异常，localFile:{},targetFile:{},override:{}"
                ,localFile.getAbsolutePath(),targetFile.getAbsolutePath(),override);
                throw ex;
            }
            CreateFileInfo createFileInfo = build(targetFile, upload, md5);
            createFileInfo.setFileCode(fileCode);
            FileLocalInfoResponse fileLocalInfoResponse = fileUpdateClient.createFile(createFileInfo).carefulGetData();
            log.info("upload response:{}", fileLocalInfoResponse);
            // 执行后续的文件转换过程
            transferService(targetFile, fileLocalInfoResponse);
            return Rsp.success(fileLocalInfoResponse);
        }catch (Exception ex){
            log.error("upload error",ex);
            ex.printStackTrace();
            return Rsp.error("上传发生异常,ExceptionMessage:"+ex.getMessage());
        }
    }

    private void transferService(File targetFile,FileLocalInfoResponse fileResponse){
        FileTransferInfo fileTransferInfo = new FileTransferInfo();
        fileTransferInfo.setFile(targetFile);
        fileTransferInfo.setFileCode(fileResponse.getFileCode());
        fileTransferInfo.setInStoragePath(fileResponse.getInStoragePath());
        fileTransferInfo.setStorage(fileResponse.getStorage());
        fileTransferService.transfer(fileTransferInfo);
    }

    public CreateFileInfo build(File file, Upload upload, String md5) {
        String suffix = FileUtil.getSuffix(file);
        return CreateFileInfo.builder()
                .md5(md5)
                .storageCode(upload.getStorageCode())
                .fileName(file.getName())
                .inStoragePath(upload.getKey())
                .unitCode(upload.getUnitCode())
                .thirdId(upload.getThirdId())
                .origin(JSON.toJSONString(upload))
                .size((int) file.length()/1024)
                .suffix(suffix)
                .type(Type.parse(suffix.toLowerCase()).getCode())
                .build();
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadBigFileParams{
        private File file;
        private String hash;
        private StorageResponse storageResponse;
        private String inStoragePath;
        private String unitCode;
        private String fileCode;
    }

}
