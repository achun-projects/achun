package site.achun.file.controller.file;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileAuthClient;
import site.achun.file.client.module.file.request.AuthFileToken;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "文件查询")
@RequiredArgsConstructor
@RestController
public class FileAuthController implements FileAuthClient {

    @Operation(summary = "校验文件访问token")
    @GetMapping("/file/auth/auth-file-header-token")
    public Rsp<Void> authFileToken(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String token = request.getHeader("token");
        String tokenP = request.getParameter("token");
        log.info("uri:{},token:{},token:{}",uri,token,tokenP);
        return Rsp.success(null);
    }

    @Override
    public Rsp<String> authFileToken(AuthFileToken req) {
        return Rsp.error("暂未实现");
    }
}
