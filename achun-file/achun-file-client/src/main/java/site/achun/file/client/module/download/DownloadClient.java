package site.achun.file.client.module.download;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.download.request.Task;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-file-app", contextId = "DownloadClient")
public interface DownloadClient {

    @Operation(summary = "新增下载任务")
    @PostMapping("/file/download/new-task")
    Rsp<Boolean> newTask(@RequestBody Task task);
}
