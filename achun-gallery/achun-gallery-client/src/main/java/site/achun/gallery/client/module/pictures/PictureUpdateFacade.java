package site.achun.gallery.client.module.pictures;


import site.achun.gallery.client.module.pictures.request.CreatePicture;
import site.achun.gallery.client.module.pictures.request.PictureLikeRequest;
import site.achun.support.api.response.Rsp;

import java.util.Collection;

/**
 * 图片增删改查
 *
 * @Author: Heiffeng
 * @Date: 2022/4/14 21:44
 */
public interface PictureUpdateFacade {

    /**
     * 批量删除文件
     * @param fileCodes
     * @return
     */
    Rsp<Integer> remove(Collection<String> fileCodes);


    /**
     * 更新图片
     * @param picture
     * @return
     */
    Rsp<Void> saveOrUpdate(CreatePicture picture);


    /**
     *
     * @param fileCodes
     * @param newGroupCode
     * @return
     */
    Rsp<Void> anewGroup(Collection<String> fileCodes,String newGroupCode);


    /**
     * 批量标记喜欢的图片
     * @param request
     * @return
     */
    Rsp<Void> batchMarkLike(PictureLikeRequest request);

    /**
     * 批量删除喜欢的图片
     * @param request
     * @return
     */
    Rsp<Void> batchRemoveLike(PictureLikeRequest request);

}
