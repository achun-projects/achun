package site.achun.gallery.client.module.board_record;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;

@FeignClient(name = "achun-gallery-app", contextId = "BoardRecordQueryClient")
public interface BoardRecordQueryClient {

    @PostMapping("/gallery/board-photos-page")
    Rsp<RspPage<Photo>> queryPageOfBoardPhotos(@RequestBody QueryRecord query);

}
