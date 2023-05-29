package site.achun.gallery.app.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileUpdateListener {

    @RabbitListener(queues = "file.update.queue")
    public void whenFileUpdate(String msg){
        log.info("fileUpdate :{}",msg);
    }

}
