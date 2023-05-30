package site.achun.updown.app.service.module.transfer.strategy;

import cn.hutool.core.img.ImgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileResponse;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.FileUpdateV4Client;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
import site.achun.updown.app.service.module.transfer.FileTransferStrategy;
import site.achun.updown.app.service.module.transfer.TransferType;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PNGTransferStrategy implements FileTransferStrategy {

    private final FileUpdateClient fileUpdateClient;

    @Override
    public boolean match(FileTransferInfo transfer) {
        List<String> suffixList = Arrays.asList("png");
        return suffixList.stream().anyMatch(suffix -> transfer.getFile().getName().toLowerCase().endsWith(suffix));
    }

    @Override
    public TransferType transferType() {
        return TransferType.PNG_TRANSFER;
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
        // 生成中级预览图
        String mediumPicUrl = transfer.getInStoragePath();
        updateFile.setMediumUrl(mediumPicUrl);
        updateFile.setSmallUrl(smallPicUrl);
        fileUpdateClient.updateFileInfo(updateFile);
    }
}
