package site.achun.updown.app.controller;

import cn.virde.common.pojo.rsp.Rsp;
import cn.virde.file.api.module.file.response.FileResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.achun.updown.app.feign.FileInfoFacade;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class FileInfoQueryController {

    private final FileInfoFacade fileInfoFacade;

    @GetMapping("/file/info")
    Rsp<FileResponse> fileInfo(@RequestParam("code") @Valid @NotEmpty String code){
        return fileInfoFacade.fileInfo(code);
    }
}
