package site.achun.updown.app.controller.upload;


import cn.virde.common.pojo.rsp.Rsp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Tag(name = "单文件上传")
@Slf4j
@RestController
@RequiredArgsConstructor
public class SingleFileUploadController {

    @PostMapping("/updown/single-file-upload")
    public Rsp<String> upload(RedirectAttributes redirectAttributes){
        log.info("调用成功:"+redirectAttributes.getAttribute("msg"));
        return Rsp.success("哈哈哈啊哈");
    }
}
