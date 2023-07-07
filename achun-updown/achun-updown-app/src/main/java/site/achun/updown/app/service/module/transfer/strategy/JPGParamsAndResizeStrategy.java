package site.achun.updown.app.service.module.transfer.strategy;

import cn.hutool.core.img.ImgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
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

    private final FileUpdateClient fileUpdateClient;

    @Override
    public boolean match(FileTransferInfo transfer) {
        List<String> suffixList = Arrays.asList("jpg", "jpeg");
        return suffixList.stream().anyMatch(suffix -> transfer.getFile().getName().toLowerCase().endsWith(suffix));
    }

    @Override
    public TransferType transferType() {
        return TransferType.JPG_PARAMS_AND_RESIZE;
    }

    @Override
    public void handler(FileTransferInfo transfer) {
        BufferedImage img = ImgUtil.read(transfer.getFile());
        UpdateFileRequest updateFile = new UpdateFileRequest();
        updateFile.setFileCode(transfer.getFileCode());
        updateFile.setWidth(img.getWidth());
        updateFile.setHeight(img.getHeight());
        updateFile.setWh((int) (((float)img.getWidth()/(float)img.getHeight())*100f));
        updateFile.setDuration(0);

        // 生成缩略图
        String smallPicUrl = transfer.getInStoragePath();
        if(transfer.getFile().length() > 500*1024){
            String storagePath = transfer.getStorage().getPath();
            smallPicUrl = "_small/"+transfer.getInStoragePath();
            File smallPicFile = new File(storagePath + smallPicUrl);
            if(!smallPicFile.exists()){
                ImageResizeUtil.scalr(transfer.getFile(),smallPicFile,560);
            }else{
                log.info("smallPicFile exists:{}",smallPicFile.getAbsolutePath());
            }
        }

        // 生成中级预览图
        String mediumPicUrl = transfer.getInStoragePath();
        if(transfer.getFile().length() > 1000 * 1024){
            String storagePath = transfer.getStorage().getPath();
            mediumPicUrl = "_medium/"+transfer.getInStoragePath();
            File mediumPicFile = new File(storagePath + mediumPicUrl);
            if(!mediumPicFile.exists()){
                ImageResizeUtil.scalr(transfer.getFile(),mediumPicFile,1080);
            }else{
                log.info("mediumPicFile exists:{}",mediumPicFile.getAbsolutePath());
            }
        }else{
            log.info("文件大小符合预期，不需要中级缩略图,fileLength:{},File:{}",transfer.getFile().length(),transfer.getFile().getName());
        }
        updateFile.setMediumUrl(mediumPicUrl);
        updateFile.setSmallUrl(smallPicUrl);
        fileUpdateClient.updateFileInfo(updateFile);
    }
}
