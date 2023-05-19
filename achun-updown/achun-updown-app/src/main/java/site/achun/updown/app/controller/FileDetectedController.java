package site.achun.updown.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.updown.app.service.module.detected.LocalFileDetectedByDatabaseService;
import site.achun.updown.app.service.module.detected.LocalFileDetectedService;
import site.achun.updown.client.module.detected.request.LocalDetectedStart;

@RestController
@RequiredArgsConstructor
public class FileDetectedController {

    private final LocalFileDetectedService localFileDetectedService;

    private final LocalFileDetectedByDatabaseService localFileDetectedByDatabaseService;

    @PostMapping("/updown/detected/local-detected-start")
    public Rsp<Object> localDetectedStart(@RequestBody LocalDetectedStart request){
        localFileDetectedService.detected(request);
        return Rsp.success(null);
    }


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
