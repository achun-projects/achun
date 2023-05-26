package site.achun.updown.app.service.module.transfer.strategy;

import com.alibaba.fastjson2.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileResponse;
import site.achun.file.client.module.file.FileUpdateV4Client;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
import site.achun.updown.app.service.module.transfer.FileTransferStrategy;
import site.achun.updown.app.service.module.transfer.TransferType;
import site.achun.updown.app.util.video.VideoUtil;

import java.io.File;
import java.io.IOException;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/3/19 21:07
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MP4ParamsStrategy implements FileTransferStrategy {

    private final FileUpdateV4Client fileUpdateV4Client;

    @Override
    public boolean match(FileTransferInfo transfer) {
        return transfer.getFile().getName().toLowerCase().endsWith("mp4");
    }

    @Override
    public TransferType transferType() {
        return TransferType.MP4_PARAMS;
    }

    @Override
    public void handler(FileTransferInfo transfer) {
        try {
            // 同目录下生成封面图，并获取视频信息
            VideoUtil.VideoInfo info = VideoUtil.parse(transfer.getFile());
            UpdateFileRequest update = new UpdateFileRequest();
            update.setFileCode(transfer.getFileCode());
            update.setDuration(info.getDuration().intValue());
            update.setHeight(info.getHeight());
            update.setWidth(info.getWidth());
            update.setWh((int) (((float)info.getWidth()/(float)info.getHeight())*100f));
            update.setCover(transfer.getInStoragePath().replace(".mp4",".cover.jpg").replace(".MP4",".cover.jpg"));
            Rsp<FileResponse> updateRsp = fileUpdateV4Client.updateByFileCode(update);
            log.info("Update Response:{}", JSONObject.toJSONString(updateRsp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
