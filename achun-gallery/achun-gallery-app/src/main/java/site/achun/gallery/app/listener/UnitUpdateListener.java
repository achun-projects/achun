package site.achun.gallery.app.listener;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import site.achun.file.client.module.unit.request.UpdateUnit;
import site.achun.gallery.app.service.FileUnitUpdateService;

@Slf4j
@Component
@RequiredArgsConstructor
public class UnitUpdateListener {

    private final FileUnitUpdateService fileUnitUpdateService;

    @RabbitListener(queues = "unit.update.queue")
    public void whenUnitUpdate(String msg){
        log.info("unitUpdate :{}",msg);
        UpdateUnit update = JSON.parseObject(msg,UpdateUnit.class);
        fileUnitUpdateService.updateUnit(update);
    }
}
