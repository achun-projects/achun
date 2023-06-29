package site.achun.video.client.module.group;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.video.client.module.group.request.CreateGalleryGroup;
import site.achun.video.client.module.group.request.ReqRemoveEmptyGroup;
import site.achun.video.client.module.group.request.UpdateGroupReq;
import site.achun.video.client.module.group.response.GroupViewResponse;
import site.achun.support.api.response.Rsp;


@FeignClient(name = "achun-gallery-app", contextId = "GroupUpdateClient")
public interface GroupUpdateClient {


    @Operation(summary = "创建分组")
    @PostMapping("/gallery/{type}/create-group")
    Rsp<GroupViewResponse> createGroup(@PathVariable("type") Integer type, @RequestBody CreateGalleryGroup create);

    @Operation(summary = "删除分组")
    @PostMapping("/gallery/group/remove-group")
    Rsp<Boolean> removeEmptyGroup(@RequestBody ReqRemoveEmptyGroup req);

    @Operation(summary = "更新分组")
    @PostMapping("/gallery/group/update-group")
    Rsp<GroupViewResponse> updateGroup(@RequestBody UpdateGroupReq req);
}
