package site.achun.updown.app.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.detected.LocalFileDetectedByDatabaseService;
import site.achun.updown.app.service.module.detected.LocalFileDetectedService;
@Slf4j
@Tag(name = "文件探测", description = "文件探测")
@RestController
@RequiredArgsConstructor
public class FileDetectedController {

    private final LocalFileDetectedService localFileDetectedService;

    private final LocalFileDetectedByDatabaseService localFileDetectedByDatabaseService;


    @PostMapping("/updown/detected/detected-by-database")
    public Rsp<Object> detectedByDatabase(){
        localFileDetectedByDatabaseService.detectedByDatabase();
        return null;
    }

    @PostMapping("/updown/detected/detected-by-local-file")
    public Rsp<Object> detectedByLocalFile(){
        return null;
    }
}
