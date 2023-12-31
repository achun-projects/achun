package site.achun.gallery.app.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.service.PicturesUpdateService;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUpdateListener {

    private final FileQueryClient fileQueryClient;

    private final PicturesUpdateService picturesUpdateService;

    private final static String LISTEN_BUCKETS = "10011,10012,10013,10014";
    @RabbitListener(queues = "file.update.queue")
    public void whenFileUpdate(String fileCode){
        log.info("fileUpdate :{}",fileCode);
        FileInfoResponse file = fileQueryClient.queryFile(QueryByFileCode.builder().fileCode(fileCode).build()).getData();
        if(!LISTEN_BUCKETS.contains(file.getBucketCode())){
            log.info("fileNotIn,fileCode:{},bucketCode:{}",fileCode,file.getBucketCode());
        }
        picturesUpdateService.update(file);
    }

}
