package site.achun.gallery.client.module.list;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.list.request.RandomQueryFromList;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;

import java.util.Collection;

@FeignClient(name = "achun-gallery-app", contextId = "ListQueryClient")
public interface GalleryListQueryClient {

    @Operation(summary = "随机查询一条记录")
    @PostMapping("/gallery/list/random-query")
    @Deprecated
    String randomQuery(Collection<String> listCodes);

    @Operation(summary = "随机从列表查询一张图")
    @PostMapping("/gallery/list/random-query-one-photo-from-list")
    Rsp<Photo> randomQueryOnePhoto(@RequestBody RandomQueryFromList query);

}
