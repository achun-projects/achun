package site.achun.gallery.client.module.board;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "achun-gallery-app", contextId = "BoardUpdateClient")
public interface BoardUpdateClient {
}
