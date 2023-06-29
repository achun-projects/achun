package site.achun.video.app.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.app.service.PicturesUpdateService;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUpdateListener {

    private final MediaFileQueryClient fileQueryClient;

    private final PicturesUpdateService picturesUpdateService;

    private final static String LISTEN_BUCKETS = "10011,10012,10013,10014";
    @RabbitListener(queues = "file.update.queue")
    public void whenFileUpdate(String fileCode){
        log.info("fileUpdate :{}",fileCode);
        MediaFileResponse file = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build()).getData();
        if(!LISTEN_BUCKETS.contains(file.getBucketCode())){
            log.info("fileNotIn,fileCode:{},bucketCode:{}",fileCode,file.getBucketCode());
        }
        picturesUpdateService.update(file);
    }

}
