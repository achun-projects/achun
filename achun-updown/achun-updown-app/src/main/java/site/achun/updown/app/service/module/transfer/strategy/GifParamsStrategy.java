package site.achun.updown.app.service.module.transfer.strategy;

import cn.hutool.core.img.ImgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileUpdateV4Client;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.updown.app.service.module.transfer.FileTransferStrategy;
import site.achun.updown.app.service.module.transfer.TransferType;
import site.achun.updown.app.util.GifImageUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2022/2/13 15:11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class GifParamsStrategy implements FileTransferStrategy {

    private final FileUpdateV4Client fileUpdateV4Client;

    @Override
    public boolean match(InitFileInfoResponse file) {
        return file.getFileName().toLowerCase().endsWith("gif");
    }

    @Override
    public TransferType transferType() {
        return TransferType.GIF_PARAMS;
    }

    @Override
    public void handler(InitFileInfoResponse fileInfo) {
        File file = new File(fileInfo.getAbsolutePath());
        BufferedImage img = ImgUtil.read(file);
        // 获取图片长度宽度
        UpdateFileRequest updateFile = new UpdateFileRequest();
        updateFile.setFileCode(fileInfo.getFileCode());
        updateFile.setWidth(img.getWidth());
        updateFile.setHeight(img.getHeight());
        updateFile.setWh((int) (((float)img.getWidth()/(float)img.getHeight())*100f));
        try {
            // 获取gif帧数
            updateFile.setDuration(GifImageUtil.getFrameCount(file));
        } catch (IOException ex) {
            log.error("获取gif图片帧数时发生异常,fileCode:{},filePath:{}",fileInfo.getFileCode(),fileInfo.getInStoragePath(),ex);
            ex.printStackTrace();
        }
        fileUpdateV4Client.updateByFileCode(updateFile);
    }
}