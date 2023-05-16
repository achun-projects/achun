package site.achun.file.service.file;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.beans.file.FileOrigin;
import site.achun.file.client.enums.Type;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.detail.Image;
import site.achun.file.client.module.file.response.detail.Video;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.domain.Storage;
import site.achun.file.generator.service.StorageService;
import site.achun.file.util.FileAuthUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileConvert {

    private final StorageService storageService;

    public List<FileInfoResponse> toFileResponse(List<FileInfo> fileInfoList){
        if(CollUtil.isEmpty(fileInfoList)) return null;
        return fileInfoList.stream()
                .map(this::toFileResponse)
                .collect(Collectors.toList());
    }

    public FileInfoResponse toFileResponse(FileInfo fileInfo){
        if(fileInfo == null) return null;

        // 基本信息
        FileInfoResponse rsp = FileInfoResponse.builder()
                .fileCode(fileInfo.getFileCode())
                .fileName(fileInfo.getFileName())
                .thirdId(fileInfo.getThirdId())
                .unitCode(fileInfo.getUnitCode())
                .type(fileInfo.getType())
                .size(fileInfo.getSize())
                .utime(fileInfo.getUtime())
                .build();

        // 请求链接
        FileOrigin origin = JSON.parseObject(fileInfo.getOrigin().toString(),FileOrigin.class);
        // 获取网图和缩略图链接，（因为2021-09之前的图没有这俩url，所以需要判断）
        String mediumUrl = StrUtil.isEmpty(origin.getMedium_url()) ? fileInfo.getInStoragePath() : origin.getMedium_url();
        String smallUrl = StrUtil.isEmpty(origin.getSmall_url()) ? fileInfo.getInStoragePath() : origin.getSmall_url();
        // 设置网图，缩略图，原文件的访问链接
        Storage storage = storageService.getStorage(fileInfo.getStorageCode());
        String domain = getStorageAccessPrefix(storage);
        rsp.setMediumUrl(makeAuthUrl(domain+mediumUrl));
        rsp.setUrl(makeAuthUrl(domain+fileInfo.getInStoragePath()));
        // 设置封面
        if(StrUtil.isNotEmpty(fileInfo.getCover())){
            rsp.setCover(makeAuthUrl(domain + rsp.getCover()));
        }else{
            rsp.setCover(makeAuthUrl(domain+smallUrl));
        }

        // 设置详细信息
        Type fileType = Type.parse(fileInfo.getType()).get();
        switch (fileType){
            case IMAGE:
                Image image = new Image();
                image.setWh(fileInfo.getWh());
                image.setWidth(fileInfo.getWidth());
                image.setHeight(fileInfo.getHeight());
                rsp.setDetail(image);
                break;
            case VIDEO:
                Video video = new Video();
                video.setHeight(fileInfo.getHeight());
                video.setWidth(fileInfo.getWidth());
                video.setWh(fileInfo.getWh());
                video.setDuration(fileInfo.getDuration());
                rsp.setDetail(video);
                break;
            case AUDIO:
            case OTHER:
            default:
        }
        return rsp;
    }

    private String makeAuthUrl(String url){
        String token = FileAuthUtil.generatorToken(url);
        return url+"?token="+token;
    }

//
//
    private String getStorageAccessPrefix(Storage storage){
//        String envCode = RpcContext.getServerAttachment().getAttachment(Env.KEY);
//        Env env = null;
//        if(envCode==null || (env = Env.parse(envCode))==null){
//            StorageExtra.Env defaultEnv = storage.getExtra().getDefaultEnv();
//            return defaultEnv.getHost() + defaultEnv.getPath();
//        }
//        Env finalEnv = env;
//        StorageExtra.Env rsp = storage.getExtra().getEnvs().stream().
//                filter(e -> e.getCode().equals(finalEnv.getCode()))
//                .findFirst().get();
//        log.info("Current envCode:{},rsp:{}",envCode,rsp);
//        return rsp.getHost() + rsp.getPath();
        return storage.getAccessPrefix();
    }

}
