package site.achun.updown.app.controller.upload;


import cn.virde.common.pojo.rsp.Rsp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "单文件上传")
@Slf4j
@RestController
@RequiredArgsConstructor
public class SingleFileUploadController {

    @GetMapping("/updown/single-file-upload")
    public Rsp<String> upload(){
        log.info("调用成功");
        return Rsp.success("哈哈哈啊哈");
    }
}
