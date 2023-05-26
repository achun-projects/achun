package site.achun.updown.app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.support.api.response.Rsp;
@Slf4j
@Tag(name = "文件查询", description = "文件查询")
@RestController
@RequiredArgsConstructor
public class FileInfoQueryController {


    @GetMapping("/file/info")
    Rsp<FileInfoResponse> fileInfo(@RequestParam("code") String code){
        return null;
    }
}
