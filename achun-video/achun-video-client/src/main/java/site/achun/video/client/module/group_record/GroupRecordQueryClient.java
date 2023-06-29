package site.achun.video.client.module.group_record;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "achun-gallery-app", contextId = "GroupRecordQueryClient")
public interface GroupRecordQueryClient {
}
