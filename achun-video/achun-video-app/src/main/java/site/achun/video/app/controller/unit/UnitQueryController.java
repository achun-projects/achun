package site.achun.video.app.controller.unit;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.video.app.service.unit.PicUnitQueryExecute;
import site.achun.video.app.utils.UserInfo;
import site.achun.video.client.module.fileset.request.QueryFileSet;
import site.achun.video.client.module.fileset.response.FileSetResponse;
import site.achun.video.client.module.unit.PicUnitQueryClient;
import site.achun.support.api.response.Rsp;

@Slf4j
@Tag(name = "图片单元查询")
@RestController
@RequiredArgsConstructor
public class UnitQueryController implements PicUnitQueryClient {

    private final PicUnitQueryExecute fileSetQueryExecute;

    @Override
    public Rsp<FileSetResponse> queryFileSet(QueryFileSet request) {
        log.info("查询文件分组,请求参数:{}",request);
        request.setUserCode(UserInfo.getCode(request::getUserCode));
        return Rsp.success(fileSetQueryExecute.query(request));
    }
}
