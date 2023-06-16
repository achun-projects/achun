package site.achun.gallery.app.controller.unit;


import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.app.service.unit.PicUnitQueryExecute;
import site.achun.gallery.app.service.unit.PicUnitUpdateExecute;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.fileset.request.UpdateFileSet;
import site.achun.gallery.client.module.fileset.response.FileSetResponse;
import site.achun.gallery.client.module.unit.PicUnitUpdateClient;
import site.achun.gallery.client.module.unit.request.CreateOrUpdateUnit;
import site.achun.gallery.client.module.unit.request.UpdateFileUnit;
import site.achun.gallery.client.module.unit.response.FileUnitResponse;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.response.Rsp;

import java.time.LocalDateTime;

@Slf4j
@Tag(name = "图片单元更新")
@RestController
@RequiredArgsConstructor
public class UnitUpdateController implements PicUnitUpdateClient {

    private final FileSetService fileSetService;
    private final PicUnitQueryExecute picUnitQueryExecute;
    private final PicUnitUpdateExecute picUnitUpdateExecute;

    @Override
    public Rsp<FileUnitResponse> updateUnit(UpdateFileUnit update) {
        return null;
    }

    @Override
    public Rsp<FileSetResponse> update(UpdateFileSet request) {
        request.setUserCode(UserInfo.getCode(request::getUserCode));
        FileSet existFileSet = fileSetService.getByCode(request.getSetCode());
        if(existFileSet == null){
            // 不存在时则更新
            FileSet fileSet = FileSet.builder()
                    .userCode(request.getUserCode())
                    .code(request.getSetCode())
                    .name(request.getName())
                    .deleted(Deleted.NO.getStatus())
                    .utime(LocalDateTime.now())
                    .ctime(LocalDateTime.now())
                    .build();
            fileSetService.save(fileSet);
        }
        fileSetService.lambdaUpdate()
                .set(StrUtil.isNotBlank(request.getName()), FileSet::getName,request.getName())
                .set(StrUtil.isNotBlank(request.getUserCode()), FileSet::getUserCode,request.getUserCode())
                .eq(FileSet::getCode,request.getSetCode())
                .update();
        // TODO 更新文件分组的标签
        return Rsp.success(picUnitQueryExecute.queryByUnitCode(request.getSetCode()));
    }

    @Override
    public Rsp<FileSetResponse> createOrUpdate(CreateOrUpdateUnit req) {
        String unitCode = picUnitUpdateExecute.createOrUpdate(req);
        return Rsp.success(picUnitQueryExecute.queryByUnitCode(unitCode));
    }
}
