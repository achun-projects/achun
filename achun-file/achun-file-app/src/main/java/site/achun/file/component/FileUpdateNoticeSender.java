package site.achun.file.component;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//
//
//    private static final String TOPIC = "REMOVE-FILE-TOPIC";
//
//    public void sendMessage(String fileCode){
//        SendResult result = rocketMQTemplate.syncSend(TOPIC, fileCode);
//        log.info("TOPIC:{},fileCode:{},result:{}",TOPIC,fileCode, JSON.toJSONString(result));
//    }
}
