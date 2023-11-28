package site.achun.gallery.app.service.unit;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.gallery.app.service.pictures.PicturesQueryService;
import site.achun.gallery.client.constant.GalleryRC;
import site.achun.gallery.client.module.pic_unit.request.QueryByFileCodes;
import site.achun.gallery.client.module.pic_unit.request.QueryPicUnitDetail;
import site.achun.gallery.client.module.pic_unit.response.PicUnitResponse;
import site.achun.support.api.exception.RspException;
import site.achun.support.api.response.Rsp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PicUnitQueryExecute {

    private final FileSetService fileSetService;

    private final FileSetConvert fileSetConvert;
    private final PicturesService picturesService;

    public List<PicUnitResponse> queryByFileCodes(QueryByFileCodes request) {
        List<Pictures> pics = picturesService.getByFileCodes(request.getFileCodes());
        if(CollUtil.isEmpty(pics)){
            return new ArrayList<>();
        }
        List<String> setCodes = pics.stream()
                .map(Pictures::getSetCode)
                .distinct()
                .collect(Collectors.toList());
        List<FileSet> fileSetList = fileSetService.getByCodes(setCodes, request.getUserCode());
        if(CollUtil.isEmpty(fileSetList)){
            return new ArrayList<>();
        }
        return fileSetList.stream()
                .map(fileSetConvert::toResponse)
                .collect(Collectors.toList());
    }

    public PicUnitResponse query(QueryPicUnitDetail request){
        FileSet fileset = fileSetService.lambdaQuery()
                .eq(FileSet::getCode,request.getPicUnitCode())
                .eq(FileSet::getUserCode,request.getUserCode())
                .like(StrUtil.isNotBlank(request.getLikeName()),FileSet::getName,request.getLikeName())
                .one();
        if(fileset == null){
            return null;
        }
        return fileSetConvert.toResponse(fileset);
    }

    // 根据setCode查询文件分组
    public PicUnitResponse queryByUnitCode(String unitCode){
        FileSet fileset = fileSetService.lambdaQuery()
                .eq(FileSet::getCode,unitCode)
                .one();
        if(fileset == null){
            throw new RspException(GalleryRC.DATA_IS_NULL,"文件分组不存在");
        }
        return fileSetConvert.toResponse(fileset);
    }
}
