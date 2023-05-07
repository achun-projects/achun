package site.achun.gallery.app.controller.file;

import cn.virde.common.pojo.rsp.Rsp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.controller.file.request.BatchRemoveFile;
import site.achun.gallery.app.controller.file.request.RemoveFile;
import site.achun.gallery.app.dubbo.consumer.FileRemoveConsumer;

@Tag(name = "文件更新")
@Slf4j
@RestController
@RequiredArgsConstructor
public class FileUpdateController {

    private final FileRemoveConsumer fileRemoveConsumer;

    @Operation(summary = "批量删除文件")
    @PostMapping("/gallery/file-update/batch-remove-files")
    public Rsp<Boolean> removeFiles(@RequestBody BatchRemoveFile request){
        fileRemoveConsumer.removeFiles(request.getFileCodes());
        return Rsp.success(true);
    }

    @Operation(summary = "删除文件")
    @PostMapping("/gallery/file-update/batch-remove-file")
    public Rsp<Boolean> removeFile(@RequestBody RemoveFile request){
        fileRemoveConsumer.removeFile(request.getFileCode());
        return Rsp.success(true);
    }
}
