package site.achun.file.controller.file;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<Void> authFileToken(HttpServletRequest request) {
        String uri = request.getHeader("origin_uri");
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            log.info("token is null when request:{},",uri);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
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

    public static void main(String[] args) {
        System.out.println(FileAuthUtil.checkToken("c423c09817a41ccc346ce7450c7df39f512829cafc314d0d56d12cc78bd33eea","http://z-buckets.ddns.achun.site:9080/weibo_d1/homeline/6422205520/4925712945646493/0089Xb6hly1hfecx8a19rj30k70qytas.jpg"));
    }
}
