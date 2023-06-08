package site.achun.file.controller.file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.generator.domain.FileInfo;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.DeleteFileRequest;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.file.generator.service.FileInfoService;
import site.achun.file.mq.sender.FileUpdateSender;
import site.achun.file.service.file.FileCreateService;
import site.achun.file.service.file.FileUpdateService;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.response.Rsp;

import java.time.LocalDateTime;

@Slf4j
@Tag(name = "文件更新")
@RequiredArgsConstructor
@RestController
public class FileUpdateController implements FileUpdateClient {

    private final FileCreateService fileCreateService;

    private final FileUpdateSender fileUpdateSender;
    private final FileUpdateService fileUpdateService;

    private final FileInfoService fileInfoService;

    @Override
    public Rsp<InitFileInfoResponse> initFileInfo(InitFileInfo init) {
        return Rsp.success(fileCreateService.initFileInfo(init));
    }

    @Override
    public Rsp<FileLocalInfoResponse> updateFileInfo(UpdateFileRequest request) {
        fileUpdateService.updateByCode(request);
        fileUpdateSender.sendMessage(request);
        return Rsp.success(null);
    }

    @Override
    public Rsp<Boolean> deleteFileInfo(DeleteFileRequest request) {
        boolean success = fileInfoService.lambdaUpdate()
                .eq(FileInfo::getFileCode, request.getFileCode())
                .set(FileInfo::getUtime, LocalDateTime.now())
                .set(FileInfo::getDeleted, Deleted.YES.getStatus())
                .update();
        return Rsp.success(success);
    }

    @Override
    public Rsp<Boolean> deleteFileInfos(DeleteFileRequest request) {
        boolean success = fileInfoService.lambdaUpdate()
                .in(FileInfo::getFileCode, request.getFileCodes())
                .set(FileInfo::getUtime,LocalDateTime.now())
                .set(FileInfo::getDeleted, Deleted.YES.getStatus())
                .update();
        return Rsp.success(success);
    }
}
