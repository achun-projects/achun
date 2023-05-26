package site.achun.updown.app.service.module.transfer.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileUpdateV4Client;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
import site.achun.updown.app.service.module.transfer.FileTransferStrategy;
import site.achun.updown.app.service.module.transfer.TransferType;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/10/13 17:28
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VideoTranscodeStrategy implements FileTransferStrategy {

    private final FileUpdateV4Client fileUpdateV4Client;

    @Override
    public boolean match(FileTransferInfo transfer) {
        return transfer.getFile().getName().toLowerCase().endsWith("flv");
    }

    @Override
    public TransferType transferType() {
        return TransferType.VIDEO_TRANSCODE;
    }

    @Override
    public void handler(FileTransferInfo transfer) {

//        try {
//            File newFile = VideoTranscodeUtil.convertToMp4(file.getFile());
//            UpdateFileRequest update = new UpdateFileRequest();
//            update.setFileCode(file.getFileCode());
//            update.setFileName(newFile.getName());
//            // TODO 更新文件
//            Rsp<FileResponse> updateRsp = fileUpdateConsumer.updateByCode(file.getFileCode(), update);
//            log.info("Update Response:{}", JSONObject.toJSONString(updateRsp));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
