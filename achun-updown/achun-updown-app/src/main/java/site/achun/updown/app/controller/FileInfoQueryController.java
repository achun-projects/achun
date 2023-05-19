package site.achun.updown.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.support.api.response.Rsp;

@RestController
@RequiredArgsConstructor
public class FileInfoQueryController {


    @GetMapping("/file/info")
    Rsp<FileInfoResponse> fileInfo(@RequestParam("code") String code){
        return null;
    }
}
