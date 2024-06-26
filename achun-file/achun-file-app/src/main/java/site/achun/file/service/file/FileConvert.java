package site.achun.file.service.file;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import site.achun.file.beans.file.FileOrigin;
import site.achun.file.client.enums.Env;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.file.client.module.storage.beans.StorageExtra;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.StorageService;
import site.achun.file.service.storage.StorageConvert;
import site.achun.file.util.FileAuthUtil;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileConvert {

    private final StorageService storageService;
    private final StorageConvert storageConvert;

    public FileLocalInfoResponse toFileLocalInfoResponse(FileInfo file){
        if(file == null){
            return null;
        }
        return FileLocalInfoResponse.builder()
                .cover(file.getCover())
                .storageCode(file.getStorageCode())
                .storage(storageConvert.toResponse(storageService.getStorage(file.getStorageCode())))
                .inStoragePath(file.getInStoragePath())
                .thirdId(file.getThirdId())
                .fileName(file.getFileName())
                .unitCode(file.getUnitCode())
                .utime(file.getUtime())
                .fileCode(file.getFileCode())
                .size(file.getSize())
                .type(file.getType())
                .build();
    }
    public List<FileLocalInfoResponse> toFileLocalInfoResponse(List<FileInfo> fileInfoList){
        if(CollUtil.isEmpty(fileInfoList)) return null;
        return fileInfoList.stream()
                .map(file -> toFileLocalInfoResponse(file))
                .collect(Collectors.toList());
    }
    public List<FileInfoResponse> toFileResponse(List<FileInfo> fileInfoList){
        if(CollUtil.isEmpty(fileInfoList)) return null;
        return fileInfoList.stream()
                .map(this::toFileResponse)
                .collect(Collectors.toList());
    }
    public List<MediaFileResponse> toMediaFileResponse(List<FileInfo> fileInfoList){
        if(CollUtil.isEmpty(fileInfoList)) return null;
        return fileInfoList.stream()
                .map(this::toMediaFileResponse)
                .collect(Collectors.toList());
    }

    public InitFileInfoResponse toInitFileInfoResponse(FileInfo fileInfo, InitFileInfo init,Boolean exist){
        InitFileInfoResponse response = BeanUtil.toBean(init, InitFileInfoResponse.class);
        response.setFileCode(fileInfo.getFileCode());
        response.setInStoragePath(fileInfo.getInStoragePath());
        response.setCover(fileInfo.getCover());
        response.setUtime(fileInfo.getUtime());
        response.setStorage(storageConvert.toResponse(storageService.getStorage(fileInfo.getStorageCode())));
        response.setExist(exist);
        return response;
    }

    public MediaFileResponse toMediaFileResponse(FileInfo fileInfo){
        if(fileInfo == null) return null;
        // 基本信息
        MediaFileResponse rsp = MediaFileResponse.builder()
                .fileCode(fileInfo.getFileCode())
                .fileName(fileInfo.getFileName())
                .suffix(fileInfo.getSuffix())
                .thirdId(fileInfo.getThirdId())
                .unitCode(fileInfo.getUnitCode())
                .width(fileInfo.getWidth())
                .height(fileInfo.getHeight())
                .wh(fileInfo.getWh())
                .duration(fileInfo.getDuration())
                .type(fileInfo.getType())
                .size(fileInfo.getSize())
                .utime(fileInfo.getUtime())
                .build();

        // 请求链接
        FileOrigin origin = new FileOrigin();
        if(fileInfo.getOrigin()!=null && StrUtil.isNotEmpty(fileInfo.getOrigin().toString())){
            origin = JSON.parseObject(fileInfo.getOrigin().toString(),FileOrigin.class);
        }
        // 获取网图和缩略图链接，（因为2021-09之前的图没有这俩url，所以需要判断）
        String mediumUrl = StrUtil.isEmpty(origin.getMedium_url()) ? fileInfo.getInStoragePath() : origin.getMedium_url();
        String smallUrl = StrUtil.isEmpty(origin.getSmall_url()) ? fileInfo.getInStoragePath() : origin.getSmall_url();
        // 设置网图，缩略图，原文件的访问链接
        Storage storage = storageService.getStorage(fileInfo.getStorageCode());
        rsp.setBucketCode(storage.getBucketCode());
        String domain = getStorageAccessPrefix(storage);
        rsp.setMediumUrl(makeAuthUrl(domain+mediumUrl));
        rsp.setUrl(makeAuthUrl(domain+fileInfo.getInStoragePath()));
        // 设置封面
        if(StrUtil.isNotEmpty(fileInfo.getCover())){
            rsp.setCover(makeAuthUrl(domain + fileInfo.getCover()));
        }else{
            rsp.setCover(makeAuthUrl(domain+smallUrl));
        }
        return rsp;
    }
    public FileInfoResponse toFileResponse(FileInfo fileInfo){
        if(fileInfo == null) return null;
        // 基本信息
        FileInfoResponse rsp = FileInfoResponse.builder()
                .fileCode(fileInfo.getFileCode())
                .fileName(fileInfo.getFileName())
                .suffix(fileInfo.getSuffix())
                .thirdId(fileInfo.getThirdId())
                .unitCode(fileInfo.getUnitCode())
                .dirCode(fileInfo.getDirCode())
                .type(fileInfo.getType())
                .size(fileInfo.getSize())
                .utime(fileInfo.getUtime())
                .build();

        // 请求链接
        FileOrigin origin = new FileOrigin();
        if(fileInfo.getOrigin()!=null && StrUtil.isNotEmpty(fileInfo.getOrigin().toString())){
            origin = JSON.parseObject(fileInfo.getOrigin().toString(),FileOrigin.class);
        }
        // 获取网图和缩略图链接，（因为2021-09之前的图没有这俩url，所以需要判断）
        String mediumUrl = StrUtil.isEmpty(origin.getMedium_url()) ? fileInfo.getInStoragePath() : origin.getMedium_url();
        String smallUrl = StrUtil.isEmpty(origin.getSmall_url()) ? fileInfo.getInStoragePath() : origin.getSmall_url();
        // 设置网图，缩略图，原文件的访问链接
        Storage storage = storageService.getStorage(fileInfo.getStorageCode());
        rsp.setBucketCode(storage.getBucketCode());
        String domain = getStorageAccessPrefix(storage);
        rsp.setMediumUrl(makeAuthUrl(domain+mediumUrl));
        rsp.setUrl(makeAuthUrl(domain+fileInfo.getInStoragePath()));
        // 设置封面
        if(StrUtil.isNotEmpty(fileInfo.getCover())){
            rsp.setCover(makeAuthUrl(domain + fileInfo.getCover()));
        }else{
            rsp.setCover(makeAuthUrl(domain+smallUrl));
        }

        // 设置详细信息
        rsp.setHeight(fileInfo.getHeight());
        rsp.setWidth(fileInfo.getWidth());
        rsp.setWh(fileInfo.getWh());
        rsp.setDuration(fileInfo.getDuration());
        return rsp;
    }

    private String makeAuthUrl(String url){
        String token = FileAuthUtil.generatorToken(url);
        return url+"?token="+token;
    }

    private String getStorageAccessPrefix(Storage storage){
        // 从header获取X-token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attr = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attr.getRequest();
        String envCode = request.getHeader("env");//网关传过来的 token

        Env env = null;
        if (!StringUtils.hasText(envCode) || (env = Env.parse(envCode))==null) {
            return storage.getAccessPrefix();
        }
        Env finalEnv = env;
        StorageExtra.Env rsp = storage.getExtra().getEnvs().stream().
                filter(e -> e.getCode().equals(finalEnv.getCode()))
                .findFirst().get();
        log.info("Current envCode:{},rsp:{}",envCode,rsp);
        return rsp.getHost() + rsp.getPath();
    }

}
