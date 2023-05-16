package site.achun.file.client.module.file;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.file.request.CreateFileInfo;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "virde-file-web", contextId = "FileCreateV4Client")
public interface FileCreateV4Client {

    /**
     * 新增一条文件记录
     * @param createFileInfo
     * @return
     */
    @PostMapping("/file/update/create-file")
    Rsp<FileResponse> createFile(@RequestBody CreateFileInfo create);
}
