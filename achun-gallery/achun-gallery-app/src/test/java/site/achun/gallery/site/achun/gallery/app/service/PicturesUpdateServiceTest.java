package site.achun.gallery.site.achun.gallery.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.GalleryApplication;
import site.achun.gallery.app.service.PicturesUpdateService;

@SpringBootTest(classes = GalleryApplication.class)
public class PicturesUpdateServiceTest {

    @Autowired
    private PicturesUpdateService picturesUpdateService;
    @Autowired
    private FileQueryClient fileQueryClient;

    @Test
    void contextLoads() {
        String fileCode = "71521494610100518";
        FileInfoResponse resp = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build()).getData();
        picturesUpdateService.update(resp);
    }

}
