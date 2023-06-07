package site.achun.gallery.app.service.pictures;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.client.module.pictures.request.CreatePicture;
import site.achun.gallery.client.module.pictures.response.PictureResponse;
import site.achun.support.api.enums.Deleted;

import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 10:51
 */
@Service
public class PictureConvertService {

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
}
