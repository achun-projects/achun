package site.achun.file.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.FileResponse;
import site.achun.file.client.module.file.request.CreateFileInfo;

@Slf4j
@RestController
public class FileCreateController {

    @PostMapping("/file/update/create-file")
    public FileResponse createFile(@RequestBody CreateFileInfo create){
        return null;
    }

}
