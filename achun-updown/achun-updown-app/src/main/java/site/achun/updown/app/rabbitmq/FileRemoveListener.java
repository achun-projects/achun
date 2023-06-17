package site.achun.updown.app.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.MediaFileResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileRemoveListener {

    private final FileQueryClient fileQueryClient;

    @RabbitListener(queues = "file.remove.queue")
    public void whenFileUpdate(String fileCode){
        log.info("fileRemove :{}",fileCode);
//        FileInfoResponse file = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build()).getData();
//        if(!LISTEN_BUCKETS.contains(file.getBucketCode())){
//            log.info("fileNotIn,fileCode:{},bucketCode:{}",fileCode,file.getBucketCode());
//        }
//        picturesUpdateService.update(file);
    }

}
