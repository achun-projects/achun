package site.achun.file.client.module;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.achun.file.client.module.response.FileResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "FileQueryV4Client",url="file.apiv4.achun.site")
public interface FileQueryV4Client {

    @GetMapping("/file/info")
    Rsp<FileResponse> fileInfo(@RequestParam("code") String code);
}
