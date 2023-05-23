package site.achun.updown.app.service.module.transfer.strategy;

import cn.hutool.core.img.ImgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileUpdateV4Client;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.updown.app.service.module.transfer.FileTransferStrategy;
import site.achun.updown.app.service.module.transfer.TransferType;
import site.achun.updown.app.util.ImageResizeUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * 压缩图片大小
 *
 * @Author: Heiffeng
 * @Date: 2021/12/1 11:55
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JPGParamsAndResizeStrategy implements FileTransferStrategy {


    private final FileUpdateV4Client fileUpdateV4Client;


    @Override
    public boolean match(InitFileInfoResponse file) {
        List<String> suffixList = Arrays.asList("jpg", "png", "jpeg");
        return suffixList.stream().anyMatch(suffix -> file.getFileName().toLowerCase().endsWith(suffix));
    }

    @Override
    public TransferType transferType() {
        return TransferType.JPG_PARAMS_AND_RESIZE;
    }

    @Override
    public void handler(InitFileInfoResponse fileInfo) {
        File file = new File(fileInfo.getAbsolutePath());
        BufferedImage img = ImgUtil.read(file);
        UpdateFileRequest updateFile = new UpdateFileRequest();
        updateFile.setFileCode(fileInfo.getFileCode());
        updateFile.setWidth(img.getWidth());
        updateFile.setHeight(img.getHeight());
        updateFile.setWh((int) (((float)img.getWidth()/(float)img.getHeight())*100f));
        updateFile.setDuration(0);

        // 生成缩略图
        String smallPicUrl = fileInfo.getInStoragePath();
        if(file.length() > 500*1024){
            String bucketPath = fileInfo.getStorage().getPath();
            smallPicUrl = "_small/"+fileInfo.getInStoragePath();
            File smallPicFile = new File(bucketPath + smallPicUrl);
            if(!smallPicFile.exists()){
                ImageResizeUtil.scalr(file,new File(bucketPath+smallPicUrl),560);
            }else{
                log.info("smallPicFile exists:{}",smallPicFile.getAbsolutePath());
            }
        }

        // 生成中级预览图
        String mediumPicUrl = fileInfo.getInStoragePath();
        if(file.length() > 1000 * 1024){
            String bucketPath = fileInfo.getStorage().getPath();
            mediumPicUrl = "_medium/"+fileInfo.getInStoragePath();
            File mediumPicFile = new File(bucketPath + mediumPicUrl);
            if(!mediumPicFile.exists()){
                ImageResizeUtil.scalr(file,new File(bucketPath + mediumPicUrl),1080);
            }else{
                log.info("mediumPicFile exists:{}",mediumPicFile.getAbsolutePath());
            }
        }else{
            log.info("文件大小符合预期，不需要中级缩略图,fileLength:{},File:{}",file.length(),file.getName());
        }
        updateFile.setMediumUrl(mediumPicUrl);
        updateFile.setSmallUrl(smallPicUrl);
        fileUpdateV4Client.updateByFileCode(updateFile);
    }
}
