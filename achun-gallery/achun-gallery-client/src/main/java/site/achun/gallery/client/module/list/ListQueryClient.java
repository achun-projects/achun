package site.achun.gallery.client.module.list;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.list.request.UpdateCover;
import site.achun.gallery.client.module.list.request.UpdateListBaseInfo;
import site.achun.support.api.response.Rsp;

import java.util.Collection;

@FeignClient(name = "achun-gallery-app", contextId = "ListQueryClient")
public interface ListQueryClient {
    @Operation(summary = "随机查询一条记录")
    @PostMapping("/gallery/list/random-query")
    String randomQuery(Collection<String> listCodes);

    @Operation(summary = "设置封面")
    @PostMapping("/gallery/list/update-cover")
    Rsp updateCover(@RequestBody UpdateCover updateCover);

    @Operation(summary = "修改名称和描述")
    @PostMapping("/gallery/list/update-name-desc")
    Rsp updateListBaseInfo(@RequestBody UpdateListBaseInfo update);

}
