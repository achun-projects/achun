package site.achun.file.client.module.file;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.file.request.CreateFileInfo;
import site.achun.file.client.module.file.request.UpdateFileRequest;
import site.achun.support.api.response.Rsp;

@FeignClient(name="virde-file-web",url = "http://file.apiv4.achun.site", contextId = "FileCreateV4Client")
public interface FileUpdateV4Client {

    @Operation(summary = "新增一条文件记录")
    @PostMapping("/file/update/create-file")
    Rsp<FileResponse> createFile(@RequestBody CreateFileInfo create);

    @Operation(summary = "修改文件信息")
    @PostMapping("/file/update/update_by_file_code")
    Rsp<FileResponse> updateByFileCode(@RequestBody UpdateFileRequest request);

}
