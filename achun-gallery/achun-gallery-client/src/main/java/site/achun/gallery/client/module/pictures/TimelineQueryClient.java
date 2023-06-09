package site.achun.gallery.client.module.pictures;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.pictures.request.QueryTimelinePage;
import site.achun.gallery.client.module.pictures.response.Timeline;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@FeignClient(name = "achun-gallery-app", contextId = "ListQueryClient")
public interface TimelineQueryClient {

    @Operation(summary = "查询时间轴")
    @PostMapping("/gallery/timeline/query-timeline")
    Rsp<RspPage<Timeline>> queryTimeline(@RequestBody QueryTimelinePage query);
}
