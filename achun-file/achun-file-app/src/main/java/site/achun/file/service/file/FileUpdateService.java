package site.achun.file.service.file;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.generator.service.FileInfoService;
import site.achun.support.api.exception.RspException;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUpdateService {

    private final FileInfoService fileInfoService;

    public void updateByCode(UpdateFileRequest request) {
        FileInfo fileInfo = fileInfoService.getByCode(request.getFileCode());
        if(fileInfo == null){
            throw new RspException("文件不存在");
        }
        if(request.getDeleted()!=null){
            fileInfo.setDeleted(request.getDeleted());
        }
        if(request.getHidden() != null){
            fileInfo.setHidden(request.getHidden());
        }
        if(request.getDuration()!=null){
            fileInfo.setDuration(request.getDuration());
        }
        if(request.getWh()!=null){
            fileInfo.setWh(request.getWh());
        }
        if(request.getHeight()!=null){
            fileInfo.setHeight(request.getHeight());
        }
        if(request.getWidth()!=null){
            fileInfo.setWidth(request.getWidth());
        }
        if(StrUtil.isNotEmpty(request.getCover())){
            fileInfo.setCover(request.getCover());
        }
        if(request.getMediumUrl()!=null||request.getSmallUrl()!=null){
            JSONObject origin = null;
            if(fileInfo.getOrigin() != null && StrUtil.isNotEmpty(fileInfo.getOrigin().toString())){
                origin = JSON.parseObject(fileInfo.getOrigin().toString());
            }else{
                origin = new JSONObject();
            }
            if(request.getMediumUrl()!=null) origin.put("medium_url",request.getMediumUrl());
            if(request.getSmallUrl()!=null) origin.put("small_url",request.getSmallUrl());
            fileInfo.setOrigin(origin.toJSONString());
        }
        fileInfo.setAtime(LocalDateTime.now());
        fileInfo.setUtime(LocalDateTime.now());
        fileInfoService.updateById(fileInfo);
    }
}
