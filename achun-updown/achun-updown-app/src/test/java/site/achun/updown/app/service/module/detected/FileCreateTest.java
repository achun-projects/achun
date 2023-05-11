package site.achun.updown.app.service.module.detected;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.achun.file.client.module.FileCreateV4Client;
import site.achun.file.client.module.FileQueryV4Client;
import site.achun.file.client.module.response.FileResponse;
import site.achun.support.api.response.Rsp;

@SpringBootTest
public class FileCreateTest {

    @Autowired
    private FileCreateV4Client fileCreateV4Client;

    @Autowired
    private FileQueryV4Client fileQueryV4Client;
    @Test
    public void create(){
        Rsp<FileResponse> rsp = fileQueryV4Client.fileInfo("86621494610100673");
        System.out.println(rsp.getData().getUrl());
    }
}
