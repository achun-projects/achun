package site.achun.audio.site.achun.audio.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.achun.audio.app.AudioApplication;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.audio.app.service.PicturesUpdateService;

@SpringBootTest(classes = AudioApplication.class)
public class PicturesUpdateServiceTest {

    @Autowired
    private PicturesUpdateService picturesUpdateService;
    @Autowired
    private MediaFileQueryClient fileQueryClient;

    @Test
    void contextLoads() {
        String fileCode = "71521494610100518";
        MediaFileResponse resp = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build()).getData();
        picturesUpdateService.update(resp);
    }

}
