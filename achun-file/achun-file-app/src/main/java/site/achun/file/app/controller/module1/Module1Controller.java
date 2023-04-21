package site.achun.file.app.controller.module1;

import cn.virde.common.pojo.rsp.Rsp;
import site.achun.file.app.service.module1.business.MyBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "模块1")
@Slf4j
@RestController
@RequiredArgsConstructor
public class Module1Controller {

    private final MyBusinessService myBusinessService;


    @Operation(summary = "测试")
    @GetMapping("hello")
    public Rsp<Boolean> hello(){
        return Rsp.success(true);
    }
}
