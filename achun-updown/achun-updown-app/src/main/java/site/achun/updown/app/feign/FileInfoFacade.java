package site.achun.updown.app.feign;

import cn.virde.common.pojo.rsp.Rsp;
import cn.virde.file.api.module.file.response.FileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@FeignClient("virde-file-web")
public interface FileInfoFacade {

    @GetMapping("/file/info")
    Rsp<FileResponse> fileInfo(@RequestParam("code") @Valid @NotEmpty String code);

}
