package site.achun.video.client.module.group;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import site.achun.video.client.module.group.request.QueryPage;
import site.achun.video.client.module.group.response.GroupResponse;
import site.achun.video.client.module.group.response.GroupViewResponse;
import site.achun.video.client.module.group.view.CascaderView;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;

@FeignClient(name = "achun-gallery-app", contextId = "GroupQueryClient")
public interface GroupQueryClient {

    @Operation(summary = "查询分组列表")
    @GetMapping("/gallery/group-list")
    Rsp<List<GroupResponse>> queryList(Integer type);

    @Operation(summary = "查询分组视图")
    @GetMapping("/gallery/group/query-by-code")
    Rsp<GroupViewResponse> queryByCode(@RequestParam("code") String code);

    @Operation(summary = "查询分组分页")
    @PostMapping("/gallery/group/query-group-page")
    Rsp<RspPage<GroupViewResponse>> queryPage(@RequestBody QueryPage query);

    @Operation(summary = "查询分组视图")
    @GetMapping("/gallery/{type}/group-cascader-view")
    Rsp<List<CascaderView>> queryCascaderView(
            @PathVariable("type") Integer type,
            @RequestParam("curr") String currentSelect,
            @RequestParam("justGroup") Boolean justGroup);

}
