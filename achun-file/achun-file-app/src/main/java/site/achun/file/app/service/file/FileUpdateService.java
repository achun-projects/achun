package site.achun.file.app.service.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.api.modules.file.FileRemoveFacade;
import site.achun.file.api.modules.file.response.DeleteFileResponse;
import site.achun.support.api.response.Rsp;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUpdateService implements FileRemoveFacade {

    @Override
    public Rsp<DeleteFileResponse> removeFile(String fileCode) {
        return null;
    }

    @Override
    public Rsp<List<DeleteFileResponse>> removeFile(Collection<String> fileCodes) {
        return null;
    }

    @Override
    public Rsp<DeleteFileResponse> removeFileByUnitCode(String unitCode) {
        return null;
    }

    @Override
    public Rsp<List<DeleteFileResponse>> removeFileByUnitCode(Collection<String> unitCodes) {
        return null;
    }
}
