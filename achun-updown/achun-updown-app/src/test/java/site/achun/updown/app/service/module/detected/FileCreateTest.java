package site.achun.updown.app.service.module.detected;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.achun.file.client.module.file.FileQueryV4Client;
import site.achun.file.client.module.file.FileResponse;
import site.achun.file.client.module.file.FileUpdateV4Client;
import site.achun.support.api.response.Rsp;

@SpringBootTest
public class FileCreateTest {

    @Autowired
    private FileUpdateV4Client fileUpdateV4Client;

    @Autowired
    private FileQueryV4Client fileQueryV4Client;
    @Test
    public void create(){
        Rsp<FileResponse> rsp = fileQueryV4Client.fileInfo("86621494610100673");
        System.out.println(rsp.getData().getUrl());
    }
}
