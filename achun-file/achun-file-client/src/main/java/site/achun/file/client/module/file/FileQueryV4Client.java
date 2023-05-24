package site.achun.file.client.module.file;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "virde-file-web", contextId = "FileQueryV4Client")
public interface FileQueryV4Client {

    @GetMapping("/file/info")
    Rsp<FileResponse> fileInfo(@RequestParam("code") String code);
}