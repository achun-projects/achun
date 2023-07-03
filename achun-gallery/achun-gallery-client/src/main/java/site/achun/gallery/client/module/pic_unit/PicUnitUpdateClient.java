package site.achun.gallery.client.module.pic_unit;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.pic_unit.request.UpdatePicUnit;
import site.achun.gallery.client.module.pic_unit.response.PicUnitResponse;
import site.achun.gallery.client.module.pic_unit.request.CreateOrUpdatePicUnit;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "PicUnitUpdateClient")
public interface PicUnitUpdateClient {

    @Operation(summary = "更新Unit")
    @PostMapping("/gallery/pic-unit/update-pic-unit")
    Rsp<PicUnitResponse> update(@RequestBody UpdatePicUnit request);

    @Operation(summary = "创建或更新unit")
    @PostMapping("/gallery/unit/create-or-update-unit")
    Rsp<PicUnitResponse> createOrUpdate(@RequestBody CreateOrUpdatePicUnit req);
}
