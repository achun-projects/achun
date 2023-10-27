package site.achun.updown.app.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByMD5;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.module.storage.StorageQueryClient;
import site.achun.file.client.module.storage.request.ByStorageCode;
import site.achun.file.client.module.storage.response.StorageResponse;
import site.achun.file.client.util.FileCodeGenerator;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.controller.enums.UploadStatus;
import site.achun.updown.app.controller.request.BigFileUploadCheckRequest;
import site.achun.updown.app.controller.request.BigFileUploadMergeRequest;
import site.achun.updown.app.controller.response.UploadHashCheckResponse;
import site.achun.updown.app.service.module.upload.UploadService;
import site.achun.updown.app.util.big.file.upload.FileUploadUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Slf4j
@Tag(name = "大文件上传", description = "大文件上传")
@RestController
@RequiredArgsConstructor
public class BigFileUploadController {

    private final static String DEFAULT_STORAGE_CODE = "226206d8c9be4bd8a33791e03df1ef99";
    private final StorageQueryClient storageQueryClient;
    private final UploadService uploadService;
    private final FileQueryClient fileQueryClient;

    @PostMapping("/updown/big-file-upload/hash-check")
    public Rsp<UploadHashCheckResponse> hashCheck(@RequestBody BigFileUploadCheckRequest request) {
        log.info("request:{}",request);
        Rsp<List<FileInfoResponse>> fileResponse = fileQueryClient.queryFileList(QueryByMD5.builder().md5(request.getHash()).build());
        if(fileResponse.success() && fileResponse.hasData()){
            return Rsp.success(UploadHashCheckResponse.builder()
                    .type(UploadStatus.EXIST.getType())
                    .fileCode(fileResponse.getData().get(0).getFileCode())
                    .build());
        }
        return Rsp.success(UploadHashCheckResponse.builder()
                .type(UploadStatus.NEVER_UPLOAD.getType())
                .build());
    }

    @PostMapping("/updown/big-file-upload/chunks_upload")
    public Rsp<Boolean> chunksUpload(@RequestParam MultipartFile file,
                                     @RequestParam Integer index,
                                     @RequestParam Integer total,
                                     @RequestParam Integer chunkSize,
                                     @RequestParam Long size,
                                     @RequestParam String name,
                                     @RequestParam String hash) throws IOException {
        log.info("index:{},hash:{},total:{},chunkSize:{},size:{},name:{}",
                index,hash,total,chunkSize,size,name);
        // 生成上传目录
        StorageResponse storage = storageQueryClient.queryStorage(ByStorageCode.builder().code(DEFAULT_STORAGE_CODE).build()).tryGetData();
        Path dirFilePath = Path.of(storage.getPath(),hash.substring(0,2),hash);
        File dirFile = dirFilePath.toFile();
        if(!dirFile.exists()) dirFile.mkdirs();

        String filename = name.split("\\.")[0];
        String fileSuffix = name.split("\\.")[1];
        String fullFileName = String.join("-", new String[] {filename, hash, index + "", }) + "." + fileSuffix;
        File uploadFile = dirFilePath.resolve(fullFileName).toFile();
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(uploadFile));
        FileUploadUtil.put(hash, index, total, fullFileName, dirFile.getAbsolutePath());
        return Rsp.success(true);
    }

    @PostMapping("/updown/big-file-upload/chunks_merge")
    public Rsp<FileLocalInfoResponse> chunksMerge(@RequestBody BigFileUploadMergeRequest request) throws Exception {
        // 检测所有文件是否上传完成
        boolean isAllChunksUploaded = FileUploadUtil.isAllChunksUploaded(request.getHash());
        try {
            if (isAllChunksUploaded) {
                String dirFilePath = FileUploadUtil.get(request.getHash())[0].getFilePath();
                log.info("正在合并文件...");
                FileUploadUtil.mergeChunks(request.getName(), request.getHash());
                // 添加文件信息
                StorageResponse storage = storageQueryClient.queryStorage(ByStorageCode.builder().code(DEFAULT_STORAGE_CODE).build()).tryGetData();
                FileLocalInfoResponse fileResponse = uploadService.uploadBigFile(UploadService.UploadBigFileParams.builder()
                        .storageResponse(storage)
                        .file(Path.of(dirFilePath, request.getName()).toFile())
                        .fileCode(FileCodeGenerator.make().toString())
                        .hash(request.getHash())
                        .inStoragePath(Path.of(request.getHash().substring(0, 2), request.getHash(), request.getName()).toString())
                        .unitCode(StrUtil.isEmpty(request.getGroupCode()) ? UUID.randomUUID().toString().replace("-", "") : request.getGroupCode())
                        .build()).tryGetData();
                return Rsp.success(fileResponse);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        return Rsp.success(null);
    }

}
