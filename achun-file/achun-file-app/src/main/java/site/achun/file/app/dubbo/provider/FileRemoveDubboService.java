package site.achun.file.app.dubbo.provider;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import site.achun.file.api.modules.file.FileRemoveFacade;
import site.achun.file.api.modules.file.response.DeleteFileResponse;
import site.achun.file.app.service.file.FileUpdateService;
import site.achun.support.api.response.Rsp;

import java.util.Collection;
import java.util.List;

@DubboService
@RequiredArgsConstructor
public class FileRemoveDubboService implements FileRemoveFacade {

    private final FileUpdateService fileUpdateService;

    @Override
    public Rsp<DeleteFileResponse> removeFile(String fileCode) {
        return fileUpdateService.removeFile(fileCode);
    }

    @Override
    public Rsp<List<DeleteFileResponse>> removeFile(Collection<String> fileCodes) {
        return fileUpdateService.removeFile(fileCodes);
    }

    @Override
    public Rsp<DeleteFileResponse> removeFileByUnitCode(String unitCode) {
        return fileUpdateService.removeFileByUnitCode(unitCode);
    }

    @Override
    public Rsp<List<DeleteFileResponse>> removeFileByUnitCode(Collection<String> unitCodes) {
        return fileUpdateService.removeFileByUnitCode(unitCodes);
    }
}
