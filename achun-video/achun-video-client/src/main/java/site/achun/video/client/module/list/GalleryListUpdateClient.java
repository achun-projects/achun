package site.achun.video.client.module.list;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.video.client.module.list.request.UpdateCover;
import site.achun.video.client.module.list.request.UpdateListBaseInfo;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "GalleryListUpdateClient")
public interface GalleryListUpdateClient {

    @Operation(summary = "设置封面")
    @PostMapping("/gallery/list/update-cover")
    Rsp updateCover(@RequestBody UpdateCover updateCover);

    @Operation(summary = "修改名称和描述")
    @PostMapping("/gallery/list/update-name-desc")
    Rsp updateListBaseInfo(@RequestBody UpdateListBaseInfo update);
}
