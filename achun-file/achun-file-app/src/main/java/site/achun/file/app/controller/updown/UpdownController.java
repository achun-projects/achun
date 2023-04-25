package site.achun.file.app.controller.updown;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "Bucket更新")
@Order(31)
@Slf4j
@RestController
@RequiredArgsConstructor
public class UpdownController {

    @Operation(summary = "转发测试")
    @GetMapping("/file/updown/repath")
    public void repath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 重定向到url
        log.info("调用成功");
        response.sendRedirect("http://127.0.0.1:9501/updown/single-file-upload");
    }
}
