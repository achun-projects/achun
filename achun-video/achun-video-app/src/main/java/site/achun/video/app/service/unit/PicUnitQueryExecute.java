package site.achun.video.app.service.unit;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.FileSet;
import site.achun.video.app.generator.service.FileSetService;
import site.achun.video.client.constant.GalleryRC;
import site.achun.video.client.module.fileset.request.QueryFileSet;
import site.achun.video.client.module.fileset.response.FileSetResponse;
import site.achun.support.api.exception.RspException;

@Slf4j
@Service
@AllArgsConstructor
public class PicUnitQueryExecute {

    private final FileSetService fileSetService;

    private final FileSetConvert fileSetConvert;

    public FileSetResponse query(QueryFileSet request){
        FileSet fileset = fileSetService.lambdaQuery()
                .eq(FileSet::getCode,request.getSetCode())
                .eq(FileSet::getUserCode,request.getUserCode())
                .like(StrUtil.isNotBlank(request.getLikeName()),FileSet::getName,request.getLikeName())
                .one();
        if(fileset == null){
            throw new RspException(GalleryRC.DATA_IS_NULL,"文件分组不存在");
        }
        return fileSetConvert.toResponse(fileset);
    }

    // 根据setCode查询文件分组
    public FileSetResponse queryByUnitCode(String unitCode){
        FileSet fileset = fileSetService.lambdaQuery()
                .eq(FileSet::getCode,unitCode)
                .one();
        if(fileset == null){
            throw new RspException(GalleryRC.DATA_IS_NULL,"文件分组不存在");
        }
        return fileSetConvert.toResponse(fileset);
    }
}
