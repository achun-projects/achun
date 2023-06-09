package site.achun.gallery.app.controller.board_record;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.board_record.BoardRecordQueryService;
import site.achun.gallery.client.module.board_record.BoardRecordQueryClient;
import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

@Slf4j
@Tag(name = "画板记录查询")
@RestController
@RequiredArgsConstructor
public class BoardRecordQueryController implements BoardRecordQueryClient {

    private final BoardRecordQueryService boardRecordQueryService;

    @Override
    public Rsp<RspPage<Photo>> queryPageOfBoardPhotos(QueryRecord query) {
        if(CollUtil.isNotEmpty(query.getExcludeBoardCodes())){
            query.getExcludeBoardCodes().remove(null);
        }
        return boardRecordQueryService.queryPage(query);
    }
}
