package site.achun.file.app.controller.file;

import cn.virde.common.pojo.rsp.Rsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.api.modules.file.FileHashCheck;

@Tag(name = "文件检查")
@Order(21)
@Slf4j
@RestController
@RequiredArgsConstructor
public class FileCheckController {


    @Operation(summary = "验证文件是否存在-使用文件hash")
    @GetMapping("/file/file-check/check-hash")
    public Rsp<String> checkHashCode(@RequestBody FileHashCheck request){

        return null;
    }
}
