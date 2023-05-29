package site.achun.file.client.module.unit;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.file.client.module.unit.request.UpdateUnit;
import site.achun.file.client.module.unit.response.UnitResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-file-app", contextId = "UnitUpdateClient")
public interface UnitUpdateClient {

    @Operation(summary = "保存或更新Unit")
    @PostMapping("/file/unit/save-or-update-unit")
    Rsp<UnitResponse> saveOrUpdateUnit(@RequestBody UpdateUnit update);
}
