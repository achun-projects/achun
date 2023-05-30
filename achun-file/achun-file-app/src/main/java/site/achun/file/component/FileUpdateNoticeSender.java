package site.achun.file.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/5/7 22:27
 */
@Slf4j
@Component
@AllArgsConstructor
public class FileUpdateNoticeSender {

    private final AmqpTemplate amqpTemplate;
    public void sendMessage(String fileCode){
       amqpTemplate.convertAndSend("FILE_UPDATE_FANOUT_EXCHANGE","",fileCode);
    }



}
