package site.achun.gallery.client.module.pic_unit;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.pic_unit.request.QueryPicUnitDetail;
import site.achun.gallery.client.module.pic_unit.response.PicUnitResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "PicUnitQueryClient")
public interface PicUnitQueryClient {

    @Operation(summary = "查询文件单元")
    @PostMapping("/gallery/pic-unit/query-pic-unit-detail")
    Rsp<PicUnitResponse> queryPicUnitDetail(@RequestBody QueryPicUnitDetail request);
}
