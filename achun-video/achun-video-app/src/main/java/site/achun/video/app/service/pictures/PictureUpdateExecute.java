package site.achun.video.app.service.pictures;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.Pictures;
import site.achun.video.app.generator.mapper.PicturesMapper;
import site.achun.video.client.module.pictures.request.CreatePicture;

import java.time.LocalDateTime;
import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
public class PictureUpdateExecute {

    private final PicturesMapper picturesMapper;

    private final PictureConvertService pictureConvertService;

    public void saveOrUpdate(Collection<CreatePicture> pictures){
        if(CollUtil.isNotEmpty(pictures)){
            for (CreatePicture picture : pictures) {
                saveOrUpdate(picture);
            }
        }
    }
    public void saveOrUpdate(CreatePicture picture){
        Pictures pic = pictureConvertService.toPictures(picture);
        pic.setAtime(LocalDateTime.now());
        pic.setCtime(LocalDateTime.now());
        pic.setUtime(LocalDateTime.now());
        picturesMapper.replaceInto(pic);
    }
}
