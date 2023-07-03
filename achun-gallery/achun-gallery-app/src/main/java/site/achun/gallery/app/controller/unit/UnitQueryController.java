package site.achun.gallery.app.controller.unit;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.unit.PicUnitQueryExecute;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.pic_unit.request.QueryPicUnitDetail;
import site.achun.gallery.client.module.pic_unit.response.PicUnitResponse;
import site.achun.gallery.client.module.pic_unit.PicUnitQueryClient;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "图片单元查询")
@RestController
@RequiredArgsConstructor
public class UnitQueryController implements PicUnitQueryClient {

    private final PicUnitQueryExecute picUnitQueryExecute;

    @Override
    public Rsp<PicUnitResponse> queryPicUnitDetail(QueryPicUnitDetail request) {
        log.info("查询文件分组,请求参数:{}",request);
        request.setUserCode(UserInfo.getCode(request::getUserCode));
        return Rsp.success(picUnitQueryExecute.query(request));
    }
}
