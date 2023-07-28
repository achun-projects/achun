package site.achun.file.controller.file;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileAuthClient;
import site.achun.file.client.module.file.request.AuthFileToken;
import site.achun.file.util.FileAuthUtil;
import site.achun.support.api.response.Rsp;


@Slf4j
@Tag(name = "文件查询")
@RequiredArgsConstructor
@RestController
public class FileAuthController implements FileAuthClient {

    @Operation(summary = "校验文件访问token")
    @GetMapping("/file/auth/auth-file-header-token")
    public ResponseEntity<Void> authFileToken(@RequestHeader("origin_uri") String uri,@RequestHeader("token") String token) {
        // 开始验证
        boolean canAccess = false;
        try{
            canAccess = FileAuthUtil.checkToken(token, uri);
        }catch (RuntimeException ex){
            log.info("校验不通过,message:{}",ex.getMessage());
        }catch (Exception ex){
            log.info("校验异常",ex);
            ex.printStackTrace();
        }
        if(canAccess){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }else{
            log.info("request:{},token:{},checkResult:{}",uri,token,canAccess);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @Override
    public Rsp<String> authFileToken(AuthFileToken req) {
        return Rsp.error("暂未实现");
    }
}
