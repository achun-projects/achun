package site.achun.gallery.client.module.board_record;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "achun-gallery-app", contextId = "BoardRecordQueryClient")
public interface BoardRecordQueryClient {
}
