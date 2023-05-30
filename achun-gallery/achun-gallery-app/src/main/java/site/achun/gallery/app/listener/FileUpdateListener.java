package site.achun.gallery.app.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUpdateListener {

    private final FileQueryClient fileQueryClient;

    @RabbitListener(queues = "file.update.queue")
    public void whenFileUpdate(String fileCode){
        log.info("fileUpdate :{}",fileCode);
        FileInfoResponse file = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build()).getData();
        log.info("file:{}",file.getFileName());
    }

}
