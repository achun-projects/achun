package site.achun.gallery.client.module.board_record;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "achun-gallery-app", contextId = "BoardRecordUpdateClient")
public interface BoardRecordUpdateClient {
}
