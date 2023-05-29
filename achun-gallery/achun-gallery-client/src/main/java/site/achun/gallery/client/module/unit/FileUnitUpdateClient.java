package site.achun.gallery.client.module.unit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.unit.request.UpdateFileUnit;
import site.achun.gallery.client.module.unit.response.FileUnitResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "FileUnitUpdateClient")
public interface FileUnitUpdateClient {

    @PostMapping("/gallery/unit/update-unit")
    Rsp<FileUnitResponse> updateUnit(@RequestBody UpdateFileUnit update);
}
