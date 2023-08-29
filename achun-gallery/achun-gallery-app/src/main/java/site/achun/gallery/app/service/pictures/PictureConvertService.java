package site.achun.gallery.app.service.pictures;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.MediaFileQueryClient;
import site.achun.file.client.module.file.request.QueryByFileCodes;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.client.module.pictures.request.CreatePicture;
import site.achun.gallery.client.module.pictures.response.Photo;
import site.achun.gallery.client.module.pictures.response.PictureResponse;
import site.achun.support.api.enums.Deleted;

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

    public PictureResponse copyToResponse(Pictures pictures){
        return BeanUtil.toBean(pictures,PictureResponse.class);
    }

    public List<PictureResponse> copyToResponse(Collection<Pictures> picturesCollection){
        return BeanUtil.copyToList(picturesCollection, PictureResponse.class);
    }

    public Pictures toPictures(CreatePicture createPicture){
        Pictures picture = BeanUtil.toBean(createPicture, Pictures.class);
        picture.setSetCode(createPicture.getSetCode());
        picture.setDeleted(Deleted.NO.getStatus());
        return picture;
    }

    public List<Photo> toPhotos(List<Pictures> list) {
        // 转换文件
        List<String> fileCodes = list.stream()
                .map(Pictures::getFileCode)
                .collect(Collectors.toList());
        QueryByFileCodes query = QueryByFileCodes.builder().fileCodes(fileCodes).build();
        Map<String, MediaFileResponse> fileResponseMap = fileQueryClient.queryFileMap(query).tryGetData();

        // 过滤掉不存在的记录
        List<PictureResponse> newList = new ArrayList<>();
        for (Pictures item : list) {
            PictureResponse newItem = copyToResponse(item);
            if(fileResponseMap.containsKey(item.getFileCode())){
                newList.add(newItem);
            }else{
                log.info("fileCode:{}在文件系统中不存在",item.getFileCode());
            }
        }
        return newList.stream()
                .map(rsp -> toPhoto(fileResponseMap.get(rsp.getFileCode())))
                .collect(Collectors.toList());
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
