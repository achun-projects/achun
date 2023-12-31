package site.achun.file.mq.sender;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.file.request.UpdateFileRequest;

@Slf4j
@Component
@AllArgsConstructor
public class FileUpdateSender {


    private final AmqpTemplate amqpTemplate;

    public void whenFileUpdate(String fileCode){
        amqpTemplate.convertAndSend("FILE_UPDATE_FANOUT_EXCHANGE","",fileCode);
    }

    public void whenFileRemove(String fileCode){
        amqpTemplate.convertAndSend("FILE_REMOVE_FANOUT_EXCHANGE","",fileCode);
    }

}
