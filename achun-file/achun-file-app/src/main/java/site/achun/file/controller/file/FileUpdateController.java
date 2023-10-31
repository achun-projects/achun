package site.achun.file.controller.file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.request.CreateFileInfo;
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
    public Rsp<InitFileInfoResponse> initFileInfoV2(InitFileInfo init) {
        return Rsp.success(fileCreateService.initFileInfoV2(init));
    }

    @Override
    public Rsp<FileLocalInfoResponse> createFile(CreateFileInfo create) {
        Rsp<FileLocalInfoResponse> rsp = fileCreateService.createFileInfo(create);
        fileUpdateSender.whenFileUpdate(create.getFileCode());
        return rsp;
    }

    @Override
    public Rsp<FileLocalInfoResponse> updateFileInfo(UpdateFileRequest request) {
        fileUpdateService.updateByCode(request);
        fileUpdateSender.whenFileUpdate(request.getFileCode());
        return Rsp.success(null);
    }

    @Override
    public Rsp<Boolean> deleteFileInfo(DeleteFileRequest request) {
        boolean success = fileInfoService.lambdaUpdate()
                .eq(FileInfo::getFileCode, request.getFileCode())
                .set(FileInfo::getUtime, LocalDateTime.now())
                .set(FileInfo::getDeleted, Deleted.YES.getStatus())
                .update();
        fileUpdateSender.whenFileRemove(request.getFileCode());
        return Rsp.success(success);
    }

    @Override
    public Rsp<Boolean> realDeleteFileInfo(DeleteFileRequest request) {
        boolean result = fileInfoService.lambdaUpdate()
                .eq(FileInfo::getFileCode, request.getFileCode())
                .remove();
        log.info("物理删除文件：{},result:{}",request.getFileCode(),result);
        return Rsp.success(Boolean.TRUE);
    }

    @Override
    public Rsp<Boolean> deleteFileInfos(DeleteFileRequest request) {
        boolean success = fileInfoService.lambdaUpdate()
                .in(FileInfo::getFileCode, request.getFileCodes())
                .set(FileInfo::getUtime,LocalDateTime.now())
                .set(FileInfo::getDeleted, Deleted.YES.getStatus())
                .update();
        request.getFileCodes().stream().forEach(fileCode->fileUpdateSender.whenFileRemove(fileCode));
        return Rsp.success(success);
    }
}
