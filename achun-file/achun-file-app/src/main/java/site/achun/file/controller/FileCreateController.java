package site.achun.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileResponse;
import site.achun.file.client.module.file.request.CreateFileInfo;
import site.achun.file.service.file.FileCreateService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileCreateController {

    @PostMapping("/file/update/create-file")
    public FileResponse createFile(@RequestBody CreateFileInfo create){
        return null;
    }

}
