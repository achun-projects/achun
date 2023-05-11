package site.achun.file.client.module;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.request.CreateFileInfo;
import site.achun.file.client.module.response.FileResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "FileCreateV4Client", url="file.apiv4.achun.site")
public interface FileCreateV4Client {

    /**
     * 新增一条文件记录
     * @param createFileInfo
     * @return
     */
    @PostMapping("/file/update/create-file")
    Rsp<FileResponse> createFile(@RequestBody CreateFileInfo create);
}
