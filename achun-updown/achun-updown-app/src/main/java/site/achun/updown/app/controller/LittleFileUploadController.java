package site.achun.updown.app.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.request.QueryByMD5;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.util.FileCodeGenerator;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.upload.Upload;
import site.achun.updown.app.service.module.upload.UploadService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Tag(name = "小文件上传", description = "小文件上传")
@RestController
@RequiredArgsConstructor
public class LittleFileUploadController {


    @Value("${upload.temp.dir}")
    private String uploadTempDir;

    @Value("${upload.bucket}")
    private String bucketCode;

    private final FileQueryClient fileQueryClient;
    private final UploadService uploadService;




    @Operation(summary = "小文件上传")
    @PostMapping("/updown/upload/little-file-upload")
    public Rsp<FileInfoResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam(value = "unit",required = false,defaultValue = "") String unitCode){
        if(StrUtil.isEmpty(unitCode)){
            unitCode = UUID.randomUUID().toString().replace("-","");
        }
        String fileCode = FileCodeGenerator.make().toString();
        String suffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        String targetFilePath = uploadTempDir + unitCode + "-" + fileCode + "." + suffix;
        File targetFile = new File(targetFilePath);
        if(targetFile.exists()){
            log.error("Temp file is exist:{}",targetFilePath);
            return Rsp.error("创建临时文件失败,targetFilePath:"+targetFilePath);
        }else{
            try {
                targetFile.createNewFile();
            } catch (IOException ex) {
                log.error("创建临时文件失败,targetFilePath:{}",targetFilePath,ex);
                ex.printStackTrace();
                return Rsp.error("创建临时文件失败,targetFilePath:"+targetFilePath);
            }
        }
        Map<String,Object> originMap = new HashMap<>();
        originMap.put("originalFilename",multipartFile.getOriginalFilename());
        originMap.put("size",multipartFile.getSize());
        try(
                InputStream inputStream = multipartFile.getInputStream();
                FileOutputStream downloadFile = new FileOutputStream(targetFile)
        ){
            int index;
            byte[] bytes = new byte[1024];
            while ((index = inputStream.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
        } catch (IOException ex) {
            log.error("上传文件失败",ex);
            ex.printStackTrace();
        }
        String md5 = MD5.create().digestHex(targetFile);
        Rsp<List<FileInfoResponse>> existFileInfoResponse = fileQueryClient.queryFileList(QueryByMD5.builder().md5(md5).build());
        if(existFileInfoResponse.hasData()){
            log.info("file exist in table by md5:{},fileInfo:{}",md5, JSON.toJSONString(existFileInfoResponse.getData()));
            return Rsp.success(existFileInfoResponse.getData().get(0),"改文件在数据中md5已存在");
        }
        String key = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"))
                +"/"+unitCode+"/"+targetFile.getName();
        Upload upload = Upload.builder()
                .path(targetFilePath)
                .storageCode(bucketCode)
                .unitCode(unitCode)
                .key(key)
                .origin(originMap)
                .override(false)
                .build();
        Rsp<FileLocalInfoResponse> fileLocalInfo = uploadService.uploadLocalFile(upload, fileCode);
        return Rsp.success(fileQueryClient.queryFile(QueryByFileCode.builder()
                .fileCode(fileLocalInfo.getData().getFileCode()).build()).getData());
    }
}
