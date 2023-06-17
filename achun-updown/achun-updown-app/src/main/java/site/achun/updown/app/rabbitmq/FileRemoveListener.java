package site.achun.updown.app.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.achun.updown.app.service.module.delete.FileDeleteService;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileRemoveListener {

    private final FileDeleteService fileDeleteService;

    @RabbitListener(queues = "file.remove.queue")
    public void whenFileUpdate(String fileCode){
        log.info("fileRemove :{}",fileCode);
        fileDeleteService.deleteFile(fileCode);
    }

}
