package site.achun.file.client.module.file;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.file.request.InitFileInfo;
import site.achun.file.client.module.file.response.InitFileInfoResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-file-app", contextId = "FileUpdateClient")
public interface FileUpdateClient {

    @PostMapping("/file/update/init-file-info")
    Rsp<InitFileInfoResponse> initFileInfo(@RequestBody InitFileInfo init);

}
