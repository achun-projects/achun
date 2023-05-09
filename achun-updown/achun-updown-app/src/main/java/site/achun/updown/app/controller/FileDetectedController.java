package site.achun.updown.app.controller;

import cn.virde.common.pojo.rsp.Rsp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.updown.app.service.module.detected.LocalFileDetectedService;
import site.achun.updown.client.module.detected.request.LocalDetectedStart;

@RestController
@RequiredArgsConstructor
public class FileDetectedController {

    private final LocalFileDetectedService localFileDetectedService;

    @PostMapping("/updown/detected/local-detected-start")
    public Rsp<Object> localDetectedStart(@RequestBody LocalDetectedStart request){
        return null;
    }

}
