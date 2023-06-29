package site.achun.video.app.controller.group_record;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.video.client.module.group_record.GroupRecordQueryClient;
@Slf4j
@Tag(name = "分组记录查询")
@RestController
@RequiredArgsConstructor
public class GroupRecordQueryController implements GroupRecordQueryClient {
}
