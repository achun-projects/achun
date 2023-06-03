package site.achun.gallery.app.service.pictures;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.enums.Type;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.detail.Detail;
import site.achun.file.client.module.file.response.detail.Image;
import site.achun.file.client.module.file.response.detail.Video;
import site.achun.gallery.client.module.pictures.response.PicturesBasicInfo;
import site.achun.support.api.response.Rsp;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PicturesQueryService {

    private final FileQueryClient fileQueryClient;

    public PicturesBasicInfo queryBasicInfo(String fileCode){
        Rsp<FileInfoResponse> rsp = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build());
        log.info("rsp:{}", JSON.toJSONString(rsp));
        if(!rsp.hasData()){
            return null;
        }
        FileInfoResponse<Detail> fileInfo = rsp.getData();
        PicturesBasicInfo pic = new PicturesBasicInfo();
        pic.setFileCode(fileCode);
        pic.setUrl(fileInfo.getUrl());
        pic.setCover(fileInfo.getCover());

        log.info("fileInfo:{}", JSON.toJSONString(fileInfo));
        Type fileType = Type.parse(fileInfo.getType()).get();
        switch (fileType){
            case IMAGE -> {
                Image image = BeanUtil.toBean(fileInfo.getDetail(), Image.class);
                pic.setWidth(image.getWidth());
                pic.setHeight(image.getHeight());
            }
            case VIDEO -> {
                Video video = BeanUtil.toBean(fileInfo.getDetail(), Video.class);
                pic.setWidth(video.getWidth());
                pic.setHeight(video.getHeight());
                pic.setDuration(video.getDuration());
            }
        }
        return pic;
    }
}
