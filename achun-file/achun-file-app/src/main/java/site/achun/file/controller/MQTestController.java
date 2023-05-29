package site.achun.file.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.component.FileUpdateNoticeSender;

@Slf4j
@Tag(name = "MQ测试")
@RestController
@RequiredArgsConstructor
public class MQTestController {

    private final FileUpdateNoticeSender fileUpdateNoticeSender;

    @GetMapping("/file/mq-test/send")
    @Operation(summary = "MQ发送测试")
    public void test(@Param("fileCode") String fileCode){
        fileUpdateNoticeSender.sendMessage(fileCode);
    }


}
