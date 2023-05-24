package site.achun.file.controller.file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "文件更新")
@RequiredArgsConstructor
@RestController
public class FileUpdateController implements FileUpdateClient {

    @Override
    public Rsp<InitFileInfoResponse> initFileInfo(InitFileInfo init) {
        log.info("initFileInfo: {}", init);
        return null;
    }

    @Override
    public Rsp<FileLocalInfoResponse> updateFileInfo(UpdateFileRequest request) {
        return null;
    }
}
