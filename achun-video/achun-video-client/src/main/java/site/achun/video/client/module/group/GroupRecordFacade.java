package site.achun.video.client.module.group;

import site.achun.video.client.module.group.request.CreateGalleryGroupRecord;
import site.achun.video.client.module.group.response.GalleryGroupResponse;
import site.achun.support.api.response.Rsp;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/28 10:17
 */
public interface GroupRecordFacade {

    /**
     * 新建记录
     * @param create
     * @return
     */
    Rsp<GalleryGroupResponse> create(CreateGalleryGroupRecord create);

    /**
     * 删除记录
     * @param create
     * @return
     */
    Rsp<Boolean> delete(CreateGalleryGroupRecord create);
}
