package site.achun.file.controller.file;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileAuthClient;
import site.achun.file.client.module.file.request.AuthFileToken;
import site.achun.support.api.response.Rsp;

import java.util.Enumeration;
import java.util.Iterator;

@Slf4j
@Tag(name = "文件查询")
@RequiredArgsConstructor
@RestController
public class FileAuthController implements FileAuthClient {

    @Operation(summary = "校验文件访问token")
    @GetMapping("/file/auth/auth-file-header-token")
    public Rsp<Void> authFileToken(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Iterator<String> headers = request.getHeaderNames().asIterator();
        while(headers.hasNext()){
            String header = headers.next();
            log.info("header:{},value:{}",header,request.getHeader(header));
        }
        String tokenP = request.getParameter("token");
        log.info("uri:{},token:{},token:{}",uri,"",tokenP);
        return Rsp.success(null);
    }

    @Override
    public Rsp<String> authFileToken(AuthFileToken req) {
        return Rsp.error("暂未实现");
    }
}
