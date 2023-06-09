package site.achun.gallery.client.module.unit;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.fileset.request.UpdateFileSet;
import site.achun.gallery.client.module.fileset.response.FileSetResponse;
import site.achun.gallery.client.module.unit.request.UpdateFileUnit;
import site.achun.gallery.client.module.unit.response.FileUnitResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "PicUnitUpdateClient")
public interface PicUnitUpdateClient {

    @Operation(summary = "更新Unit")
    @PostMapping("/gallery/unit/update-unit")
    Rsp<FileUnitResponse> updateUnit(@RequestBody UpdateFileUnit update);

    @Operation(summary = "更新Unit")
    @PostMapping("/gallery/fileset/update-fileset")
    Rsp<FileSetResponse> update(@RequestBody UpdateFileSet request);
}
