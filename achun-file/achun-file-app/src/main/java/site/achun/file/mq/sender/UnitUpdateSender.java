package site.achun.file.mq.sender;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.unit.request.UpdateUnit;

@Slf4j
@Component
@AllArgsConstructor
public class UnitUpdateSender {

    private final AmqpTemplate amqpTemplate;
    public void sendMessage(UpdateUnit updateUnit){
        String json = JSON.toJSONString(updateUnit);
        amqpTemplate.convertAndSend("UNIT_UPDATE_FANOUT_EXCHANGE","",json);
    }
}
