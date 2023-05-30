package site.achun.file.controller.unit;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.unit.UnitUpdateClient;
import site.achun.file.client.module.unit.request.UpdateUnit;
import site.achun.file.client.module.unit.response.UnitResponse;
import site.achun.file.mq.sender.UnitUpdateSender;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "Unit更新")
@RestController
@RequiredArgsConstructor
public class UnitUpdateController implements UnitUpdateClient {

    private final UnitUpdateSender unitUpdateSender;
    @Override
    public Rsp<UnitResponse> saveOrUpdateUnit(UpdateUnit update) {
        unitUpdateSender.sendMessage(update);
        return Rsp.success(null);
    }
}
