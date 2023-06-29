package site.achun.video.client.module.group_record;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.video.client.module.group.request.CreateGalleryGroupRecord;
import site.achun.video.client.module.group.response.GalleryGroupResponse;
import site.achun.support.api.response.Rsp;

@FeignClient(name = "achun-gallery-app", contextId = "GroupRecordUpdateClient")
public interface GroupRecordUpdateClient {
    @Operation(summary = "删除分组记录")
    @PostMapping("/gallery/group/remove-record")
    Rsp<Boolean> removeGroupRecord(@RequestBody CreateGalleryGroupRecord create);

    @Operation(summary = "创建分组记录")
    @PostMapping("/gallery/group/create-record")
    Rsp<GalleryGroupResponse> createGroupRecord(@RequestBody CreateGalleryGroupRecord create);
}
