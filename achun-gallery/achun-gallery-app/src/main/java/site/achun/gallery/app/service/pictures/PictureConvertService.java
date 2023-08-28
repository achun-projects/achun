package site.achun.gallery.app.service.pictures;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.pictures.request.CreatePicture;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.gallery.client.module.pictures.response.PictureResponse;
import site.achun.support.api.enums.Deleted;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 10:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PictureConvertService {

    private final MediaFileQueryClient fileQueryClient;

    public PictureResponse toResponse(Pictures pictures){
        return BeanUtil.toBean(pictures,PictureResponse.class);
    }

    public List<PictureResponse> toResponse(Collection<Pictures> picturesCollection){
        return BeanUtil.copyToList(picturesCollection, PictureResponse.class);
    }

    public Pictures toPictures(CreatePicture createPicture){
        Pictures picture = BeanUtil.toBean(createPicture, Pictures.class);
        picture.setSetCode(createPicture.getSetCode());
        picture.setDeleted(Deleted.NO.getStatus());
        return picture;
    }

    public Rsp<RspPage<Photo>> toPhotoPage(RspPage<PictureResponse> rspPage) {
        // 转换文件
        List<String> fileCodes = rspPage.getRows().stream()
                .map(PictureResponse::getFileCode)
                .collect(Collectors.toList());
        QueryByFileCodes query = QueryByFileCodes.builder().fileCodes(fileCodes).build();
        Map<String, MediaFileResponse> fileResponseMap = fileQueryClient.queryFileMap(query).tryGetData();

        // 过滤掉不存在的记录
        List<PictureResponse> newRows = new ArrayList<>();
        for (PictureResponse row : rspPage.getRows()) {
            if(fileResponseMap.containsKey(row.getFileCode())){
                newRows.add(row);
            }else{
                log.info("fileCode:{}在文件系统中不存在",row.getFileCode());
            }
        }
        rspPage.setRows(newRows);
        return Rsp.success(PageUtil.parse(rspPage,pic -> toPhoto(fileResponseMap.get(pic.getFileCode()))));
    }

    public Photo toPhoto(MediaFileResponse file){
        return Photo.builder()
                .fileCode(file.getFileCode())
                .cover(file.getCover())
                .url(file.getUrl())
                .duration(file.getDuration())
                .fileName(file.getFileName())
                .height(file.getHeight())
                .mediumUrl(file.getMediumUrl())
                .type(file.getType())
                .unitCode(file.getUnitCode())
                .width(file.getWidth())
                .size(file.getSize())
                .build();
    }

}
