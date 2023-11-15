package site.achun.updown.app.controller.file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.support.api.response.Rsp;
import site.achun.updown.client.module.file.GetSubDirsReq;
import site.achun.updown.client.module.file.LocalFileInfoClient;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Tag(name = "本地文件信息")
@RestController
@RequiredArgsConstructor
public class LocalFileInfoController implements LocalFileInfoClient {
    @Override
    public Rsp<List<String>> getSubDirectoryList(GetSubDirsReq req) {
        File file = Path.of(req.getPath()).toFile();
        if(!file.exists()){
            return Rsp.error("file not exist");
        }
        File[] listFiles = file.listFiles();
        List<String> response = new ArrayList<>();
        for (File f : listFiles) {
            if(f.isDirectory() && !f.getName().startsWith("_")){
                response.add(f.getAbsolutePath());
            }
        }
        return Rsp.success(response);
    }
}
