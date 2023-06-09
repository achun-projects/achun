package site.achun.gallery.app.controller.group_record;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.generator.domain.GalleryGroupRecord;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.client.module.group.request.CreateGalleryGroupRecord;
import site.achun.gallery.client.module.group.response.GalleryGroupResponse;
import site.achun.gallery.client.module.group_record.GroupRecordUpdateClient;
import site.achun.support.api.response.Rsp;

import java.time.LocalDateTime;

@Slf4j
@Tag(name = "分组记录更新")
@RestController
@RequiredArgsConstructor
public class GroupRecordUpdateController implements GroupRecordUpdateClient {

    private final GalleryGroupRecordService groupRecordService;
    @Override
    public Rsp<Boolean> removeGroupRecord(CreateGalleryGroupRecord create) {
        boolean result = groupRecordService.lambdaUpdate()
                .eq(GalleryGroupRecord::getListCode, create.getListCode())
                .eq(GalleryGroupRecord::getGroupCode, create.getGroupCode())
                .remove();
        return Rsp.success(result);
    }

    @Override
    public Rsp<GalleryGroupResponse> createGroupRecord(CreateGalleryGroupRecord create) {
        GalleryGroupRecord record = toRecord(create);
        groupRecordService.save(record);
        return Rsp.success(toResponse(record));
    }

    private GalleryGroupRecord toRecord(CreateGalleryGroupRecord create) {
        return GalleryGroupRecord.builder()
                .listCode(create.getListCode())
                .groupCode(create.getGroupCode())
                .ctime(LocalDateTime.now())
                .build();
    }
    private GalleryGroupResponse toResponse(GalleryGroupRecord record) {
        return BeanUtil.toBean(record,GalleryGroupResponse.class);
    }
}
