package site.achun.gallery.client.module.list;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@FeignClient(name = "achun-gallery-app", contextId = "ListQueryClient")
public interface GalleryListQueryClient {
    @Operation(summary = "随机查询一条记录")
    @PostMapping("/gallery/list/random-query")
    String randomQuery(Collection<String> listCodes);
}
