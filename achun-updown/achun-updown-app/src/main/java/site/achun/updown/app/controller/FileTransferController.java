package site.achun.updown.app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.transfer.FileTransferInfo;
import site.achun.updown.app.service.module.transfer.FileTransferService;
import site.achun.updown.client.module.transfer.FileTransferClient;
import site.achun.updown.client.module.transfer.request.RequestTransferFile;

import java.nio.file.Path;

@Slf4j
@Tag(name = "文件处理", description = "文件处理")
@RestController
@RequiredArgsConstructor
public class FileTransferController implements FileTransferClient {


    private final FileQueryClient fileQueryClient;
    private final FileTransferService fileTransferService;

    @Override
    public void transferFile(RequestTransferFile request) {
        var queryByFileCode = QueryByFileCode.builder()
                .fileCode(request.getFileCode())
                .build();
        var fileResponse = fileQueryClient.queryFileLocalInfo(queryByFileCode).getData();
        var localFilePath = Path.of(fileResponse.getStorage().getPath(), fileResponse.getInStoragePath());
        FileTransferInfo transfer = new FileTransferInfo();
        transfer.setFile(localFilePath.toFile());
        transfer.setFileCode(fileResponse.getFileCode());
        transfer.setStorage(fileResponse.getStorage());
        transfer.setInStoragePath(fileResponse.getInStoragePath());
        fileTransferService.transfer(transfer);
    }
}
