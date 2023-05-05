package site.achun.file.api.modules.file;

import site.achun.file.api.modules.file.response.DeleteFileResponse;
import site.achun.support.api.response.Rsp;

import java.util.Collection;
import java.util.List;

public interface FileRemoveFacade {

    Rsp<DeleteFileResponse> removeFile(String fileCode);

    Rsp<List<DeleteFileResponse>> removeFile(Collection<String> fileCodes);

    Rsp<DeleteFileResponse> removeFileByUnitCode(String unitCode);

    Rsp<List<DeleteFileResponse>> removeFileByUnitCode(Collection<String> unitCodes);

}
