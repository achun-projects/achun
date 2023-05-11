package site.achun.file.client.module;

import site.achun.file.client.module.request.CreateFileInfo;
import site.achun.file.client.module.response.FileResponse;
import site.achun.support.api.response.Rsp;

public interface FileCreateClient {

    /**
     * 新增一条文件记录
     * @param createFileInfo
     * @return
     */
    Rsp<FileResponse> createFileInfo(CreateFileInfo createFileInfo);
}
