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
import site.achun.updown.client.module.transfer.FileTransferClient;
import site.achun.updown.client.module.transfer.request.RequestTransferFile;

@Slf4j
@Tag(name = "文件处理", description = "文件处理")
@RestController
@RequiredArgsConstructor
public class FileTransferController implements FileTransferClient {


    private final FileQueryClient fileQueryClient;

    @Override
    public void transferFile(RequestTransferFile request) {
        var queryByFileCode = QueryByFileCode.builder()
                .fileCode(request.getFileCode())
                .build();
        var fileResponse = fileQueryClient.queryFile(queryByFileCode).getData();

        fileResponse
        FileTransferInfo transfer = new FileTransferInfo();
        transfer.setFile(new java.io.File(fileResponse.getFilePath()));
    }
}
