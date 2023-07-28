package site.achun.file.client.module.file;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.configuration.FileFeignConfiguration;
import site.achun.file.client.module.file.request.AuthFileToken;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-file-app", contextId = "FileAuthClient",configuration = FileFeignConfiguration.class)
public interface FileAuthClient {

    @Operation(summary = "校验文件访问token")
    @PostMapping("/file/auth/auth-file-token")
    Rsp<String> authFileToken(@RequestBody AuthFileToken req);

}
