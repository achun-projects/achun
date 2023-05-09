package site.achun.file.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FileCreateController {

    @PostMapping("/file/update/create-file")
    public String createFile(){
        return "createFile";
    }

}
